import com.bookflux.aplication.mapper.BookMapper;
import com.bookflux.dto.GoogleBooksResponseDto;
import com.bookflux.dto.ImagelinksDto;
import com.bookflux.enums.MaturityRating;
import com.bookflux.repository.collection.BookCollection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    @Test
    void toDomain_shouldMapGoogleBooksResponseDtoToBookCollection() {

        GoogleBooksResponseDto.VolumeInfo volumeInfo = new GoogleBooksResponseDto.VolumeInfo();
        volumeInfo.setTitle("Clean Code");
        volumeInfo.setAuthors(List.of("Robert C. Martin"));
        volumeInfo.setPublisher("Prentice Hall");
        volumeInfo.setPublishedDate("2008-08-01");
        volumeInfo.setDescription("A Handbook of Agile Software Craftsmanship");
        volumeInfo.setPageCount(464);
        volumeInfo.setCategories(List.of("Programming"));
        volumeInfo.setMaturityRating("NOT_MATURE");
        volumeInfo.setAverageRating(4.7);
        volumeInfo.setImageLinks(new ImagelinksDto(
                "http://example.com/small.jpg",
                "http://example.com/image.jpg"
        ));
        volumeInfo.setLanguage("en");

        GoogleBooksResponseDto.Item item = new GoogleBooksResponseDto.Item();
        item.setVolumeInfo(volumeInfo);

        GoogleBooksResponseDto responseDto = new GoogleBooksResponseDto();
        responseDto.setItems(List.of(item));

        // Act
        BookCollection result = BookMapper.toDomain(responseDto);

        // Assert
        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
        assertEquals(List.of("Robert C. Martin"), result.getAuthors());
        assertEquals("Prentice Hall", result.getPublisher());
        assertEquals("2008-08-01", result.getPublishedDate());
        assertEquals("A Handbook of Agile Software Craftsmanship", result.getDescription());
        assertEquals(464, result.getPageCount());
        assertEquals(List.of("Programming"), result.getCategories());
        assertEquals(MaturityRating.NOT_MATURE, result.getMaturityRating());
        assertEquals(4.7, result.getAverageRating());
        assertEquals("http://example.com/image.jpg", result.getImageLinks().getThumbnail());
        assertEquals("http://example.com/small.jpg", result.getImageLinks().getSmallThumbnail());
        assertEquals("en", result.getLanguage());
    }

    @Test
    void toDomain_shouldReturnFallbackBook_whenResponseIsEmpty() {
        // Arrange
        GoogleBooksResponseDto emptyResponse = new GoogleBooksResponseDto();
        emptyResponse.setItems(List.of()); // vazio

        // Act
        BookCollection result = BookMapper.toDomain(emptyResponse);

        // Assert
        assertNotNull(result);
        assertEquals("Book not found", result.getTitle());
        assertTrue(result.getAuthors().isEmpty());
    }
}