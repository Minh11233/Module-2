package View;

import Service.CustomerService;
import Service.StaffService;

import java.util.Scanner;

public class LoginView {
    public static void LoginView() {
     do {
        try {
            System.out.println("Bạn là: ");
            System.out.println("1. Nhân viên đại lý");
            System.out.println("2. Khách hàng");
            Scanner input = new Scanner(System.in);
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    StaffService.getInstance().accountFunction();
                    break;
                case 2:
                    CustomerService.getInstance().accountFunction();
                    break;
                default:
                    System.err.println("Invalid Input. Please re-enter your choice");
            }
        } catch (Exception ignored) {
            System.err.println("Invalid Input. Please re-enter your choice");
        }
    } while(true);
}
}
