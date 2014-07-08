package user;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;

/* Bean, um das eingegebene Passwort zu verschlüsseln und einen Salt zu generieren */

public class PasswordBean {

	public static String hash(String password, String salt) {
		return DigestUtils.shaHex(password + salt);
	}
	
	public static String getNextSalt() {
		return RandomStringUtils.randomAscii(20);
	}
	
}
