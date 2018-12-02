/**
 * Created by benzali on 11/29/2018.
 */

package Stax.datastructure;


public class Event extends Item{
    protected String date;
    protected String location;
    protected String party;

    public Event(){
        super();
        this.date = null;
        this.location = null;
        this.party = null;
    }

    @Override
    public String getHeader(){    return "events";   }
    @Override
    public String getBody(){    return "event";     }

    public String getDate(){    return this.date;   }
    public void setDate(String adate){  this.date = adate;  }

    public String getLocation(){    return this.location;   }
    public void setLocation(String alocation){  this.location = alocation;  }

    public String getParty(){     return this.party;    }
    public void setParty(String aparty){    this.party = aparty;    }

    @Override
    public String toString(){
        return "Item [id=" + id + ", name=" + name + ", date=" + date + ", location=" + location + ", party=" + party +", description=" + description + "]";
    }

    @Override
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
                output = "party";
                break;
            case 9:
                output = party;
                break;
            case 10:
                output = "description";
                break;
            case 11:
                output = description;
                break;
            default:
                counter = 0;
                output = "id";

        }
        counter += 1;
        return output;
    }

    @Override
    public boolean hasNextData(){
        return (counter <=11);
    }

    @Override
    public void setNextData(String input){
        switch(inputCounter){
            case 0:
                setId(input);
                break;
            case 1:
                setName(input);
                break;
            case 2:
                setDate(input);
                break;
            case 3:
                setLocation(input);
                break;
            case 4:
                setParty(input);
                break;
            case 5:
                setDescription(input);
                break;
            default:
                inputCounter = -1;
                setId(input);

        }
        inputCounter += 1;
    }
}
