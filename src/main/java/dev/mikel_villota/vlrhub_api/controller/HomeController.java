package dev.mikel_villota.vlrhub_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class HomeController {

    @Operation(summary = "App & DB Status check", description = "Check the status of the database and the the Spring Boot app")
    @ApiResponse(responseCode = "200", description = "App and database are running",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseEntity.class))))
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @GetMapping("/status")
    public ResponseEntity<Void> apiStatus(){
        return ResponseEntity.ok().build();
    }
}
