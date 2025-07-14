package com.twogether.deokhugam.comments.service;

import com.twogether.deokhugam.comments.dto.CommentCreateRequest;
import com.twogether.deokhugam.comments.dto.CommentResponse;
import com.twogether.deokhugam.comments.entity.Comment;
import com.twogether.deokhugam.comments.mapper.CommentMapper;
import com.twogether.deokhugam.comments.repository.CommentRepository;
import com.twogether.deokhugam.review.entity.Review;
import com.twogether.deokhugam.review.repository.ReviewRepository;
import com.twogether.deokhugam.user.entity.User;
import com.twogether.deokhugam.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.NoSuchElementException;

/**
 * 댓글 도메인의 비즈니스 로직을 담당.
 */
@Service
@RequiredArgsConstructor
@Validated
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    /**
     * Creates a new comment associated with a specific review and user.
     *
     * Retrieves the review and user by their IDs from the request, creates and saves a new comment entity, and returns the result as a response DTO.
     *
     * @param request the DTO containing the review ID, user ID, and comment content
     * @return the response DTO representing the created comment
     * @throws NoSuchElementException if the specified review or user does not exist
     */
    public CommentResponse createComment(@Valid CommentCreateRequest request) {
        Review review = reviewRepository.findById(request.reviewId())
            .orElseThrow(() -> new NoSuchElementException("해당 리뷰가 없습니다."));
        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new NoSuchElementException("해당 유저가 없습니다."));
        Comment entity = new Comment(user, review, request.content());
        Comment saved = commentRepository.save(entity);
        return commentMapper.toResponse(saved);
    }
}
