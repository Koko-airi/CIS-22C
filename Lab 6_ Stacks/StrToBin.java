import java.util.Scanner;

public class StrToBin {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        StrToBin k = new StrToBin();
        for(int i = 0 ; i < 3 ; i++)
            System.out.println("Value: " + k.BinToInt(k.getInput("Enter Binary: ")));
        System.out.println();
        for(int i = 0 ; i < 3 ; i++)
            System.out.println("Value: " + k.HexToInt(k.getInput("Enter Hex: ").toUpperCase()));
    }

    public int BinToInt(String bin) {
        bin = bin.replaceAll("\\s+", "");  // removes whitespace
                                                        // makes "1234 1234 1234" -> "123412341234"

        int ans = 0;
        for (char i : bin.toCharArray()) {
            ans = ans << 1;   //ans *= 2;
            if (i == '1') {
                ans++;
            }
        }
        return ans;
    }

    public int HexToInt(String hex) {
        hex = hex.replaceAll("\\s+", "");  // removes whitespace
                                                        // makes "ABCD ABCD" -> "ABCDABCD"

        int ans = 0;
        for (char i : hex.toCharArray()) {
            ans *= 16;
            if (Character.isAlphabetic(i)) {
                ans += i - 'A' + 10;
            } else
                ans += i - '0';
        }
        return ans;
    }

    public String getInput(String prompt)
    {
        System.out.print(prompt);
        return sc.nextLine();
    }
}