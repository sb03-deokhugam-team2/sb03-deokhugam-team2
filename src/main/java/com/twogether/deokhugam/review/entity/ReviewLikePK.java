package com.twogether.deokhugam.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;

/* Embeddable: 임베드 될 수 있는 복합 키 타입을 지정할 때 사용하는 어노테이션 */
@Embeddable
public class ReviewLikePK implements Serializable {

    @Getter
    @Column(name="review_id")
    private UUID reviewId;

    @Column(name="user_id")
    private UUID userId;

    public ReviewLikePK() {}

    public ReviewLikePK(UUID reviewId, UUID userId) {
        super();
        this.reviewId = reviewId;
        this.userId = userId;
    }

    public void updateReviewId(UUID reviewId) {
        this.reviewId = reviewId;
    }

    public void updateUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewLikePK reviewLikePK = (ReviewLikePK) o;
        return reviewId == reviewLikePK.reviewId && Objects.equals(userId, reviewLikePK.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, userId);
    }

    @Override
    public String toString() {
        return "MemberPK [memberNo=" + reviewId + ", memberId=" + userId + "]";
    }

}
