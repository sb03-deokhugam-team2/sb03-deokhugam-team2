package com.twogether.deokhugam.review.repository;

import com.twogether.deokhugam.review.entity.Review;
import com.twogether.deokhugam.review.repository.custom.ReviewRepositoryCustom;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID>, ReviewRepositoryCustom {

    boolean existsByUserIdAndBookId(UUID userId, UUID bookId);
    boolean existsByUserIdAndBookIdAndIsDeletedFalse(UUID userId, UUID bookId);
    
    @Modifying
    @Query("""
    UPDATE Book b SET 
        b.reviewCount = (SELECT COUNT(r) FROM Review r WHERE r.book.id = :bookId AND r.isDeleted = false),
        b.rating = (SELECT COALESCE(AVG(r.rating * 1.0), 0) FROM Review r WHERE r.book.id = :bookId AND r.isDeleted = false)
    WHERE b.id = :bookId
""")
    void updateBookReviewStats(@Param("bookId") UUID bookId);

    @Modifying
    @Query("UPDATE Review r SET r.commentCount = r.commentCount + 1, r.updatedAt = CURRENT_TIMESTAMP WHERE r.id = :reviewId")
    void incrementCommentCount(@Param("reviewId") UUID reviewId);

    @Modifying
    @Query("UPDATE Review r SET r.commentCount = r.commentCount - 1, r.updatedAt = CURRENT_TIMESTAMP WHERE r.id = :reviewId")
    void decrementCommentCount(@Param("reviewId") UUID reviewId);


}
