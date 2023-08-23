package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * 従業員情報を全件取得する(employeeRepositoryのfindAll()を呼び出す)
   * 
   * @return
   */
  public List<Employee> showList() {
    List<Employee> employeeList = employeeRepository.findAll();
    return employeeList;
  }

  /**
   * IDで従業員情報を取得する
   * 
   * @param id
   * @return employeeRepositoryのload()メソッドから取得した従業員情報
   */
  public Employee showDetail(Integer id) {
    Employee employee = employeeRepository.load(id);
    return employee;
  }
}
