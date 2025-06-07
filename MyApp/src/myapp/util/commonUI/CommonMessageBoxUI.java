/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.commonUI;

import java.awt.Component;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import myapp.util.commonTask.CommonTask;
import myapp.view.user.ReservationUserView;

/**
 *
 * @author HP
 */
public class CommonMessageBoxUI {

    // Confirmation message box
    public static boolean showOkCancelConfirmationMessageBox(Component parent, String message, String title) {
        int option = JOptionPane.showConfirmDialog(
                parent,
                message,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );
        return option == JOptionPane.OK_OPTION;
    }

    // Operation success message box
    public static void showOperationSuccessMessageBox(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Operation fail message box
    public static void showOperationFailMessageBox(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Booking completion message box
    public static void showBookingSuccessMessageBox(Component parent, String userName, int userId, int passengerId) {
        String htmlMessage = "<html>Your airline ticket has been successfully booked.<br><br>"
                + "<a style='color: blue; text-decoration: underline;' href='#'>Click here</a> for more booking information.</html>";

        JEditorPane messagePane = new JEditorPane("text/html", htmlMessage);
        messagePane.setEditable(false);
        messagePane.setOpaque(false);
        messagePane.setBorder(null);
        messagePane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);

        messagePane.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                JFrame currentFrame = getParentJFrame(parent);
                CommonTask.switchView(currentFrame, new ReservationUserView(userName, userId, passengerId));
            }
        });

        // Show custom option dialog to detect button click
        int result = JOptionPane.showConfirmDialog(
                parent,
                messagePane,
                "Success",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
            JFrame currentFrame = getParentJFrame(parent);
            CommonTask.switchView(currentFrame, new ReservationUserView(userName, userId, passengerId));
        }
    }

    // Helper to ensure we always get the parent JFrame safely
    private static JFrame getParentJFrame(Component parent) {
        if (parent instanceof JFrame jFrame) {
            return jFrame;
        }

        java.awt.Window window = SwingUtilities.getWindowAncestor(parent);
        if (window instanceof JFrame jFrame) {
            return jFrame;
        }

        System.err.println("Warning: Unable to determine parent JFrame. Using fallback.");
        return null; // fallback case
    }
}
