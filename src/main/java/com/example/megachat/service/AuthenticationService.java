package com.example.megachat.service;

import com.example.megachat.config.JwtService;
import com.example.megachat.dto.AuthenticationRequestDto;
import com.example.megachat.dto.AuthenticationResponseDto;
import com.example.megachat.dto.UserInputDto;
import com.example.megachat.entity.UserEntity;
import com.example.megachat.enums.Role;
import com.example.megachat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    //    private final DigestSHA3 passwordEncoder = new DigestSHA3(256);

    public AuthenticationResponseDto register(UserInputDto userInputDto, Role role) {
        UserEntity userEntity = UserEntity.builder()
                .username(userInputDto.getUsername())
                .password(passwordEncoder.encode(userInputDto.getPassword()))
                //                .password(Hex.toHexString(passwordEncoder.digest(userInputDto.getPassword().getBytes())))
                .name(userInputDto.getName())
                .role(role)
                .isActive(true)
                .build();
        userRepository.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getUsername(),
                        authenticationRequestDto.getPassword()
                )
        );
        UserEntity userEntity = userRepository.findByUsername(authenticationRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username %s not found", authenticationRequestDto.getUsername())
                ));
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public Boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
