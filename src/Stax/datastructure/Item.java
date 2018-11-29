package Stax.datastructure;

/**
 * Created by benzali on 11/29/2018.
 */
public class Item {
    protected String type;
    protected String name;
    protected String description;
    protected int counter;

    public Item(){
        this.type = null;
        this.name = null;
        this.description = null;
        this.counter = 0;
    }

    public String getType(){    return this.type;   }
    public void setType(String atype){    this.type = atype;    }

    public String getName(){    return this.name;   }
    public void setName(String aname){  this.name = aname;  }

    public String getDescription(){     return this.description;    }
    public void setDescription(String adescription){    this.description = adescription;    }

    @Override
    public String toString() {
        return "Item [type=" + type + ", name=" + name + ", description=" + description + "]";
    }

    public String nextData(){
        String output = "";
        switch(counter){
            case 0:
                output = "type";
            case 1:
                output = type;
            case 2:
                output = "name";
            case 3:
                output = name;
            case 4:
                output = "description";
            case 5:
                output = description;
            default:
                counter = 0;
                output = "type";

        }
        counter += 1;
        return output;
    }

    public boolean hasNextData(){
        return (counter <= 5);
    }
}
