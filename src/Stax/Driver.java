package Stax;

import Stax.datastructure.Item;

import java.util.List;
import java.util.Vector;

/**
 * Created by benzali on 11/29/2018.
 */
public class Driver {
    private List<Item> data;

    public List<Item> retrieveData(String file) {
        StaXParser read = new StaXParser();
        data = read.readConfig(file);
        return data;
    }

    public Vector<Vector<String>> retrieveDataVector(String file){
        Vector<Vector<String>> table =  new Vector<Vector<String>>();
        retrieveData(file);
        for (Item item : data){
            Vector<String> row;

            row = getSpecifiedData(item, 1);
            table.add(row);
        }
        return table;
    }

    public String[] retrieveColumnNames(String file) {
        String[] columnNamesArray = null;

        retrieveData(file);
        Vector<String> columnNames;
        if (!data.isEmpty()) {

            Item anitem = data.get(0);
            columnNames = getSpecifiedData(anitem, 0);
            columnNamesArray = new String[columnNames.size()];
            columnNamesArray = columnNames.toArray(columnNamesArray);
        }

        return columnNamesArray;
    }

    public void saveData(String file, List<Item> itemList) {
        StaXWriter write = new StaXWriter();
        write.setFile(file);
        try {
            write.saveConfig(itemList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printItems() {
        for (Item item : data) {
            System.out.println(item);
        }
    }

    private Vector<String> getSpecifiedData(Item anItem, int value){
        Vector<String> dataList = new Vector<String>();

        while (anItem.hasNextData() && !dataList.contains(anItem.nextData())) {
            anItem.setCounter(anItem.getCounter() - 1);
            if (anItem.getCounter() % 2 == value) {
                dataList.add(anItem.nextData());
            }
            anItem.nextData();
        }
        anItem.setCounter(0);
        return dataList;
    }

}
