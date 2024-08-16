package com.example.Taskmanagement.util;




//import com.dhruv.ToDoApp.exceptions.UserNotFoundException;

import com.example.Taskmanagement.exception.UserNotFoundException;
import com.example.Taskmanagement.models.User;
import com.example.Taskmanagement.repo.UserRepo;

import java.util.Base64;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TokenUtil {
    private static final String SECRET_KEY = "this is sceret";

    public static String generateToken(User user) {
        try {
            long timestamp = (new Date()).getTime();
            String var10000 = user.getUserName();
            String data = var10000 + ":" + timestamp;
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec("this is sceret".getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKey);
            byte[] hash = sha256Hmac.doFinal(data.getBytes());
            String hashString = Base64.getEncoder().encodeToString(hash);
            String token = Base64.getEncoder().encodeToString((data + ":" + hashString).getBytes());
            return token;
        } catch (Exception var9) {
            throw new RuntimeException("Failed to generate token", var9);
        }
    }

    public static String[] decodeToken(String token) {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String decodedString = new String(decodedBytes);
        return decodedString.split(":");
    }

    public static User validateToken(String token, UserRepo userRepo) {
        try {
            String[] tokenData = decodeToken(token);
            String username = tokenData[0];
            long timestamp = Long.parseLong(tokenData[1]);
            String tokenHash = tokenData[2];
            User user = (User)userRepo.findByUserName(username).orElseThrow(() -> {
                return new UserNotFoundException("User not found");
            });
            String data = username + ":" + timestamp;
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec("this is sceret".getBytes(), "HmacSHA256");
            sha256Hmac.init(secretKey);
            byte[] hash = sha256Hmac.doFinal(data.getBytes());
            String recreatedHash = Base64.getEncoder().encodeToString(hash);
            return recreatedHash.equals(tokenHash) ? user : null;
        } catch (Exception var13) {
            System.out.print(var13);
            return null;
        }
    }
}
