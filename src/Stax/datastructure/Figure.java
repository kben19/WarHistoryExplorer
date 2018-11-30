/**
 * Created by benzali on 11/30/2018.
 */

package Stax.datastructure;

public class Figure extends Item{
    protected String dob;
    protected String country;
    protected String role;

    public Figure(){
        super();
        this.dob = null;
        this.country = null;
        this.role = null;
    }

    @Override
    public String getHeader(){    return "figures";   }
    @Override
    public String getBody(){    return "figure";     }

    public String getDob(){    return this.dob;   }
    public void setDob(String adob){  this.dob = adob;  }

    public String getCountry(){    return this.country;   }
    public void setCountry(String acountry){  this.country = acountry;  }

    public String getRole(){     return this.role;    }
    public void setRole(String arole){    this.role = arole;    }

    @Override
    public String toString(){
        return "Item [id=" + id + ", name=" + name + ", dob=" + dob + ", country=" + country + ", role=" + role +", description=" + description + "]";
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
                output = "dob";
                break;
            case 5:
                output = dob;
                break;
            case 6:
                output = "country";
                break;
            case 7:
                output = country;
                break;
            case 8:
                output = "role";
                break;
            case 9:
                output = role;
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
                setDob(input);
                break;
            case 3:
                setCountry(input);
                break;
            case 4:
                setRole(input);
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
