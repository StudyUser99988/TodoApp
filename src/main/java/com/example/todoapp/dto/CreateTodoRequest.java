package com.example.todoapp.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTodoRequest{
    @NotBlank(message = "内容は必須項目です")
    @Size(max = 100, message="100文字以内で入力してください")
    private String todoText;
    @NotNull(message = "期限は必須項目です")
    private LocalDate todoLimitDate;

    @AssertTrue(message = "期限は現在年月日より先の日付にしてください")
    public boolean isAfterNowDate() {
        LocalDate now = LocalDate.now();
        return now.isEqual(this.todoLimitDate) || now.isBefore(this.todoLimitDate);
    }
}
