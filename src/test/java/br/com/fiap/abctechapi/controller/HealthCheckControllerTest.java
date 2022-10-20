package br.com.fiap.abctechapi.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HealthCheckControllerTest {

    @InjectMocks
    private HealthCheckController controller;

    @Test
    void status() throws IOException {
        ResponseEntity<String> response = controller.status();
        Assertions.assertEquals("'abctechapi' - '0.0.1-SNAPSHOT'", response.getBody());
    }
}