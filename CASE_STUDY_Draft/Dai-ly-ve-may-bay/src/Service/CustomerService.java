package Service;

import Entity.Users.Customer;
import ReadAndWrite.ReadFiles;
import ReadAndWrite.WriteFiles;
import View.LoginView;

import java.util.List;
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
    public List<Customer> customerAccountsList;

    static {
        customerAccount.customerAccountsList = ReadFiles.readCustomerAccountsData("src\\Data\\CustomerAccount.csv");
    }

    public void customerAbility() {
        System.out.println("Chào mừng bạn quay lại. Bạn muốn làm gì?");
        System.out.println("1. Đặt chuyến bay");
        System.out.println("2. Sửa thông tin cá nhân");
        System.out.println("3. Đổi mật khẩu");
        System.out.println("4. Đăng xuất");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                FlightService.selectDepart();
                break;
            case 2:
                changeUserInfoService();
                break;
            case 3:
                changePassword();
                break;
            default:
                LoginView.LoginView();
        }
    }

    public boolean checkDuplicateUsers(String userName) {
        for (Customer member: customerAccountsList) {
            if (Objects.equals(member.getAccount(), userName)) {
                return true;
            }
        } return false;
    }
    public boolean checkDuplicatePassword(String password) {
        for (Customer member: customerAccountsList) {
            if (Objects.equals(member.getPassWord(), password)) {
                return true;
            }
        } return false;
    }

    public void changePassword() {
        int CUSTOMER_ACCOUNT_LIST_LENGTH = customerAccountsList.size();
        WriteFiles.writeDataToFile("src\\Data\\CustomerAccount.csv","TÀI KHOẢN;MẬT KHẨU\n");
        for (int i = 0; i < CUSTOMER_ACCOUNT_LIST_LENGTH; i++) {
            String CURRENT_LOGIN_USER = customerAccountsList.get(i).getAccount();
            if (Objects.equals(RegisteredUserName, CURRENT_LOGIN_USER)) {
                String newPassword = prompt("Nhập mật khẩu mới");
                String checkPassword = prompt("Nhập lại mật khẩu");
                if (Objects.equals(newPassword, checkPassword)) {
                    // gắn mật khẩu mới vào đối tượng
                    customerAccountsList.get(i).setPassWord(newPassword);
                    System.out.println("Đổi mật khẩu thành công.\nQuay về màn hình chọn...");

                } else {
                    System.out.println("Mật khẩu bạn nhập chưa trùng nhau. Hãy nhập lại");
                    changePassword();
                }
            }
            WriteFiles.writeDataToFileWithAppend("src\\Data\\CustomerAccount.csv",customerAccount.customerAccountsList.get(i).writeToFile() + "\n");
        }
        customerAbility();
    }
    public void changeUserInfoService() {
        for (int i = 0; i < customerAccountsList.size(); i++) {
            String accountName = customerAccountsList.get(i).getAccount();
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
                        System.out.println("----------------\nHoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        customerAbility();
                        break;
                    case 2:
                        String newDayOfBirth = prompt("Nhập ngày sinh:");
                        UsersInfoService.usersInfo.get(i).setDayOfBirth(newDayOfBirth);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn...");
                        customerAbility();
                        break;
                    case 3:
                        String newEmail = prompt("Nhập email:");
                        UsersInfoService.usersInfo.get(i).setEmail(newEmail);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        customerAbility();
                        break;
                    case 4:
                        String newPhoneNumber = prompt("Nhập số điện thoại:");
                        UsersInfoService.usersInfo.get(i).setPhoneNumber(newPhoneNumber);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        customerAbility();
                        break;
                    case 5:
                        String newAddress = prompt("Nhập địa chỉ:");
                        UsersInfoService.usersInfo.get(i).setAddress(newAddress);
                        System.out.println(UsersInfoService.usersInfo.get(i));
                        System.out.println("Hoàn tất sửa thông tin\nQuay trở lại màn hình chọn");
                        customerAbility();
                        break;
                }
            }
        }
    }

    @Override
    public void register() {
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
                customerAccountsList.add(new Customer(newUserName,newPassword));
                System.out.println("Bạn đã đăng ký thành công. Chuyển tới màn hình đăng nhập...");
                login();
            }
        } while (checkDuplicateUsers(newUserName));
    }
    @Override
    public void login() {
        do {
            RegisteredUserName = prompt("Hãy nhập tên đăng nhập:");
            RegisteredPassword = prompt("Hãy nhập mật khẩu:");
            if (checkDuplicatePassword(RegisteredPassword) && checkDuplicateUsers(RegisteredUserName)) {
                customerAbility();
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
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.err.println("Vui lòng đăng nhập để sử dụng chức năng này!!!");
                accountFunction();
        }
    }
}
