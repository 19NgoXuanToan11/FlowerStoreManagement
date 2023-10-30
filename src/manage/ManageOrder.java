package manage;

import obj.Flower;
import obj.OrderDetail;
import obj.OrderHeader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import util.Validation;

public class ManageOrder {
    private ArrayList<OrderHeader> listOrder = new ArrayList();
    private ManageFlower fm;
    
    public ManageOrder(ManageFlower fm) {
        this.fm = fm;
    }

    public ArrayList<OrderHeader> getListOrder() {
        return listOrder;
    }

    public void addOrder() {
        Set<Flower> listFlo = fm.getListFlower();
        if (listFlo.isEmpty()) {
            System.out.println("There are no flowers in the store!");
        } else {
            int stt = listFlo.size() + 1;
            ArrayList<OrderDetail> listCart = new ArrayList<>();
            String date = Validation.getDate("Input date: ", "Error!");
            String name = Validation.getString("Input name: ", "Error!");
            System.out.println("The following is the list of the store's flowers: ");
            for (Flower x : listFlo) {
                System.out.println(x.toString());
            }

            String confirm;
            boolean check;   
            double price = 0;
            do {
                check = false;
                String flowerID = Validation.getRegexString("Input ID(Fxxxx): ", "Wrong format. Ex: F0001,...", "^[F|f]\\d{4}$");
                for (Flower x : listFlo) {
                    if (flowerID.equalsIgnoreCase(x.getId())) {
                        price = x.getUnitPrice();
                        check = true;
                    }
                }
                if (!check) {
                    System.out.println("There are no flowers in the store!");
                } else {
                    int quantity = Validation.getAnInteger("Input quantity: ", "All the fields are not allowed null and must be a number!", 0);
                    listCart.add(new OrderDetail(flowerID, quantity, price));
                }
                confirm = Validation.getRegexString("Do you want to buy more flowers? (y/n): ", "Just y or n!", "^[Y|y|n|N]$");
            } while (confirm.equalsIgnoreCase("y"));

            int quantityBill = 0;
            double totalBill = 0; 
            for (int i = 0; i < listCart.size(); i++) {
                quantityBill += listCart.get(i).getQuantity();
                totalBill += listCart.get(i).getQuantity() * listCart.get(i).getFlowerCost();
            }
            listOrder.add(new OrderHeader(stt, date, name, quantityBill, totalBill, listCart));
            System.out.println("Your bill has done!");
        }
    }

    public void displayOrders() throws ParseException {
        String dateStart = Validation.getDate("Input date Start(dd/MM/yyyy): ", "Wrong. Input again!");
        String dateEnd;
        do {
            dateEnd = Validation.getDate("Input date End(dd/MM/yyyy): ", "Wrong. Input again!");
            if (getMilisecondsByString(dateEnd) <= getMilisecondsByString(dateStart)) {
                System.out.println("The end date must be greater than the start date!");
            }
        } while (getMilisecondsByString(dateEnd) <= getMilisecondsByString(dateStart));

        int stt = 1;
        int countFlower = 0;
        double totalPrice = 0;
        System.out.println("LIST ORDERS FROM " + dateStart + " TO " + dateEnd);
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
        System.out.println("| NO  | ORDER ID |  ORDER DATE   |      CUSTOMER      |FLOWER COUNT |ORDER TOTAL($) |");
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
        for (OrderHeader o : listOrder) {
            if (getMilisecondsByString(o.getOrderDate()) >= getMilisecondsByString(dateStart) && 
                getMilisecondsByString(o.getOrderDate()) <= getMilisecondsByString(dateEnd)) {
                countFlower += o.getQuantity();
                totalPrice += o.getTotal();
                System.out.printf("|%-5d|%-10s|%-15s|%-20s|%13d|%15.1f|\n", stt++, o.getOrderID(), o.getOrderDate(), o.getCustomerName(), o.getQuantity(), o.getTotal());
            }
        }
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
        System.out.println("|     |TOTAL     |               |                    |            " + countFlower + "|         " + totalPrice + "|");
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
    }

    public long getMilisecondsByString(String date) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        return formater.parse(date).getTime();
    }

    public void sortOrders() {
        int choice = Validation.getAnInteger("Input your choice[Order(1.ID 2.Date 3.Name or 4.Total)]: ", "Just from 1 to 4", 1, 4);
        int choiceType = Validation.getAnInteger("[1. ASC or 2. DESC]: ", "Just 1 or 2", 1, 2);
        System.out.println("LIST OF ORDERS: ");
        switch (choice) {
            case 1:
                System.out.println("Sorted by: Order ID");
                if (choiceType == 1) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(listOrder, (o1, o2) -> {
                        return o1.getOrderID() - o2.getOrderID();
                    });
                } else {
                    System.out.println("Sort order: DESC");
                    Collections.sort(listOrder, (o1, o2) -> {
                        return o2.getOrderID() - o1.getOrderID();
                    });
                }
                break;
                
            case 2:
                if (choiceType == 1) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(listOrder, (o1, o2) -> {
                        return formatDate(o1.getOrderDate()).compareToIgnoreCase(formatDate(o2.getOrderDate()));
                    });
                   Collections.sort(listOrder, (o1, o2) -> {
                        return formatDate(o2.getOrderDate()).compareToIgnoreCase(formatDate(o1.getOrderDate()));
                    });
                }
                break;
                
            case 3:
                System.out.println("Sorted by: Order Customer Name");
                if (choiceType == 1) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(listOrder, (o1, o2) -> {
                        return o1.getCustomerName().compareToIgnoreCase(o2.getCustomerName());
                    });
                } else {
                    System.out.println("Sort order: DESC");
                    Collections.sort(listOrder, (o1, o2) -> {
                        return o2.getCustomerName().compareToIgnoreCase(o1.getCustomerName());
                    });
                }
                break;

            case 4:
                System.out.println("Sorted by: Order Total");
                if (choiceType == 1) {
                    System.out.println("Sort order: ASC");
                    Collections.sort(listOrder, (o1, o2) -> {
                        return (int) (o1.getTotal() - o2.getTotal());
                    });
                } else {
                    System.out.println("Sort order: DESC");
                    Collections.sort(listOrder, (o1, o2) -> {
                        return (int) (o2.getTotal() - o1.getTotal());
                    });
                }
                break;
        }

        int stt = 1;
        int countFlower = 0;
        double totalPrice = 0;
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
        System.out.println("| NO  | ORDER ID |  ORDER DATE   |      CUSTOMER      |FLOWER COUNT |ORDER TOTAL($) |");
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
        for (OrderHeader o : listOrder) {
            countFlower += o.getQuantity();
            totalPrice += o.getTotal();
            System.out.printf("|%-5d|%-10s|%-15s|%-20s|%13d|%15.1f|\n", stt++, o.getOrderID(), o.getOrderDate(), o.getCustomerName(), o.getQuantity(), o.getTotal());
        }
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
        System.out.println("|     |TOTAL     |               |                    |            " + countFlower + "|         " + totalPrice + "|");
        System.out.println("+-----+----------+---------------+--------------------+-------------+---------------+");
    }

    public String formatDate(String date) {
        String txt[] = date.split("/");
        if (txt[1].length() == 1) {
            txt[1] = "0" + txt[1];
        }
        if (txt[0].length() == 1) {
            txt[0] = "0" + txt[0];
        }
        String newDate = txt[2] + txt[1] + txt[0];
        return newDate;
    }

    public void saveData() {
        boolean checkOrder = true;
        try {
            FileOutputStream fos = new FileOutputStream("orders.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listOrder);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Save data to orders.dat fail!");
            checkOrder = false;
        }
        if (checkOrder) {
            System.out.println("Save data to orders.dat successfully!");
        }
    }

    public void loadData() {
        listOrder.clear();
        boolean checkOrder = true;
        try {
            FileInputStream fis = new FileInputStream("orders.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listOrder = (ArrayList<OrderHeader>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load data from orders.dat fail!");
            checkOrder = false;
        }
        if (checkOrder) {
            System.out.println("Load data from orders.dat successfully!");
        }
    }
}
