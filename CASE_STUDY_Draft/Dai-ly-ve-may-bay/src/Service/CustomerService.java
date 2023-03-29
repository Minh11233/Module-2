package Service;

import Entity.Users.Customer;
import Entity.Users.Staff;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CustomerService extends UserService {
    private final Scanner input = new Scanner(System.in);
    public String RegisteredUserName;
    private String RegisteredPassword;
    private String newUserName;
    private String newPassword;
    public static final CustomerService customerAccount = new CustomerService();
    private CustomerService() {}
    public static CustomerService getInstance() {
        return customerAccount;
    }

    static {
        customerAccount.Account = new ArrayList<>();
        customerAccount.Account.add(new Customer("Huong","123"));
        customerAccount.Account.add(new Customer("Minh","123"));
        customerAccount.Account.add(new Customer("Khanh","123"));
    }

    public void CustomerAbility() {
        System.out.println("Chào mừng bạn quay lại. Bạn muốn làm gì?");
        System.out.println("1. Đặt chuyến bay");
        System.out.println("2. Sửa thông tin cá nhân");
        System.out.println("3. Đổi mật khẩu");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                FlightService.selectDepart();
                break;
            case 2:
                ChangeUserInfoService();
                break;
            case 3:
                ChangePassword();
                break;
        }
    }

    public void ChangePassword() {
        for (int i = 0; i < Account.size(); i++) {
            String accountName = Account.get(i).getAccount();
            if (Objects.equals(RegisteredUserName, accountName)) {
                String newPassword = prompt("Nhập mật khẩu mới");
                String checkPassword = prompt("Nhập lại mật khẩu");
                if (Objects.equals(newPassword, checkPassword)) {
                    Account.get(i).setPassWord(newPassword);
                    System.out.println("Đổi mật khẩu thành công.\nQuay về màn hình chọn...");
                    CustomerAbility();
                } else {
                    System.out.println("Mật khẩu bạn nhập chưa trùng nhau. Hãy nhập lại");
                    ChangePassword();
                }
            }
        }
    }
    public void ChangeUserInfoService() {
        for (int i = 0; i < Account.size(); i++) {
            String accountName = Account.get(i).getAccount();
            if (Objects.equals(RegisteredUserName, accountName)) {
                System.out.println(UsersInfoService.usersInfo.get(i));
                System.out.println("Bạn muốn sửa:");
                System.out.println("1. Họ và tên");
                System.out.println("2. Năm sinh");
                System.out.println("3. Email");
                System.out.println("4. Số điện thoại");
                System.out.println("5. Điạ chỉ");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        String newName = prompt("Nhập tên:");
                        UsersInfoService.usersInfo.get(i).setName(newName);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        CustomerAbility();
                        break;
                    case 2:
                        String newDayOfBirth = prompt("Nhập ngày sinh:");
                        UsersInfoService.usersInfo.get(i).setDayOfBirth(newDayOfBirth);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn...");
                        CustomerAbility();
                        break;
                    case 3:
                        String newEmail = prompt("Nhập email:");
                        UsersInfoService.usersInfo.get(i).setEmail(newEmail);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        CustomerAbility();
                        break;
                    case 4:
                        String newPhoneNumber = prompt("Nhập số điện thoại:");
                        UsersInfoService.usersInfo.get(i).setPhoneNumber(newPhoneNumber);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        CustomerAbility();
                        break;
                    case 5:
                        String newAddress = prompt("Nhập địa chỉ:");
                        UsersInfoService.usersInfo.get(i).setPhoneNumber(newAddress);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        CustomerAbility();
                        break;
                }
            }
        }
    }

    @Override
    public void Register() {
        do {
            System.out.println("Đăng ký tên đăng nhập:");
            newUserName = input.nextLine();
            System.out.println("Tên đăng nhập của bạn là: " + newUserName);

            System.out.println("Đăng ký password: ");
            newPassword = input.nextLine();
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
                CustomerAbility();
            } else if (!checkDuplicatePassword(RegisteredPassword)) {
                System.out.println("Mật khẩu sai, vui lòng nhập lại");
            } else {
                accountFunction();
            }
        } while (!checkDuplicatePassword(RegisteredPassword) || !checkDuplicateUsers(RegisteredUserName));
    }
    @Override
    public void accountFunction() {
        System.out.println("Bạn chưa có tài khoản? Hãy đăng ký ngay");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("3. Xem chuyến bay");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                Login();
                break;
            case 2:
                Register();
                break;
            case 3:
                System.err.println("Vui lòng đăng nhập để sử dụng chức năng này!!!");
                accountFunction();
        }
    }
}
