/**
 * The HTML Parser class definition
 *
 * @author Michael
 * CIS 22C, Lab 9.1
 */

import java.io.*;
import java.util.Scanner;

public class HTMLParser {
    Stack<String> stack = new Stack<>();

    /**
     * User interface prompts user
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        HTMLParser card = new HTMLParser();
        System.out.println("Enter the name of the file to check: ");
//        String fileName = "invalid.html";
        String fileName = sc.nextLine();
        System.out.println("Expected Actual");

        card.fill(fileName);

    }

    /**
     * User interface prompts user
     *
     * @param fileName the file to process
     */
    public void fill(String fileName) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            while (line != null) {

                processLine(line);


                line = reader.readLine();


            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Please revise your html and try again.");
            return;
        }

        System.out.println("All tags match. Nice work!");
    }


    /**
     * Searches for tags in String
     *
     * @param line the input String
     */
    public void processLine(String line) {
        line = line.toLowerCase();
        if (line.indexOf("<") == -1)
            return;
        int start = line.indexOf("<");
        int end = line.indexOf(">");

        while (start != -1) {
            String temp = line.substring(start + 1, min(line, start));

            if (temp.charAt(0) == '/') {
                temp = temp.substring(1);
                if (!stack.peek().equals(temp)) {
                    System.out.println("<" + temp + "> <" + stack.peek() + ">");
                    System.out.println("No matching tag for <" + stack.peek() + ">");
                    throw new NullPointerException();
                } else {
                    System.out.println("<" + temp + "> <" + temp + ">");
                    stack.pop();
                }
            } else {
                if (temp.equals("br") || temp.equals("img") || temp.equals("hr")
                        || temp.equals("meta") || temp.equals("!DOCTYPE"));
                else
                    stack.push(temp);
            }


            start = line.indexOf("<", start + 1);
            end = line.indexOf(">", end + 1);

        }

    }

    /**
     * Finds the end of the tag
     *
     * @param line the string to search
     * @param start the index to start searching
     * @return index of the end tag
     */
    public int min(String line, int start) {

        int temp = line.indexOf(" ", start);
        if (temp == -1)
            return line.indexOf(">", start);
        return Math.min(temp, line.indexOf(">", start));
    }
}
