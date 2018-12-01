/**
 * Created by benzali on 11/29/2018.
 */
import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

    private void searchListener(){
        System.out.println(view.getSelectedTab());
    }

    private void addListener(){
        //Adding constraint if only if title and description are not empty
        if(!view.getFooterValues().get(0).equals("") && !view.getFooterValues().get(1).equals("")) {
            model.addData(view.getFooterValues());
            view.resetFooter();
            System.out.println("Controller: database added");
        }
    }

    private void deleteListener(){
        //check if an item is properly selected
        if(!view.getSelectedID().equals("")) {
            model.deleteData(view.getSelectedID());
            view.resetFooter();
            System.out.println("Controller: database deleted");
        }
    }

    private void selectListener(){
        JTable myTable = view.getTable(view.getSelectedTab());
        if(myTable.getSelectedRow() != -1) {

            int columnCount = myTable.getColumnCount();
            int row = myTable.getSelectedRow();
            DataType type = null;
            List<String> rowData = new ArrayList<String>();
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
