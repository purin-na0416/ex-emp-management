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
}
