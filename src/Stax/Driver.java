package Stax;

import Stax.datastructure.Item;
import Stax.datastructure.Event;

import java.util.List;

/**
 * Created by benzali on 11/29/2018.
 */
public class Driver {
    private List<Event> data;

    public void retrieveData(String file) {
        StaXParser read = new StaXParser();
        data = read.readConfig(file);
    }

    public void printItems() {
        for (Item item : data) {
            System.out.println(item);
        }
    }
}
