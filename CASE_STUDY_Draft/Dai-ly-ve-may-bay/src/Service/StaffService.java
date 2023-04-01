package Service;

import Entity.Others.Revenue;
import Entity.Users.Staff;
import Utils.ReadFiles;
import Utils.WriteFiles;
import Utils.Validate;
import View.LoginView;

import java.text.SimpleDateFormat;
import java.util.*;

public class StaffService extends UserService {
    private String RegisteredUserName;
   private String RegisteredPassword;
    private String newUserName;
    private String newPassword;
    private final static double PROFIT_RATE = 0.13;

    private final Scanner input = new Scanner(System.in);
    private static final StaffService staffAccount = new StaffService();

    private StaffService() {
    }

    public static StaffService getInstance() {
        return staffAccount;
    }

    private static List<Staff> staffAccountsList;

    static {
        StaffService.staffAccountsList = ReadFiles.readStaffAccountsData("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\StaffAccount.csv");
    }

    public void staffAbility() throws Exception {
        System.out.println("Chào mừng bạn quay lại. Bạn muốn làm gì?");
        System.out.println("1. Xem thông tin cá nhân");
        System.out.println("2. Thêm xoá giờ bay");
        System.out.println("3. Book vé cho khách hàng");
        System.out.println("4. Xem doanh thu, lợi nhuận");
        System.out.println("5. Đổi mật khẩu");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                System.err.println("TÍNH NĂNG CHƯA HOÀN THIỆN");
                staffAbility();
                break;
            case 2:
                editFlightTime();
                break;
            case 3:
                FlightService.selectDepart();
                break;
            case 4:
                reviewRevenueAndProfit();
                break;
            case 5:
                changePassword();
                break;
        }
    }

    private void reviewRevenueAndProfit() throws Exception {
        System.out.println("Hãy nhập lựa chọn của bạn");
        System.out.println("1. Xem doanh thu, lợi nhuận theo tháng");
        System.out.println("2. Xem tổng doanh thu, lợi nhuận");
        System.out.println("3. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                checkRevenueAndProfitByMonth();
                break;
            case 2:
                checkTotalRevenueAndProfit();
                break;
            case 3:
                staffAbility();
                break;
        }
    }

    private void checkTotalRevenueAndProfit() throws Exception {
        List<Revenue> revenues = ReadFiles.readRevenuesData("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\Revenue.csv");
        int TotalRevenue = 0;
        for (int i = 0; i < revenues.size(); i++) {
            int subTotalRevenue = Integer.parseInt(revenues.get(i).getIncome());
            TotalRevenue += subTotalRevenue;
        }
        System.out.println("Doanh thu từ đầu năm đến nay là: " + TotalRevenue + " VNĐ");
        System.out.println("Lợi nhuận từ đầu năm đến nay là: " + TotalRevenue * PROFIT_RATE + " VNĐ");
        reviewRevenueAndProfit();
    }

    private void checkRevenueAndProfitByMonth() throws Exception {
        List<Revenue> revenues = ReadFiles.readRevenuesData("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\Revenue.csv");
        int TotalRevenue = 0;
        int Last_Month_With_Revenue = new SimpleDateFormat("dd/MM/yyyy").parse(revenues.get(revenues.size()-1).getBuyDate()).getMonth() + 1;
        int month = 0;
        int inputMonth = Integer.parseInt(prompt("Lựa chọn tháng muốn in doanh thu"));
        boolean isMonth = inputMonth > 0 && inputMonth < 13;
        if (isMonth) {
            boolean isRevenueAvalabie = Last_Month_With_Revenue >= inputMonth;
            if (isRevenueAvalabie) {
                for (int i = 0; i < revenues.size(); i++) {
                    Date buyDate = new SimpleDateFormat("dd/MM/yyyy").parse(revenues.get(i).getBuyDate());
                    month = buyDate.getMonth() + 1;
                    if (inputMonth > month) {
                        continue;
                    } else if (inputMonth == month) {
                        int subTotalRevenue = Integer.parseInt(revenues.get(i).getIncome());
                        TotalRevenue += subTotalRevenue;
                    }
                }
                System.out.println("Doanh thu tháng " + inputMonth + " là: " + TotalRevenue + " VNĐ");
                System.out.println("Lợi nhuận tháng " + inputMonth + " là: " + TotalRevenue * PROFIT_RATE + " VNĐ");
                reviewRevenueAndProfit();
            } else {
                System.out.println("Tháng " + inputMonth + " chưa cập nhật doanh thu. Vui lòng thử lại");
                checkRevenueAndProfitByMonth();
            }

        } else {
            System.err.println("Invalid input!!! Please try again");
            checkRevenueAndProfitByMonth();
        }


    }

    public void editFlightTime() throws Exception {
        System.out.println("1. Thêm thời gian bay");
        System.out.println("2. Xoá thời gian bay");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                addFlightTime();
                break;
            case 2:
                removeFlightHour();
                break;
        }
    }

    public void addFlightTime() throws Exception {
        System.out.println("Giờ bay hiện hữu: ");
        System.out.println(FlightService.flightHourList);
        String newFlightHour = prompt("Nhập mốc thời gian bạn muốn thêm");
        if (Validate.validate(newFlightHour, "FLIGHT_TIME")) {
            FlightService.flightHourList.add(newFlightHour);
            // Sort flight time
            FlightService.flightHourList.sort(Comparator.comparing(String::toString));
            System.err.println("Giờ bay đã được thêm thành công");
            int FLIGHT_HOUR_LIST = FlightService.flightHourList.size();
            WriteFiles.writeDataToFile("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\FlightTime.csv", "");
            for (int i = 0; i < FLIGHT_HOUR_LIST; i++) {
                WriteFiles.writeDataToFileWithAppend("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\FlightTime.csv", FlightService.flightHourList.get(i) + "\n");
            }
            System.out.println(FlightService.flightHourList);
            System.out.println("Đang quay trở về màn hình chính...");
            staffAbility();
        } else {
            System.err.println("Invalid Input. Please re-enter");
            addFlightTime();
        }

    }

    public void removeFlightHour() throws Exception {
        System.out.println("Giờ bay hiện hữu: ");
        System.out.println(FlightService.flightHourList);
        String flightHourRemoval = prompt("Nhập mốc thời gian bạn muốn xoá");
        if (Validate.validate(flightHourRemoval, "FLIGHT_TIME")) {
            if (FlightService.flightHourList.contains(flightHourRemoval)) {
                System.out.println(FlightService.flightHourList);
                FlightService.flightHourList.remove(flightHourRemoval);
                System.err.println("Giờ bay đã được xoá thành công");
                int FLIGHT_HOUR_LIST_LENGTH = FlightService.flightHourList.size();
                WriteFiles.writeDataToFile("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\FlightTime.csv", "");
                for (int i = 0; i < FLIGHT_HOUR_LIST_LENGTH; i++) {
                    WriteFiles.writeDataToFileWithAppend("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\FlightTime.csv", FlightService.flightHourList.get(i) + "\n");
                }
                System.out.println(FlightService.flightHourList);
                System.out.println("Đang quay trở về màn hình chính...");
                staffAbility();
            } else {
                System.err.println("Giờ bay không có trong hệ thống. Vui lòng nhập lại");
                removeFlightHour();
            }
        } else {
            System.err.println("Invalid Input. Please re-enter");
            removeFlightHour();
        }
    }

    public boolean checkDuplicateUsers(String userName) {
        for (Staff member : staffAccountsList) {
            if (Objects.equals(member.getAccount(), userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicatePassword(String password) {
        for (Staff member : staffAccountsList) {
            if (Objects.equals(member.getPassWord(), password)) {
                return true;
            }
        }
        return false;
    }

    public void changePassword() throws Exception {
        int STAFF_ACCOUNT_LIST_LENGTH = staffAccountsList.size();
        WriteFiles.writeDataToFile("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\StaffAccount.csv", "TÀI KHOẢN;MẬT KHẨU\n");
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
            WriteFiles.writeDataToFileWithAppend("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\StaffAccount.csv", StaffService.staffAccountsList.get(i).writeToFile() + "\n");
        }
        staffAbility();
    }

    @Override
    public void register() throws Exception {
        do {
            newUserName = prompt("Đăng ký tên đăng nhập");
            System.out.println("Tên đăng nhập của bạn là: " + newUserName);
            newPassword = prompt("Đăng ký password: ");
            System.out.println("Password: " + newPassword);

            if (checkDuplicateUsers(newUserName)) {
                System.out.println("Tên đăng nhập đã tồn tại. Vui lòng đăng ký ID khác");
            } else {
                staffAccountsList.add(new Staff(newUserName, newPassword));
                int STAFF_ACCOUNT_LIST_LENGTH = staffAccountsList.size();
                System.out.println("Bạn đã đăng ký thành công. Chuyển tới màn hình đăng nhập...");
                WriteFiles.writeDataToFile("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\StaffAccount.csv", "TÀI KHOẢN;MẬT KHẨU\n");
                for (int i = 0; i < STAFF_ACCOUNT_LIST_LENGTH; i++) {
                    WriteFiles.writeDataToFileWithAppend("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\StaffAccount.csv", StaffService.staffAccountsList.get(i).writeToFile() + "\n");
                }
                login();
            }
        } while (checkDuplicateUsers(newUserName));
    }

    @Override
    public void login() throws Exception {

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
    public void accountFunction() throws Exception {
        System.out.println("Vui lòng Đăng nhập. Bạn chưa có tài khoản? Hãy đăng ký ngay");
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("3. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                LoginView.LoginView();
            default:
                accountFunction();
        }
    }
}
