/**
 * Created by benzali on 11/29/2018.
 */
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Controller implements java.awt.event.ActionListener{
    private Model model;
    private View view;

    public Controller(){

        System.out.println("Controller initialized");
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){
        if (e.getActionCommand().equals("Search")){
            searchListener();
        }
        else if(e.getActionCommand().equals("Add")){
            addListener();
        }
        else if(e.getActionCommand().equals("Delete")){
            deleteListener();
        }
        else if(e.getActionCommand().equals("Reset")){
            resetListener();
        }
        else if(e.getActionCommand().equals("Select")){
            selectListener();
        }
    }

    public void addModel(Model amodel){
        this.model = amodel;
        System.out.println("Controller: connected to model");
    }

    public void addView(View aview){
        this.view = aview;
        System.out.println("Controller: connected to view");
    }

    //Search button listener
    public void searchListener(){
        String text = view.getSearchTextField().getText().toLowerCase();
        if (!text.equals("")) {
            String type = view.getFilterComboBox().getSelectedItem().toString().toLowerCase();
            Vector<Vector<String>> modelTable = model.getData(type);    //Get the table from model
            Vector<Integer> matchedRows = new Vector<>();
            int columnCount = modelTable.get(0).size();
            int rowCount = modelTable.size();

            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    if(modelTable.get(row).get(column).toLowerCase().contains(text)){
                        matchedRows.add(row);
                        break;
                    }
                }
            }

            JTable myTable = view.getTable(view.getFilterComboBox().getSelectedIndex());
            DefaultTableModel tableModel = (DefaultTableModel) myTable.getModel();
            tableModel.setRowCount(0);  //Reset the table
            for (Integer row : matchedRows) {
                tableModel.addRow(modelTable.get(row)); //Add the filtered rows to the table
            }
        }
        else{   //If empty, reload the table from model
            model.updateData();
        }
    }

    //Add button listener
    private void addListener(){
        //Adding constraint if only if title and description are not empty
        if(!view.getFooterValues().get(0).equals("") && !view.getFooterValues().get(1).equals("")) {
            model.addData(view.getFooterValues());
            view.setSelectedIndex(-1);
            view.resetFooter();
            System.out.println("Controller: database added");
        }
    }

    //Delete button listener
    private void deleteListener(){
        //check if an item is properly selected
        int selectedTab = view.getSelectedTab();
        DataType type = null;
        if (selectedTab == 0){
            type = DataType.EVENT;
        }
        else if(selectedTab == 1){
            type = DataType.FIGURE;
        }
        if(view.getSelectedIndex() != -1) {
            model.deleteData(type, view.getSelectedIndex());
            view.resetFooter();
            view.setSelectedIndex(-1);
            System.out.println("Controller: database deleted");
        }
    }

    //reset button listener
    private void resetListener(){
        view.setSelectedIndex(-1);
        view.resetFooter();
    }

    //select button listener
    private void selectListener(){
        JTable myTable = view.getTable(view.getSelectedTab());
        if(myTable.getSelectedRow() != -1) {

            int columnCount = myTable.getColumnCount();
            int row = myTable.getSelectedRow();
            view.setSelectedIndex(row);
            DataType type = null;
            List<String> rowData = new ArrayList<>();
            for (int i = 0; i < columnCount; i++) {
                String value = myTable.getValueAt(row, i).toString();
                rowData.add(value);
                if (i == 0){
                    if (value.contains("event")){
                        type = DataType.EVENT;
                    }
                    else if(value.contains("figure")){
                        type = DataType.FIGURE;
                    }
                }
            }
            view.setFooterValues(model.getColumnNames(type), rowData);
        }
    }


}
