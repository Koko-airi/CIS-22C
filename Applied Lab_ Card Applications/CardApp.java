/**
 * CardApp.java
 *
 * @author Michael
 * CIS 22C, Applied Lab 1
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CardApp {
    private LinkedList<Card> list;

    /**
     * User interface prompts user, reads and writes files.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CardApp card = new CardApp();
        System.out.print("Enter the name of a file containing a deck of cards:");
//        String fileName = "cards1.txt";
        String fileName = sc.nextLine();
        card.fill(fileName);


        card.shuffle();
        card.printDeck("shuffled.txt");
        card.sort();
        card.printDeck("sorted.txt");

        System.out.print("Please open shuffled.txt and sorted.txt.");


    }

    public void printDeck(String fileName) {
        list.positionIterator();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < list.getLength(); i++) {
                writer.append(list.getIterator().toString() + "\n");
                list.advanceIterator();
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Default constructor to initialize the deck
     */
    public CardApp() {
        list = new LinkedList<>();
    }

    public void fill(String fileName) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {

                String rank = line.substring(0, line.length() - 1);
                String suit = line.charAt(line.length() - 1) + "";
                Card temp = new Card(rank, suit);


                addCard(temp);

                line = reader.readLine();


            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        System.out.println(toString());
    }


    /**
     * Inserts a new Card into the deck
     *
     * @param card a playing Card
     */
    public void addCard(Card card) {
        list.addLast(card);

    }

    /**
     * Shuffles cards following this algorithm:
     * First swaps first and last card
     * <p>
     * Next, swaps every even card with the card 3
     * nodes away from that card. Stops when it
     * reaches the 3rd to last node
     * <p>
     * Then, swaps ALL cards with the card that is
     * 2 nodes away from it, starting at the 2nd card
     * and stopping stopping at the 3rd to last node
     */
    public void shuffle() {

        final int THREE = 3;

        Card[] temp = new Card[list.getLength()];
        list.positionIterator();

        for (int i = 0; i < list.getLength(); i++) {
            temp[i] = list.getIterator();
            list.advanceIterator();
        }


        swap(temp, 0, temp.length - 1);


        for (int i = 1; i < temp.length - THREE; i += 2) {
            swap(temp, i, i + THREE);
        }

        for (int i = 1; i < temp.length - 2; i++) {
            swap(temp, i, i + 2);
        }

        list.clear();
        for (int i = 0; i < temp.length; i++) {
            addCard(temp[i]);
        }

    }

    public void swap(Card[] arr, int i, int j) {
        Card temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Implements the bubble sort algorithm
     * to sort cardList into sorted order, first by suit
     * (alphabetical order)
     * then by rank from 2 to A
     */
    public void sort() {
        Card[] temp = new Card[list.getLength()];
        list.positionIterator();

        for (int i = 0; i < list.getLength(); i++) {
            temp[i] = list.getIterator();
            list.advanceIterator();
        }


        for (int i = 0; i < temp.length - 1; i++) {
            for (int j = 0; j < temp.length - i - 1; j++) {

                if (temp[j].compareTo(temp[j + 1]) > 0) {

                    Card tempElement = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempElement;
                }
            }
        }

        list.clear();
        for (int i = 0; i < temp.length; i++) {
            addCard(temp[i]);
        }
    }


    /**
     * Returns the deck of cards with each card separated
     * by a blank space and a new line character at the end.
     *
     * @return The deck of cards as a string.
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
