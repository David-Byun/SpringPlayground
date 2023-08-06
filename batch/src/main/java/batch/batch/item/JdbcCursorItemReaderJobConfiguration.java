package batch.batch.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JdbcCursorItemReaderJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    private static final int chunkSize = 10;

    @Bean
    public Job jdbcCursorItemReaderJob() {
        return jobBuilderFactory.get("jdbcCursorItemReaderJob")
                .start(jdbcCursorItemReaderStep())
                .build();
    }

    /**
     * reader는 Tasklet이 아니기 때문에 reader만으로 수행될 수 없고, Writer를 하나 추가함
     * reader에서 읽은 데이터에 대해 큰 변경이 없다면 processor 를 제외하고 writer만 구현하면 됨
     */
    @Bean
    public Step jdbcCursorItemReaderStep() {
        return stepBuilderFactory.get("jdbcCursorItemReaderStep")
                .<Pay, Pay>chunk(chunkSize) // 첫번째 Pay는 Reader에서 반활할 타입이며, 두번째 Pay는 Writer에 파라미터로 넘어올 타입. chunkSize로 인자값을 넣은 경우는 Reader & Writer가 묶일 Chunk 트랜잭션 범위
                .reader(jdbcCursorItemReader())
                .writer(jdbcCursorItemWriter())
                .build();
    }

    //@Bean 어노테이션이 존재하는 이유는 ?

    /**
     * ItemReader의 가장 큰 장점은 데이터를 Streaming할 수 있다는 점. read() 메서드는 데이터를 하나씩 가져와 itemWriter로 데이터를 전달하고 다음 데이터를 다시 가져옴
     * reader & processor & writer가 Chunk 단위로 수행되고 주기적으로 Commit 함
     * 배치 수행시간이 오래 걸리는 경우에는 PagingItemReader를 사용하는게 나음
     * Paging의 경우 한 페이지를 읽을 때마다 Connection을 맺고 끊기 때문에 아무리 많은 데이터라도 타임아웃과 부하 없이 수행 가능
     */
    @Bean
    public JdbcCursorItemReader<Pay> jdbcCursorItemReader() {
        return new JdbcCursorItemReaderBuilder<Pay>()
                .fetchSize(chunkSize) // Database에서 한번에 가져올 데이터 양. Paging은 실제 쿼리를 limit, offset 이용하는 반면, Cursor는 분할처리 없이 실행되나 내부적으로 가져오는 데이터는 FetchSize만큼 가져와 read()를 통해서 하나씩 가져옴
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(Pay.class)) // 쿼리 결과를 Java Instance로 매핑하기 위한 Mapper.
                .sql("SELECT id, amount, tx_name, tx_date_time FROM pay")
                .name("jdbcCursorItemReader") // reader의 이름을 지정. Bean 이름이 아닌 Spring Batch의 ExecutionContext에 저장되어질 이름
                .build();
    }

    private ItemWriter<Pay> jdbcCursorItemWriter() {
        return list -> {
            for (Pay pay : list) {
                log.info("Current Pay = {}", pay);
            }
        };
    }
}
