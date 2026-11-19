package com.example.todoapp.service;

import com.example.todoapp.dto.CreateTodoRequest;
import com.example.todoapp.dto.UpdateTodoDoneRequest;
import com.example.todoapp.dto.UpdateTodoRequest;
import com.example.todoapp.entity.TodoInfo;
import com.example.todoapp.exception.TodoNotFoundException;
import com.example.todoapp.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void 全件取得できること() {

        List<TodoInfo> todos = List.of(
                new TodoInfo(
                        "Spring学習",
                        LocalDate.of(2026, 11, 30))
        );

        when(todoRepository.findAll())
                .thenReturn(todos);

        List<TodoInfo> result =
                todoService.findAll();

        assertEquals(1, result.size());

        verify(todoRepository)
                .findAll();
    }

    @Test
    void Todoが登録できること() {

        CreateTodoRequest request =
                new CreateTodoRequest();

        request.setTodoText("テスト");
        request.setTodoLimitDate(
                LocalDate.now());

        TodoInfo saved =
                new TodoInfo(
                        "テスト",
                        LocalDate.now());

        when(todoRepository.save(any()))
                .thenReturn(saved);

        TodoInfo result =
                todoService.create(request);

        assertEquals(
                "テスト",
                result.getTodoText());
    }

    @Test
    void 更新できること() {

        UpdateTodoRequest request =
                new UpdateTodoRequest();

        request.setTodoId(1L);
        request.setTodoText("更新後");
        request.setTodoLimitDate(
                LocalDate.of(2026, 12, 31));

        TodoInfo entity =
                new TodoInfo(
                        "更新前",
                        LocalDate.of(2026, 11, 30));

        entity.setTodoId(1L);

        when(todoRepository.findById(1L))
                .thenReturn(
                        Optional.of(entity));

        todoService.update(request);

        assertEquals(
                "更新後",
                entity.getTodoText());

        assertEquals(
                LocalDate.of(2026, 12, 31),
                entity.getTodoLimitDate());

        verify(todoRepository)
                .findById(1L);
    }

    @Test
    void TODOを完了状態に更新できること() {

        UpdateTodoDoneRequest request =
                new UpdateTodoDoneRequest();

        request.setTodoId(1L);
        request.setTodoDone(true);

        TodoInfo entity =
                new TodoInfo(
                        "Spring学習",
                        LocalDate.of(2026, 11, 30));

        entity.setTodoId(1L);
        entity.setTodoDone(false);

        when(todoRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        todoService.updateTodoDone(request);

        assertTrue(entity.isTodoDone());

        verify(todoRepository)
                .findById(1L);
    }

    @Test
    void 存在しない場合は例外を送出すること() {

        UpdateTodoRequest request =
                new UpdateTodoRequest();

        request.setTodoId(999L);

        when(todoRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TodoNotFoundException.class,
                () -> todoService.update(request)
        );
    }
}
