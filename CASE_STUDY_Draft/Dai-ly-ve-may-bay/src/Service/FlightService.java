package Service;

import Builder.FlightTicketConcreteBuilder;
import Builder.UsersInfoConcreteBuilder;
import Interface.*;
import Factory.FlightFactory;
import ReadAndWrite.ReadFiles;
import ReadAndWrite.WriteFiles;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightService {
    private final static Scanner input = new Scanner(System.in);
    static FlightFactory flightFactory = new FlightFactory();
    static IPlane planeType = null;
    static IClass position = null;
    static String time = null;
    static ISeat seat = null;
    static String departString = null;
    static IDepart depart = null;
    static IDest dest = null;
    static String flightHour = null;
    static String price = null;
    static String dateString = null;
    static FlightTicketBuilder flightTicketBuilder;
    static List<String> flightHourList = ReadFiles.readDataFromFile("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\FlightTime.csv");
    ;

    public static void selectDepart() {
        do {
            try {
                System.out.println("Chọn điểm khởi hành: ");
                System.out.println("1. Sài Gòn");
                System.out.println("2. Hà Nội");
                System.out.println("3. Nha Trang");
                System.out.println("4. Đà Nẵng");
                System.out.println("5. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        depart = flightFactory.getDepart("Sài Gòn");
                        departString = "Sài Gòn";
                        selectDest();
                        break;
                    case 2:
                        depart = flightFactory.getDepart("Hà Nội");
                        departString = "Hà Nội";
                        selectDest();
                        break;
                    case 3:
                        depart = flightFactory.getDepart("Nha Trang");
                        departString = "Nha Trang";
                        selectDest();
                        break;
                    case 4:
                        depart = flightFactory.getDepart("Đà Nẵng");
                        departString = "Đà Nẵng";
                        selectDest();
                        break;
                    case 5:
                        CustomerService.getInstance().accountFunction();
                        break;
                    default:
                        System.err.println("Invalid Input. Please re-enter your choice");
                }
            } catch (Exception e) {
                System.err.println("Invalid Input. Please re-enter your choice");
            }
        } while (true);

    }

    public static void selectDest() {
        do {
            try {
                System.out.println("Chọn đích đến: ");
                System.out.println("1. Sài Gòn");
                System.out.println("2. Hà Nội");
                System.out.println("3. Nha Trang");
                System.out.println("4. Đà Nẵng");
                System.out.println("5. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        dest = flightFactory.getDest("Sài Gòn");
                        if (departString.equals("Hà Nội")) {
                            flightHour = "02 tiếng 15 phút";
                        } else if (departString.equals("Nha Trang")) {
                            flightHour = "45 phút";
                        } else if (departString.equals("Đà Nẵng")){
                            flightHour = "01 tiếng 20 phút";
                        } else {
                            System.err.println("Điểm khởi hành và đích đến không thể trùng nhau. Vui lòng chọn lại");
                            selectDepart();
                        }
                        selectDate();
                        break;
                    case 2:
                        dest = flightFactory.getDest("Hà Nội");
                        if (departString.equals("Sài Gòn")) {
                            flightHour = "02 tiếng 15 phút";
                        } else if (departString.equals("Nha Trang")) {
                            flightHour = "01 tiếng 50 phút";
                        } else if (departString.equals("Đà Nẵng")){
                            flightHour = "01 tiếng 30 phút";
                        } else {
                            System.err.println("Điểm khởi hành và đích đến không thể trùng nhau. Vui lòng chọn lại");
                            selectDepart();
                        }
                        selectDate();
                        break;
                    case 3:
                        dest = flightFactory.getDest("Nha Trang");
                        if (departString.equals("Sài Gòn")) {
                            flightHour = "45 phút";
                        } else if (departString.equals("Hà Nội")) {
                            flightHour = "01 tiếng 50 phút";
                        } else if (departString.equals("Đà Nẵng")){
                            flightHour = "01 tiếng 50 phút";
                        } else {
                            System.err.println("Điểm khởi hành và đích đến không thể trùng nhau. Vui lòng chọn lại");
                            selectDepart();
                        }
                        selectDate();
                        break;
                    case 4:
                        dest = flightFactory.getDest("Đà Nẵng");
                        if (departString.equals(("Sài Gòn"))) {
                            flightHour = "01 tiếng 20 phút";
                        } else if (departString.equals("Hà Nội")) {
                            flightHour = "01 tiếng 30 phút";
                        } else if (departString.equals("Nha Trang")){
                            flightHour = "01 tiếng 50 phút";
                        } else {
                            System.err.println("Điểm khởi hành và đích đến không thể trùng nhau. Vui lòng chọn lại");
                            selectDepart();
                        }
                        selectDate();
                        break;
                    case 5:
                        selectDepart();
                        break;
                    default:
                        System.err.println("Invalid Input. Please re-enter your choice");
                }
            } catch (Exception e) {
                System.err.println("Invalid Input. Please re-enter your choice");
            }
        } while (true);

    }

    public static void selectDate() {
        List<String> dateList = new ArrayList<>();
        Date nextDay;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        final long MILLISECONDS_IN_1_DAY = 24 * 60 * 60 * 1000;
        final long MILLISECONDS_TODAY = date.getTime();
        System.out.println("Chọn ngày bay:");
        for (int i = 0; i < 7; i++) {
            long NEXT_DAY = MILLISECONDS_TODAY + MILLISECONDS_IN_1_DAY * i;
            nextDay = new Date(NEXT_DAY);
            StringBuilder line = new StringBuilder("");
            line.append(i + 1);
            line.append(". ");
            line.append(formatter.format(nextDay));
            dateList.add(formatter.format(nextDay));
            System.out.println(line);
        }
        System.out.println("8. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        for (int i = 0; i < 7; i++) {
            if (choice == i + 1) {
                dateString = dateList.get(i);
                selectTime();
            }
        }
        selectDest();
    }

    public static void selectTime() {
        System.out.println("Vui lòng chọn giờ bay");
        for (int i = 0; i < flightHourList.size(); i++) {
            StringBuilder line = new StringBuilder("");
            line.append(i + 1);
            line.append(". ");
            line.append(flightHourList.get(i));
            System.out.println(line);
        }
        System.out.println("7. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        for (int i = 0; i < flightHourList.size(); i++) {
            if (choice - 1 == i) {
                time = flightHourList.get(i);
                selectPlane();
            }
        }
        selectDate();

    }

    public static void selectPlane() {
        do {
            try {
                System.out.println("Chọn loại máy bay: ");
                System.out.println("1. AIRBUS A330");
                System.out.println("2. AIRBUS A350");
                System.out.println("3. BOEING 777");
                System.out.println("4. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        planeType = flightFactory.getType("AIRBUS A330");
                        selectClass();
                        break;
                    case 2:
                        planeType = flightFactory.getType("AIRBUS A350");
                        selectClass();
                        break;
                    case 3:
                        planeType = flightFactory.getType("BOEING 777");
                        selectClass();
                        break;
                    case 4:
                        selectTime();
                        break;
                    default:
                        System.err.println("Invalid Input. Please re-enter your choice");
                        break;
                }
            } catch (Exception e) {
                System.err.println("Invalid Input. Please re-enter your choice");
            }
        } while (true);

    }

    public static void selectClass() {
        do {
            try {
                System.out.println("Chọn hạng ghế");
                System.out.println("1. Hạng thương gia - Giá vé: 5,000,000 VNĐ");
                System.out.println("2. Hạng phổ thông - Giá vé: 1,000,000 VNĐ");
                System.out.println("3. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        position = flightFactory.getClass("Hạng thương gia");
                        price = "5,000,000 VNĐ - Bằng chữ: Năm triệu Việt Nam đồng";
                        selectLuxurySeat();
                        break;
                    case 2:
                        position = flightFactory.getClass("Hạng phổ thông");
                        price = "1,000,000 VNĐ - Bằng chữ: Một triệu Việt Nam đồng";
                        selectEconomySeat();
                        break;
                    default:
                        System.err.println("Invalid Input. Please re-enter your choice");
                }
            } catch (Exception e) {
                System.err.println("Invalid Input. Please re-enter your choice");
            }
        } while (true);

    }

    public static void selectLuxurySeat() {
        String luxurySeat[] = {"[L-01]\t\t","[L-02]\n\n"};
        do {
            try {
                System.out.println("Chọn chỗ ngồi:\n");
                for (int i = 0; i < 2; i++) {
                    System.out.print(luxurySeat[i]);
                }
                System.out.println("1. Ghế Luxury 01 ");
                System.out.println("2. Ghế Luxury 02 ");
                System.out.println("3. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        seat = flightFactory.getSeat("L-01");
                        printInfo();
                        break;
                    case 2:
                        seat = flightFactory.getSeat("L-02");
                        printInfo();
                        break;
                    case 3:
                        selectClass();
                        break;
                    default:
                        System.err.println("Invalid Input. Please re-enter your choice");
                }
            } catch (Exception e) {
                System.err.println("Invalid Input. Please re-enter your choice");
            }
        } while (true);

    }

    public static void selectEconomySeat() {
        String economySeat[][] = {{"[E-01]\t","[E-02]"},{"[E-03]\t","[E-04]"}};
        do {
            try {
                System.out.println("Chọn chỗ ngồi:\n");
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        System.out.print(economySeat[i][j] + "\t");
                    }
                    System.out.println("\n");
                }
                System.out.println("1. Ghế Economy 01 ");
                System.out.println("2. Ghế Economy 02 ");
                System.out.println("3. Ghế Economy 03 ");
                System.out.println("4. Ghế Economy 04 ");
                System.out.println("5. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        seat = flightFactory.getSeat("E-01");
                        printInfo();
                        break;
                    case 2:
                        seat = flightFactory.getSeat("E-02");
                        printInfo();
                        break;
                    case 3:
                        seat = flightFactory.getSeat("E-03");
                        printInfo();
                        break;
                    case 4:
                        seat = flightFactory.getSeat("E-04");
                        printInfo();
                        break;
                    case 5:
                        selectClass();
                        break;
                }
            } catch (Exception e) {
                System.err.println("Invalid Input. Please re-enter your choice");
                e.printStackTrace();
            }
        } while (true);

    }

    private static void changePlaneTicketInfo() {
        System.out.println("Bạn muốn sửa:");
        System.out.println("1. Điạ điểm khởi hành");
        System.out.println("2. Đích đến");
        System.out.println("3. Ngày bay");
        System.out.println("4. Loại máy bay");
        System.out.println("5. Hạng ghế");
        System.out.println("6. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                selectDepart();
                break;
            case 2:
                selectDest();
                break;
            case 3:
                selectDate();
                break;
            case 4:
                selectPlane();
                break;
            case 5:
                selectClass();
                break;
            default:
                printInfo();
        }

    }

    public static void printInvoice() {
        int CUSTOMER_ACCOUNT_LIST_LENGTH = CustomerService.customerAccount.customerAccountsList.size();
        for (int i = 0; i < CUSTOMER_ACCOUNT_LIST_LENGTH; i++) {
            String STORE_LOGIN_USER = CustomerService.customerAccount.customerAccountsList.get(i).getAccount();
            String CURRENT_LOGIN_USER = CustomerService.getInstance().RegisteredUserName;
            String FLIGHT_INFO = String.valueOf(flightTicketBuilder.build());
            String CUSTOMER_INFO = String.valueOf(UsersInfoService.usersInfo.get(i));
            if (Objects.equals(CURRENT_LOGIN_USER, STORE_LOGIN_USER)) {
                WriteFiles.writeDataCustomersInfoToFile("src\\Data\\Invoice.txt",CUSTOMER_INFO);
                WriteFiles.writeDataFlightInfoToFile("src\\Data\\Invoice.txt",FLIGHT_INFO);
            }
        }
        System.err.println("CÁM ƠN ĐÃ SỬ DỤNG DỊCH VỤ CỦA CHÚNG TÔI!!!");
        CustomerService.getInstance().customerAbility();
    }

    public static void printInfo() {
        String CUSTOMER_INFO = null;
        String STORE_LOGIN_USER = null;
        flightTicketBuilder = new FlightTicketConcreteBuilder()
                .setPosition(position.getPosition())
                .setPlane(planeType.getPlane())
                .setTime(time)
                .setSeat(seat.getSeat())
                .setDepart(depart.getDepart())
                .setDest(dest.getDest())
                .setFlightHour(flightHour)
                .setPrice(price)
                .setDate(dateString);
        int CUSTOMER_ACCOUNT_LIST_LENGTH = CustomerService.customerAccount.customerAccountsList.size();
        int USERS_INFO_LIST_LENGTH = UsersInfoService.usersInfo.size();
        String CURRENT_LOGIN_USER = CustomerService.getInstance().RegisteredUserName;
        String FLIGHT_INFO = String.valueOf(flightTicketBuilder.build());


        for (int i = 0; i < CUSTOMER_ACCOUNT_LIST_LENGTH; i++) {
//            CUSTOMER_INFO = String.valueOf(UsersInfoService.usersInfo.get(i)); gắn lại giá trị
            STORE_LOGIN_USER = CustomerService.customerAccount.customerAccountsList.get(i).getAccount();
            if (Objects.equals(CURRENT_LOGIN_USER, STORE_LOGIN_USER)
                    && USERS_INFO_LIST_LENGTH < CUSTOMER_ACCOUNT_LIST_LENGTH) {
                System.out.println("Vui lòng nhập thông tin cá nhân để hoàn tất việc đặt vé");
                String name = prompt("Nhập Họ và Tên:");
                String dayOfBirth = prompt("Nhập ngày tháng năm sinh:");
                String gender = prompt("Nhập giới tính:");
                String phoneNumber = prompt("Nhập số điện thoại:");
                String email = prompt("Nhập email:");
                String address = prompt("Nhập địa chỉ:");
                UsersInfoBuilder usersInfoBuilder = new UsersInfoConcreteBuilder()
                        .setName(name)
                        .setDayOfBirth(dayOfBirth)
                        .setGender(gender)
                        .setPhoneNumber(phoneNumber)
                        .setEmail(email)
                        .setAddress(address);
                WriteFiles.writeDataToFile("src\\Data\\UsersInfo.csv","HỌ VÀ TÊN;NGÀY SINH;GIỚI TÍNH;SỐ ĐIỆN THOẠI;EMAIL;ĐỊA CHỈ\n");
                for (int j = 0; i < USERS_INFO_LIST_LENGTH; j++) {
                    WriteFiles.writeDataToFileWithAppend("src\\Data\\UsersInfo.csv",UsersInfoService.usersInfo.get(i).toString() + "\n");
                }
                WriteFiles.writeDataToFileWithAppend("src\\Data\\UsersInfo.csv",usersInfoBuilder.build().writeToFile());
                System.out.println(usersInfoBuilder.build());
                System.err.println("VUI LÒNG KIỂM TRA KỸ THÔNG TIN!!!");
                System.out.println(CUSTOMER_INFO);
                System.out.println(FLIGHT_INFO);
            }
        }

        for (int i = 0; i < CUSTOMER_ACCOUNT_LIST_LENGTH; i++) {
//            CUSTOMER_INFO = String.valueOf(UsersInfoService.usersInfo.get(i));
            STORE_LOGIN_USER = CustomerService.customerAccount.customerAccountsList.get(i).getAccount();
            if(Objects.equals(CURRENT_LOGIN_USER, STORE_LOGIN_USER)) {
                System.err.println("VUI LÒNG KIỂM TRA KỸ THÔNG TIN!!!");
                System.out.println(CUSTOMER_INFO);
                System.out.println(FLIGHT_INFO);
            } else {
                System.out.println("Vui lòng nhập thông tin khách hàng để hoàn tất việc đặt vé");
                String name = prompt("Nhập Họ và Tên:");
                String dayOfBirth = prompt("Nhập ngày tháng năm sinh:");
                String gender = prompt("Nhập giới tính:");
                String phoneNumber = prompt("Nhập số điện thoại:");
                String email = prompt("Nhập email:");
                String address = prompt("Nhập địa chỉ:");
                UsersInfoBuilder usersInfoBuilder = new UsersInfoConcreteBuilder()
                        .setName(name)
                        .setDayOfBirth(dayOfBirth)
                        .setGender(gender)
                        .setPhoneNumber(phoneNumber)
                        .setEmail(email)
                        .setAddress(address);
            }
        }
        System.err.println("VUI LÒNG KIỂM TRA KỸ THÔNG TIN!!!");
        System.out.println(CUSTOMER_INFO);
        System.out.println(FLIGHT_INFO);
        System.out.println("1. Sửa thông tin cá nhân");
        System.out.println("2. Sửa thông tin chuyến bay");
        System.out.println("3. Thông tin chính xác. Tiến hành in hoá đơn");

        int choice = Integer.parseInt(input.nextLine());
        switch (choice) {
            case 1:
                CustomerService.getInstance().changeUserInfoService();
                break;
            case 2:
                changePlaneTicketInfo();
                break;
            case 3:
                printInvoice();
                break;
        }
    }

    public static String prompt(String request) {
        Scanner input = new Scanner(System.in);
        System.out.println(request);
        return input.nextLine();
    }
}
