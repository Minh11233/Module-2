package Service;

import Entity.Others.UsersInfo;
import java.util.ArrayList;
import java.util.List;

public class UsersInfoService {
    public static List<UsersInfo> usersInfo;
    static {
        usersInfo = new ArrayList<>();
        usersInfo.add(new UsersInfo("Đoàn Văn Hưởng","20","0911333666","huong113@gmail.com","21k Nguyễn Văn Trỗi"));
        usersInfo.add(new UsersInfo("Nguyễn Quang Minh","18","0911223666","minhdeptrai@gmail.com","21k Nguyễn Văn Trỗi"));
        usersInfo.add(new UsersInfo("Nguyễn Văn Khánh","30","0923333666","khanhcobac@gmail.com","21k Nguyễn Văn Trỗi"));
    }
}
