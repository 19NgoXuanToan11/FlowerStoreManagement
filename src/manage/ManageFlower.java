package manage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import obj.Flower;
import obj.OrderHeader;

public class ManageFlower {
    private Set<Flower> listFlower = new HashSet<>();
    
    public Set<Flower> getListFlower() {
        return listFlower;
    }

    public void addFlower() {
        boolean checkExist;
        String id, description, confirm;
        do {
            do {
                id = util.Validation.getRegexString("Input ID(Fxxxx): ", "Wrong format. Ex: F0001,...", "^[F|f]\\d{4}$");
                checkExist = checkIDOfFlower(id);
                if (checkExist) {
                    System.out.println("Duplicated ID. Please try again!");
                }
            } while (checkExist);

            String name = util.Validation.getString("Input name: ", "ALl the fields are not allowed null!");

            do {
                description = util.Validation.getString("Input description: ", "All fields are not allowed null and the length of the department field must be from 3 to 50 characters!");
                if (description.length() < 3 || description.length() > 50) {
                    System.out.println("All fields are not allowed null and the length of the department field must be from 3 to 50 characters!");
                }
            } while (description.length() < 3 || description.length() > 50);

            String importDate = util.Validation.getDate("Input date(dd/MM/yyyy): ", "Error!");

            double price = util.Validation.getADouble("Input price: ", "Must be a number larger than 0 and all fields are not allowed null!", 0);

            String category = util.Validation.getString("Input category: ", "All the fields are not allowed null!");

            listFlower.add(new Flower(id, name, description, importDate, price, category));
            System.out.println("Added flower successfully!");

            confirm = util.Validation.getRegexString("Do you want to continue? (Y/N): ", "Just Y(y) or N(n)", "[Y|y|n|N]$");
        } while (confirm.equalsIgnoreCase("Y"));
    }

    public boolean checkIDOfFlower(String idFromKeyBoard) {
        boolean check = false;
        for (Flower x : listFlower) {
            if (x.getId().equals(idFromKeyBoard)) {
                check = true;
            }
        }
        return check;
    }

    public void findFlower() {
        for (Flower f : listFlower) {
            System.out.println(f.toString());
        }
        String search = util.Validation.getString("Input name to search: ", "All fields are not allowed null!");
        boolean check = false;
        for (Flower f : listFlower) {
            if (search.equalsIgnoreCase(f.getId()) || search.equalsIgnoreCase(f.getName())) {
                System.out.println(f.toString());
                check = true;
            }
        }
        if (!check) {
            System.out.println("The flower " + search + " does not exist!");
        }
    }

    public void updateFlower() {
        for (Flower f : listFlower) {
            System.out.println(f.toString());
        }
        boolean check = false;
        String name = util.Validation.getString("Input name you want update: ", "All the fields are not allowed null!");
        for (Flower f : listFlower) {
            if (f.getName().equalsIgnoreCase(name)) {
                String newName = util.Validation.updateString("Input new name: ", f.getName());
                String newDescription = util.Validation.updateDescription("Input new description: ", f.getDescription());
                String newImportDate = util.Validation.updateDate("Input new date: ", f.getImportDate());
                double newPrice = util.Validation.updateDouble("Input new description: ", 0, f.getUnitPrice());
                String newCategory = util.Validation.updateString("Input category: ", f.getCategory());
                f.setName(newName);
                f.setDescription(newDescription);
                f.setImportDate(newImportDate);
                f.setUnitPrice(newPrice);
                f.setCategory(newCategory);
                check = true;
            }
        }
        System.out.println("Updated successfully!");
        if (!check) {
            System.out.println("The flower does not exist!");
        }
    }

    public void deleteFlower() {
        ManageOrder mo = new ManageOrder(this);
        ArrayList<OrderHeader> listOrder = mo.getListOrder();
        boolean check = true;
        String id = util.Validation.getRegexString("Input ID(Fxxxx) to delete: ", "Wrong format. Ex: F0001,...", "^[F|f]\\d{4}$");
        for (Flower f : listFlower) {
            if (f.getId().equalsIgnoreCase(id)) {
                check = true;
                String confirm = util.Validation.getRegexString("Are you sure want to delete? (y/n): ", "Just y or n!", "^[Y|y|n|N]$");
                if (confirm.equalsIgnoreCase("Y")) {
                    boolean checkExist = false;
                    for (OrderHeader o : listOrder) {
                        for (int i = 0; i < o.getListOfDetail().size(); i++) {
                            if (o.getListOfDetail().get(i).getFlowerId().equalsIgnoreCase(id)) {
                                checkExist = true;
                            }
                        }
                    } 
                    if (checkExist == true) {
                        System.out.println("This flower has already been purchased, so it cannot be deleted");
                    } else {
                        listFlower.remove(f);
                        System.out.println("Deleted successfully!");
                        break;
                    }
                } else {
                    System.out.println("You canceled!");
                }
            }
        }
        if (check == false) {
            System.out.println("The flower does not exist!");
        }
    }

    public void saveData() {
        boolean checkFlower = true;
        try {
            FileOutputStream fos = new FileOutputStream("flowers.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listFlower);
            oos.close();     
            fos.close();
        } catch (IOException e) {
            System.out.println("Save data to flowers.dat fail!");
            checkFlower = false;
        }
        if (checkFlower) {
            System.out.println("Save data to flowers.dat successfully!");
        }
    }

    public void loadData() {
        listFlower.clear();
        boolean checkFlower = false;
        try {
            FileInputStream fis = new FileInputStream("flowers.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listFlower = (HashSet<Flower>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Load data from flowers.dat fail!");
            checkFlower = false;
        }
        if (checkFlower) {
            System.out.println("Load data from flowers.dat successfully!");
        }
    }
}
