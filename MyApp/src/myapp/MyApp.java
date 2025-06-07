/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package myapp;

import myapp.view.LoginView;

/**
 *
 * @author Dannoruwa-Anush
 */
public class MyApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.setLocationRelativeTo(null); // center it
        loginView.setVisible(true);            // then show it
    }
    
}
