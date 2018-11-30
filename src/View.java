/**
 * Created by benzali on 11/29/2018.
 */
import Model.Model;
import Model.DataType;
import ObserverPackage.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.annotation.Inherited;
import java.util.List;
import java.util.Vector;

public class View implements Observer{
    private static Font myFont = new Font("Arial", Font.PLAIN, 24);

    private JFrame frame;
    private JTextField myTextField;
    private JTable eventTable, figureTable;

    View(Model myModel){
        frame = new JFrame("War History Explorer");
        JPanel header = renderHeader();
        JPanel body = renderBody(myModel);
        JPanel footer = renderFooter();

        frame.add(header, BorderLayout.NORTH);
        frame.add(body, BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);

        //Add window close button listener
        frame.addWindowListener(new CloseListener());

        //View Log
        System.out.println("View initialized");

        //Set frame properties
        frame.setSize(900,900);
        frame.setMinimumSize(new Dimension(500, 800));
        frame.setLocation(100,100);
        frame.setVisible(true);

    }

    private JPanel renderHeader(){

        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        //Add search text field
        myTextField = new JTextField();
        myTextField.setFont(myFont);
        myTextField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(0, 15, 0, 15),
                        myTextField.getBorder()
                )
        );
        header.add(myTextField, BorderLayout.NORTH);

        JPanel buttonHeader = new JPanel();
        buttonHeader.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 1));

        //Add Filter Combo Box
        String[] filterArray = {"Event", "Figure"};
        JComboBox filterComboBox = new JComboBox(filterArray);
        filterComboBox.setFont(myFont);
        buttonHeader.add(filterComboBox);

        //Add search button
        Button searchButton = new Button("Search");
        searchButton.setFont(myFont);
        buttonHeader.add(searchButton);

        header.add(buttonHeader, BorderLayout.SOUTH);

        return header;
    }

    private JPanel renderBody(Model myModel){
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());

        JPanel tablePanel1 = createTable(myModel, DataType.EVENT);
        JPanel tablePanel2 = createTable(myModel, DataType.FIGURE);

        JTabbedPane myTabbedPane = new JTabbedPane();
        myTabbedPane.setFont(myFont);
        myTabbedPane.addTab("Event", tablePanel1);
        myTabbedPane.addTab("Figure", tablePanel2);

        body.add(myTabbedPane, BorderLayout.NORTH);

        JPanel buttonBody = new JPanel();
        Button addButton = new Button("add");
        Button delButton = new Button("delete");
        Button showButton = new Button("show");

        addButton.setFont(myFont);
        delButton.setFont(myFont);
        showButton.setFont(myFont);

        buttonBody.add(addButton);
        buttonBody.add(delButton);
        buttonBody.add(showButton);

        body.add(buttonBody, BorderLayout.SOUTH);

        return body;
    }

    private JPanel renderFooter(){
        JPanel footer = new JPanel();

        return footer;
    }

    // Close button listener
    private static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

    private JPanel createTable(Model myModel, DataType type){
        // Initialize table
        JTable table = new JTable(new DefaultTableModel(myModel.getColumnNames(type), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //make cells non-editable
                return false;
            }
        });

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    //allow only one selection at a time
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        if(type.equals(DataType.EVENT)){
            eventTable = table;
        }
        else if (type.equals(DataType.FIGURE)){
            figureTable = table;
        }

        JScrollPane js = new JScrollPane(table);    // Enable the table to be scrollable

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(js, BorderLayout.SOUTH);

        return tablePanel;
    }

    public void update(Vector<Vector<String>> anObject){
        DefaultTableModel model = new DefaultTableModel();
        if (anObject.get(0).get(0).contains("event")) {
            model = (DefaultTableModel) eventTable.getModel();
        }
        else{
            model = (DefaultTableModel) figureTable.getModel();
        }

        for (Vector<String> alist : anObject){
            model.addRow(alist);
        }
    }

    public void setVisible(boolean input){
        frame.setVisible(input);
    }

}
