/**
 * Created by benzali on 11/29/2018.
 */
import Model.*;
import ObserverPackage.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class View implements Observer{
    private static Font myFont = new Font("Arial", Font.PLAIN, 24);
    private static String[] comboBoxArray = {"Event", "Figure"};

    private JFrame frame;
    private JTextField myTextField;
    private JTable eventTable, figureTable;
    private Controller myController;
    private List<JComponent> footerTextField = new ArrayList<JComponent>();

    View(Model myModel, Controller controller){
        myController = controller;

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
        frame.setMinimumSize(new Dimension(700, 900));
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
        JComboBox filterComboBox = new JComboBox(comboBoxArray);
        filterComboBox.setFont(myFont);
        buttonHeader.add(filterComboBox);

        //Add search button
        Button searchButton = new Button("Search");
        searchButton.setFont(myFont);
        searchButton.addActionListener(myController);
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
        buttonBody.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 1));
        Button addButton = new Button("Add");
        Button delButton = new Button("Delete");
        Button showButton = new Button("Show");

        addButton.setFont(myFont);
        delButton.setFont(myFont);
        showButton.setFont(myFont);

        addButton.addActionListener(myController);
        delButton.addActionListener(myController);
        showButton.addActionListener(myController);

        buttonBody.add(addButton);
        buttonBody.add(delButton);
        buttonBody.add(showButton);

        body.add(buttonBody, BorderLayout.SOUTH);

        return body;
    }

    private JPanel renderFooter(){
        JPanel footer = new JPanel();
        footer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JTextField titleField = new JTextField();
        titleField.setFont(new Font("Arial", Font.BOLD, 32));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 0.5;
        footer.add(titleField, c);

        JTextArea descriptionArea = new JTextArea(5, 0);
        descriptionArea.setFont(myFont);
        descriptionArea.setLineWrap(true);
        JScrollPane jsArea = new JScrollPane(descriptionArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weightx = 0.5;
        footer.add(jsArea, c);

        JLabel icon = new JLabel();
        icon.setSize(200, 250);
        ImageIcon img = new ImageIcon(
                new ImageIcon("src/800px-Sir_Winston_Churchill_-_19086236948.jpg").getImage().getScaledInstance(icon.getWidth(), icon.getHeight(), Image.SCALE_SMOOTH)
        );
        img.setDescription("src/800px-Sir_Winston_Churchill_-_19086236948.jpg");
        icon.setIcon(img);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.5;
        footer.add(icon, c);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());

        JLabel label0 = new JLabel("Type:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        infoPanel.add(label0, c);

        JLabel label1 = new JLabel("Date:");
        c.gridx = 0;
        c.gridy = 1;
        infoPanel.add(label1, c);

        JLabel label2 = new JLabel("Location:");
        c.gridy = 2;
        infoPanel.add(label2, c);

        JLabel label3 = new JLabel("Party:");
        c.gridy = 3;
        infoPanel.add(label3, c);

        JComboBox comboBox0 = new JComboBox(comboBoxArray);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        infoPanel.add(comboBox0, c);

        JTextField field1 = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        infoPanel.add(field1, c);

        JTextField field2 = new JTextField();
        c.gridy = 2;
        infoPanel.add(field2, c);

        JTextField field3 = new JTextField();
        c.gridy = 3;
        infoPanel.add(field3, c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        footer.add(infoPanel, c);

        //Store all JTextField values inside array list
        footerTextField.add(titleField);
        footerTextField.add(descriptionArea);
        footerTextField.add(icon);
        footerTextField.add(label0);
        footerTextField.add(label1);
        footerTextField.add(label2);
        footerTextField.add(label3);
        footerTextField.add(comboBox0);
        footerTextField.add(field1);
        footerTextField.add(field2);
        footerTextField.add(field3);

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
        js.setPreferredSize(new Dimension(frame.getWidth(), 250));
        js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

        tablePanel.add(js, BorderLayout.CENTER);

        return tablePanel;
    }

    public void update(Vector<Vector<String>> anObject){
        DefaultTableModel model = new DefaultTableModel();

        if (anObject.get(0).get(0).contains("event")) {
            model = (DefaultTableModel) eventTable.getModel();
        }
        else if (anObject.get(0).get(0).contains("figure")){
            model = (DefaultTableModel) figureTable.getModel();
        }

        //Reset the table
        model.setRowCount(0);

        for (Vector<String> alist : anObject){
            model.addRow(alist);
        }
    }

    public List<String> getFooterValues(){
        int counter = 0;
        List<String> outputList = new ArrayList<String>();
        for(int i = 0; i < footerTextField.size(); i++){
            if(i == 1){
                JTextArea temp = (JTextArea) footerTextField.get(i);
                outputList.add(temp.getText());
            }
            else if (i == 2){
                JLabel temp = (JLabel) footerTextField.get(i);
                ImageIcon icon = (ImageIcon) temp.getIcon();
                outputList.add(icon.getDescription());
            }
            else if(i > 3 && i <= 6){
                JLabel temp = (JLabel) footerTextField.get(i);
                outputList.add(temp.getText());
            }
            else if(i == 0 || i >= 8) {
                JTextField temp = (JTextField) footerTextField.get(i);
                outputList.add(temp.getText());
            }
            else if(i == 7){
                JComboBox temp = (JComboBox) footerTextField.get(i);
                outputList.add(temp.getSelectedItem().toString().toLowerCase());
            }
        }

        return outputList;
    }


}
