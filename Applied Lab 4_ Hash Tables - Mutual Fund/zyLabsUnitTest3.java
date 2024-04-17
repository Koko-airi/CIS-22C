// Following for main()
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;
// Following for console output redirection
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//---copy below this line---
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.lang.reflect.*; // Counting method names, fields
import java.util.ArrayList;

// Unit test most assigned methods for Customer
public class zyLabsUnitTest3 { // remove number after name
    private final int SIZE = 10;
    private PrintWriter testFeedback; // for zyLab

    public int passed(PrintWriter testFeedback) {
        this.testFeedback = testFeedback;
        double ptsEarned = 0, ptVal = 0, numTst = 0, ptsPerTest = 0;
        int cntErr = 0;

        // Test constructors x3 and accessors
        msg("**Testing constructors and accessors**");
        ptVal = 3;
        numTst = 26;
        ptsPerTest = ptVal / numTst;
        msg("Calling testCustomerEmailPassword()");
        cntErr += testCustomerEmailPassword();  // 8 tests
        msg("Calling testCustomer4Arg()");
        cntErr += testCustomer4Arg(); // 8 tests
        msg("Calling testCustomer6Arg()");
        cntErr += testCustomer6Arg(); // 10 tests
        ptsEarned += Math.rint(ptVal - cntErr * ptsPerTest);

        // Test mutators x 8
        msg("**Testing mutators**");
        ptVal = 4;
        numTst = 25;
        ptsPerTest = ptVal / numTst;
        cntErr = 0;
        msg("Calling testSetFirstName()");
        cntErr += testSetFirstName(); // 2 tests
        msg("Calling testSetLastName()");
        cntErr += testSetLastName(); // 2 tests
        msg("Calling testSetEmail()");
        cntErr += testSetEmail(); // 2 tests
        msg("Calling testSetPassword()");
        cntErr += testSetPassword(); // 3 tests
        msg("Calling testUpdateCash()");
        cntErr += testUpdateCash(); // 3 tests
        msg("Calling testAddFund()");
        cntErr += testAddFund(); // 7 tests
        msg("Calling testSellFund()");
        cntErr += testSellFund(); // 3 tests
        msg("Calling testSellShares()");
        cntErr += testSellShares(); // 3 tests
        ptsEarned += Math.rint(ptVal - cntErr * ptsPerTest);

        // Test other x 3
        msg("**Testing Additional Operations**");
        ptVal = 3;
        numTst = 9;
        ptsPerTest = ptVal / numTst;
        cntErr = 0;
        msg("Calling testToString()");
        cntErr += testToString(); // 1 test
        msg("Calling testEqualsObject()");
        cntErr += testEqualsObject(); // 5 tests
        msg("Calling testHashCode()");
        cntErr += testHashCode(); // 3 tests
        ptsEarned += Math.rint(ptVal - cntErr * ptsPerTest);
        // Missing printAccountsByName(), printAccountsByValue()

        // Verify no added/extra/additional methods, variables or inner classes
        msg("Testing for added methods, fields or inner classes");
        final int NUM_METHODS = 9;
        final int NUM_FIELDS = 4;
        final int NUM_INNER_CLASSES = 0;
        try {
            Class<?> cls = Class.forName("MutualFund");
            Method methods[] = cls.getDeclaredMethods();
            //System.out.println("countMethods=" + methods.length);
            if (methods.length != NUM_METHODS) {
                msg("Must not add or remove MutualFund methods!");
                ptsEarned = 0;
            }
            Field fields[] = cls.getDeclaredFields();
            //System.out.println("countFields=" + fields.length);
            if (fields.length != NUM_FIELDS) {
                msg("Must not add or remove MutualFund fields!");
                ptsEarned = 0;
            }
            Class<?>[] innerClasses = HashTable.class.getDeclaredClasses();
            //System.out.println("countInnerClasses=" + innerClasses.length);
            if (innerClasses.length != NUM_INNER_CLASSES) {
                testFeedback.write("Must not add inner classes!\n");
                ptsEarned = 0;
            }
        } catch (Throwable e) {
            System.err.println(e);
        }

        return (int) (ptsEarned + 0.01); // round down
    }

    private void msg(String message) {
        testFeedback.write(message + "\n");
    }

    private int testCustomerEmailPassword() {
        int cntErr = 0;
        String str = "";
        Customer cust = new Customer("a@b.com", "folly");
        //assertEquals("first name unknown", cust.getFirstName());
        str =  cust.getFirstName();
        if (!str.equals("first name unknown")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getFirstName() "
                + "must return \"first name unknown\" not \"" + str + "\".");
            cntErr++;
        }
        //assertEquals("last name unknown", cust.getLastName());
        str =  cust.getLastName();
        if (!str.equals("last name unknown")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getLastName() "
                + "must return \"last name unknown\" not \"" + str + "\".");
            cntErr++;
        }
        //assertEquals("none", cust.getAccountNum());
        str =  cust.getAccountNum();
        if (!str.equals("none")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getAccountNum() "
                + "must return \"none\" not \"" + str + "\".");
            cntErr++;
        }
        //assertEquals("a@b.com", cust.getEmail());
        str =  cust.getEmail();
        if (!str.equals("a@b.com")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getEmail() "
                + "must return \"a@b.com\" not \"" + str + "\".");
            cntErr++;
        }
        //assertTrue(cust.passwordMatch("folly"));
        if (!cust.passwordMatch("folly")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") "
                + "passwordMatch(\"folly\") must return true not false.");
            cntErr++;
        }
        //assertFalse(cust.passwordMatch("abc123"));
        if (cust.passwordMatch("abc123")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") "
                + "passwordMatch(\"abc123\") must return false not true.");
            cntErr++;
        }
        //assertEquals(0.0, cust.getCash());
        double cash = cust.getCash();
        if (Math.abs(cash - 0.0) >= 0.001) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getCash() "
                + "must return 0.0 not " + cash);
            cntErr++;
        }
        //assertNull(cust.getAccountByName());
        MutualFundAccount mfa = cust.getAccountByName("name");
        if (mfa != null) {
            msg("-After new Customer(\"a@b.com\", \"folly\") "
                + "getAccountByName(\"name\") must return null not "
                + mfa + ".");
            cntErr++;
        }
        return cntErr;
    }

    private int testCustomer4Arg() {
        int cntErr = 0;
        String str = "";
        Customer cust = new Customer("Taylor", "Swift", "a@b.com", "tour");
        //assertEquals("Taylor", cust.getFirstName());
        str =  cust.getFirstName();
        System.out.println(cust);
        if (!str.equals("Taylor")) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " getFirstName() must return \"Taylor\" not \"" + str + "\".");
            cntErr++;
        }
        //assertEquals("Swift", cust.getLastName());
        str =  cust.getLastName();
        if (!str.equals("Swift")) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " getLastName() must return \"Swift\" not \"" + str + "\".");
            cntErr++;
        }
        //assertNotEquals("none", cust.getAccountNum());
        str =  cust.getAccountNum();
        if (str.equals("none")) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " getAccountNum() must NOT return \"none\".");
            cntErr++;
        }
        //assertEquals("a@b.com", cust.getEmail());
        str =  cust.getEmail();
        if (!str.equals("a@b.com")) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " getEmail() must return \"a@b.com\" not \"" + str + "\".");
            cntErr++;
        }
        //assertTrue(cust.passwordMatch("tour"));
        if (!cust.passwordMatch("tour")) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " passwordMatch(\"tour\") must return true not false.");
            cntErr++;
        }
        //assertFalse(cust.passwordMatch("abc123"));
        if (cust.passwordMatch("abc123")) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " passwordMatch(\"abc123\") must return false not true.");
            cntErr++;
        }
        //assertEquals(0.0, cust.getCash());
        double cash = cust.getCash();
        if (Math.abs(cash - 0.0) >= 0.001) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " getCash() must return 0.0 not " + cash);
            cntErr++;
        }
        //assertNull(cust.getAccountByName());
        MutualFundAccount mfa = cust.getAccountByName("Taylor");
        if (mfa != null) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " getAccountByName(\"Taylor\") must return null not " + mfa);
            cntErr++;
        }
        return cntErr;
    }

    private int testCustomer6Arg() {
        int cntErr = 0;
        String str = "";
        ArrayList<MutualFundAccount> al = new ArrayList<>();
        al.add(new MutualFundAccount(15, new MutualFund("U.S. Growth", "VWUSX")));
        Customer cust = new Customer("Xing", "Li", "a@b.com", "xyz", 123, al);
        //assertEquals("Taylor", cust.getFirstName());
        str =  cust.getFirstName();
        if (!str.equals("Xing")) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " getFirstName() must return \"Xing\" not \"" + str + "\".");
            cntErr++;
        }
        //assertEquals("Swift", cust.getLastName());
        str =  cust.getLastName();
        if (!str.equals("Li")) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " getLastName() must return \"Li\" not \"" + str + "\".");
            cntErr++;
        }
        //assertNotEquals("none", cust.getAccountNum());
        str =  cust.getAccountNum();
        if (str.equals("none")) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " getAccountNum() must NOT return \"none\".");
            cntErr++;
        }
        //assertEquals("a@b.com", cust.getEmail());
        str =  cust.getEmail();
        if (!str.equals("a@b.com")) {
            msg("-For new Customer(\"Taylor\", \"Swift\", \"a@b.com\", \"tour\")"
                + " getEmail() must return \"a@b.com\" not \"" + str + "\".");
            cntErr++;
        }
        //assertTrue(cust.passwordMatch("tour"));
        if (!cust.passwordMatch("xyz")) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " passwordMatch(\"xyz\") must return true not false.");
            cntErr++;
        }
        //assertFalse(cust.passwordMatch("abc123"));
        if (cust.passwordMatch("abc123")) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " passwordMatch(\"abc123\") must return false not true.");
            cntErr++;
        }
        //assertEquals(0.0, cust.getCash());
        double cash = cust.getCash();
        if (Math.abs(cash - 123.0) >= 0.001) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " getCash() must return 123.0 not " + cash);
            cntErr++;
        }
        //assertNull(cust.getAccountByName("Xing"));
        MutualFundAccount mfa = cust.getAccountByName("Xing");
        if (mfa != null) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " getAccountByName(\"Xing\") must return null not " + mfa);
            cntErr++;
        }
        mfa = cust.getAccountByName("U.S. Growth");
        if (mfa == null) {
            msg("-For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 123, al)"
                + " with MutualFundAccount containing MutualFund \"U.S. Growth\""
                + " getAccountByName(\"U.S. Growth\") must NOT return null");
        } else if (!mfa.getMf().getFundName().equals("U.S. Growth")) {
            msg("-For Customer with MutualFundAccount containing MutualFund("
                + "\"U.S. Growth\", \"VWUSX\"),\n  getAccountByName("
                + "\"U.S. Growth\") must return the  MutualFundAccount with\n"
                + "  fund name \"U.S. Growth\" NOT: \""
                + mfa.getMf().getFundName() + "\"");
            cntErr++;
        }
        return cntErr;
    }

    private int testSetFirstName() {
        int cntErr = 0;
        String str = "";
        Customer cust = new Customer("a@b.com", "folly");
        //assertEquals("first name unknown", cust.getFirstName());
        str =  cust.getFirstName();
        if (!str.equals("first name unknown")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getFirstName() "
                + "must return \"first name unknown\" not \"" + str + "\".");
            cntErr++;
        }
        cust.setFirstName("Xing");
        str =  cust.getFirstName();
        if (!str.equals("Xing")) {
            msg("-After setFirstName(\"Xing\"), getFirstName() "
                + "must return \"Xing\" not \"" + str + "\".");
            cntErr++;
        }
        return cntErr;
    }

    private int testSetLastName() {
        int cntErr = 0;
        String str = "";
        Customer cust = new Customer("a@b.com", "folly");
        //assertEquals("last name unknown", cust.getLastName());
        str =  cust.getLastName();
        if (!str.equals("last name unknown")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getLastName() "
                + "must return \"last name unknown\" not \"" + str + "\".");
            cntErr++;
        }
        cust.setLastName("Li");
        str =  cust.getLastName();
        if (!str.equals("Li")) {
            msg("-After setLastName(\"Li\"), getLastName() "
                + "must return \"Li\" not \"" + str + "\".");
            cntErr++;
        }
        return cntErr;
    }

    private int testSetEmail() {
        int cntErr = 0;
        String str = "";
        Customer cust = new Customer("a@b.com", "folly");
        //assertEquals("a@b.com", cust.getEmail());
        str =  cust.getEmail();
        if (!str.equals("a@b.com")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") getEmail() "
                + "must return \"a@b.com\" not \"" + str + "\".");
            cntErr++;
        }
        cust.setEmail("me@x.com");
        str =  cust.getEmail();
        if (!str.equals("me@x.com")) {
            msg("-After setEmail(\"me@x.com\"), getEmail() "
                + "must return \"me@x.com\" not \"" + str + "\".");
            cntErr++;
        }
        return cntErr;
    }

    private int testSetPassword() {
        int cntErr = 0;
        String str = "";
        Customer cust = new Customer("a@b.com", "folly");
        //assertTrue(cust.passwordMatch("folly"));
        if (!cust.passwordMatch("folly")) {
            msg("-After new Customer(\"a@b.com\", \"folly\") "
                + "passwordMatch(\"folly\") must return true not false.");
            cntErr++;
        }
        cust.setPassword("555!!!h");
        //assertFalse(cust.passwordMatch("555!!!h"));
        if (cust.passwordMatch("folly")) {
            msg("-After setPassword(\"555!!!h\") "
                + "passwordMatch(\"folly\") must return false not true.");
            cntErr++;
        }
        //assertTrue(cust.passwordMatch("555!!!h"));
        if (!cust.passwordMatch("555!!!h")) {
            msg("-After setPassword(\"555!!!h\") "
                + "passwordMatch(\"555!!!h\") must return true not false.");
            cntErr++;
        }
        return cntErr;
    }

    private int testUpdateCash() {
        int cntErr = 0;
        double cash = 0.0;
        Customer cust = new Customer("a@b.com", "folly");
        //assertEquals(0.0, cust.getCash());
        cash = cust.getCash();
        if (Math.abs(cash - 0.0) >= 0.001) {
            msg("-After new Customer(\"a@b.com\", \"folly\") "
                + "getCash() must return 0.0 not " + cash);
            cntErr++;
        }
        cust.updateCash(12.34);
        cash = cust.getCash();
        if (Math.abs(cash - 12.34) >= 0.001) {
            msg("-After updateCash(12.34), getCash() must return 12.34 not "
                + cash);
            cntErr++;
        }
        cust.updateCash(-1.00);
        cash = cust.getCash();
        if (Math.abs(cash - 11.34) >= 0.001) {
            msg("-After updateCash(12.34) then updateCash(-1.00), getCash() "
                + "must return 11.34 not " + cash);
            cntErr++;
        }
        return cntErr;
    }

    private int testAddFund() {
        int cntErr = 0;
        boolean added = false;
        Customer cust = new Customer("a@b.com", "folly");
        MutualFundAccount mfa = cust.getAccountByName("Test Fund");
        if (mfa != null) {
            msg("-After new Customer(\"a@b.com\", \"folly\") "
                + "getAccountByName(\"Test Fund\") must return null not "
                + mfa + ".");
            cntErr++;
        }
        added = cust.addFund(42.3, new MutualFund("Test Fund", "TEST"));
        if (!added) {
            msg("-addFund(42.3, new MutualFund(\"Test Fund\", \"TEST\"))"
                + " must return true not false.");
            cntErr++;
        }
        mfa = cust.getAccountByName("Test Fund");
        if (mfa == null) {
            msg("-After addFund(42.3, new MutualFund(\"Test Fund\", \"TEST\"))"
                + ", getAccountByName(\"Test Fund\") must\n  return the "
                + "MutualFundAccount named \"Test Fund\" NOT: null.");
            cntErr++;
        } else if (!mfa.getMf().getFundName().equals("Test Fund")) {
            msg("-After addFund(42.3, new MutualFund(\"Test Fund\", \"TEST\"))"
                + ", getAccountByName(\"Test Fund\") must\n  return the "
                + "MutualFundAccount named \"Test Fund\" NOT: \""
                + mfa.getMf().getFundName() + "\".");
            cntErr++;
        }
        MutualFund mf = new MutualFund("More Fund", "TST2", 1.23, 0.12);
        added = cust.addFund(10, mf);
        if (added) {
            msg("-addFund(10, MutualFund(\"More Fund\", \"TST2\", 1.23, "
                + "0.12)) must return false not true (not enough cash).");
            cntErr++;
        }
        cust.updateCash(1000);
        added = cust.addFund(10, mf);
        if (!added) {
            msg("-addFund(10, MutualFund(\"More Fund\", \"TST2\", 1.23, "
                + "0.12)) must return true not false.");
            cntErr++;
        }
        double cash = cust.getCash();
        if (Math.abs(cash - 987.7) >= 0.001) {
            msg("-For customer with $1000 purchasing 10 shares at $1.23, "
                + "remaining cash must be $987.7 NOT $" + cash);
            cntErr++;
        }
        return cntErr;
    }

    private int testSellFund() {
        int cntErr = 0;
        double cash = 0.0;
        ArrayList<MutualFundAccount> al = new ArrayList<>();
        MutualFund mf = new MutualFund("Test Fund", "TEST", 5.0, 0.1);
        al.add(new MutualFundAccount(15, mf));
        Customer cust = new Customer("Xing", "Li", "a@b.com", "xyz", 10, al);
        cust.sellFund("Test Fund");
        cash = cust.getCash();
        if (Math.abs(cash - 84.925) >= 0.001) {
            msg("-For customer with $10 selling 10 shares @ $5.00 with fee 0.1"
                + ", new cash must be $84.925 NOT $" + cash);
            cntErr++;
        }
        try {
            cust.sellFund("No Fund");
            msg("-Must throw NoSuchElementException when fund does not exist.");
            cntErr++;
        } catch (NoSuchElementException e) {
            // thrown as expected
        }
        cash = cust.getCash();
        if (Math.abs(cash - 84.925) >= 0.001) {
            msg("-Trying to sell non-existant fund must not change cash.");
            cntErr++;
        }
        return cntErr;
    }

    private int testSellShares() {
        int cntErr = 0;
        double cash = 0.0;
        ArrayList<MutualFundAccount> al = new ArrayList<>();
        MutualFund mf = new MutualFund("Test Fund", "TEST", 5.0, 0.1);
        al.add(new MutualFundAccount(15, mf));
        Customer cust = new Customer("Xing", "Li", "a@b.com", "xyz", 10, al);
        cust.sellShares("Test Fund", 5);
        cash = cust.getCash();
        if (Math.abs(cash - 34.975) >= 0.001) {
            msg("-For customer with $10 selling 5 shares @ $5.00 with fee 0.1"
                + ", new cash must be $34.975 NOT $" + cash);
            cntErr++;
        }
        cust.sellShares("Test Fund", 5);
        cash = cust.getCash();
        if (Math.abs(cash - 59.95) >= 0.001) {
            msg("-For customer with $34.975 selling 5 shares @ $5.00 with fee "
                + "0.1, new cash must be $59.95 NOT $" + cash);
            cntErr++;
        }
        try {
            cust.sellShares("No Fund", 1);
            msg("-Must throw NoSuchElementException when fund does not exist.");
            cntErr++;
        } catch (NoSuchElementException e) {
            // thrown as expected
        }
        return cntErr;
    }

    private int testToString() {
        int cntErr = 0;
        String str = "";
        ArrayList<MutualFundAccount> al = new ArrayList<>();
        MutualFund mf = new MutualFund("Test Fund", "TEST", 5.0, 0.1);
        Customer cust = new Customer("Xing", "Li", "a@b.com", "xyz", 10, al);
        str =  cust.toString();
        if (!str.startsWith("Name: Xing Li\nEmail: a@b.com\nAccount Number:")
            || !str.contains("Total Cash: $10.00\n")) {
            msg("For Customer(\"Xing\", \"Li\", \"a@b.com\", \"xyz\", 10, al)"
                + " toString must start with:\nName: Xing Li\nEmail: a@b.com\n"
                + "Account Number: (some number)\nTotal Cash: $10.00\nNOT:\n"
                + str);
            cntErr++;
        }
        return cntErr;
    }

    private int testEqualsObject() {
        int cntErr = 0;
        Customer cust1 = new Customer("a@b.com", "folly");
        Customer cust2 = new Customer("a@b.com", "folly");
        //assertTrue(cust1.equals(cust1));
        if (!cust1.equals(cust1)) {
            msg("-Customer objects must be equal to itself.");
            cntErr++;
        }
        //assertTrue(cust1.equals(cust2));
        if (!cust1.equals(cust2)) {
            msg("-Customer objects must be equal if they have the same email "
                 + "and password.");
            cntErr++;
        }
        cust2 = new Customer("a@b.com", "xyz");
        if (cust1.equals(cust2)) {
            msg("-Customer objects are not equal if they have the same email "
                 + "but different passwords.");
            cntErr++;
        }
        cust2 = new Customer("a@bc.com", "folly");
        if (cust1.equals(cust2)) {
            msg("-Customer objects are not equal if they have the same "
                 + "passwords but different emails.");
            cntErr++;
        }
        if (cust1.equals(new MutualFund("TICK"))) {
            msg("-Customer objects are not equal to MutualFund objects.");
            cntErr++;
        }
        return cntErr;
    }

    private int testHashCode() {
        int cntErr = 0, code = 0;
        Customer cust = new Customer("a@b.com", "folly");
        code = cust.hashCode();
        if (code != 1174) {
            msg("-Customer(\"a@b.com\", \"folly\") hashCode() must return 1174"
                + " not " + code + ".");
            cntErr++;
        }
        cust = new Customer("a@b.com", "xyz");
        code = cust.hashCode();
        if (code != 987) {
            msg("-Customer(\"a@b.com\", \"xyz\") hashCode() must return 987"
                + " not " + code + ".");
            cntErr++;
        }
        cust = new Customer("y@x.com", "folly");
        code = cust.hashCode();
        if (code != 1220) {
            msg("-Customer(\"y@x.com\", \"folly\") hashCode() must return 1220"
                + " not " + code + ".");
            cntErr++;
        }
        return cntErr;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("testfeedback.txt");
        PrintWriter testFeedback = new PrintWriter(file);
        zyLabsUnitTest3 zy = new zyLabsUnitTest3();
        int points = zy.passed(testFeedback);
        testFeedback.close();
        System.out.println("Total points: " + points);
        // Following two lines print testfeedback.txt
        List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
        lines.forEach(System.out::println);
    }
}
