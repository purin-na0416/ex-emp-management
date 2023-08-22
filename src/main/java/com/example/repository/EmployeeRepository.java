package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {

  /**
   * Employeeオブジェクトを操作するRowMapper
   */
  private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
    Employee employee = new Employee();

    employee.setId(rs.getInt("id"));
    employee.setName(rs.getString("name"));
    employee.setImage(rs.getString("image"));
    employee.setGender(rs.getString("gender"));
    employee.setHireDate(rs.getDate("hire_date"));
    employee.setMailAddress(rs.getString("mail_address"));
    employee.setZipCode(rs.getString("zip_code"));
    employee.setAddress(rs.getString("address"));
    employee.setTelephone(rs.getString("telephone"));
    employee.setSalary(rs.getInt("salary"));
    employee.setCharacteristics(rs.getString("characteristics"));
    employee.setDependentsCount(rs.getInt("dependents_count"));

    return employee;
  };

  @Autowired
  private NamedParameterJdbcTemplate template;

  /**
   * 従業員一覧情報を入社日順(降順)で取得する
   * 
   * @param なし
   * @return 従業員一覧
   */
  public List<Employee> findAll() {
    String sql = "select * from employees order by hire_date desc;";

    List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

    return employeeList;
  }

  /**
   * 主キーから従業員一覧を取得する
   * 
   * @param id 検索したい従業員のid
   * @return 従業員情報
   */
  public Employee load(Integer id) {
    String sql = "select * from employees where id =:id;";

    SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

    Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

    return employee;
  }

  /**
   * 従業員情報を変更する
   * 
   * @param employee 更新したい従業員情報
   * @return なし
   */
  public void update(Employee employee) {
    String sql = """
                    update employees 
                    set name =:name, 
                        image =:image,
                        gender =:gender, 
                        hire_date =:hireDate,
                        mail_address =:mailAddress,
                        zip_code =:zipCode,
                        address =:address,
                        telephone =:telephone,
                        salary =:salary,
                        characteristics =:characteristics,
                        dependents_count =:dependentsCount
                    where id =:id;
                 """;

    SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

    template.update(sql, param);
  }
}
