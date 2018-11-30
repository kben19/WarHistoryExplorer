/**
 * Created by benzali on 11/29/2018.
 */
package Model;

import ObserverPackage.Subject;
import Stax.Driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Model extends Subject {
    private static String eventFile = "src/Stax/database/events.xml";
    private static String figureFile = "src/Stax/database/figures.xml";

    private Driver myDriver;

    public Model(){
        myDriver = new Driver();


        System.out.println("Model Initialized");
    }

    public String[] getColumnNames(DataType type){
        String[] columnNamesArray = null;
        String file = "";

        if (type.equals(DataType.EVENT)){
            file = eventFile;
        }
        else if (type.equals(DataType.FIGURE)){
            file = figureFile;
        }

        return myDriver.retrieveColumnNames(file);
    }

    public void updateData(){
        Vector<Vector<String>> table;
        table = myDriver.retrieveDataVector(eventFile);

        notifyObservers(table);

        table = myDriver.retrieveDataVector(figureFile);

        notifyObservers(table);

    }

}
