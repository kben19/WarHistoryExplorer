/**
 * Created by benzali on 11/29/2018.
 */

package Stax;

import Stax.datastructure.*;

import java.util.List;
import java.util.Vector;

public class Driver {
    private List<Item> data;

    public List<Item> retrieveData(String file) {
        StaXParser read = new StaXParser();
        data = read.readConfig(file);
        return data;
    }

    public Vector<Vector<String>> retrieveDataVector(String file){
        Vector<Vector<String>> table =  new Vector<>();
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
            columnNames.remove(columnNames.size()-1);   //Remove the icon names
            columnNamesArray = new String[columnNames.size()];
            columnNamesArray = columnNames.toArray(columnNamesArray);
        }

        return columnNamesArray;
    }

    public void addData(String file, List<String> inputList){
        Item newItem = null;
        if (file.contains("event")) {
            newItem = new Event();
            Event event = (Event) newItem;
            event.setId("event" + (Integer.parseInt(inputList.get(6)) + 1));
            event.setName(inputList.get(0));
            event.setDate(inputList.get(3));
            event.setLocation(inputList.get(4));
            event.setParty(inputList.get(5));
            event.setDescription(inputList.get(1));
            event.setIcon(inputList.get(2));
        }
        else if(file.contains("figure")){
            newItem = new Figure();
            Figure figure = (Figure) newItem;
            figure.setId("figure" + (Integer.parseInt(inputList.get(6)) + 1));
            figure.setName(inputList.get(0));
            figure.setDob(inputList.get(3));
            figure.setCountry(inputList.get(4));
            figure.setRole(inputList.get(5));
            figure.setDescription(inputList.get(1));
            figure.setIcon(inputList.get(2));
        }

        List<Item> database = retrieveData(file);
        database.add(newItem);

        saveData(file, database);

    }

    public void deleteData(String file, int id){
        try{
            List<Item> database = retrieveData(file);
            database.remove(id);
            saveData(file, database);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void saveData(String file, List<Item> itemList) {
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
            //Append value based on value type, 0 for column names, 1 for data value
            if (anItem.getCounter() % 2 == value) {
                dataList.add(anItem.nextData());
            }
            anItem.nextData();
        }
        //Add icon source if only if the requested value is 1 (data value)
//        if(value == 1) {
//            dataList.add(anItem.getIcon());
//        }
        anItem.setCounter(0);
        return dataList;
    }

}
