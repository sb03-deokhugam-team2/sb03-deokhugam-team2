package com.twogether.deokhugam.book.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public record BookCreateRequest(
    @NotBlank @Size(max = 100)
    String title,

    @NotBlank @Size(max = 50)
    String author,

    @NotBlank
    String description,

    @NotBlank @Size(max = 50)
    String publisher,

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate publishedDate,

    @Size(max = 13)
    String isbn // optional
) {}