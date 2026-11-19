package com.example.todoapp.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException() {
        super();
    }
    public TodoNotFoundException(Long id) {
        super("指定のTODOが見つかりません。 ID: "+id);
    }
}
