package com.example.ioc.evshare.network.api.UserService;

import java.util.List;

public class ListUsersResponse {

    private int totalNumber;
    private List<GetUserResponse> users;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<GetUserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<GetUserResponse> users) {
        this.users = users;
    }

}
