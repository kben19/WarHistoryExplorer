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
    private int[] databaseSize = {0, 0};

    private Driver myDriver;

    public Model(){
        myDriver = new Driver();
        databaseSize[0] = myDriver.retrieveData(eventFile).size();
        databaseSize[1] = myDriver.retrieveData(figureFile).size();

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

    public void addData(List<String> inputList){

        if(inputList.get(6).equals("event")){
            for(int i = 0; i < 4; i++){
                inputList.remove(3);
            }
            inputList.add(databaseSize[0] + "");
            myDriver.addData(eventFile, inputList);
            databaseSize[0]++;
        }
        else if(inputList.get(6).equals("figure")){
            for(int i = 0; i < 4; i++){
                inputList.remove(3);
            }
            inputList.add(databaseSize[1] + "");
            myDriver.addData(figureFile, inputList);
            databaseSize[1]++;
        }
        updateData();
    }

    public void updateData(){
        Vector<Vector<String>> table;
        table = myDriver.retrieveDataVector(eventFile);

        notifyObservers(table);

        table = myDriver.retrieveDataVector(figureFile);

        notifyObservers(table);

    }

}
