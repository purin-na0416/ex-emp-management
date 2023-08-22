package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

@Repository
public class AdministratorRepository {

  /**
   * Administratorオブジェクトを操作するRowMapper
   */
  private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
    Administrator ad = new Administrator();
    ad.setId(rs.getInt("id"));
    ad.setName(rs.getString("name"));
    ad.setMailAddress(rs.getString("mail_address"));
    ad.setPassword(rs.getString("password"));
    return ad;
  };

  @Autowired
  NamedParameterJdbcTemplate template;

  /**
   * 管理者情報を挿入する
   * 
   * @param administrator 挿入したい管理者の情報
   * @return なし
   */

  public void insert(Administrator administrator) {
    String sql = "insert into administrators(name, mail_address, password) values(:name, :mailAddress, :password);";

    SqlParameterSource param = new MapSqlParameterSource().addValue("name", administrator.getName()).addValue("mailAddress", administrator.getMailAddress()).addValue("password", administrator.getPassword());

    template.update(sql, param);
  }

  /**
   * 管理者情報を取得する
   * 
   * @param mailAddress 検索したいメールアドレス
   * @param password 検索したいパスワード
   * @return 管理者情報(該当するものがない場合はnull)
   */

  public Administrator findByMailaddressAndPassword(String mailAddress, String password) {
    String sql = "select from administrators where mail_address=:mailAddress and password=:password;";

    SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);

    Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);

    if(administrator == null) {
      return null;
    }

    return administrator;
  }
}
