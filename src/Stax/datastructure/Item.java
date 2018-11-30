package Stax.datastructure;

import javax.xml.stream.events.Attribute;
import java.util.Iterator;

/**
 * Created by benzali on 11/29/2018.
 */
public class Item {
    protected String id;
    protected String name;
    protected String description;
    protected int counter;

    public Item(){
        this.id = null;
        this.name = null;
        this.description = null;
        this.counter = 0;
    }

    public String getHeader(){    return "items";   }
    public String getBody(){    return "item";  }

    public String getId(){    return this.id;   }
    public void setId(String anid){    this.id = anid;    }

    public String getName(){    return this.name;   }
    public void setName(String aname){  this.name = aname;  }

    public String getDescription(){     return this.description;    }
    public void setDescription(String adescription){    this.description = adescription;    }

    public int getCounter(){    return this.counter;    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

    public String nextData(){
        String output = "";
        switch(counter){
            case 0:
                output = "id";
            case 1:
                output = id;
            case 2:
                output = "name";
            case 3:
                output = name;
            case 4:
                output = "description";
            case 5:
                output = description;
            default:
                counter = -1;
                output = "id";

        }
        counter += 1;
        return output;
    }

    public boolean hasNextData(){
        return (counter <= 5);
    }
}
