/**
 * Created by benzali on 11/29/2018.
 */
package Stax.datastructure;

public class Item {
    protected String id;
    protected String name;
    protected String description;
    protected String icon;
    protected int counter, inputCounter;

    public Item(){
        this.id = null;
        this.name = null;
        this.description = null;
        this.counter = 0;
        this.icon = null;
        this.inputCounter = 0;
    }

    public String getHeader(){    return "items";   }
    public String getBody(){    return "item";  }

    public String getId(){    return this.id;   }
    public void setId(String anid){    this.id = anid;    }

    public String getName(){    return this.name;   }
    public void setName(String aname){  this.name = aname;  }

    public String getDescription(){     return this.description;    }
    public void setDescription(String adescription){    this.description = adescription;    }

    public void setCounter(int value){   this.counter = value;  }
    public int getCounter(){    return this.counter;    }

    public String getIcon(){    return this.icon;   }
    public void setIcon(String iconSource){  this.icon = iconSource;    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

    public String nextData(){
        String output = "";
        switch(counter){
            case 0:
                output = "id";
                break;
            case 1:
                output = id;
                break;
            case 2:
                output = "name";
                break;
            case 3:
                output = name;
                break;
            case 4:
                output = "description";
                break;
            case 5:
                output = description;
                break;
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

    public void setNextData(String input){
        switch(inputCounter){
            case 0:
                setId(input);
                break;
            case 1:
                setName(input);
                break;
            case 2:
                setDescription(input);
                break;
            default:
                inputCounter = -1;
                setId(input);

        }
        inputCounter += 1;
    }
}
