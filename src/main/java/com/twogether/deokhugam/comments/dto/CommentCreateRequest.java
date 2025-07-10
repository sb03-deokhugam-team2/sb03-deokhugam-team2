package com.twogether.deokhugam.comments.dto;

import java.util.UUID;

public record CommentCreateRequest(
    UUID reviewId,
    String content
) {}