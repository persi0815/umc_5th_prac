package study.web.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class ReviewRequestDTO {
    @Getter
    public static class WriteDto {
        String body;
        Float score;
        MultipartFile image;
        Long memberId;
        Long storeId;
    }
}
