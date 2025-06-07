/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.commonUI;

import java.util.List;
import java.util.function.Function;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 * @param <T>
 */

//This is a generic class
public class PaginatedTableUI<T>{
   private final JTable table;
    private final String[] columnNames;
    private final Function<T, String[]> rowMapper;
    private final int pageSize;
    private List<T> data;
    private int currentPage = 1;
    private int totalPages = 1;

    /*
        Note: 
        lambda expression syntax : (parameters) -> expression or block of code
        A lambda is just a shortcut way to write a function, especially when we want to pass that function as a value.
    
        Function<T, R> : Functional Interface | T -InputType, R - OutputType
        An interface with only one abstract method
            Example :
                @FunctionalInterface
                public interface Function<T, R> {
                    R apply(T t); //Means : Take an input of type T, do something with it, and return a result of type R.
                }
            
            Example :
                Function<T, String[]> rowMapper 
                //Function<T, String[]>	Interface for a function taking T: ObjResponseDTO and returning String[]
                //rowMapper : A variable holding that function (a lambda)       
    */
    
    public PaginatedTableUI(JTable table, String[] columnNames, Function<T, String[]> rowMapper, int pageSize) {
        this.table = table;
        this.columnNames = columnNames;
        this.rowMapper = rowMapper;
        this.pageSize = pageSize;
    }

    public void setData(List<T> data) {
        this.data = data; //Get List<ObjResponseDTO> dtos
        this.totalPages = (int) Math.ceil((double) data.size() / pageSize);
        this.currentPage = 1;
        updateTable();
    }

    private void updateTable() {
        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (data == null || data.isEmpty()) {
            dtm.addRow(new String[]{"", "No data found", ""});
        } else {
            int start = (currentPage - 1) * pageSize;
            int end = Math.min(start + pageSize, data.size());
            for (int i = start; i < end; i++) {
                /*
                    //Convert the i-th object into a String[] using the lambda, and add it to the table
                    //data.get(i) retrieves one object from the list.
                    //rowMapper.apply(): this calls the lambda function, passing that single object as the argument.
                */
                dtm.addRow(rowMapper.apply(data.get(i))); //apply() calls the lambda function:
            }
        }

        table.setModel(dtm);
        
        // Hide primary key column
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        
        // Set widths for visible columns
        table.getColumnModel().getColumn(1).setPreferredWidth(40);   // No column
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
            updateTable();
        }
    }

    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
            updateTable();
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    } 
}
