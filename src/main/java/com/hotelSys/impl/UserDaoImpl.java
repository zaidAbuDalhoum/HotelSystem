package com.hotelSys.impl;

import com.hotelSys.model.Login;
import com.hotelSys.model.User;
import com.hotelSys.dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserDaoImpl implements UserDao {


  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static Logger logger = Logger.getLogger(UserDaoImpl.class);

  //register user to database
  public void register(User user) {
    final String INSERT =
        "INSERT INTO users(usersFirstName,usersLastName,usersEmail,usersPassword,usersPermission," +
            "usersPhoneNum,usersDateOfBirth,usersStatus)VALUES(?,?,?,?,?,?,?,?)";
    if (user.getPermission() == null) {
      user.setPermission("user");
    }

    user.setStatus("Active");

    jdbcTemplate.update(INSERT, new Object[]{user.getFirstName(),
        user.getLastName(), user.getEmail(), encrypt(user.getPassword()), user.getPermission(),
        user.getPhoneNumber(),
        user.getDateOfBirth(), user.getStatus()});

    logger.info("New User Signed up : " + user.getFirstName() + " " + user.getLastName());

  }

  //checks for the user email and password
  public User validateUser(final Login login) {

    final String SELECT = "select * from users where usersEmail = ? and usersPassword = ?";

    List<User> list = jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, login.getEmail());
        preparedStatement.setString(2, encrypt(login.getPassword()));
      }
    }, new RowMapper<User>() {
      @Override
      public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();
        user.setPassword(resultSet.getString("usersPassword"));
        user.setFirstName(resultSet.getString("usersFirstName"));
        user.setLastName(resultSet.getString("usersLastName"));
        user.setEmail(resultSet.getString("usersEmail"));
        user.setDateOfBirth(resultSet.getString("usersDateOfBirth"));
        user.setPhoneNumber(resultSet.getString("usersPhoneNum"));
        user.setPermission(resultSet.getString("usersPermission"));
        user.setId(resultSet.getInt("usersId"));

        logger.info("User " + user.getFirstName() + user.getLastName() + " logged in");
        return user;

      }
    });

    return list.size() > 0 ? list.get(0) : null;

  }


  //To Encrypt Passwords
  public static String encrypt(String password) {

    final byte[] defaultBytes = password.getBytes();
    try {
      final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
      md5MsgDigest.reset();
      md5MsgDigest.update(defaultBytes);
      final byte messageDigest[] = md5MsgDigest.digest();
      final StringBuffer hexString = new StringBuffer();
      for (final byte element : messageDigest) {
        final String hex = Integer.toHexString(0xFF & element);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      password = hexString + "";
    } catch (final NoSuchAlgorithmException nsae) {
      nsae.printStackTrace();
    }
    return password;
  }
}

