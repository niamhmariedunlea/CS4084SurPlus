package ie.ul.surplusv2;

public class offers {
    private String docID;
    private String currPrice;
    private String item;
    private String location;
    private String prevPrice;

    public offers() {}

    public offers(String docID, String currPrice, String item, String location, String prevPrice) {
        this.docID = docID;
        this.currPrice = currPrice;
        this.prevPrice = prevPrice;
        this.location = location;
        this.item = item;
    }

    public String getDocID(){
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getItem(){
        return item;
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

    public void setCurrPrice(String currPrice) {
        this.currPrice = currPrice;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrevPrice(String prevPrice) {
        this.prevPrice = prevPrice;
    }

}
