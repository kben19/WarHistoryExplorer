/**
 * Created by benzali on 11/29/2018.
 */
import Stax.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View {
    private static Font myFont = new Font("Arial", Font.PLAIN, 24);

    private JFrame frame;
    private JTextField myTextField;
    private JTable table;

    View(){
        frame = new JFrame("War History Explorer");
        JPanel header = renderHeader();
        JPanel body = renderBody();
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
        String[] filterArray = {"Any", "Event", "Figure"};
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

    private JPanel renderBody(){
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());

        // Initialize table
        Driver myDriver = new Driver();
        myDriver.retrieveData("src/Stax/database/events.xml");
        table = new JTable(new DefaultTableModel(myDriver.getColumnNames(), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //make cells non-editable
                return false;
            }
        });

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    //allow only one selection at a time
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        JScrollPane js = new JScrollPane(table);    // Enable the table to be scrollable

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(js, BorderLayout.SOUTH);

        JTabbedPane myTabbedPane = new JTabbedPane();
        myTabbedPane.setFont(myFont);
        myTabbedPane.addTab("Event", tablePanel);

        body.add(myTabbedPane, BorderLayout.NORTH);


        return body;
    }

    private JPanel renderFooter(){
        JPanel footer = new JPanel();

        return footer;
    }

    // Close button listener
    public static class CloseListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            e.getWindow().setVisible(false);
            System.exit(0);
        }
    }

}
