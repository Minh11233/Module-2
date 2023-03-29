package Service;

import Builder.FlightTicketConcreteBuilder;
import Interface.*;
import Factory.FlightFactory;
import ReadAndWrite.WriteFiles;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightService {
    static FlightTicketBuilder flightTicketBuilder;
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
    public static void selectDepart() {
        do {
            try {
                System.out.println("Chọn điểm xuất phát: ");
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
        for (int i = 1; i <= 7; i++) {
            long NEXT_DAY = MILLISECONDS_TODAY + MILLISECONDS_IN_1_DAY * i;
            nextDay = new Date(NEXT_DAY);
            StringBuilder line = new StringBuilder("");
            line.append(i);
            line.append(". ");
            line.append(formatter.format(nextDay));
            dateList.add(formatter.format(nextDay));
            System.out.println(line);
        }
        System.out.println("8. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= 7; i++) {
            if (choice == i) {
                dateString = dateList.get(i);
                selectTime();
            }
        }
        selectDest();
    }

    public static void selectTime() {
        System.out.println("Vui lòng chọn giờ bay");
        List<String> flightTime = new ArrayList<>();
        flightTime.add("07:15");
        flightTime.add("08:30");
        flightTime.add("09:45");
        flightTime.add("15:30");
        flightTime.add("19:30");
        flightTime.add("22:30");
        for (int i = 0; i < flightTime.size(); i++) {
            StringBuilder line = new StringBuilder("");
            line.append(i + 1);
            line.append(". ");
            line.append(flightTime.get(i));
            System.out.println(line);
        }
        System.out.println("7. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        for (int i = 0; i < flightTime.size(); i++) {
            if (choice - 1 == i) {
                time = flightTime.get(i);
                selectPlane();
            }
        }
        selectDate();

    }

    public static void selectPlane() {
        do {
            try {
                System.out.println("Chọn loại máy bay: ");
                System.out.println("1. Large");
                System.out.println("2. Medium");
                System.out.println("3. Small");
                System.out.println("4. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        planeType = flightFactory.getType("Large");
                        selectClass();
                        break;
                    case 2:
                        planeType = flightFactory.getType("Medium");
                        selectClass();
                        break;
                    case 3:
                        planeType = flightFactory.getType("Small");
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
                System.out.println("Chọn loại ghế");
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
        do {
            try {
                System.out.println("Chọn chỗ ngồi: ");
                System.out.println("1. Ghế Luxury 01 ");
                System.out.println("2. Ghế Luxury 02 ");
                System.out.println("3. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        seat = flightFactory.getSeat("L-01");
                        printTicket();
                        break;
                    case 2:
                        seat = flightFactory.getSeat("L-02");
                        printTicket();
                        break;
                    case 3:
                        selectPlane();
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
        do {
            try {
                System.out.println("Chọn chỗ ngồi: ");
                System.out.println("1. Ghế Economy 01 ");
                System.out.println("2. Ghế Economy 02 ");
                System.out.println("3. Ghế Economy 03 ");
                System.out.println("4. Ghế Economy 04 ");
                System.out.println("5. Quay lại");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        seat = flightFactory.getSeat("E-01");
                        printTicket();
                        break;
                    case 2:
                        seat = flightFactory.getSeat("E-02");
                        printTicket();
                        break;
                    case 3:
                        seat = flightFactory.getSeat("E-03");
                        printTicket();
                        break;
                    case 4:
                        seat = flightFactory.getSeat("E-04");
                        printTicket();
                        break;
                    case 5:
                        selectPlane();
                        break;
                }
            } catch (Exception e) {
                System.err.println("Invalid Input. Please re-enter your choice");
            }
        } while (true);

    }

    public static void printInvoice() {
        for (int i = 0; i < CustomerService.customerAccount.Account.size(); i++) {
            String accountName = CustomerService.customerAccount.Account.get(i).getAccount();
            if (Objects.equals(CustomerService.getInstance().RegisteredUserName, accountName)) {
                String FLIGHT_INFO = String.valueOf(flightTicketBuilder.build());
                String CUSTOMER_INFO = String.valueOf(UsersInfoService.usersInfo.get(i));
                WriteFiles.writeDataUsersInfoToFile("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\Invoice.txt",CUSTOMER_INFO);
                WriteFiles.writeDataFlightInfoToFile("D:\\CODEGYM\\Module-2\\CASE_STUDY_Draft\\Dai-ly-ve-may-bay\\src\\Data\\Invoice.txt",FLIGHT_INFO);
            }
        }
    }
    public static void printTicket() {
        flightTicketBuilder = new FlightTicketConcreteBuilder()
                .setPosition(position.getPosition())
                .setPlane(planeType.getPlane())
                .setTime(time)
                .setSeat(seat.getSeat())
                .setDepart(depart.getDepart())
                .setDest(dest.getDest())
                .setFlightHour(flightHour)
                .setPrice(price).setDate(dateString);
        System.out.println(flightTicketBuilder.build());
        printInvoice();
    }

}
