package com.example.todoapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTodoDoneRequest {
    @NotNull
    private Long todoId;
    @NotNull
    private boolean todoDone;
}
