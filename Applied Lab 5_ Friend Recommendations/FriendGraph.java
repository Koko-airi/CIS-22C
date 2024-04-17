/**
 * FriendGraph.java
 *
 * @author Michael Chen
 * CIS 22C, Applied Lab 5
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FriendGraph {
    Scanner sc;
    Graph uf;
    ArrayList<String> names;
    boolean[] friend;

    /**
     * Main method for the program.
     */
    public static void main(String[] args) {
        FriendGraph kg = new FriendGraph();
        kg.run();
        System.out.print("Goodbye!");
    }

    /**
     * runs
     */
    public void run() {
        System.out.println("Welcome to the Friend Recommender!\n");
        System.out.print("Enter the name of a file:");
        sc = new Scanner(System.in);
        String file = sc.nextLine();


        readFile(file);

//        System.out.println(uf);

        System.out.println("Enter your user number below:");
        printUsers();
        System.out.print("\nEnter your choice: ");

        int user = sc.nextInt();

        LinkedList<Integer> temp = uf.getAdjacencyList(user);
        temp.positionIterator();
        while (!temp.offEnd()) {
            int next = temp.getIterator();
            friend[next - 1] = true;
            temp.advanceIterator();
        }
        friend[user - 1] = true;


        if (display(user) == -1)
            return;


        int input = 0;
        while (input != -1) {

            System.out.print("\nEnter the number of a friend to add or -1 to quit:\n" +
                    "Enter your choice: ");

            input = sc.nextInt();
            if (input != -1) {
                uf.addUndirectedEdge(user, input);
                friend[input - 1] = true;
                input = display(user);
            } else {

            }

        }


    }


    /**
     * fish
     * @param user
     * @return
     */
    public int display(int user) {
        System.out.println("\nHere are your current friends:");
        LinkedList<Integer> temp = uf.getAdjacencyList(user);

        temp.positionIterator();
        while (!temp.offEnd()) {
            int next = temp.getIterator();
            System.out.println(next + ". " + names.get(next));

            temp.advanceIterator();
        }


        boolean work = false;
        System.out.println("\nHere are your recommended friends:");
        uf.BFS(user);
        for (int i = 1; i < names.size(); i++) {
            if (uf.getColor(i) == 'B' && !friend[i - 1]) {
                System.out.println(i + ". " + names.get(i));
                work = true;
            }

        }
        if (!work) {
            System.out.println("Sorry! We don't have any recommendations for you at this time.");
            return -1;
        }
        return 0;
    }

    /**
     * fish
     */
    public void printUsers() {
        for (int i = 1; i < names.size(); i++) {
            System.out.println(i + ". " + names.get(i));
        }
    }


    /**
     * does stuff
     * @param file
     */
    public void readFile(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int size = Integer.parseInt(reader.readLine());
            uf = new Graph(size);
            names = new ArrayList<>();
            friend = new boolean[size + 1];

            for (int i = -1; i < size; i++)
                names.add("");


            boolean end = false;
            while (!end) {
                String input = reader.readLine();
                if (input != null) {
                    int id = Integer.parseInt(input);
                    names.set(id, reader.readLine());
                    String temp = reader.readLine();
                    StringTokenizer st = new StringTokenizer(temp);
                    while (st.hasMoreElements()) {
                        String friend = st.nextToken();
                        uf.addDirectedEdge(id, Integer.parseInt(friend.substring(0, friend.length() - 1)));
                    }
                } else
                    end = true;
            }
        } catch (IOException ex) {
            System.out.print("Invalid file name\n" +
                    "Enter the name of a file: ");
            readFile(sc.nextLine());
        }

    }
}
