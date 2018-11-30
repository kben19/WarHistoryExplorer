/**
 * Created by benzali on 8/15/2018.
 */

import Stax.*;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) {
        Driver myDriver = new Driver();
        myDriver.saveData("src/Stax/database/events.xml", myDriver.retrieveData("src/Stax/database/config2.xml"));
        myDriver.printItems();
        System.out.println(myDriver.getColumnNames());


        AppDriver myApp = new AppDriver();

        //myApp.run();

    }


}
