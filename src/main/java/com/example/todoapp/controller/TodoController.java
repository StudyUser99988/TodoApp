package com.example.todoapp.controller;

import com.example.todoapp.constants.Constants;
import com.example.todoapp.dto.CreateTodoRequest;
import com.example.todoapp.dto.TodoResponse;
import com.example.todoapp.dto.UpdateTodoDoneRequest;
import com.example.todoapp.dto.UpdateTodoRequest;
import com.example.todoapp.entity.TodoInfo;
import com.example.todoapp.service.TodoService;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(Constants.URL_PATH_TODO_LIST)
    public String listView(Model model) throws Exception {
        List<TodoResponse> todoList = todoService.findAll().stream().map(TodoResponse::from).toList();
        model.addAttribute(Constants.ATTRIBUTE_KEY_TODO_LIST, todoList);
        return Constants.VIEW_PATH_TODO_LIST;
    }
    @GetMapping(Constants.URL_PATH_TODO_REGIST_INIT)
    public String registView(Model model) throws Exception {
        model.addAttribute(Constants.ATTRIBUTE_KEY_CREATE_REQUEST, new CreateTodoRequest());
        return Constants.VIEW_PATH_TODO_REGIST;
    }

    @PostMapping(Constants.URL_PATH_TODO_REGIST_REGIST)
    public String regist(Model model, @Validated CreateTodoRequest request,
                         BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
        if(result.hasErrors()) {
            return Constants.VIEW_PATH_TODO_REGIST;
        }
        todoService.create(request);
        redirectAttributes.addFlashAttribute(Constants.ATTRIBUTE_KEY_SUCCESS_MESSAGE, "TODOを登録しました。");
        return Constants.URL_REDIRECT + Constants.URL_PATH_TODO_LIST;
    }

    @GetMapping(Constants.URL_PATH_TODO_UPDATE_INIT)
    public String updateView(Model model, @RequestParam Long todoId) throws Exception {
        TodoInfo todoInfo = todoService.findById(todoId);
        model.addAttribute(Constants.ATTRIBUTE_KEY_UPDATE_TODO_REQUEST, new UpdateTodoRequest(todoInfo));
        return Constants.VIEW_PATH_TODO_UPDATE;
    }

    @PostMapping(Constants.URL_PATH_TODO_UPDATE_UPDATE)
    public String update(Model model, @Validated UpdateTodoRequest request,
                         BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
        if(result.hasErrors()) {
            return Constants.VIEW_PATH_TODO_UPDATE;
        }
        todoService.update(request);
        redirectAttributes.addFlashAttribute(Constants.ATTRIBUTE_KEY_SUCCESS_MESSAGE, "TODOを更新しました。");
        return Constants.URL_REDIRECT + Constants.URL_PATH_TODO_LIST;
    }

    @PostMapping(Constants.URL_PATH_TODO_DONE)
    @ResponseBody
    public void updateTodoDone(@RequestBody UpdateTodoDoneRequest request) throws Exception{
        todoService.updateTodoDone(request);
    }
}
