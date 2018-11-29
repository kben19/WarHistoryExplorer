package Stax.datastructure;

/**
 * Created by benzali on 11/29/2018.
 */
public class Event extends Item{
    protected String date;
    protected String location;
    protected String belligerent;

    public Event(){
        super();
        this.date = null;
        this.location = null;
        this.belligerent = null;
    }

    public String getDate(){    return this.date;   }
    public void setDate(String adate){  this.date = adate;  }

    public String getLocation(){    return this.location;   }
    public void setLocation(String alocation){  this.location = alocation;  }

    public String getBelligerent(){     return this.belligerent;    }
    public void setBelligerent(String abelligerent){    this.belligerent = abelligerent;    }

    @Override
    public String toString(){
        return "Item [type=" + type + ", name=" + name + ", date=" + date + ", location=" + location + ", belligerent=" + belligerent +", description=" + description + "]";
    }

    @Override
    public String nextData(){
        String output = "";
        switch(counter){
            case 0:
                output = "type";
                break;
            case 1:
                output = type;
                break;
            case 2:
                output = "name";
                break;
            case 3:
                output = name;
                break;
            case 4:
                output = "date";
                break;
            case 5:
                output = date;
                break;
            case 6:
                output = "location";
                break;
            case 7:
                output = location;
                break;
            case 8:
                output = "belligerent";
                break;
            case 9:
                output = belligerent;
                break;
            case 10:
                output = "description";
                break;
            case 11:
                output = description;
                break;
            default:
                counter = 0;
                output = "type";

        }
        counter += 1;
        return output;
    }

    @Override
    public boolean hasNextData(){
        return (counter <= 11);
    }

}
