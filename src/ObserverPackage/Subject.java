package ObserverPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by benzali on 11/30/2018.
 */
public class Subject {

    ArrayList<Observer> observers = new ArrayList<>();

    public Subject() {}

    public void attach(Observer o) {
        this.observers.add(o);
    }

    public void detach(Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers(Vector<Vector<String>> anObject) {
        for( Observer o : observers ) {
            o.update(anObject);
        }
    }
}
