/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author HP
 */
public class SecurityUtil {
     public static String hashPassword(String password) {
        try {
            // Get SHA-256 MessageDigest instance
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            
            // Hash the password
            byte[] hashBytes = messageDigest.digest(password.getBytes());
            
            // Convert the hash bytes to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            
            return hexString.toString();  // Return the hashed password as a hex string
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
