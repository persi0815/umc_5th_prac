package study.web.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReviewRequestDto {
    @Getter
    public static class WriteDto {
        String body;
        Float score;
        List<String> image;
        Long memberId;
    }

    @Getter
    public static class ReviewDTO {
        String title;
        Float score;
        String body;

    }
}
