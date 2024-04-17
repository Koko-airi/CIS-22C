/**
 * CustomerInterface.java
 * @author Michael
 * CIS 22C, Applied Lab 3
 */
import java.io.*;
import java.util.Scanner;

public class CustomerInterface {
    public static void main(String[] args) throws FileNotFoundException {
        BST<MutualFundAccount> accountValue = new BST<>();
        BST<MutualFundAccount> accountName = new BST<>();
        LinkedList<MutualFund> funds = new LinkedList<>();


        String mutualName, ticker; //
        //String first, last, email, password; //<-to be used in lab 6
        double sharePrice, numShares;

        CustomerInterface k = new CustomerInterface();
        k.readFile("mutual_funds.txt", funds);

//        System.out.println(funds.numberedListString());
        System.out.println("Welcome to Mutual Fund InvestorTrack (TM)!\n");

        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
        }catch(Exception ex){}


        String input = "";
        while(!input.equals("X"))
        {
            System.out.print("Please select from the following options:\n\n" +
                    "A. Purchase a Fund\n" +
                    "B. Sell a Fund\n" +
                    "C. Display Your Current Funds\n" +
                    "X. Exit\n\n" +
                    "Enter your choice: ");

            input = sc.next().trim();
//            System.out.println(input);
            if(input.equals("A")) {
                System.out.println("\nPlease select from the options below:\n");
                System.out.print(funds.numberedListString());
                System.out.print("Enter your choice: (1-7): ");
                int index = sc.nextInt();
//                int index = Integer.parseInt(temp);

                System.out.print("\nEnter the number of shares to purchase: ");
                int shares = sc.nextInt();
//                int shares = Integer.parseInt(temp);

                funds.advanceIteratorToIndex(index - 1);
                MutualFundAccount fund = new MutualFundAccount(funds.getIterator(), shares);
                accountName.insert(fund, new NameComparator());
                accountValue.insert(fund, new ValueComparator());
//                System.out.println("-------------------------\n");
//                System.out.println(accountName.inOrderString());
//                System.out.println("-------------------------\n");
//                System.out.println(accountValue.inOrderString());
//                System.out.println("-------------------------\n");
            }
            else if(input.equals("B")) {
                if(accountName.getSize() == 0)
                    System.out.println("\nYou don't have any funds to sell at this time.\n");
                else {
                    System.out.println("You own the following mutual funds:");
                    System.out.println(accountName.inOrderString());
                    System.out.print("Enter the name of the fund to sell: ");
                    sc.nextLine();
                    String fund = sc.nextLine();
//                    System.out.println("fund: \"" + fund + "\"");
                    System.out.print("Enter the number of shares to sell or \"all\" to sell everything: ");
                    if (!sc.hasNextInt()) {
//                        System.out.println(fund + "   " + sc.next());
//                        int temp = sc.nextInt;
//                    }
//                    if (temp.equalsIgnoreCase("all")) {
//                        System.out.println("----------------------\n" +
//                                "Entered: \"" + fund + "\"\n" +
//                                "found? " + accountName.search(new MutualFundAccount(new MutualFund(fund)), new NameComparator()) +
//                                "\"----------------------\"");
                        sc.nextLine();
                        MutualFundAccount copy = new MutualFundAccount(new MutualFund(fund));
                        MutualFundAccount mfa = accountName.search(copy, new NameComparator());

//                        System.out.println(mfa);
                        accountName.remove(mfa, new NameComparator());
                        accountValue.remove(mfa, new ValueComparator());
                    } else {
                        int sub = sc.nextInt();
//                        System.out.println(fund + "   " +sub+"here");
                        MutualFundAccount copy = new MutualFundAccount(new MutualFund(fund));
                        MutualFundAccount mfa = accountName.search(copy, new NameComparator());

                        if (sub < mfa.getNumShares()) {
                            mfa.updateShares(sub * -1);
                            accountValue.remove(mfa, new ValueComparator());
                            accountValue.insert(mfa, new ValueComparator());
                        } else {
                            accountName.remove(mfa, new NameComparator());
                            accountValue.remove(mfa, new ValueComparator());
                        }
                    }
                }
            }
            else if(input.equals("C")) {
                if (accountName.getSize() == 0)
                    System.out.println("\nYou don't have any funds to display at this time.\n");
                else {
                    System.out.print("View Your Mutual Funds By:\n\n1. Name\n2. Value\n Enter your choice (1 or 2): ");
                    String temp = sc.next();
                    int index = Integer.parseInt(temp);
                    if (index == 1) {
                        System.out.println();
                        System.out.println(accountName.inOrderString());
                    } else if (index == 2) {
                        System.out.println();
                        System.out.println(accountValue.inOrderString());
                    } else
                        System.out.println("\n Invalid Choice!\n");
                }
            }
            else if(input.equals("X")) {
                System.out.println("\nGoodbye!");
            }
            else
            {
//                System.out.println(input);
                System.out.println("Invalid menu option. Please enter A-C or X to exit.");
            }
        }


        sc.close();
    }



    public void readFile(String fileName, LinkedList<MutualFund> funds)
    {

        File file = new File("mutual_funds.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String name, tick;
            double price;
            boolean end = false;
            while (!end) {
                name = reader.readLine();
                if(name != null) {
                    tick = reader.readLine();
                    price = Double.parseDouble(reader.readLine());
                    funds.addLast(doSomething(name, tick, price));
                }
                else
                    end = true;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public MutualFund doSomething(String name, String ticker, double price)
    {
        return new MutualFund(name, ticker, price);
    }
}
