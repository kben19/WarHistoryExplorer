/**
 * Created by benzali on 8/15/2018.
 */
import Stax.Driver;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class main {  // JDK 7 and above
    public static void main(String[] args) {
        Stax.Driver staxDriver = new Stax.Driver();
        staxDriver.retrieveData("src/Stax/database/events.xml");
        staxDriver.printItems();

    }


}