package ie.ul.surplusv2;

public class offers {
    private String currPrice;
    private String prevPrice;
    private String location;
    private String itemName;

    public offers() {}

    public offers(String currPrice, String prevPrice, String location, String itemName) {
        this.currPrice = currPrice;
        this.prevPrice = prevPrice;
        this.location = location;
        this.itemName = itemName;
    }

    public String getName(){
        return itemName;
    }

    public String getCurrPrice(){
        return currPrice;
    }

    public String getPrevPrice(){
        return prevPrice;
    }

    public String getLocation(){
        return location;
    }
}
