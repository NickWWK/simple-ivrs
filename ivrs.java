import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class ivrs {
    // Define scanner and userDB as static fields for accessibility
    public static Map<String, User> userDB = new HashMap<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize user data
        userDB.put("1001", new User("張先生", 1500.0));
        userDB.put("1002", new User("李小姐", 3200.0));

        // Start IVR
        runIVR();
    }

    public static void displayMenu() {
        System.out.println("歡迎致電奇妙數據客戶服務熱線");
        System.out.println("請選擇服務：");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 聯繫客服");
        System.out.println("3. 報更查詢");
        System.out.println("0. 退出");
    }

    public static void runIVR() {
        while (true) {
            displayMenu();
            System.out.print("請輸入您的選擇（0-3）：");
            String choice = scanner.nextLine();
            logAction("User selected option: " + choice);

            switch (choice) {
                case "1":
                    System.out.print("請輸入您的用戶 ID：");
                    String userId = scanner.nextLine();
                    checkBalance(userId);
                    break;
                case "2":
                    System.out.println("正在轉接至客服，請稍候...");
                    break;
                case "3":
                    System.out.println("當前排班：張先生 - 09:00-17:00，李小姐 - 13:00-21:00");
                    break;
                case "0":
                    System.out.println("感謝您的來電，再見！");
                    return;
                default:
                    System.out.println("無效選擇，請重試。");
            }
        }
    }

    public static void checkBalance(String userId) {
        if (userDB.containsKey(userId)) {
            User user = userDB.get(userId);
            System.out.println("尊敬的 " + user.name + "，您的餘額是 $" + user.balance);
        } else {
            System.out.println("無效用戶 ID，請重新輸入");
        }
    }

    public static void logAction(String action) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(java.time.LocalDateTime.now() + ": " + action + "\n");
        } catch (IOException e) {
            System.out.println("日誌記錄失敗：" + e.getMessage());
        }
    }
}