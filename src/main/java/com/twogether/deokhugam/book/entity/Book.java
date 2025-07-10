package com.twogether.deokhugam.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "books")
@Getter
@NoArgsConstructor
public class Book {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @Column(name = "author", nullable = false, length = 50)
  private String author;

  @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
  private String description;

  @Column(name = "publisher", nullable = false, length = 50)
  private String publisher;

  @Column(name = "published_date", nullable = false)
  private LocalDate publishedDate;

  @Column(name = "isbn", length = 13)
  private String isbn;

  @Column(name = "thumbnail_url")
  private String thumbnailUrl;

  @Column(name = "review_count", nullable = false)
  private Integer reviewCount;

  @Column(name = "rating", nullable = false)
  private Float rating;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @Column(name = "is_deleted", nullable = false)
  private Boolean isDeleted = false;
}
