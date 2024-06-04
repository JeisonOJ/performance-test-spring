package com.jeison.perfomance_test.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeison.perfomance_test.api.dto.errors.ErrorResp;
import com.jeison.perfomance_test.api.dto.request.QuestionOnlyTextReq;
import com.jeison.perfomance_test.api.dto.request.QuestionReq;
import com.jeison.perfomance_test.api.dto.response.QuestionResp;
import com.jeison.perfomance_test.infrastructure.abstract_services.IQuestionService;
import com.jeison.perfomance_test.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private final IQuestionService service;

    @Operation(summary = "Get the entire questions list in a paginated manner")
    @GetMapping
    public ResponseEntity<Page<QuestionResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false) SortType sortType) {
        if (Objects.isNull(sortType)) {
            sortType = SortType.NONE;
        }
        return ResponseEntity.ok(this.service.findAll(page - 1, size, sortType));
    }

    @Operation(summary = "Get a question by its ID number")
    @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResp> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Create a question")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @PostMapping
    public ResponseEntity<QuestionResp> createQuestion(@Validated @RequestBody QuestionReq questionReq) {
        return ResponseEntity.ok(service.create(questionReq));
    }

    @Operation(summary = "Update a question by its ID number")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @PutMapping("{id}/options")
    public ResponseEntity<QuestionResp> updateQuestion(@Validated @RequestBody QuestionReq questionReq,
            @PathVariable Long id) {
        return ResponseEntity.ok(service.update(questionReq, id));
    }

    @Operation(summary = "Update only text in question by its ID number")
    @ApiResponse(responseCode = "400", description = "When the request is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @PatchMapping("{id}")
    public ResponseEntity<QuestionResp> updateTextQuestion(@Validated @RequestBody QuestionOnlyTextReq questionReq,
            @PathVariable Long id) {
        return ResponseEntity.ok(service.updateText(questionReq, id));
    }

    @Operation(summary = "Delete a question by its ID number")
    @ApiResponse(responseCode = "204", description = "Question deleted successfully")
    @ApiResponse(responseCode = "400", description = "When the ID is not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResp.class))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
