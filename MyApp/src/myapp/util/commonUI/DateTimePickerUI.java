/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.commonUI;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author HP
 */
public class DateTimePickerUI {

    // Date and time picker
    public static LocalDateTime showDateTimePickerDialog(String title) {
        JDialog dialog = new JDialog((Frame) null, title, true);

        DateTimePicker dateTimePicker = new DateTimePicker();
        DatePicker datePicker = dateTimePicker.getDatePicker();
        TimePicker timePicker = dateTimePicker.getTimePicker();

        // Disable past dates
        datePicker.getSettings().setVetoPolicy(new FutureDateVetoPolicy());

        // Date panel
        JPanel datePanel = new JPanel(new BorderLayout(5, 5));
        datePanel.add(new JLabel("Select Date:"), BorderLayout.NORTH);
        datePanel.add(datePicker, BorderLayout.CENTER);

        // Time panel
        JPanel timePanel = new JPanel(new BorderLayout(5, 5));
        timePanel.add(new JLabel("Select Time:"), BorderLayout.NORTH);
        timePanel.add(timePicker, BorderLayout.CENTER);

        // Picker panel
        JPanel pickerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        pickerPanel.add(datePanel);
        pickerPanel.add(timePanel);

        // OK button
        JButton okButton = new JButton("OK");
        final LocalDateTime[] selectedDateTime = {null};
        okButton.addActionListener(e -> {
            LocalDateTime selected = dateTimePicker.getDateTimeStrict();
            if (selected != null && selected.isBefore(LocalDateTime.now())) {
                JOptionPane.showMessageDialog(dialog,
                        "Please select a future date and time.",
                        "Invalid Selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            selectedDateTime[0] = selected;
            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(okButton, BorderLayout.EAST);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        contentPanel.add(pickerPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(contentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return selectedDateTime[0];
    }

    // Time picker
    public static LocalTime showTimePickerDialog(String title) {
        JDialog dialog = new JDialog((Frame) null, title, true);

        TimePicker timePicker = new TimePicker();

        // Time panel
        JPanel timePanel = new JPanel(new BorderLayout(5, 5));
        timePanel.add(new JLabel("Select Time:"), BorderLayout.NORTH);
        timePanel.add(timePicker, BorderLayout.CENTER);

        // OK button
        JButton okButton = new JButton("OK");
        final LocalTime[] selectedTime = {null};
        okButton.addActionListener(e -> {
            selectedTime[0] = timePicker.getTime();
            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(okButton, BorderLayout.EAST);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        contentPanel.add(timePanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.setPreferredSize(new Dimension(400, 150));
        dialog.setContentPane(contentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return selectedTime[0];
    }

    // Date picker
    public static LocalDate showDatePickerDialog(String title) {
        JDialog dialog = new JDialog((Frame) null, title, true);

        DatePicker datePicker = new DatePicker();
        // Disable past dates
        datePicker.getSettings().setVetoPolicy(new FutureDateVetoPolicy());

        // Date panel
        JPanel datePanel = new JPanel(new BorderLayout(5, 5));
        datePanel.add(new JLabel("Select Date:"), BorderLayout.NORTH);
        datePanel.add(datePicker, BorderLayout.CENTER);

        // OK button
        JButton okButton = new JButton("OK");
        final LocalDate[] selectedDate = {null};
        okButton.addActionListener(e -> {
            selectedDate[0] = datePicker.getDate();
            dialog.dispose();
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(okButton, BorderLayout.EAST);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        contentPanel.add(datePanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.setPreferredSize(new Dimension(400, 150));
        dialog.setContentPane(contentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return selectedDate[0];
    }

    // Custom veto policy to disable past dates
    private static class FutureDateVetoPolicy implements DateVetoPolicy {
        @Override
        public boolean isDateAllowed(LocalDate date) {
            return !date.isBefore(LocalDate.now());
        }
    }
}
