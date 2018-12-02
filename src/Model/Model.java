/**
 * Created by benzali on 11/29/2018.
 */
package Model;

import ObserverPackage.Subject;
import Stax.Driver;

import java.util.List;
import java.util.Vector;

public class Model extends Subject {
    private static String eventFile = "src/Stax/database/events.xml";
    private static String figureFile = "src/Stax/database/figures.xml";
    private int eventCount, figureCount;

    private Driver myDriver;

    public Model(){
        myDriver = new Driver();
        eventCount = myDriver.retrieveData(eventFile).size();
        figureCount = myDriver.retrieveData(figureFile).size();

        System.out.println("Model Initialized");
    }

    //Return the database column names
    public String[] getColumnNames(DataType type){

        String file = "";

        if (type.equals(DataType.EVENT)){
            file = eventFile;
        }
        else if (type.equals(DataType.FIGURE)){
            file = figureFile;
        }

        return myDriver.retrieveColumnNames(file);
    }

    //Add data to the database
    //Input List composition:
    //[ 0.Title,
    //  1.Description,
    //  2.Icon,
    //  3.Date/Dob,
    //  4.Location/Country,
    //  5.Party/Role,
    //  6.itemID]
    public void addData(List<String> inputList){

        if(dataChecker(inputList.get(6)).equals(DataType.EVENT)){
            for(int i = 0; i < 4; i++){
                inputList.remove(3);
            }
            inputList.add(eventCount + "");
            myDriver.addData(eventFile, inputList);
            eventCount++;
        }
        else if(dataChecker(inputList.get(6)).equals(DataType.FIGURE)){
            for(int i = 0; i < 4; i++){
                inputList.remove(3);
            }
            inputList.add(figureCount + "");
            myDriver.addData(figureFile, inputList);
            figureCount++;
        }
        updateData();
    }

    //Delete data from the database
    public void deleteData(DataType type, int itemID){
        String file = "";
        if (type.equals(DataType.EVENT)){
            file = eventFile;
        }
        else if (type.equals(DataType.FIGURE)){
            file = figureFile;
        }
        myDriver.deleteData(file, itemID);

        updateData();
    }

    //Update database to the view
    public void updateData(){
        Vector<Vector<String>> table;
        table = myDriver.retrieveDataVector(eventFile);

        notifyObservers(table);

        table = myDriver.retrieveDataVector(figureFile);

        notifyObservers(table);

    }

    public Vector<Vector<String>> getData(String type){
        DataType theType = dataChecker(type);
        String filename = "";
        if(theType.equals(DataType.EVENT)){
            filename = eventFile;
        }
        else if(theType.equals(DataType.FIGURE)){
            filename = figureFile;
        }
        return myDriver.retrieveDataVector(filename);

    }

    //Data type enumerator checker
    public DataType dataChecker(String input){
        if(input.contains("event")){
            return DataType.EVENT;
        }
        else if (input.contains("figure")){
            return DataType.FIGURE;
        }
        return null;
    }

}
