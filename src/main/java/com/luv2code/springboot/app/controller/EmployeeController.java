package com.luv2code.springboot.app.controller;

import com.luv2code.springboot.app.entity.Employee;
//import com.luv2code.springboot.thymeleafdemo.model.Employee;
import com.luv2code.springboot.app.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getList(Model theModel){
        List<Employee> employeeList = employeeService.findAll();
        theModel.addAttribute("employees",employeeList);
        return "list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }
    @PostMapping("/saveEmployee")
    public String saveForm(@ModelAttribute("employee") @Valid Employee employee   , BindingResult bindingResult){
        {
            if (bindingResult.hasErrors()) {
                return "employee-form";
            }
        employeeService.save(employee);

        return "redirect:/employees/list";
        }
    }

    @GetMapping("/showUpdateForm/{id}")
    public String updateForm(@PathVariable("id") int id, Model theModel ){
        Employee employee = employeeService.findById(id);
        theModel.addAttribute("employee",employee);
        return "employee-form";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid Employee employee,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "update-user";
        }

        employeeService.save(employee);
        model.addAttribute("employee", employeeService.findAll());
        return "redirect:/employees/list";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
