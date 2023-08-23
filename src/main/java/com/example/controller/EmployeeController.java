package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/showList")

  /**
   * employeeServiceのshowList()から従業員一覧を取得し、requestスコープに格納する
   * 
   * @param model
   * @return employee/list.htmlに遷移する
   */
  public String showList(Model model) {
    List<Employee> employeeList = employeeService.showList();
    model.addAttribute("employeeList", employeeList);

    return "employee/list";
  }

  @GetMapping("/showDetail")

  /**
   * 従業員の詳細ページを表示する
   * 
   * @param id リクエストパラメータで送られてくる従業員ID
   * @param model
   * @param form
   * @return employee/detail.htmlに遷移する
   */
  public String showDetail(String id, Model model, UpdateEmployeeForm form) {
    Employee employee = employeeService.showDetail(Integer.parseInt(id));
    model.addAttribute("employee", employee);

    return "employee/detail";
  }
}
