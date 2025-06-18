package com.fox.chat.userservice.service;

import com.fox.chat.common.dto.JwtResponseDto;
import com.fox.chat.common.dto.UserDto;
import com.fox.chat.common.util.JwtService;
import com.fox.chat.userservice.exception.InvalidCredentialsException;
import com.fox.chat.userservice.exception.UserAlreadyExistsException;
import com.fox.chat.userservice.exception.UserNotFoundException;
import com.fox.chat.userservice.mapper.UserMapper;
import com.fox.chat.userservice.repository.UserRepository;
import com.fox.chat.userservice.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user if the username is not already taken.
     *
     * @param userDto the user data to register
     * @return JWT response with user ID and token
     * @throws UserAlreadyExistsException if a user with the same username already exists
     */
    @Override
    @Transactional
    public JwtResponseDto registerUser(UserDto userDto) {
        var isUsernameExist = userRepository.existsByUsername(userDto.username());
        if (isUsernameExist) {
            throw new UserAlreadyExistsException(userDto.username());
        }

        var user = userMapper.toEntity(userDto);
        user.setEnabled(true);
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        userDto = userMapper.toDto(user);
        return generateJwtResponse(userDto);
    }

    @Override
    public JwtResponseDto loginUser(UserDto dto) {
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.username(),
                            dto.password()
                    )
            );
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            throw new InvalidCredentialsException();
        }
        CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
        UserDto userDto = userMapper.toDto(principal.user());
        return generateJwtResponse(userDto);
    }

    @Override
    public UserDto findById(Long id) {
        var user = userRepository.findById(id);
        return user
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    private JwtResponseDto generateJwtResponse(UserDto userDto) {
        String userToken = jwtService.generateToken(userDto);
        return new JwtResponseDto(userDto.id(), userToken);
    }
}
