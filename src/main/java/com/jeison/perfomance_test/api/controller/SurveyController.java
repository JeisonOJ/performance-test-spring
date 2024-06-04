package com.jeison.perfomance_test.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeison.perfomance_test.api.dto.errors.ErrorResp;
import com.jeison.perfomance_test.api.dto.request.SurveyReq;
import com.jeison.perfomance_test.api.dto.response.SurveyResp;
import com.jeison.perfomance_test.api.dto.response.SurveyRespWithQuestions;
import com.jeison.perfomance_test.infrastructure.abstract_services.ISurveyService;
import com.jeison.perfomance_test.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private final ISurveyService service;

    @Operation(summary = "Get the entire surveys list in a paginated manner")
    @GetMapping
    public ResponseEntity<Page<SurveyResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(this.service.findAll(page - 1, size, sortType));
    }

    @Operation(summary = "Get a survey by its ID number and its questions")
    @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SurveyRespWithQuestions> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByIdWithQuestions(id));
    }

    @Operation(summary = "Create a survey")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @PostMapping
    public ResponseEntity<SurveyResp> createSurvey(@Validated @RequestBody SurveyReq surveyReq) {
        return ResponseEntity.ok(service.create(surveyReq));
    }

    @Operation(summary = "Update a survey by its ID number")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @PutMapping("{id}")
    public ResponseEntity<SurveyResp> updateSurvey(@Validated @RequestBody SurveyReq surveyReq, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(surveyReq, id));
    }

    @Operation(summary = "Delete a survey by its ID number")
    @ApiResponse(responseCode = "204", description = "Survey deleted successfully")
    @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
