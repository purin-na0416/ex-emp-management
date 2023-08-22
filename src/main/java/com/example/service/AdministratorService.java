package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
  @Autowired
  private AdministratorRepository administratorRepository;

  /**
   * 管理者情報を挿入する(administratorRepositoryのinsert()メソッドを呼ぶ)
   * 
   * @param administrator
   * @return なし
   */
  public void insert(Administrator administrator) {
    administratorRepository.insert(administrator);
  }

  /**
   * ログイン処理を行う(administratorRepositoryのfindByMailAddressAndPassword()メソッドを呼ぶ)
   * 
   * @param mailAddress メールアドレス
   * @param password パスワード
   * @return 呼び出したメソッドの管理者情報
   */
  public Administrator login(String mailAddress, String password) {
    Administrator administrator = administratorRepository.findByMailaddressAndPassword(mailAddress, password);
   
    return administrator;
  }
}
