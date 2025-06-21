package com.bookflux.dto;

import com.bookflux.dto.google.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCollectionDto {

  private String id;
  private String title;
  private List<String> authors;
  private String publisher;
  private String publishedDate;
  private String description;
  private Integer pageCount;
  private List<String> categories;
  private MaturityRating maturityRating;
  private Double averageRating;
  private ImagelinksDto imageLinks;
  private String language;

}
