import com.bookflux.aplication.service.BookCollectionApiServiceImpl;
import com.bookflux.dto.GoogleBooksResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class BookCollectionApiServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BookCollectionApiServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new BookCollectionApiServiceImpl(restTemplate);
        ReflectionTestUtils.setField(service, "apiUrl", "https://www.googleapis.com/books/v1/volumes?q=");
    }

    @Test
    void searchBook_shouldCallRestTemplateWithCorrectUrl() {
        // Arrange
        String query = "harry";
        String expectedUrl = "https://www.googleapis.com/books/v1/volumes?q=harry";

        GoogleBooksResponseDto mockResponse = new GoogleBooksResponseDto();
        when(restTemplate.getForObject(expectedUrl, GoogleBooksResponseDto.class)).thenReturn(mockResponse);

        // Act
        GoogleBooksResponseDto result = service.searchBook(query);

        // Assert
        assertNotNull(result);
        assertEquals(mockResponse, result);
        verify(restTemplate, times(1)).getForObject(expectedUrl, GoogleBooksResponseDto.class);
    }
}