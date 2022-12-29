import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RealEstate system = new RealEstate();
        int menuSelection;

        do {
            showMainMenu();
            menuSelection = scanner.nextInt();
            scanner.nextLine();
            if (menuSelection == 1) {
                system.createUser();
            }
            if (menuSelection == 2) {
                User userLogIn = system.login();
                if (userLogIn != null) {
                    int userMenuSelection;
                    do {
                        showUserMenu();
                        userMenuSelection = scanner.nextInt();
                        if (userMenuSelection == 1) {
                            if (system.postNewProperty(userLogIn)) {
                                System.out.println("Listing created successfully, returning to user menu." + "\n");
                            } else {
                                System.out.println("Failed to create listing, returning to user menu." + "\n");
                            }
                        }
                        if (userMenuSelection == 2) {
                            system.removeProperty(userLogIn);
                        }
                        if (userMenuSelection == 3) {
                            system.printAllProperties();
                        }
                        if (userMenuSelection == 4) {
                            system.printProperties(userLogIn);
                        }
                        if (userMenuSelection == 5) {
                            Property[] filteredProperties = system.search();
                            System.out.println();
                            if (filteredProperties == null) {
                                System.out.println("There are no listings in the system." + "\n");
                            } else {
                                if (filteredProperties.length == 0) {
                                    System.out.println("No properties match your search settings." + "\n");
                                } else {
                                    System.out.println("Here are the properties that match your search settings:" + "\n");
                                    for (int i = 0; i < filteredProperties.length; i++) {
                                        System.out.println(filteredProperties[i].toString());
                                    }
                                }
                            }
                        }
                        if (userMenuSelection == 6) {
                            System.out.println("Logging out and returning to main menu..." + "\n");
                        }
                    } while (userMenuSelection != 6);
                } else {
                    System.out.println("Invalid login credentials, returning to main menu" + "\n");
                }
            }
            if (menuSelection == 3) {
                System.out.println("Exiting...");
            }
            if (menuSelection > 3 || menuSelection < 1) {
                System.out.println("Invalid input, try again." + "\n");
                menuSelection = scanner.nextInt();
            }
        } while (menuSelection != 3);
    }

    public static void showMainMenu () {
        System.out.println("Welcome, please select an option:");
        System.out.println();
        System.out.println("1. Create a new account");
        System.out.println("2. Log in to existing account");
        System.out.println("3. Exit");
    }

    public static void showUserMenu () {
        System.out.println("1. Post a new property listing");
        System.out.println("2. Remove a property listing");
        System.out.println("3. Show all listings in the system");
        System.out.println("4. Show your listings.");
        System.out.println("5. Search for a property listing");
        System.out.println("6. Log out and return to the main menu");
    }
}
