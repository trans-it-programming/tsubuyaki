package cc.shinbi.tsubuyaki.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import jp.trans_it.tsubuyaki.model.Const;
import jp.trans_it.tsubuyaki.model.dao.UserDAO;
import jp.trans_it.tsubuyaki.model.entity.User;
import jp.trans_it.tsubuyaki.util.DbUtil;

public class UserTest {

	@Test
	public void testDefaultUser() throws ClassNotFoundException, SQLException {
		Connection connection = DbUtil.connect();
		UserDAO dao = new UserDAO(connection);

		String account = Const.DEFAULT_USER_ACCOUNT;
		String[] passwords = {Const.DEFAULT_USER_PASSWORD, "Wrong Password"};

		for(String password : passwords) {
			User user = dao.login(account, password);
			if(user == null) {
				System.out.println("ログイン失敗");
			}
			else {
				System.out.println("ログイン成功");
				System.out.println(
					String.format(
						"Account: %s, Name: %s, Created At: %s, Updated At: %s",
						user.getAccount(),
						user.getName(),
						user.getCreatedAt().toString(),
						user.getUpdatedAt().toString()
					)
				);
			}
		}
		connection.close();
	}
}
