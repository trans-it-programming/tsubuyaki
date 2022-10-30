package jp.trans_it.tsubuyaki.model;

import jp.trans_it.tsubuyaki.model.entity.Message;
import jp.trans_it.tsubuyaki.model.entity.User;

public class Pair {
	private User user;
	private Message message;

	public Pair(User user, Message message) {
		this.user = user;
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public Message getMessage() {
		return message;
	}
}
