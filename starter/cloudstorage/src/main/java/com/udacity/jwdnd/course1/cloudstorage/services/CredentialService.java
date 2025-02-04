package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService){
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;

    }

    public int createCredential(Credential credential) {
//        String encodedKey = generateSecureRandomKey(16);
//        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
//        credential.setKey(encodedKey);
//        credential.setPassword(encryptedPassword);
        return credentialMapper.insert(credential);
    }

    public int deleteRecordById(Integer credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }

    public int updateCredential(Credential credential, Integer credentialId) {
        return credentialMapper.updateCredential(credential.getUrl(),credential.getUsername(),this.encryptionService.encryptValue(credential.getPassword(),credential.getKey()),credential.getKey(), credentialId);
    }

    public boolean isUserCredentialsAvailable(Integer userId) {
        return credentialMapper.getCredentialsByUser(userId) == null;
    }

    public List<Credential> getCredentialByUser(Integer userId){
        return credentialMapper.getCredentialsByUser(userId);
    }

    public Credential getCredentialById(Integer credentialId){
        return credentialMapper.getCredentialById(credentialId);
    }

//    private String generateSecureRandomKey(int length){
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] randomBytes = new byte[length];
//        secureRandom.nextBytes(randomBytes);
//        return Base64.getEncoder().encodeToString(randomBytes);
//    }
}

