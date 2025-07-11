package com.twogether.deokhugam.comments.service;

import com.twogether.deokhugam.comments.dto.CommentCreateRequest;
import com.twogether.deokhugam.comments.dto.CommentResponse;
import com.twogether.deokhugam.comments.entity.Comment;
import com.twogether.deokhugam.comments.mapper.CommentMapper;
import com.twogether.deokhugam.comments.repository.CommentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 댓글 도메인의 비즈니스 로직을 담당.
 */
@Service
@RequiredArgsConstructor
@Validated
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    /**
     * 댓글 등록.
     *
     * @param request 등록 요청 DTO
     * @return 등록 결과 DTO
     */
    public CommentResponse createComment(@Valid CommentCreateRequest request) {
        Comment entity = commentMapper.toEntity(request);
        // ▶ userId, reviewId 연관 로딩은 추후 도메인 완성 시점에 교체 예정
        Comment saved = commentRepository.save(entity);
        return commentMapper.toResponse(saved);
    }
}
