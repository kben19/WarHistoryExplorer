package Stax.datastructure;

/**
 * Created by benzali on 11/29/2018.
 */
public class Event extends Item{
    private String date;
    private String location;
    private String belligerent;

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
        return "Item [type=" + this.getType() + ", title=" + this.getName() + ", date=" + date + ", location=" + location + ", belligerent=" + belligerent +", description=" + this.getDescription() + "]";
    }

}
