/**
 * CustomerInterface.java
 * @author Your name
 * @author Partner's name
 * CIS 22C, Applied Lab 4
 */
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInterface {
    public static void main(String[] args) throws IOException {
        final int NUM_MUTUAL_FUNDS = 7;
        final int NUM_CUSTOMERS = 100;
        HashTable<MutualFund> funds = new HashTable<>(NUM_MUTUAL_FUNDS * 2);
        HashTable<Customer> customers = new HashTable<>(NUM_CUSTOMERS);

        DecimalFormat df = new DecimalFormat("###,##0.00");

        String first, last, email, password, mutualName, ticker;
        double cash, sharePrice, numShares, fee;

        File file1 = new File("mutual_funds.txt");
        File file2 = new File("customers.txt");

        CustomerInterface main = new CustomerInterface();
        main.readFunds(file1, funds);
        main.readCus(file2, customers, funds);

//
//        System.out.println(funds);
//        System.out.println(customers);


        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");

        System.out.print("Please enter your email address: ");
        email = sc.next();
        System.out.print("Please enter your password: ");
        password = sc.next();

        Customer cus;
        if(!customers.contains(new Customer(email, password)))
        {
            System.out.println("\nWe don't have your account on file...");
            System.out.print("\nLet's create an account for you!\n" +
                    "Enter your first name: ");
            first = sc.next();
            System.out.println("Enter your last name: ");
            last = sc.next();

            cus = new Customer(first, last, email, password);
            customers.add(cus);
        }
        else {
            cus = customers.get(new Customer(email, password));
            first = cus.getFirstName();
            last = cus.getLastName();
        }
        System.out.println("Welcome, " + first + " " + last + "!\n\n");



        String input = "";
        while(!input.equals("X"))
        {
            System.out.print("Please select from the following options:\n\n" +
                    "A. Purchase a Fund\n" +
                    "B. Sell a Fund\n" +
                    "C. Add Cash\n" +
                    "D. Display Your Current Funds\n" +
                    "X. Exit\n\n" +
                    "Enter your choice: ");

            input = sc.next().trim();
//            System.out.println(input);
//            System.out.println(input);
            if(input.equals("A")) {

            //                sc.nextLine();
                System.out.println("\nPlease select from the options below:\n");
                System.out.print(funds.toString());
                System.out.print("Enter the ticker of the fund to purchase: ");
                ticker = sc.next();

                System.out.print("\nEnter the number of shares to purchase: ");
            //                System.out.println(ticker);
                int shares = sc.nextInt();

                if (cus.addFund(shares, funds.get(new MutualFund(ticker)))) {
                    System.out.println("\nYou successfully added shares of the following fund:\n" +
                            funds.get(new MutualFund(ticker)) +
                            "Number of shares added: " + shares + ".0\n");

                                /*
                                        "\n" +
                                        "U.S. Growth\n" +
                                        "VWUSX\n" +
                                        "Share Price: $62.41\n" +
                                        "Trading Fee: 0.39%\n" +
                                        "Number of shares added: 1.0");

                                 */

                } else {
                    System.out.println("\n" +
                            "You don't have enough cash to purchase that fund.\n" +
                            "Please add cash to make a purchase\n");


            //                System.out.println("-------------------------\n");
            //                System.out.println(accountName.inOrderString());
            //                System.out.println("-------------------------\n");
            //                System.out.println(accountValue.inOrderString());
            //                System.out.println("-------------------------\n");


                }
            }
            else if(input.equals("B")) {
                if(!cus.hasOpenAccounts())
                    System.out.println("\nYou don't have any funds to sell at this time.\n");
                else {
                    System.out.println("You own the following mutual funds:\n");
                    cus.printAccountsByName();
                    System.out.print("Enter the name of the fund to sell: ");
                    sc.nextLine();
                    String fund = sc.nextLine();
                    if(cus.getAccountByName(fund) != null ) {
//                    System.out.println("fund: \"" + fund + "\"");
                        System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");

                        if (!sc.hasNextInt()) {
                            sc.next();
//
                            cus.sellFund(fund);
                        } else {
                            int sub = sc.nextInt();
                            System.out.println();
                            cus.sellShares(fund, sub);
                        }
                        System.out.println("You own the following funds:");
                        cus.printAccountsByName();
                        System.out.println("Your current cash balance is $" + df.format(cus.getCash()));
                    }
                    else
                        System.out.println("Sorry you don't own an account by that name.");
                }
            }
            else if(input.equals("C")) {
                System.out.println("Your current cash balance is $" + df.format(cus.getCash()) + "\n");
                System.out.print("Enter the amount of cash to add: $");
                String add = sc.next();

                cus.updateCash(Double.parseDouble(add));

                System.out.println("Your current cash balance is $" + df.format(cus.getCash()) + "\n");

            }
            else if(input.equals("D")) {

                    if (!cus.hasOpenAccounts())
                        System.out.println("\nYou don't have any funds to display at this time.\n");
                    else {
                        System.out.print("View Your Mutual Funds By:\n\n1. Name\n2. Value\n Enter your choice (1 or 2): ");
                        String temp = sc.next();
                        int index = Integer.parseInt(temp);
                        if (index == 1) {
                            System.out.println();
                            cus.printAccountsByName();
                        } else if (index == 2) {
                            System.out.println();
                            cus.printAccountsByValue();
                        } else
                            System.out.println("\n Invalid Choice!\n");
                    }

            }
            else if(input.equals("X")) {
                System.out.println("\nGoodbye!");
            }
            else {
//                System.out.println(input);
                System.out.println("\nInvalid menu option. Please enter A-D or X to exit.\n");
            }
        }



    }

    public void readCus(File file, HashTable<Customer> cus, HashTable<MutualFund> funds)
    {
        /*
            Name
            Email
            Password
            Cash balance
            Total number of mutual funds accounts for this customer
            The ticker symbol for that number of mutual funds
            Number of shares for each associated mutual fund
         */
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String first, last, email, pass;
            int cash;
            boolean end = false;
            while (!end) {
                first = reader.readLine();
                if(first != null) {
//                    System.out.println("Name: " + first + "   " + first.indexOf(" "));
                    last = first.substring(first.indexOf(" ") + 1);
                    first = first.substring(0, first.indexOf(" "));
                    email = reader.readLine();
                    pass = reader.readLine();
                    cash = Integer.parseInt(reader.readLine());

                    int num = Integer.parseInt(reader.readLine());
                    ArrayList<MutualFundAccount> list = new ArrayList<>();
                    for(int i = 0; i < num; i++)
                    {
                        String tick = reader.readLine();
                        int shares = Integer.parseInt(reader.readLine());
                        MutualFundAccount mfa = new MutualFundAccount(funds.get(new MutualFund(tick)), shares);
                        list.add(mfa);
                    }
                    reader.readLine();
                    Customer per = new Customer(first, last, email, pass, cash, list);
                    cus.add(per);
                }
                else
                    end = true;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public void readFunds(File file, HashTable<MutualFund> funds)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String name, tick;
            double price, fee;
            boolean end = false;
            while (!end) {
                name = reader.readLine();
                if(name != null) {
                    tick = reader.readLine();
                    price = Double.parseDouble(reader.readLine());
                    fee = Double.parseDouble(reader.readLine());
                    funds.add(new MutualFund(name, tick, price, fee));
                }
                else
                    end = true;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

