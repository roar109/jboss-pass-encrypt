package com.rageco.encrypt;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author hector.mendoza
 * 12/03/2014
 * */
public class DecryptModule {

	public static void main(String[] args) {
		try {
			System.out.println(decode(args[0]));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Taken from <a href="https://source.jboss.org/browse/PicketBox/trunk/security-jboss-sx/jbosssx/src/main/java/org/picketbox/datasource/security/SecureIdentityLoginModule.java?r=276">Jboss api</a>
	 * */
	private static char[] decode(String secret) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException {
		byte[] kbytes = "jaas is the way".getBytes();
		SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

		BigInteger n = new BigInteger(secret, 16);
		byte[] encoding = n.toByteArray();

		// SECURITY-344: fix leading zeros
		if (encoding.length % 8 != 0) {
			int length = encoding.length;
			int newLength = ((length / 8) + 1) * 8;
			int pad = newLength - length; // number of leading zeros
			byte[] old = encoding;
			encoding = new byte[newLength];
			for (int i = old.length - 1; i >= 0; i--) {
				encoding[i + pad] = old[i];
			}
			// SECURITY-563: handle negative numbers
			if (n.signum() == -1) {
				for (int i = 0; i < newLength - length; i++) {
					encoding[i] = (byte) -1;
				}
			}
		}

		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decode = cipher.doFinal(encoding);
		return new String(decode).toCharArray();
	}
}
