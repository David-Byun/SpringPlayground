package batch.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextConditionalJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {
        return jobBuilderFactory.get("stepNextConditionalJob")
                .start(conditionalJobStep1())
                    .on("FAILED") // Failed일 경우, ExitStatus : Step의 실행 후 상태 의미
                    .to(conditionalJobStep3()) // step3으로 이동한다.
                    .on("*") // step3의 결과 관계없이 캐치할 exitStatus 지정. * 일 경우 모든 ExitStatus가 지정된다.
                    .end() // step3으로 이동하면 flow가 종료한다.
                .from(conditionalJobStep1()) //step1로부터
                    .on("*") //failed외에 모든 경우
                    .to(conditionalJobStep2()) //step2로 이동한다.
                    .next(conditionalJobStep3()) //step2가 정상 종료되면 step3으로 이동한다.
                    .on("*")//stpe3의 결과 관계 없이
                    .end() //step3으로 이동하면 flow가 종료한다.
                .end()
                .build(); //step3으로 이동하면 flow가 종료한다.
    }

    /**
     * from() : 일종의 이벤트 리스너 역할. 상태값을 보고 일치하는 상태라면 to()에 포함된 step을 호출합니다. step1의 이벤트 캐치가 failed로 되어 있는 상태에서 추가로 이벤트 캐치하려면 from을 써야함
     * end() : FlowBuilder를 반환하는 end와 FlowBuilder를 종료하는 end 2개가 있음. on('*') 뒤에 있는 end는 FlowBuilder를 반환하는 end
     * build() : 앞에 있는 end는 FlowBuilder를 종료하는 end
     * 중요한 점은 on이 캐치하는 상태값이 BatchStatus가 아닌 ExitStatus라는 점이다. 분기처리를 위해 상태값 조정이 필요하다면 ExitStatus를 조정해야 함
     * 기본적으로 ExitStatus의 exitCode는 Step의 BatchStatus 와 같도록 설정 되어 있음
     * BatchStatus 와 다르면서 ExitStatus 를 커스터마이즈해서 원하는 결과를 도출하기 위한 방법
     */
    @Bean
    public Step conditionalJobStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>> This is stepNextConditionalJob Step1");
                    /**
                     * ExitStatus를 FAILED로 지정한다
                     * 해당 status를 보고 flow가 진행된다.
                     */
                    //contribution.setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step conditionalJobStep2() {
        return stepBuilderFactory.get("conditionalJobStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>> This is stepNextConditionalJob Step2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step conditionalJobStep3() {
        return stepBuilderFactory.get("conditionalJobStep3")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>> This is stepNextConditionalJob Step3");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
