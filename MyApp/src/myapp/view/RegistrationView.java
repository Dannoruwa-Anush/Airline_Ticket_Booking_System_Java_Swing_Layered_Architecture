/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package myapp.view;

import java.util.Arrays;
import myapp.controller.UserController;
import myapp.dto.user.UserRequestDTO;
import myapp.controller.UserRoleController;
import myapp.dto.userRole.UserRoleResponseDTO;
import myapp.util.audit.AuditUtil;
import myapp.util.commonTask.CommonTask;
import myapp.util.commonUI.CommonMessageBoxUI;
import myapp.util.enums.UserRoleEnum;
import myapp.util.formValidation.FormValidationUtil;

/**
 *
 * @author HP
 */
public class RegistrationView extends javax.swing.JFrame {

    /**
     * Creates new form PassengerRegistrationView
     */
    private final UserController userController;
    private final UserRoleController userRoleController;

    public RegistrationView() {
        initComponents();

        userController = new UserController();
        userRoleController = new UserRoleController();
        
        clearForm();
        initializeUIState();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle1 = new javax.swing.JLabel();
        lblId1 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        btn_Register = new javax.swing.JButton();
        lblId5 = new javax.swing.JLabel();
        lblId6 = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();
        pwd_password = new javax.swing.JPasswordField();
        pwd_confirmPassword = new javax.swing.JPasswordField();
        lbl_toRegister = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lbl_copyright = new javax.swing.JLabel();
        lblId2 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblTitle1.setBackground(new java.awt.Color(204, 204, 204));
        lblTitle1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1.setText("Registration");

        lblId1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblId1.setText("Email :");

        btn_Register.setText("Register");
        btn_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterActionPerformed(evt);
            }
        });

        lblId5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblId5.setText("Confirm Password :");

        lblId6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblId6.setText("Password :");

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        pwd_password.setText("jPasswordField1");

        pwd_confirmPassword.setText("jPasswordField1");

        lbl_toRegister.setForeground(new java.awt.Color(0, 51, 255));
        lbl_toRegister.setText("link to login");
        lbl_toRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_toRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_toRegisterMouseClicked(evt);
            }
        });

        jLabel1.setText("Airline Ticket Booking System");

        lbl_copyright.setText("@C");

        lblId2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        lblId2.setText("Username :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_reset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Register))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblId1)
                                    .addComponent(lblId5)
                                    .addComponent(lblId6)
                                    .addComponent(lblId2))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_username)
                                    .addComponent(pwd_password)
                                    .addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(pwd_confirmPassword))))
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lbl_toRegister))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lbl_copyright)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblId2)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblId1)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblId6)
                            .addComponent(pwd_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(pwd_confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblId5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Register)
                    .addComponent(btn_reset))
                .addGap(18, 18, 18)
                .addComponent(lbl_toRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_copyright)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        registerPassenger();
    }//GEN-LAST:event_btn_RegisterActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        resetUIAfterOperation();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void lbl_toRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_toRegisterMouseClicked
        CommonTask.switchView(this, new LoginView());
    }//GEN-LAST:event_lbl_toRegisterMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PassengerRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PassengerRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PassengerRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PassengerRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PassengerRegistrationView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Register;
    private javax.swing.JButton btn_reset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblId1;
    private javax.swing.JLabel lblId2;
    private javax.swing.JLabel lblId5;
    private javax.swing.JLabel lblId6;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JLabel lbl_copyright;
    private javax.swing.JLabel lbl_toRegister;
    private javax.swing.JPasswordField pwd_confirmPassword;
    private javax.swing.JPasswordField pwd_password;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables

    //------------ [ Start - btn handle] -------------
    private void initCommonLabels() {
        lbl_copyright.setText("@Dannoruwa-Anush 2025");
        lbl_toRegister.setText("<html><u>Already have an account? Login</u></html>");
    }

    //-------------------||||||| Start : input field validation |||||||---------
    private boolean isInputFieldsValid() {
        String email = txt_email.getText().trim();
        String password = new String(pwd_password.getPassword()).trim();
        String confirmPassword = new String(pwd_confirmPassword.getPassword()).trim();

        StringBuilder errorMessage = new StringBuilder();

        if (!FormValidationUtil.isValidEmail(email)) {
            errorMessage.append("Invalid Email.\n");
        }

        if (!FormValidationUtil.isValidTxtField(password)) {
            errorMessage.append("Invalid Password.\n");
        }

        if (!FormValidationUtil.isValidTxtField(confirmPassword)) {
            errorMessage.append("Invalid Confirm password.\n");
        } else if (!FormValidationUtil.isContentMatch(password, confirmPassword)) {
            errorMessage.append("Passwords do not match.\n");
        }

        // Clear passwords immediately after validation
        Arrays.fill(pwd_password.getPassword(), '\0');
        Arrays.fill(pwd_confirmPassword.getPassword(), '\0');
        
        // If any validation error exists, show the accumulated errors in a single dialog
        if (errorMessage.length() > 0) {
            CommonMessageBoxUI.showOperationFailMessageBox(this, errorMessage.toString());
            return false;
        }

        return true;
    }
    //-------------------||||||| End : input field validation |||||||-----------

    //****** [Start : register/add] ***************
    private void registerPassenger() {
        if (!isInputFieldsValid()) {
            return;
        }
        try {

            UserRoleResponseDTO userRole = userRoleController.getUserRoleByName(UserRoleEnum.PASSENGER.getRoleName());
            UserRequestDTO userRequestDTO = new UserRequestDTO(
                    txt_username.getText().trim(),
                    txt_email.getText().trim(),
                    new String(pwd_password.getPassword()).trim(),
                    userRole.getRole_id()
            );
            String result = userController.addUser(userRequestDTO);
            
            // Clear passwords immediately after use
            Arrays.fill(pwd_password.getPassword(), '\0');
            Arrays.fill(pwd_confirmPassword.getPassword(), '\0');
            postOperationSuccess(result);
        } catch (Exception ex) {
            AuditUtil.logException(this.getClass(), ex);
            CommonMessageBoxUI.showOperationFailMessageBox(this, "Failed to register");
        }
    }
    //****** [End : register/add] ***************

    //****** [Start : Reset] ***************
    private void clearForm() {
        resetInputFields();
    }

    private void resetInputFields() {
        txt_email.setText("");
        pwd_password.setText("");
        pwd_confirmPassword.setText("");
    }

    private void initializeUIState() {
        initCommonLabels();
    }

    private void postOperationSuccess(String message) {
        CommonMessageBoxUI.showOperationSuccessMessageBox(this, message);
        resetUIAfterOperation();
       
        //navigate to login page
        CommonTask.switchView(this, new LoginView());
    }

    private void resetUIAfterOperation() {
        clearForm();
        initializeUIState();
    }
    //****** [End : Reset] ***************
    //------------ [ End - btn handle] ---------------
}
