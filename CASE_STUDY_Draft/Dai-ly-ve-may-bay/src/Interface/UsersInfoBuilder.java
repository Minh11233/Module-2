package Interface;

import Entity.Others.UsersInfo;

public interface UsersInfoBuilder {
    UsersInfoBuilder setName(String name);
    UsersInfoBuilder setEmail(String email);
    UsersInfoBuilder setAddress(String address);
    UsersInfoBuilder setAge(int Age);
    UsersInfoBuilder setPhoneNumber(int phoneNumber);

    UsersInfoBuilder setAge(String age);

    UsersInfoBuilder setPhoneNumber(String phoneNumber);

    UsersInfo build();
}
