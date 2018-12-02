/**
 * Created by benzali on 11/29/2018.
 */
import Model.*;
import ObserverPackage.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class View implements Observer{
    private static Font myFont = new Font("Arial", Font.PLAIN, 24);
    private static String[] comboBoxArray = {"Event", "Figure"};
    private static String noImagePath = "Resource/noimage.png";

    private JFrame frame;
    private JTextField searchTextField;
    private JTable eventTable, figureTable;
    private List<String> eventPic = new ArrayList<>();
    private List<String> figurePic = new ArrayList<>();
    private JTabbedPane myTabbedPane;
    private Controller myController;
    private List<JComponent> footerTextField = new ArrayList<JComponent>();
    private JComboBox filterComboBox;
    private Button addButton;
    private int selectedIndex = -1;

    //View Constructor
    View(Model myModel, Controller controller){
        myController = controller;

        frame = new JFrame("War History Explorer");
        JPanel header = renderHeader();     //Initialize header panel
        JPanel body = renderBody(myModel);  //Initialize body panel
        JPanel footer = renderFooter();     //Initialize footer panel

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

    //Header panel consist of search text field and search button
    private JPanel renderHeader(){

        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        //Add search text field
        searchTextField = new JTextField();
        searchTextField.setFont(myFont);
        searchTextField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(0, 15, 0, 15),
                        searchTextField.getBorder()
                )
        );
        header.add(searchTextField, BorderLayout.NORTH);

        JPanel buttonHeader = new JPanel();
        buttonHeader.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 1));

        //Add Filter Combo Box
        filterComboBox = new JComboBox(comboBoxArray);
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

    //Body panel consist of tabbed panel with all the database table and interaction buttons
    private JPanel renderBody(Model myModel){
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());

        //Create tables for each database type
        JPanel tablePanel1 = createTable(myModel, DataType.EVENT);
        JPanel tablePanel2 = createTable(myModel, DataType.FIGURE);

        //Initialize tabbed panel
        myTabbedPane = new JTabbedPane();
        myTabbedPane.setFont(myFont);
        myTabbedPane.addTab("Event", tablePanel1);
        myTabbedPane.addTab("Figure", tablePanel2);

        body.add(myTabbedPane, BorderLayout.NORTH);

        //Create buttons
        JPanel buttonBody = new JPanel();
        buttonBody.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 1));
        addButton = new Button("Add");
        Button delButton = new Button("Delete");
        Button resetButton = new Button("Reset");
        Button selectButton = new Button("Select");

        addButton.setFont(myFont);
        delButton.setFont(myFont);
        resetButton.setFont(myFont);
        selectButton.setFont(myFont);

        addButton.addActionListener(myController);
        delButton.addActionListener(myController);
        resetButton.addActionListener(myController);
        selectButton.addActionListener(myController);

        buttonBody.add(addButton);
        buttonBody.add(delButton);
        buttonBody.add(resetButton);
        buttonBody.add(selectButton);

        body.add(buttonBody, BorderLayout.SOUTH);

        return body;
    }

    //Footer panel consist of text fields and labels showing information from selected row in database
    private JPanel renderFooter(){
        //Footer panel using Grid Bag Layout
        JPanel footer = new JPanel();
        footer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Title text field
        JTextField titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(600, 40));
        titleField.setFont(new Font("Arial", Font.BOLD, 32));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 0.1;
        footer.add(titleField, c);

        //Description Text Area
        JTextArea descriptionArea = new JTextArea(5, 0);
        descriptionArea.setFont(myFont);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane jsArea = new JScrollPane(descriptionArea);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        footer.add(jsArea, c);

        //Label picture icon
        JLabel icon = new JLabel();
        ImageIcon img = new ImageIcon(
                new ImageIcon(noImagePath).getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH)
        );
        img.setDescription(noImagePath);
        icon.setIcon(img);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weighty = 0.2;
        footer.add(icon, c);

        //Add another JPanel Grid Bag Layout inside the footer panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());

        //Label Type
        JLabel label0 = new JLabel("Type:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        infoPanel.add(label0, c);

        //Label Date/Dob
        JLabel label1 = new JLabel("Date:");
        c.gridx = 0;
        c.gridy = 1;
        infoPanel.add(label1, c);

        //Label Location/Country
        JLabel label2 = new JLabel("Location:");
        c.gridy = 2;
        infoPanel.add(label2, c);

        //Label Party/Role
        JLabel label3 = new JLabel("Party:");
        c.gridy = 3;
        infoPanel.add(label3, c);

        //ComboBox Type
        JComboBox comboBox0 = new JComboBox(comboBoxArray);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        infoPanel.add(comboBox0, c);

        //TextField Date/Dob
        JTextField field1 = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        infoPanel.add(field1, c);

        //TextField Location/Country
        JTextField field2 = new JTextField();
        c.gridy = 2;
        infoPanel.add(field2, c);

        //TextField Party/Role
        JTextField field3 = new JTextField();
        field3.setPreferredSize(new Dimension(100, 20));
        c.gridy = 3;
        infoPanel.add(field3, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        footer.add(infoPanel, c);

        //Store all JTextField values inside array list
        footerTextField.add(titleField);        //Title 0
        footerTextField.add(descriptionArea);   //Desc  1
        footerTextField.add(icon);              //Icon  2
        footerTextField.add(label0);            //Type  3
        footerTextField.add(label1);            //Date  4
        footerTextField.add(label2);            //Loc   5
        footerTextField.add(label3);            //Party 6
        footerTextField.add(comboBox0);         //Box   7
        footerTextField.add(field1);            //DateF 8
        footerTextField.add(field2);            //LocF  9
        footerTextField.add(field3);            //PartyF10

        return footer;
    }

    // Close button listener
    private static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

    //Method for creating table dynamically based on model and database type
    private JPanel createTable(Model myModel, DataType type){

        // Initialize table
        JTable table = new JTable(new DefaultTableModel(myModel.getColumnNames(type), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //make cells non-editable
                return false;
            }
        });

        //Table properties
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    //allow only one selection at a time
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setAutoCreateRowSorter(true);

        //Assigning View Table based on database type
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

        //Combining table header(columns) and table data
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);

        tablePanel.add(js, BorderLayout.CENTER);

        return tablePanel;
    }

    //Inherited from Observer Interface
    //Update all View tables given the updated tables from model
    public void update(Vector<Vector<String>> anObject){
        DefaultTableModel model = new DefaultTableModel();
        List<String> modelPic = new ArrayList<>();

        if (anObject.get(0).get(0).contains("event")) {
            model = (DefaultTableModel) eventTable.getModel();
            modelPic = eventPic;
        }
        else if (anObject.get(0).get(0).contains("figure")){
            model = (DefaultTableModel) figureTable.getModel();
            modelPic = figurePic;
        }

        //Reset the table
        model.setRowCount(0);
        modelPic.clear();

        for (Vector<String> alist : anObject){
            modelPic.add(alist.get(alist.size()-1));
            model.addRow(alist);
        }
    }

    //Get all the footer panel TextFields, TextArea, and label values
    //Output Composition:
    // [0.Title,
    //  1.Description,
    //  2.icon source,
    //  3.Label Date/Dob,
    //  4.Label Location/Country,
    //  5.Label Party/Role,
    //  6.database type
    //  7.Date/Dob value
    //  8.Location/Country value
    //  9.Party/Role value]
    public List<String> getFooterValues(){
        int counter = 0;
        List<String> outputList = new ArrayList<String>();
        for(int i = 0; i < footerTextField.size(); i++){
            if(i == 1){         //For Text Area
                JTextArea temp = (JTextArea) footerTextField.get(i);
                outputList.add(temp.getText());
            }
            else if (i == 2){   //For Icon
                JLabel temp = (JLabel) footerTextField.get(i);
                ImageIcon icon = (ImageIcon) temp.getIcon();
                outputList.add(icon.getDescription());
            }
            else if(i > 3 && i <= 6){   //For Label
                JLabel temp = (JLabel) footerTextField.get(i);
                outputList.add(temp.getText());
            }
            else if(i == 0 || i >= 8) { //ForText Field
                JTextField temp = (JTextField) footerTextField.get(i);
                outputList.add(temp.getText());
            }
            else if(i == 7){            //For Combo Box
                JComboBox temp = (JComboBox) footerTextField.get(i);
                outputList.add(temp.getSelectedItem().toString().toLowerCase());
            }
        }
        return outputList;
    }

    public int getSelectedIndex(){      return selectedIndex;       }

    public void setSelectedIndex(int input){      selectedIndex = input;     }

    public int getSelectedTab(){    return myTabbedPane.getSelectedIndex();     }

    public Button getAddButton(){   return addButton;   }

    public JTextField getSearchTextField(){   return searchTextField;     }

    public JComboBox getFilterComboBox(){   return filterComboBox;      }

    public JTable getTable(int value){
        if (value == 0){
            return eventTable;
        }
        else if (value == 1){
            return figureTable;
        }
        return null;
    }

    //Reset footer value
    public void resetFooter(){
        for(int i = 0; i < footerTextField.size(); i++){
            if ((i >= 0 && i <= 1) || (i >=8 && i <= 10)){
                JTextComponent component = (JTextComponent) footerTextField.get(i);
                component.setText("");
            }
            if(i==2){
                JLabel label = (JLabel) footerTextField.get(i);
                ImageIcon img = new ImageIcon(
                        new ImageIcon(noImagePath).getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH)
                );
                img.setDescription(noImagePath);
                label.setIcon(img);
            }
        }

    }

    //Footer panel mutator
    public void setFooterValues(String[] columnNames, List<String> inputData){
        String typeInput = inputData.get(0).toUpperCase();
        DataType type = null;
        List<String> itemIcon = new ArrayList<>();
        JTable table = new JTable();
        if(typeInput.contains(DataType.EVENT.toString())){
            type = DataType.EVENT;
            itemIcon = eventPic;
            table = eventTable;
        }
        else if(typeInput.contains(DataType.FIGURE.toString())){
            type = DataType.FIGURE;
            itemIcon = figurePic;
            table = figureTable;
        }

        for(int i = 0; i < footerTextField.size(); i++){
            if(i == 1){
                JTextArea temp = (JTextArea) footerTextField.get(i);
                temp.setText(inputData.get(5));
            }
            else if (i == 2){
                JLabel temp = (JLabel) footerTextField.get(i);
                String src = itemIcon.get(table.getSelectedRow());
                if(src.equals("null")){
                    src = noImagePath;
                }
                ImageIcon img = new ImageIcon(
                        new ImageIcon(src).getImage().getScaledInstance(200, 250, Image.SCALE_SMOOTH)
                );
                img.setDescription(src);
                temp.setIcon(img);
            }
            else if(i > 3 && i <= 6){
                JLabel temp = (JLabel) footerTextField.get(i);
                temp.setText(columnNames[i - 2]);
            }
            else if(i == 0 || i >= 8) {
                JTextField temp = (JTextField) footerTextField.get(i);
                if(i == 0){
                    temp.setText(inputData.get(1));
                }
                else {
                    temp.setText(inputData.get(i - 6));
                }
            }
            else if(i == 7){
                JComboBox temp = (JComboBox) footerTextField.get(i);

                if(type.equals(DataType.EVENT)){
                    temp.setSelectedIndex(0);
                }
                else if(type.equals(DataType.FIGURE)){
                    temp.setSelectedIndex(1);
                }
            }
        }

    }


}
