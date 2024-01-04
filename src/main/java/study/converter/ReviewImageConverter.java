package study.converter;

import study.domain.Review;
import study.domain.ReviewImage;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;

public class ReviewImageConverter {

    public static List<ReviewImage> toReviewImageList(Review review, List<String> imageList) {
        return imageList.stream()
                .map(image -> ReviewImage.builder()
                        //todo getName to S3 url
                        .imageUrl(image)
                        .review(review)
                        .build()
                ).toList();
    }
}
