/**
 * Created by benzali on 11/29/2018.
 */
import Model.Model;

public class Controller implements java.awt.event.ActionListener{
    private Model model;
    private View view;

    public Controller(){

        System.out.println("Controller initialized");
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e){
        if (e.getActionCommand().equals("Search")){
            searchListener();
        }
        else if(e.getActionCommand().equals("Add")){
            addListener();
        }
        else if(e.getActionCommand().equals("Delete")){
            deleteListener();
        }
        else if(e.getActionCommand().equals("Select")){
            selectListener();
        }
    }

    public void addModel(Model amodel){
        this.model = amodel;
        System.out.println("Controller: connected to model");
    }

    public void addView(View aview){
        this.view = aview;
        System.out.println("Controller: connected to view");
    }

    private void searchListener(){
        System.out.println("Testing");
    }

    private void addListener(){
        model.addData(view.getFooterValues());
        view.resetFooter();
    }

    private void deleteListener(){
        model.deleteData(view.getSelectedID());
        view.resetFooter();
    }

    private void selectListener(){

    }


}
