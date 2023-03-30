package Service;

import java.util.Scanner;

public abstract class UserService {
    public String prompt(String request) {
        Scanner input = new Scanner(System.in);
        System.out.println(request);
        return input.nextLine();
    }
    public abstract void register();

    public abstract void login();

    public abstract void accountFunction();
}
