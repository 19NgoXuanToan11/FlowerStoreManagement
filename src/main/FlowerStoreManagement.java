package main;

import java.text.ParseException;

import manage.ManageFlower;
import manage.ManageOrder;

import menu.Menu;

public class FlowerStoreManagement {

    public static void main(String[] args) throws ParseException {
        ManageFlower manageFlower = new ManageFlower();
        ManageOrder manageOrder = new ManageOrder(manageFlower);
        
        Menu menu = new Menu("Flower Store Management-----");
        menu.addNewOption("                 1. Flower");
        menu.addNewOption("                 2. Order");
        menu.addNewOption("                 3. Quit!");
        
        Menu menuFlower = new Menu("Flower Management-----");
        menuFlower.addNewOption("          1. Add a flower");
        menuFlower.addNewOption("          2. Find a flower");
        menuFlower.addNewOption("          3. Update a flower");
        menuFlower.addNewOption("          4. Delete a flower");
        menuFlower.addNewOption("          5. Quit - Other");
        
        Menu menuOrder = new Menu("Order Management-----");
        menuOrder.addNewOption("          1. Add an order");
        menuOrder.addNewOption("          2. Display orders");
        menuOrder.addNewOption("          3. Sort orders");
        menuOrder.addNewOption("          4. Save data");
        menuOrder.addNewOption("          5. Load data");        
        menuOrder.addNewOption("          6. Quit - Orther");    
        
        int choice = 0;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    int choiceFlower = 0;
                    do {
                        menuFlower.printMenu();
                        choiceFlower = menuFlower.getChoice();
                        switch (choiceFlower) {
                            case 1:
                                manageFlower.addFlower();
                                break;
                            case 2:
                                manageFlower.findFlower();
                                break;
                            case 3:
                                manageFlower.updateFlower();
                                break;
                            case 4:
                                manageFlower.deleteFlower();
                                break;
                            case 5:
                                System.out.println("Return main menu!");
                                break;
                        }
                    } while (choiceFlower != 5);
                    break;
                   
                case 2:
                    int choiceOrder = 0;
                    do {
                        menuOrder.printMenu();
                        choiceOrder = menuOrder.getChoice();
                        switch (choiceOrder) {
                            case 1:
                                manageOrder.addOrder();
                                break;
                            case 2:
                                manageOrder.displayOrders();
                                break;
                            case 3:
                                manageOrder.sortOrders();
                                break;
                            case 4:
                                manageFlower.saveData();
                                manageOrder.saveData();
                                break;
                            case 5:
                                manageFlower.loadData();
                                manageOrder.loadData();
                                break;
                            case 6:
                                System.out.println("Return main menu!");
                                break;
                        }
                    } while (choiceOrder != 6);
                    break;
                    
                case 3:
                    System.out.println("Existing...");
                    break;
            }
        } while (choice != 3);
    }
    
}
