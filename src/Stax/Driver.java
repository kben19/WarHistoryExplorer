package Stax;

import Stax.datastructure.Item;
import Stax.datastructure.Event;

import java.util.List;

/**
 * Created by benzali on 11/29/2018.
 */
public class Driver {
    private List<Event> data;

    public List<Event> retrieveData(String file) {
        StaXParser read = new StaXParser();
        data = read.readConfig(file);
        return data;
    }

    public void saveData(String file, List<Event> eventList) {
        StaXWriter write = new StaXWriter();
        write.setFile(file);
        try {
            write.saveConfig(eventList);
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
}
