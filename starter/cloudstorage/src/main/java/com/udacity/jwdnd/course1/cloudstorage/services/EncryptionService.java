package com.udacity.jwdnd.course1.cloudstorage.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncryptionService {

    private final Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    public String encryptValue(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = getAESKey(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception e) {
            logger.error("Error encrypting value: " + e.getMessage());
        }
        return null;
    }

    public String decryptValue(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = getAESKey(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(decryptedValue, "UTF-8");
        } catch (Exception e) {
            logger.error("Error decrypting value: " + e.getMessage());
        }
        return null;
    }

    public String getEncryptionKey() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private SecretKey getAESKey(String key) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[16];
        byte[] keyInputBytes = key.getBytes("UTF-8");
        System.arraycopy(keyInputBytes, 0, keyBytes, 0, Math.min(keyInputBytes.length, 16));
        return new SecretKeySpec(keyBytes, "AES");
    }
}
