package com.example.todoapp.service;

import com.example.todoapp.dto.CreateTodoRequest;
import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.dto.UpdateTodoDoneRequest;
import com.example.todoapp.dto.UpdateTodoRequest;
import com.example.todoapp.entity.TodoInfo;
import com.example.todoapp.exception.TodoNotFoundException;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public List<TodoInfo> findAll() {
        return todoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TodoInfo findById(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException(todoId));
    }

    public TodoInfo create(CreateTodoRequest request) {
        TodoInfo todoInfo = new TodoInfo(
                request.getTodoText()
                , request.getTodoLimitDate()
        );
        TodoInfo saved = todoRepository.save(todoInfo);
        return saved;
    }

    public void update(UpdateTodoRequest request) {
        TodoInfo todoInfo = todoRepository.findById(request.getTodoId()).orElseThrow(() -> new TodoNotFoundException(request.getTodoId()));

        todoInfo.setTodoText(
                request.getTodoText());

        todoInfo.setTodoLimitDate(
                request.getTodoLimitDate());
    }

    public void updateTodoDone(UpdateTodoDoneRequest request) {
        TodoInfo todoInfo = todoRepository.findById(request.getTodoId()).orElseThrow(() -> new TodoNotFoundException(request.getTodoId()));
        todoInfo.setTodoDone(request.isTodoDone());
    }
}
