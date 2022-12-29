public class Property {
    private String city;
    private String street;
    private Integer rooms;
    private Integer price;
    private Integer propertyType;
    private Integer rentOrSell;
    private Integer houseNumber;
    private Integer houseFloor;
    private User postedBy;

    public static final int TYPE_APARTMENT = 1;
    public static final int TYPE_PENTHOUSE = 2;
    public static final int TYPE_HOUSE = 3;
    public static final int FOR_RENT = 1;
    public static final int FOR_SALE = 2;


    public Property () {

    }

    public Integer getHouseFloor () {
        int output;
        if (this.houseFloor == null) {
            output = 0;
        } else {
            output = this.propertyType;
        }
        return output;
    }

    public String getPrintablePropertyType () {
        String output = "";
        switch (this.propertyType) {
            case TYPE_APARTMENT -> output = "Regular Apartment";
            case TYPE_PENTHOUSE -> output = "Penthouse";
            case TYPE_HOUSE -> output = "House";
        }
        return output;
    }

    public String getPrintableRentOrSell () {
        String output = "";
        switch (this.rentOrSell) {
            case FOR_RENT -> output = "For rent";
            case FOR_SALE -> output = "For sale";
        }
        return output;
    }

    public boolean rentOrSellMatch (int filter) {
        boolean valid = false;
        if (filter == RealEstate.NO_FILTER || this.rentOrSell == filter) {
            valid = true;
        }
        return valid;
    }

    public boolean typeMatch (int filter) {
        boolean valid = false;
        if (filter == RealEstate.NO_FILTER || this.propertyType == filter) {
            valid = true;
        }
        return valid;
    }

    public boolean roomCountMatch (int filter) {
        boolean valid = false;
        if (filter == RealEstate.NO_FILTER || this.rooms == filter) {
            valid = true;
        }
        return valid;
    }

    public boolean priceMinMatch (int filter) {
        boolean valid = false;
        if (filter == RealEstate.NO_FILTER || this.price >= filter) {
            valid = true;
        }
        return valid;
    }

    public boolean priceMaxMatch (int filter) {
        boolean valid = false;
        if (filter == RealEstate.NO_FILTER || this.price <= filter) {
            valid = true;
        }
        return valid;
    }

    public String getPoster () {
        return this.postedBy.getUserName();
    }

    public void setCity (String input) {
        this.city = input;
    }

    public void setStreet (String input) {
        this.street = input;
    }

    public void setRoomCount (Integer input) {
        this.rooms = input;
    }

    public void setPrice (Integer input) {
        this.price = input;
    }

    public void setPropertyType (Integer input) {
        this.propertyType = input;
    }

    public void setRentOrSell (Integer input) {
        this.rentOrSell = input;
    }

    public void setHouseNumber (Integer input) {
        this.houseNumber = input;
    }

    public void setHouseFloor (Integer input) {
        this.houseFloor = input;
    }

    public void setPostedBy (User input) {
        this.postedBy = input;
    }

    public String toString () {
        String output = "";
        output += this.city + " - " + this.street + " " + this.houseNumber + "\n";
        output += getPrintablePropertyType() + " - " + getPrintableRentOrSell() +": " + this.rooms + " rooms";
        if (getHouseFloor() == 0) {
            output += "\n";
        } else {
            output += ", floor " + this.houseFloor + "\n";
        }
        output += this.price + "$" + "\n";
        output += "Contact info: " + this.postedBy.toString();
        output += "\n";
        return output;
    }

    public boolean isNull (Property[] input) {
        boolean output = false;
        if (input == null) {
            output = true;
        }
        return output;
    }
}
