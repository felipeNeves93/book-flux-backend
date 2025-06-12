package com.bookflux.repository.collection.book;

import com.bookflux.config.mongo.SharedEntity;
import com.bookflux.dto.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookCollection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@SharedEntity
public class BookCollection {

  @Id
  private String id;
  @Indexed
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
