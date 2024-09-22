package com.example.megachat.controller;

import com.example.megachat.dto.AuthenticationRequestDto;
import com.example.megachat.dto.AuthenticationResponseDto;
import com.example.megachat.dto.UserInputDto;
import com.example.megachat.enums.Role;
import com.example.megachat.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AuthenticationController extends BaseController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @Valid @RequestBody UserInputDto request
    ) {
        log.info("POST:api/auth/register username: {}", request.getUsername());
        StopWatch sw = StopWatch.createStarted();
        AuthenticationResponseDto result = authenticationService.register(request, Role.USER);
        log.info("POST:api/auth/register time: {}ms", sw.getTime());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequestDto authenticationRequestDto
    ) {
        log.info("POST:api/auth/authenticate username: {}", authenticationRequestDto.getUsername());
        StopWatch sw = StopWatch.createStarted();
        AuthenticationResponseDto result = authenticationService.authenticate(authenticationRequestDto);
        log.info("POST:api/auth/authenticate time: {}ms", sw.getTime());
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Проверка на свободность имени пользователя")
    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(
            @Parameter(name = "username", description = "Имя пользователя, которое необходимо проверить")
            @RequestParam(name = "username") String username
    ) {
        log.info("GET:api/auth/check-username username: {}", username);
        StopWatch sw = StopWatch.createStarted();
        Boolean result = authenticationService.checkUsername(username);
        log.info("GET:api/auth/check-username time: {}ms", sw.getTime());
        return ResponseEntity.ok(result);
    }
}
