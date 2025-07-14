package com.twogether.deokhugam.comments.mapper;

import com.twogether.deokhugam.comments.dto.CommentCreateRequest;
import com.twogether.deokhugam.comments.dto.CommentResponse;
import com.twogether.deokhugam.comments.entity.Comment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Maps a {@link CommentCreateRequest} DTO to a new {@link Comment} entity, excluding the fields {@code id}, {@code createdAt}, {@code updatedAt}, and {@code isDeleted}.
     *
     * The excluded fields are not set from the DTO and will be managed elsewhere (e.g., by the persistence layer).
     *
     * @param dto the comment creation request data to map
     * @return a new {@link Comment} entity populated from the provided DTO
     */
    @BeanMapping(builder = @Builder(disableBuilder = true))
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    Comment toEntity(CommentCreateRequest dto);

    /**
     * Maps a Comment entity to a CommentResponse DTO.
     *
     * The userNickname field in the response is set to null as a placeholder for future user integration.
     *
     * @param entity the Comment entity to map
     * @return the corresponding CommentResponse DTO
     */
    @Mapping(target = "userNickname", expression = "java(null)") // 추후 User 연동 시 수정
    CommentResponse toResponse(Comment entity);
}