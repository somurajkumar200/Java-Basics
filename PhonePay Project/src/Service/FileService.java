package Service;
import Model.*;
import java.io.*;
import java.util.*;

public class FileService {
    private static final String USER_FILE = "data/users.txt";
    private static final String ACC_FILE = "data/accounts.txt";
    private static final String TXN_FILE = "data/transactions.txt";

    // USERS
    public static HashMap<String, User> loadUsers() {
        HashMap<String, User> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                User u = User.fromString(line);
                map.put(u.getMobile(), u);
            }
        } catch (Exception ignored) {}
        return map;
    }

    public static void saveUsers(Collection<User> users) {

        try {
            File file = new File(USER_FILE);

            // create folder if not exists
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (User u : users) {
                bw.write(u.toString());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ACCOUNTS
    public static ArrayList<BankAccount> loadAccounts() {
        ArrayList<BankAccount> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ACC_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(BankAccount.fromString(line));
            }
        } catch (Exception ignored) {}
        return list;
    }

    public static void saveAccounts(List<BankAccount> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACC_FILE))) {
            for (BankAccount b : list) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}