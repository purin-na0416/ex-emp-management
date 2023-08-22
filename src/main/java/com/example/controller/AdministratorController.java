package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {
  @Autowired
  private AdministratorService administratorService;

  @GetMapping("/toInsert")
  /**
   * 従業員登録画面を表示する
   * 
   * @param form 従業員登録フォームのリクエストパラメータを格納する
   * @return administrator/insert.htmlに遷移する
   */
  public String toInsert(InsertAdministratorForm form) {
    return "administrator/insert";
  }
}
