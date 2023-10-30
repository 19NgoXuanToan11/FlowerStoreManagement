package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Scanner;

public class Validation {
    public static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String inputMsg, String errorMsg, int min) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static Double getADouble(String inputMsg, String errorMsg, double min) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < min) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String id;
        while (true) {
            try {
                System.out.print(inputMsg);
                id = sc.nextLine().trim();
                if (id.length() == 0 || id.isEmpty()) {
                    throw new Exception();
                }
                return id;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getRegexString(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            try {
                System.out.print(inputMsg);
                id = sc.nextLine().trim();
                match = id.matches(format);
                if (id.isEmpty() || id.length() == 0 || match == false) {
                    throw new Exception();
                }
                return id;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getDate(String inputMsg, String errorMsg) {
        String data;
        while (true) {
            System.out.print(inputMsg);
            data = sc.nextLine().trim();
            try {
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                date.setLenient(false);
                date.parse(data);
                return data;
            } catch (ParseException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int updateInteger(String inputMsg, int min, int oldData) {
        int number = oldData;
        boolean check = true;
        do {
            try {
                System.out.print(inputMsg);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!");
            }
        } while (check == true || number < min);
        return number;
    }

    public static double updateDouble(String inputMsg, double min, double oldData) {
        double number = oldData;
        boolean check = true;
        do {
            try {
                System.out.print(inputMsg);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!");
            }
        } while (check == true || number < min);
        return number;
    }

    public static String updateString(String inputMsg, String oldData) {
        String result = oldData;
        System.out.print(inputMsg);
        String tmp = sc.nextLine().trim();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static String updateDescription(String inputMsg, String oldData) {
        String result = oldData;
        System.out.print(inputMsg);
        String tmp = sc.nextLine().trim();
        if (!tmp.isEmpty()) {
            boolean check = true;
            if (tmp.length() < 3 || tmp.length() > 50) {
                System.out.println("The description field must be from 3 to 50 characters!");
                check = false;
            }

            if (check == false) {
                String description;
                do {
                    description = util.Validation.getString("Input new description: ", "All the fields are not allowed null!");
                    if (description.length() < 3 || description.length() > 50) {
                        System.out.println("All fields are not allowed null and the length of the department field must be from 3 to 50 characters!");
                    }
                } while (description.length() < 3 || description.length() > 50);
                result = description;
            } else {
                result = tmp;
            }
        }
        return result;
    }

    public static String updateDate(String inputMsg, String oldData) {
        String result = oldData;
        System.out.print(inputMsg);
        String tmp = sc.nextLine().trim();
        if (!tmp.isEmpty()) {
            boolean check = true;
            try {
                SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                date.setLenient(false);
                date.parse(tmp);
                return tmp;
            } catch (ParseException e) {
                System.out.println("Error!");
                check = false;
            }
            
            if (check == false) {
                String date = getDate("Input new date: ", "Error!");
                result = date;
            } else {
                result = tmp;
            }
        }
        return result;
    }
}
