package ReadAndWrite;

import Entity.Others.FlightTicket;
import Entity.Others.UsersInfo;

import java.io.*;

public class WriteFiles {
    public static void writeDataUsersInfoToFile(String path, String objects) {
        try(FileWriter fw = new FileWriter(path,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw)) {
            bufferedWriter.write("\n\t\tHOÁ ĐƠN");
            bufferedWriter.write(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeDataFlightInfoToFile(String path, String objects) {
        try(FileWriter fw = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw)) {
            bufferedWriter.write(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
