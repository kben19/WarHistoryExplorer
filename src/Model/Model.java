/**
 * Created by benzali on 11/29/2018.
 */
package Model;

import ObserverPackage.Subject;
import Stax.Driver;
import Stax.datastructure.Event;

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

        if(dataChecker(inputList.get(6)).equals(DataType.EVENT)){
            for(int i = 0; i < 4; i++){
                inputList.remove(3);
            }
            inputList.add(databaseSize[0] + "");
            myDriver.addData(eventFile, inputList);
            databaseSize[0]++;
        }
        else if(dataChecker(inputList.get(6)).equals(DataType.FIGURE)){
            for(int i = 0; i < 4; i++){
                inputList.remove(3);
            }
            inputList.add(databaseSize[1] + "");
            myDriver.addData(figureFile, inputList);
            databaseSize[1]++;
        }
        updateData();
    }

    public void deleteData(String itemID){
        String file = "";
        int idNum = -1;
        if (dataChecker(itemID).equals(DataType.EVENT)){
            file = eventFile;
            idNum = Integer.parseInt(itemID.substring(5));
        }
        else if (dataChecker(itemID).equals(DataType.FIGURE)){
            file = figureFile;
            idNum = Integer.parseInt(itemID.substring(6));
        }
        myDriver.deleteData(file, idNum-1);

        updateData();
    }

    public void updateData(){
        Vector<Vector<String>> table;
        table = myDriver.retrieveDataVector(eventFile);

        notifyObservers(table);

        table = myDriver.retrieveDataVector(figureFile);

        notifyObservers(table);

    }

    private DataType dataChecker(String input){
        if(input.contains("event")){
            return DataType.EVENT;
        }
        else if (input.contains("figure")){
            return DataType.FIGURE;
        }
        return null;
    }

}
