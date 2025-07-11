package com.twogether.deokhugam.review.mapper;

import com.twogether.deokhugam.review.dto.ReviewDto;
import com.twogether.deokhugam.review.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDto toDto(Review review){
        return new ReviewDto(
                review.getId(),
                review.getBook().getId(),
                review.getBookTitle(),
                review.getBookThumbnailUrl(),
                review.getUser().getId(),
                review.getUserNickName(),
                review.getContent(),
                review.getRating(),
                review.getLikeCount(),
                review.getCommentCount(),
                review.isLikedByMe(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
