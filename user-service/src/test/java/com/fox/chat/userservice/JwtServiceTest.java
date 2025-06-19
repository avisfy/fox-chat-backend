package com.fox.chat.userservice;

import com.fox.chat.common.dto.UserDto;
import com.fox.chat.common.util.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1L, "testuser", "123", true);
    }

    @Test
    public void shouldGenerateValidToken() {
        String token = jwtService.generateToken(userDto);

        assertNotNull(token);
        assertFalse(token.isEmpty());

        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(userDto.username(), extractedUsername);
    }
}
