package com.example.todoapp.constants;

import org.springframework.stereotype.Component;

@Component
public class Constants {
    // URLPATH
    public static final String URL_REDIRECT = "redirect:";
    public static final String URL_PATH_TODO_LIST = "/list";
    public static final String URL_PATH_TODO_REGIST_INIT = "/regist/init";
    public static final String URL_PATH_TODO_REGIST_REGIST = "/regist/regist";
    public static final String URL_PATH_TODO_UPDATE_INIT = "/update/init";
    public static final String URL_PATH_TODO_UPDATE_UPDATE = "/update/update";
    public static final String URL_PATH_TODO_DONE = "/todo/done";

    // HTMLPTH
    public static final String VIEW_PATH_TODO_LIST = "todos/todolist";
    public static final String VIEW_PATH_TODO_REGIST = "todos/todoregist";
    public static final String VIEW_PATH_TODO_UPDATE = "todos/todoupdate";

    // ATTRIBUTE
    public static final String ATTRIBUTE_KEY_TODO_LIST = "todoList";
    public static final String ATTRIBUTE_KEY_SUCCESS_MESSAGE = "successMessage";
    public static final String ATTRIBUTE_KEY_CREATE_REQUEST = "createTodoRequest";
    public static final String ATTRIBUTE_KEY_UPDATE_TODO_REQUEST = "updateTodoRequest";

}
