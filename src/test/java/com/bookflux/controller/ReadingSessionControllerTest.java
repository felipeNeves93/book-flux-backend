package com.bookflux.controller;

import com.bookflux.dto.StartReadingSessionRequest;
import com.bookflux.integration.dto.TestDataFactory;
import com.bookflux.integration.service.ReadingSessionService;
import com.bookflux.repository.collection.ReadingSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.bookflux.integration.dto.TestDataFactory.buildReadingSession;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReadingSessionController.class)
class ReadingSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReadingSessionService readingSessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should return 201 when starting a session")
    void shouldReturn201WhenStartSession() throws Exception {
        StartReadingSessionRequest request = TestDataFactory.buildStartReadingSessionRequest();
        ReadingSession sessionMock = buildReadingSession();

        when(readingSessionService.startSession(any(StartReadingSessionRequest.class)))
                .thenReturn(sessionMock);

        mockMvc.perform(post("/api/reading-session/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sessionId").exists())
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    @DisplayName("Should return 204 when finishing a session")
    void shouldReturn204WhenFinishSession() throws Exception {
        String finishRequest = """
                {
                  "sessionId": "session-123",
                  "bookId": "book-123",
                  "userId": "user-456",
                  "startTime": "2025-05-25T10:00:00",
                  "numberOfPagesRead": 10
                }
                """;

        when(readingSessionService.finishSession(any())).thenReturn(null);

        mockMvc.perform(post("/api/reading-session/finish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(finishRequest))
                .andExpect(status().isNoContent());
    }
}
