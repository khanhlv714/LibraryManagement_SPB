package com.example.library.enums;

public enum SyncType {

    BOOKS,
    CATEGORIES,
    MEMBERS,
    LOAN_SLIPS;


    public static SyncType from(String resource) {
        if(resource.equals("book")) return BOOKS;
        if(resource.equals("category")) return CATEGORIES;
        if(resource.equals("member")) return MEMBERS;
        if(resource.equals("loan-slips")) return LOAN_SLIPS;
        return null;
    }
}