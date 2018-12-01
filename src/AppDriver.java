/**
 * Created by benzali on 11/29/2018.
 */

import Model.Model;

public class AppDriver {
    public AppDriver(){}

    public void run(){
        //create Model, View, and Controller
        Model myModel = new Model();
        Controller myController = new Controller();
        View myView = new View(myModel, myController);

        //attach model and view to controller
        myController.addModel(myModel);
        myController.addView(myView);

        //Attach Observer
        myModel.attach(myView);

        myModel.updateData();
    }
}
