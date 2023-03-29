package Service;

import Entity.Users.Staff;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class StaffService extends UserService {
    String RegisteredUserName;
    String RegisteredPassword;
    String newUserName;
    String newPassword;
    private final Scanner input = new Scanner(System.in);
    private static final StaffService staffAccount = new StaffService();
    private StaffService() {}
    public static StaffService getInstance() {
        return staffAccount;
    }

    static {
        staffAccount.Account = new ArrayList<>();
        staffAccount.Account.add(new Staff("staff1","123"));
        staffAccount.Account.add(new Staff("staff2","123"));
    }
    public void StaffAbility() {
        System.out.println("Chào mừng bạn quay lại. Bạn muốn làm gì?");
        System.out.println("1. Xem thông tin cá nhân");
        System.out.println("2. Sửa đổi giờ bay");
        System.out.println("3. Book vé cho khách hàng");
        System.out.println("4. Đổi mật khẩu");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                System.out.println("Phục vụ khách hàng bằng cả con tim");
                break;
            case 4:
                ChangePassword();
                break;
        }
    }

    public void ChangePassword() {
        int accountID = 0;
        for (int i = 0; i < Account.size(); i++) {
            String accountName = Account.get(i).getAccount();
            if (Objects.equals(RegisteredUserName, accountName)) {
                String newPassword = prompt("Nhập mật khẩu mới");
                String checkPassword = prompt("Nhập lại mật khẩu");
                if (Objects.equals(newPassword, checkPassword)) {
                    Account.get(i).setPassWord(newPassword);
                    System.out.println("Đổi mật khẩu thành công.\nQuay về màn hình chọn...");
                    StaffAbility();
                } else {
                    System.out.println("Mật khẩu bạn nhập chưa trùng nhau. Hãy nhập lại");
                    ChangePassword();
                }
            }
        }
    }
    @Override
    public void Register() {
        do {
            newUserName = prompt("Đăng ký tên đăng nhập");
            System.out.println("Tên đăng nhập của bạn là: " + newUserName);
            newPassword = prompt("Đăng ký password: ");
            System.out.println("Password: " + newPassword);

            if (checkDuplicateUsers(newUserName)) {
                System.out.println("Tên đăng nhập đã tồn tại. Vui lòng đăng ký ID khác");
            } else {
                Account.add(new Staff(newUserName,newPassword));
                System.out.println("Bạn đã đăng ký thành công. Chuyển tới màn hình đăng nhập...");
                Login();
            }
        } while (checkDuplicateUsers(newUserName));
    }
    @Override
    public void Login() {

        do {
            RegisteredUserName = prompt("Hãy nhập tên đăng nhập:");
            RegisteredPassword = prompt("Hãy nhập mật khẩu:");
            if (checkDuplicatePassword(RegisteredPassword) && checkDuplicateUsers(RegisteredUserName)) {
                StaffAbility();
            } else if (!checkDuplicatePassword(RegisteredPassword)) {
                System.out.println("Mật khẩu sai, vui lòng nhập lại");
            } else {
                accountFunction();
            }
        } while (!checkDuplicatePassword(RegisteredPassword) || !checkDuplicateUsers(RegisteredUserName));
    }
    @Override
    public void accountFunction() {
        System.out.println("Vui lòng Đăng nhập. Bạn chưa có tài khoản? Hãy đăng ký ngay");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                Login();
                break;
            case 2:
                Register();
                break;
        }
    }
}
