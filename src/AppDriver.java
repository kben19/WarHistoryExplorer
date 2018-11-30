/**
 * Created by benzali on 11/29/2018.
 */

import Model.Model;

public class AppDriver {
    public AppDriver(){}

    public void run(){
        //create Model.Model and View
        Model myModel = new Model();
        View myView = new View(myModel);

        //create Controller
        Controller myController = new Controller();

        //Attach Observer
        myModel.attach(myView);

        myModel.updateData();
    }
}
