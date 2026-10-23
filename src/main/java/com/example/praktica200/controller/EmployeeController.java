package com.example.praktica200.controller;

import com.example.praktica200.model.Employee;
import com.example.praktica200.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private static final String DUPLICATE_EMAIL = "Сотрудник с таким email уже существует";

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home() {
        return "redirect:/employees";
    }

    @GetMapping("/employees")
    public String list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Boolean active,
                       Model model) {
        model.addAttribute("employees", employeeService.search(name, active));
        model.addAttribute("name", name);
        model.addAttribute("active", active);
        return "employees-list";
    }

    @GetMapping("/employees/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    // сохранение нового
    @PostMapping("/employees/add")
    public String add(@Valid @ModelAttribute("employee") Employee employee,
                      BindingResult result) {
        employee.setId(null);
        if (!result.hasFieldErrors("email") && employeeService.emailExists(employee.getEmail())) {
            result.rejectValue("email", "duplicate", DUPLICATE_EMAIL);
        }
        if (result.hasErrors()) {
            return "employee-form";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    // редактирования
    @GetMapping("/employees/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee-form";
    }

    // сохранение
    @PostMapping("/employees/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("employee") Employee employee,
                       BindingResult result) {
        employeeService.getById(id); 
        employee.setId(id);
        if (!result.hasFieldErrors("email") && employeeService.emailExists(employee.getEmail(), id)) {
            result.rejectValue("email", "duplicate", DUPLICATE_EMAIL);
        }
        if (result.hasErrors()) {
            return "employee-form";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    // удаление
    @PostMapping("/employees/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
