package com.twogether.deokhugam.comments.entity;

import com.twogether.deokhugam.review.entity.Review;
import com.twogether.deokhugam.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    /**
 * Protected no-argument constructor required by JPA for entity instantiation.
 */
protected Comment() {}

    /**
     * Creates a new Comment associated with the specified user and review, initializing its content and setting it as not deleted.
     *
     * @param user    the author of the comment
     * @param review  the review to which the comment belongs
     * @param content the text content of the comment
     */
    public Comment(User user, Review review, String content) {
        this.user = user;
        this.review = review;
        this.content = content;
        this.isDeleted = false;
    }

    /**
     * Updates the content of the comment and refreshes the last updated timestamp.
     *
     * @param newContent the new text content for the comment
     */
    public void editContent(String newContent) {
        this.content = newContent;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Marks the comment as deleted and updates the last modified timestamp.
     */
    public void delete() {
        this.isDeleted = true;
        this.updatedAt = LocalDateTime.now();
    }

    /**
 * Returns the unique identifier of this comment.
 *
 * @return the UUID of the comment
 */
public UUID getId() { return id; }
    /**
 * Returns the user who authored this comment.
 *
 * @return the associated User entity
 */
public User getUser() { return user; }
    /**
 * Returns the review associated with this comment.
 *
 * @return the Review entity to which this comment belongs
 */
public Review getReview() { return review; }
    /**
 * Returns the text content of the comment.
 *
 * @return the comment's content
 */
public String getContent() { return content; }
    /**
 * Returns the timestamp when the comment was created.
 *
 * @return the creation time of the comment
 */
public LocalDateTime getCreatedAt() { return createdAt; }
    /**
 * Returns the timestamp of the last update to this comment.
 *
 * @return the date and time when the comment was last modified
 */
public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Boolean getIsDeleted() { return isDeleted; }
}
