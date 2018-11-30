package Stax;

import Stax.datastructure.Item;
import Stax.datastructure.Event;

import java.util.ArrayList;
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

    public Vector<String> getColumnNames(){
        Vector<String> myColumnNames = new Vector<String>();
        if (!data.isEmpty()) {

            Item anitem = data.get(0);
            while(anitem.hasNextData()){
                if(anitem.getCounter() % 2 == 0){
                    myColumnNames.add(anitem.nextData());
                    anitem.nextData();
                }
            }
        }
        return myColumnNames;
    }
}
