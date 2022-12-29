import java.util.Scanner;

public class RealEstate {
    Scanner scanner = new Scanner(System.in);
    private User[] users;
    private Property[] properties;
    private City[] cities;

    public static final int MAX_USER_PROPERTIES = 2;
    public static final int MAX_BROKER_PROPERTIES = 5;
    public static final int MIN_PASSWORD_LENGTH = 5;
    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final int NO_FILTER = -999;


    public RealEstate() {
        this.cities = new City[10];
        this.cities[0] = new City("Eilat", City.REGION_NEGEV, City.EILAT_STREETS);
        this.cities[1] = new City("Dimona", City.REGION_NEGEV, City.DIMONA_STREETS);
        this.cities[2] = new City("Ber-Sheva", City.REGION_NEGEV, City.BER_SHEVA_STREETS);
        this.cities[3] = new City("Ashkelon", City.REGION_SOUTH, City.ASHKELON_STREETS);
        this.cities[4] = new City("Ashdod", City.REGION_SOUTH, City.ASHDOD_STREETS);
        this.cities[5] = new City("Tel-Aviv", City.REGION_CENTER, City.TEL_AVIV_STREETS);
        this.cities[6] = new City("Jerusalem", City.REGION_CENTER, City.JERUSALEM_STREETS);
        this.cities[7] = new City("Netanya", City.REGION_SHARON, City.NETANYA_STREETS);
        this.cities[8] = new City("Haifa", City.REGION_NORTH, City.HAIFA_STREETS);
        this.cities[9] = new City("Kiryat-Shmona", City.REGION_NORTH, City.KIRYAT_SHMONA_STREETS);
    }

    public void createUser() {
        User newUser;
        if (userArrayIsNull(this.users)) {
            newUser = createFirstUser();
        } else {
            String userName = createUserGetName();
            for (int i = 0; i < this.users.length; i++) {
                if (this.users[i].userNameEquals(userName) || userName.equals("")) {
                    do {
                        System.out.println("Username is unavailable, try again.");
                        userName = scanner.nextLine();
                    } while (this.users[i].userNameEquals(userName));
                }
            }

            String password = createUserGetPassword();
            String phoneNumber = createUserGetPhoneNumber();
            int userType = createUserGetUserType();
            scanner.nextLine();

            newUser = new User(userName, password, phoneNumber, userType);
        }
        User[] temp;
        if (userArrayIsNull(this.users)) {
            temp = new User[1];
            temp[0] = newUser;
        } else {
            temp = new User[this.users.length + 1];
            int count = 0;
            do {
                temp[count] = this.users[count];
                count++;
            } while (count < this.users.length);
            temp[count] = newUser;
        }
        this.users = temp;
        System.out.println("Account creation successful. Returning to main menu..." + "\n");
    }

    public User login() {
        User user = null;
        System.out.println("Enter your username");
        String userNameLogin = scanner.nextLine();
        System.out.println("Enter your password");
        String userPasswordLogin = scanner.nextLine();

        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getUserName().equals(userNameLogin) && this.users[i].getPassword().equals(userPasswordLogin)) {
                user = this.users[i];
                break;
            }
        }
        return user;
    }

    public boolean postNewProperty(User user) {
        boolean valid = false;
        boolean firstProperty = false;
        Property newListing = new Property();
        int userPropertyCount = 0;

        if (propertyArrayIsNull(this.properties)) {
            firstProperty = true;
        } else {
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getPoster().equals(user.getUserName())) {
                    userPropertyCount++;
                }
            }
        }
        if ((user.getUserType() == User.USER_TYPE_BROKER && userPropertyCount >= MAX_BROKER_PROPERTIES) || (user.getUserType() == User.USER_TYPE_USER && userPropertyCount >= MAX_USER_PROPERTIES)) {
            System.out.println("Cannot post any more listings.");
        } else {
            String citySelection = postNewPropertyGetCity();
            String[] validCityStreets = checkCitySelection(citySelection);
            if (validCityStreets == null) {
                System.out.println("Invalid selection.");
            } else {
                String streetSelection = postNewPropertyGetStreet(validCityStreets);
                String validStreetSelection = checkStreetSelection(streetSelection, validCityStreets);
                if (validStreetSelection == null) {
                    System.out.println("Invalid selection.");
                } else {
                    int propertyType = postNewPropertyGetType();
                    if (propertyType == Property.TYPE_APARTMENT) {
                        newListing.setHouseFloor(postNewPropertyGetFloor());
                    }
                    if (propertyType == Property.TYPE_APARTMENT || propertyType == Property.TYPE_PENTHOUSE || propertyType == Property.TYPE_HOUSE) {
                        newListing.setPostedBy(user);
                        newListing.setCity(citySelection);
                        newListing.setStreet(streetSelection);
                        newListing.setPropertyType(propertyType);
                        newListing.setRoomCount(postNewPropertyGetRooms());
                        newListing.setHouseNumber(postNewPropertyGetHouseNumber());
                        newListing.setPrice(postNewPropertyGetPrice());
                        int rentOrSale = postNewPropertyGetRentOrSell();
                        if (rentOrSale == Property.FOR_RENT) {
                            newListing.setRentOrSell(Property.FOR_RENT);
                            valid = true;
                        }
                        if (rentOrSale == Property.FOR_SALE) {
                            newListing.setRentOrSell(Property.FOR_SALE);
                            valid = true;
                        }
                        if (rentOrSale != Property.FOR_RENT && rentOrSale != Property.FOR_SALE) {
                            System.out.println("Invalid selection.");
                        }
                    } else {
                        System.out.println("Invalid selection.");
                    }
                }
            }
        }
        if (valid) {
            if (firstProperty) {
                this.properties = new Property[1];
                this.properties[0] = newListing;
            } else {
                Property[] temp = new Property[this.properties.length + 1];
                int count = 0;
                do {
                    temp[count] = this.properties[count];
                    count++;
                } while (count < this.properties.length);
                temp[count] = newListing;
                this.properties = temp;
            }
        }
        return valid;
    }

    public void removeProperty (User user) {
        if (propertyArrayIsNull(this.properties) || this.properties.length == 0) {
            System.out.println("There are no listings in the system." + "\n");
        } else {
            int count = 0;

            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getPoster().equals(user.getUserName())) {
                    count++;
                }
            }

            if (count == 0) {
                System.out.println("You have no listings, returning to user menu." + "\n");
            } else {
                Property[] userProperties = new Property[count];
                for (int i = 0, j = 0; i < this.properties.length; i++) {
                    if (this.properties[i].getPoster().equals(user.getUserName())) {
                        userProperties[j] = this.properties[i];
                        j++;
                    }
                }
                System.out.println("Select the property listing you wish to remove:");
                for (int i = 0; i < userProperties.length; i++) {
                    System.out.println((i + 1) + ". " + userProperties[i].toString());
                }
                int selection = scanner.nextInt();
                if (selection < 1 || selection > count) {
                    System.out.println("Invalid input, returning to user menu." + "\n");
                } else {
                    this.properties = removePropertyFromArray(user, selection);
                    System.out.println("Listing removed, returning to user menu" + "\n");
                }
            }
        }
    }

    public void printAllProperties () {
        if (propertyArrayIsNull(this.properties) || this.properties.length == 0) {
            System.out.println("There are no listings in the system." + "\n");
        } else {
            for (int i = 0; i < this.properties.length; i++) {
                System.out.println(this.properties[i].toString());
            }
        }
    }

    public void printProperties (User user) {
        if (propertyArrayIsNull(this.properties) || this.properties.length == 0) {
            System.out.println("There are no listings in the system" + "\n");
        } else {
            int count = 0;
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].getPoster().equals(user.getUserName())) {
                    System.out.println(this.properties[i].toString());
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("You have no listings." + "\n");
            }
        }
    }

    public Property[] search () {
        Property[] filteredProperties;
        if (propertyArrayIsNull(this.properties) || this.properties.length == 0) {
            System.out.println("There are no listings in the system." + "\n");
            filteredProperties = null;
        } else {
            int rentOrSellSelection;
            int typeSelection;
            int roomCountSelection;
            int priceRangeMin;
            int priceRangeMax;

            System.out.println("Is the property " + Property.FOR_RENT + ". For rent or " + Property.FOR_SALE + ". For sale? Enter " + NO_FILTER + " for any.");
            rentOrSellSelection = scanner.nextInt();
            if (rentOrSellSelection != Property.FOR_RENT && rentOrSellSelection != Property.FOR_SALE && rentOrSellSelection != NO_FILTER) {
                do {
                    System.out.println("Invalid selection, try again.");
                    rentOrSellSelection = scanner.nextInt();
                } while (rentOrSellSelection != Property.FOR_RENT && rentOrSellSelection != Property.FOR_SALE && rentOrSellSelection != NO_FILTER);
            }
            System.out.println("What is the desired type? (" + Property.TYPE_APARTMENT + ". Apartment, " + Property.TYPE_PENTHOUSE + ". Penthouse, " + Property.TYPE_HOUSE + ". House. Enter " + NO_FILTER + " for any.)");
            typeSelection = scanner.nextInt();
            if (typeSelection != Property.TYPE_APARTMENT && typeSelection != Property.TYPE_PENTHOUSE && typeSelection != Property.TYPE_HOUSE && typeSelection != NO_FILTER) {
                do {
                    System.out.println("Invalid selection, try again.");
                    typeSelection = scanner.nextInt();
                } while (typeSelection != Property.TYPE_APARTMENT && typeSelection != Property.TYPE_PENTHOUSE && typeSelection != Property.TYPE_HOUSE && typeSelection != NO_FILTER);
            }
            System.out.println("How many rooms? Enter " + NO_FILTER + " for any room count.");
            roomCountSelection = scanner.nextInt();
            System.out.println("What is the minimum asking price? Enter " + NO_FILTER + " for no filter.");
            priceRangeMin = scanner.nextInt();
            System.out.println("What is the maximum asking price? Enter " + NO_FILTER + " for no filter.");
            priceRangeMax = scanner.nextInt();

            int count = 0;
            for (int i = 0; i < this.properties.length; i++) {
                if (this.properties[i].rentOrSellMatch(rentOrSellSelection) && this.properties[i].typeMatch(typeSelection)
                        && this.properties[i].roomCountMatch(roomCountSelection) && this.properties[i].priceMinMatch(priceRangeMin)
                        && this.properties[i].priceMaxMatch(priceRangeMax)) {

                    count++;
                }
            }

            filteredProperties = new Property[count];
            for (int i = 0, j = 0; i < this.properties.length; i++) {
                if (this.properties[i].rentOrSellMatch(rentOrSellSelection) && this.properties[i].typeMatch(typeSelection)
                        && this.properties[i].roomCountMatch(roomCountSelection) && this.properties[i].priceMinMatch(priceRangeMin)
                        && this.properties[i].priceMaxMatch(priceRangeMax)) {

                    filteredProperties[j] = this.properties[i];
                    j++;
                }
            }
        }
        return filteredProperties;
    }

    private boolean checkPasswordStrength (String password) {
        boolean valid = false;

        if (password.length() >= MIN_PASSWORD_LENGTH) {
            if (password.contains("%") || password.contains("_") || password.contains("$")) {
                boolean containsNumber = false;
                for (int i = 0; i < password.length(); i++) {
                    switch (password.charAt(i)) {
                        case ('0'), ('1'), ('2'), ('3'), ('4'), ('5'), ('6'), ('7'), ('8'), ('9') -> containsNumber = true;
                    }
                    if (containsNumber) {
                        valid = true;
                    }
                }
            }
        }
        return valid;
    }

    private boolean checkPhoneNumber (String phoneNumber) {
        boolean valid = false;

        if (phoneNumber.length() == PHONE_NUMBER_LENGTH) {
            if (phoneNumber.startsWith("05")) {
                int count = 0;
                for (int i = 0; i < phoneNumber.length(); i++) {
                    switch (phoneNumber.charAt(i)) {
                        case ('0'), ('1'), ('2'), ('3'), ('4'), ('5'), ('6'), ('7'), ('8'), ('9') -> count++;
                    }
                }
                if (count == 10) {
                    valid = true;
                }
            }
        }
        return valid;
    }

    private Property[] removePropertyFromArray (User user, int selection) {
        Property[] temp = new Property[this.properties.length - 1];
        int count = 1;

        for (int i = 0, j = 0; i < this.properties.length; i++) {
            if (this.properties[i].getPoster().equals(user.getUserName())) {
                if (count == selection) {
                    continue;
                } else {
                    count++;
                }
            }
            temp[j] = this.properties[i];
            j++;
        }
        return temp;
    }

    private boolean userArrayIsNull (User[] input) {
        boolean output = false;
        if (input == null) {
            output = true;
        }
        return output;
    }

    private User createFirstUser () {
        String userName = createUserGetName();
        String password = createUserGetPassword();
        String phoneNumber = createUserGetPhoneNumber();
        int userType = createUserGetUserType();
        return new User(userName, password, phoneNumber, userType);
    }

    private String createUserGetName() {
        System.out.println("Enter the username you wish to use");
        return scanner.nextLine();
    }

    private String createUserGetPassword() {
        System.out.println("Enter the password you wish to use:");
        System.out.println("Note: Password must be at least 5 characters long, contain at least 1 number and contain one of the special symbols : '%' '_' or '$'");
        String password = scanner.nextLine();
        if (!checkPasswordStrength(password)) {
            do {
                System.out.println("Invalid input, try again.");
                password = scanner.nextLine();
            } while (!checkPasswordStrength(password));
        }
        return password;
    }

    private String createUserGetPhoneNumber () {
        System.out.println("Enter your phone number:");
        System.out.println("Note: Phone number must be a valid Israeli phone number; 10 digits long, starts with 05 and contains only numbers.");
        String phoneNumber = scanner.nextLine();
        if (!checkPhoneNumber(phoneNumber)) {
            do {
                System.out.println("Invalid input, try again.");
                phoneNumber = scanner.nextLine();
            } while (!checkPhoneNumber(phoneNumber));
        }
        return phoneNumber;
    }

    private int createUserGetUserType () {
        System.out.println("Select the user type for this account");
        System.out.println(User.USER_TYPE_USER + ". Regular user");
        System.out.println(User.USER_TYPE_BROKER + ". Broker");
        int userType = scanner.nextInt();
        if (userType != User.USER_TYPE_USER && userType != User.USER_TYPE_BROKER) {
            do {
                System.out.println("Invalid input, try again.");
                userType = scanner.nextInt();
            } while (userType != User.USER_TYPE_USER && userType != User.USER_TYPE_BROKER);
        }
        return userType;
    }

    private boolean propertyArrayIsNull (Property[] input) {
        boolean output = false;
        if (input == null) {
            output = true;
        }
        return output;
    }

    private String postNewPropertyGetCity () {
        System.out.println("Enter the name of the city you wish to post in");
        for (int i = 0; i < this.cities.length; i++) {
            System.out.println(this.cities[i].getCityName());
        }
        return scanner.nextLine();
    }

    private String[] checkCitySelection(String input) {
        String[] output = null;
        for (int i = 0; i < this.cities.length; i++) {
            if (input.equalsIgnoreCase(this.cities[i].getCityName())) {
                output = this.cities[i].getStreetList();
                break;
            }
        }
        return output;
    }

    private String checkStreetSelection(String input, String[] streets) {
        String output = null;
        for (int i = 0; i < streets.length; i++) {
            if (input.equalsIgnoreCase(streets[i])) {
                output = input;
                break;
            }
        }
        return output;
    }

    private String postNewPropertyGetStreet (String[] streets) {
        System.out.println("Select the street your property is in");
        for (int i = 0; i < streets.length; i++) {
            System.out.println(streets[i]);
        }
        return scanner.nextLine();
    }

    private int postNewPropertyGetType () {
        System.out.println("What is the type of property?");
        System.out.println(Property.TYPE_APARTMENT + ". Apartment");
        System.out.println(Property.TYPE_PENTHOUSE + ". Penthouse apartment");
        System.out.println(Property.TYPE_HOUSE + ". House");
        return scanner.nextInt();
    }

    private int postNewPropertyGetFloor () {
        System.out.println("What floor is the property located on?");
        return scanner.nextInt();
    }

    private int postNewPropertyGetRooms () {
        System.out.println("How many rooms are in the property?");
        return scanner.nextInt();
    }

    private int postNewPropertyGetHouseNumber () {
        System.out.println("What is the house number?");
        return scanner.nextInt();
    }

    private int postNewPropertyGetPrice () {
        System.out.println("What is the asking price?");
        return scanner.nextInt();
    }

    private int postNewPropertyGetRentOrSell () {
        System.out.println("Is the property " + Property.FOR_RENT + ". For rent or " + Property.FOR_SALE + ". For sale?");
        return scanner.nextInt();
    }
}
