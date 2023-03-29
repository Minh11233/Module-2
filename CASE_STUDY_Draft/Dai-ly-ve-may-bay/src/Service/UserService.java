package Service;

import Entity.Users.Users;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public abstract class UserService {
    protected List<Users> Account;
    public boolean checkDuplicateUsers(String userName) {
        for (Users member: Account) {
            if (Objects.equals(member.getAccount(), userName)) {
                return true;
            }
        } return false;
    }
    public boolean checkDuplicatePassword(String password) {
        for (Users member: Account) {
            if (Objects.equals(member.getPassWord(), password)) {
                return true;
            }
        } return false;
    }

    public String prompt(String request) {
        Scanner input = new Scanner(System.in);
        System.out.println(request);
        return input.nextLine();
    }

    public abstract void Register();

    public abstract void Login();

    public abstract void accountFunction();
}
