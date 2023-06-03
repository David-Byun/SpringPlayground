package airbnb.back.dto.review;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Slf4j
@Builder
public class ReviewSaveRequestDto {

    private long review_id;
    private List<MultipartFile> reviewImages;
    private Integer score;
    private String content;
}
