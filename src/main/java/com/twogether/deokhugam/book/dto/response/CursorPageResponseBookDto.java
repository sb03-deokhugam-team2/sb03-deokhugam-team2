package com.twogether.deokhugam.book.dto.response;

import com.twogether.deokhugam.book.dto.BookDto;
import java.time.Instant;
import java.util.List;

public record CursorPageResponseBookDto(
    List<BookDto> content,
    String nextCursor,
    Instant nextAfter,
    int size,
    long totalElements,
    boolean hasNext
) {}
