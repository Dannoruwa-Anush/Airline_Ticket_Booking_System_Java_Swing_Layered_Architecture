/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.formValidation;

/**
 *
 * @author HP
 */
public class FormValidationUtil {

    // Email validation
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    // Text field non-empty validation
    public static boolean isValidTxtField(String txtFieldContent) {
        return txtFieldContent != null && !txtFieldContent.trim().isEmpty();
    }

    // Phone number validation (10 digits)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String phoneRegex = "^[0-9]{10}$";
        return phoneNumber.matches(phoneRegex);
    }

    // ID selection (In dropdown) validation
    public static boolean isIdSelectionValid(int selectedId) {
        return selectedId > 0;
    }

    // Content match validation
    public static boolean isContentMatch(String content1, String content2) {
        if (content1 == null || content2 == null) {
            return false;
        }
        return content1.equals(content2);
    }

    // Checks if two IDs are distinct and non-negative
    public static boolean areIdsDistinct(int id1, int id2) {
        if (id1 <= 0 || id2 <= 0) {
            return false;
        }
        return id1 != id2;
    }
}
