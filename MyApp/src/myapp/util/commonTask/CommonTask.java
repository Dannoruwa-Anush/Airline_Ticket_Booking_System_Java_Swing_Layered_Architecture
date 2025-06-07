/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.commonTask;

import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class CommonTask {

    public static void switchView(JFrame currentView, JFrame nextView) {
        System.out.println("Redirecting to: " + nextView.getClass().getSimpleName());

        if (currentView != null) {
            currentView.dispose();
        } else {
            System.err.println("Warning: currentView is null, cannot dispose.");
        }

        nextView.setVisible(true);
        nextView.setLocationRelativeTo(null);
    }
}
