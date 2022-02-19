package main.java.model;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class Hashing {

	// Hash a password
	protected static String generatePasswordHash(char[] password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 10; // Number of encryption iterations
		byte[] salt = getSalt(); // Generate Salt (factor of randomness)

		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 64 * 8);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		byte[] hash = keyFactory.generateSecret(spec).getEncoded();

		// Return all information needed for verifying passwords as String
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	// Generate Salt (factor of randomness)
	protected static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	// create hexadecimal String from byte array
	protected static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger big = new BigInteger(1, array);
		String hex = big.toString(16);

		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	// create byte array from hexadecimal String
	protected static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	protected static boolean validatePassword(String plaintext, String hashedInfo)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = hashedInfo.split(":");
		int iterations = Integer.parseInt(parts[0]);

		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		// generate hash from plaintext password input
		PBEKeySpec spec = new PBEKeySpec(plaintext.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length; // bitwise XOR
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0; // check if difference is 0 and return boolean
	}

}
