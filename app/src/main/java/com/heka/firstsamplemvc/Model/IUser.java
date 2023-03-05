package com.heka.firstsamplemvc.Model;

public interface IUser {
    String getEmail();
    String getPassword();
    int isValid();
}