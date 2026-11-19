package com.example.todoapp.dto;

import com.example.todoapp.entity.TodoInfo;

import java.time.LocalDate;

public record TodoResponse(
        Long todoId,
        String todoText,
        boolean todoDone,
        LocalDate todoLimitDate
) {
    public static TodoResponse from(TodoInfo info) {
        return new TodoResponse(
                info.getTodoId(),
                info.getTodoText(),
                info.isTodoDone(),
                info.getTodoLimitDate()
        );
    }
}
