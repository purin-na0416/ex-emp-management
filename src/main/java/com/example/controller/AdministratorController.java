package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AdministratorController {
  @Autowired
  private AdministratorService administratorService;

  @Autowired
  private HttpSession session;

  @GetMapping("/toInsert")
  /**
   * 管理者登録画面を表示する
   * 
   * @param form 管理者登録フォームのリクエストパラメータを格納する
   * @return administrator/insert.htmlに遷移する
   */
  public String toInsert(InsertAdministratorForm form) {
    return "administrator/insert";
  }  
  
  @PostMapping("/insert")

  /**
   * 管理者情報を登録する
   * 
   * @param form リクエストパラメータが入っているInsertAdministratorFormのオブジェクト
   * @return ログイン画面にリダイレクト
   */
  public String insert(InsertAdministratorForm form) {
    Administrator administrator = new Administrator();

    administrator.setName(form.getName());
    administrator.setMailAddress(form.getMailAddress());
    administrator.setPassword(form.getPassword());
    
    administratorService.insert(administrator);

    return "redirect:/";
  }  

  @GetMapping("/")
  /**
   * ログイン画面を表示する
   * 
   * @param form リクエストパラメータが入っているLoginFormのオブジェクト
   * @return administrator/login.htmlに遷移する
   */
  public String toLogin(LoginForm form) {
    return "administrator/login";
  }

  @PostMapping("/login")
  public String login(LoginForm form, Model model) {
    Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

    if(administrator == null) {
      String message = "メールアドレスまたはパスワードが不正です。";
      model.addAttribute("error", message);
      return "administrator/login";
    } else {
      session.setAttribute("administratorName", administrator);
      return "redirect:/employee/showList";
    }
  }
}
