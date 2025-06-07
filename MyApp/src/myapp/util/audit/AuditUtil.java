/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.audit;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class AuditUtil {
    
    public static void logException(Class<?> sourceClass, Exception ex) {
        Logger.getLogger(sourceClass.getName()).log(Level.SEVERE, null, ex);
    }
}
