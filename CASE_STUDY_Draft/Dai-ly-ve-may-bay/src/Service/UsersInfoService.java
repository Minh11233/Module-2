package Service;

import Entity.Others.UsersInfo;
import ReadAndWrite.ReadFiles;
import java.util.List;

public class UsersInfoService {
    public static List<UsersInfo> usersInfo;
    static {
        usersInfo = ReadFiles.readUsersInfoData("src\\Data\\UsersInfo.csv");
    }
}
