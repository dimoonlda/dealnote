package biz.dealnote.rest.security;

public interface AuthenticationService {
	/**
	 * Authenticate user and check serial number.
	 * If all checks is ok, then return true, otherwise return false.
	 * @param userName - user name
	 * @param passwd - password
	 * @param sn - device serial number
	 * @return
	 */
	public boolean authenticateAndCheckSerialNumber(String userName,
			String passwd, String sn);
}
