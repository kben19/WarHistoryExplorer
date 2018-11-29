package Stax.datastructure;

/**
 * Created by benzali on 11/29/2018.
 */
public class Item {
    private String type;
    private String name;
    private String description;

    public Item(){
        this.type = null;
        this.name = null;
        this.description = null;
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
}
