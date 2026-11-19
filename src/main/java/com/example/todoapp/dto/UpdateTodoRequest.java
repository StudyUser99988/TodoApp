package com.example.todoapp.dto;


import com.example.todoapp.entity.TodoInfo;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateTodoRequest {
    @NotNull
    private Long todoId;
    @NotBlank(message = "内容は必須項目です")
    @Size(max = 100, message = "100文字以内で入力してください")
    private String todoText;
    @NotNull(message = "期限は必須項目です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate todoLimitDate;

    public UpdateTodoRequest() {}

    public UpdateTodoRequest(TodoInfo todoInfo) {
        this.todoId = todoInfo.getTodoId();
        this.todoText = todoInfo.getTodoText();
        this.todoLimitDate = todoInfo.getTodoLimitDate();
    }

    @AssertTrue(message = "期限は現在年月日より先の日付にしてください")
    public boolean isAfterNowDate() {
        LocalDate now = LocalDate.now();
        return now.isEqual(this.todoLimitDate) || now.isBefore(this.todoLimitDate);
    }
}