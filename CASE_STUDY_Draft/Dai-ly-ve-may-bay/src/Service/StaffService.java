package Service;

import Entity.Users.Staff;
import ReadAndWrite.ReadFiles;
import ReadAndWrite.WriteFiles;

import java.util.Comparator;
import java.util.List;
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
    private static List<Staff> staffAccountsList;

    static {
        StaffService.staffAccountsList = ReadFiles.readStaffAccountsData("src\\Data\\StaffAccount.csv");
    }
    public void staffAbility() {
        System.out.println("Chào mừng bạn quay lại. Bạn muốn làm gì?");
        System.out.println("1. Xem thông tin cá nhân");
        System.out.println("2. Thêm xoá giờ bay");
        System.out.println("3. Book vé cho khách hàng");
        System.out.println("4. Đổi mật khẩu");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                break;
            case 2:
                editFlightTime();
                break;
            case 3:
                System.err.println("Phục vụ khách hàng bằng cả con tim");
                FlightService.selectDepart();
                break;
            case 4:
                changePassword();
                break;
        }
    }

    public void editFlightTime() {
        System.out.println("1. Thêm thời gian bay");
        System.out.println("2. Xoá thời gian bay");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                addFlightTime();
                break;
            case 2:
                deleteFlightTime();
                break;
        }
    }

    public void addFlightTime() {
        System.out.println("Giờ bay hiện hữu: ");
        System.out.println(FlightService.flightHourList);
        String newFlightHour = prompt("Nhập mốc thời gian bạn muốn thêm");
        FlightService.flightHourList.add(newFlightHour);
        // sắp xếp thời gian bay
        FlightService.flightHourList.sort(Comparator.comparing(String::toString));
        System.err.println("Giờ bay đã được thêm thành công");
        int FLIGHT_HOUR_LIST = FlightService.flightHourList.size();
        WriteFiles.writeDataToFile("src\\Data\\FlightTime.csv","");
        for (int i = 0; i < FLIGHT_HOUR_LIST; i++ ) {
            WriteFiles.writeDataToFileWithAppend("src\\Data\\FlightTime.csv",FlightService.flightHourList.get(i) + "\n");
        }
        System.out.println(FlightService.flightHourList);
        System.out.println("Đang quay trở về màn hình chính...");
        staffAbility();
    }

    public void deleteFlightTime() {
        System.out.println("Giờ bay hiện hữu: ");
        System.out.println(FlightService.flightHourList);
        String deleteFlightHour = prompt("Nhập mốc thời gian bạn muốn xoá");
        if (FlightService.flightHourList.contains(deleteFlightHour)) {
            System.out.println(FlightService.flightHourList);
            FlightService.flightHourList.remove(deleteFlightHour);
            System.err.println("Giờ bay đã được xoá thành công");
            int FLIGHT_HOUR_LIST_LENGTH = FlightService.flightHourList.size();
            WriteFiles.writeDataToFile("src\\Data\\FlightTime.csv","");
            for (int i = 0; i < FLIGHT_HOUR_LIST_LENGTH; i++ ) {
                WriteFiles.writeDataToFileWithAppend("src\\Data\\FlightTime.csv",FlightService.flightHourList.get(i) + "\n");
            }
            System.out.println(FlightService.flightHourList);
            System.out.println("Đang quay trở về màn hình chính...");
            staffAbility();
        } else {
            System.err.println("Giờ bay không có trong hệ thống. Vui lòng nhập lại");
            deleteFlightTime();
        }
    }

    public boolean checkDuplicateUsers(String userName) {
        for (Staff member: staffAccountsList) {
            if (Objects.equals(member.getAccount(), userName)) {
                return true;
            }
        } return false;
    }
    public boolean checkDuplicatePassword(String password) {
        for (Staff member: staffAccountsList) {
            if (Objects.equals(member.getPassWord(), password)) {
                return true;
            }
        } return false;
    }

    public void changePassword() {
        int STAFF_ACCOUNT_LIST_LENGTH = staffAccountsList.size();
        WriteFiles.writeDataToFile("src\\Data\\StaffAccount.csv","TÀI KHOẢN;MẬT KHẨU\n");
        for (int i = 0; i < STAFF_ACCOUNT_LIST_LENGTH; i++) {
            String CURRENT_LOGIN_USER = staffAccountsList.get(i).getAccount();
            if (Objects.equals(RegisteredUserName, CURRENT_LOGIN_USER)) {
                String newPassword = prompt("Nhập mật khẩu mới");
                String checkPassword = prompt("Nhập lại mật khẩu");
                if (Objects.equals(newPassword, checkPassword)) {
                    // gắn mật khẩu mới vào đối tượng
                    staffAccountsList.get(i).setPassWord(newPassword);
                    System.out.println("Đổi mật khẩu thành công.\nQuay về màn hình chọn...");

                } else {
                    System.out.println("Mật khẩu bạn nhập chưa trùng nhau. Hãy nhập lại");
                    changePassword();
                }
            }
            WriteFiles.writeDataToFileWithAppend("src\\Data\\StaffAccount.csv",StaffService.staffAccountsList.get(i).writeToFile() + "\n");
        }
        staffAbility();
    }
    @Override
    public void register() {
        do {
            newUserName = prompt("Đăng ký tên đăng nhập");
            System.out.println("Tên đăng nhập của bạn là: " + newUserName);
            newPassword = prompt("Đăng ký password: ");
            System.out.println("Password: " + newPassword);

            if (checkDuplicateUsers(newUserName)) {
                System.out.println("Tên đăng nhập đã tồn tại. Vui lòng đăng ký ID khác");
            } else {
                staffAccountsList.add(new Staff(newUserName,newPassword));
                int STAFF_ACCOUNT_LIST_LENGTH = staffAccountsList.size();
                System.out.println("Bạn đã đăng ký thành công. Chuyển tới màn hình đăng nhập...");
                WriteFiles.writeDataToFile("src\\Data\\StaffAccount.csv","TÀI KHOẢN;MẬT KHẨU\n");
                for (int i = 0; i < STAFF_ACCOUNT_LIST_LENGTH; i++) {
                    WriteFiles.writeDataToFileWithAppend("src\\Data\\StaffAccount.csv",StaffService.staffAccountsList.get(i).writeToFile() + "\n");
                }
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
                staffAbility();
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
                login();
                break;
            case 2:
                register();
                break;
        }
    }
}
