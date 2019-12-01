package com.ashwin.spring_app_vemployee.controller;

import com.ashwin.spring_app_vemployee.model.Todo;
import com.ashwin.spring_app_vemployee.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    private ToDoService toDoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value="/list-todos",method= RequestMethod.GET)
    public String showToDolist(ModelMap model){
        String newName="ashwin";
        model.put("newNam",newName);
       String name=(String) model.get("name");
        model.put("todos",toDoService.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id){
        toDoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/add-todo",method= RequestMethod.GET)
    public String showAddToDoPage(ModelMap model){
        model.addAttribute("todo", new Todo(0, "abc", "Default Desc",
                new Date(), false));
         return "todo";
    }

    @RequestMapping(value="/add-todo",method= RequestMethod.POST)
    public String addToDo(ModelMap model, @Valid @ModelAttribute Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        toDoService.addTodo((String) model.get("name"),todo.getDesc(),todo.getTargetDate(),false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = toDoService.retrieveTodo(id);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        todo.setUser("in28Minutes");
        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUser((String) model.get("name"));

        toDoService.updateTodo(todo);

        return "redirect:/list-todos";
    }
}
