package com.example.megachat.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController extends BaseController {

    @Operation(summary = "Test")
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("OK!)");
    }
}
