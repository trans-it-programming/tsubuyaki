package jp.trans_it.tsubuyaki.test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import jp.trans_it.tsubuyaki.model.Const;
import jp.trans_it.tsubuyaki.model.dao.UserDAO;
import jp.trans_it.tsubuyaki.model.entity.User;
import jp.trans_it.tsubuyaki.util.DbUtil;

class UserTest {

	@Test
	void testDefaultUser() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
		Connection connection = DbUtil.connect();
		UserDAO dao = new UserDAO(connection);

		String account = Const.DEFAULT_USER_EMAIL;
		String[] passwords = {Const.DEFAULT_USER_PASSWORD, "Wrong Password"};
		boolean[] results = {true, false};

		for(int i = 0; i < 2; i++) {
			String password = passwords[i];
			boolean result = results[i];
			
			User user = dao.login(account, password);
			if(user == null) {
				System.out.println("ログイン失敗");
				if(result) {
					fail("意図しない結果です。");
				}
			}
			else {
				System.out.println("ログイン成功");
				System.out.println(
					String.format(
						"Account: %s, Name: %s, Created At: %s, Updated At: %s",
						user.getEmail(),
						user.getName(),
						user.getCreatedAt().toString(),
						user.getUpdatedAt().toString()
					)
				);
				if(!result) {
					fail("意図しない結果です。");
				}
			}
		}
		connection.close();
	}

}
