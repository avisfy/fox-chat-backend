package com.fox.chat.userservice.service;

import com.fox.chat.common.dto.JwtResponseDto;
import com.fox.chat.common.dto.UserDto;
import com.fox.chat.common.util.JwtService;
import com.fox.chat.userservice.mapper.UserMapper;
import com.fox.chat.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public JwtResponseDto registerUser(UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        user.setEnabled(true);
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        userDto = userMapper.toDto(user);
        String userToken = jwtService.generateToken(userDto);
        return new JwtResponseDto(userDto.id(), userToken);
    }

    @Override
    public JwtResponseDto loginUser(UserDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()
                )
        );

        UserDto userDto = findByUsername(dto.username()).orElseThrow();
        String userToken = jwtService.generateToken(userDto);
        return new JwtResponseDto(userDto.id(), userToken);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return user.map(userMapper::toDto);
    }


    @Override
    public Optional<UserDto> findById(Long id) {
        var user = userRepository.findById(id);
        return user.map(userMapper::toDto);
    }
}
