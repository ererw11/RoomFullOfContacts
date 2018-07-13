package com.android.emery.roomfullofcontacts;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "contact_table")
public class Contact {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "first")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "last")
    private String lastName;

    @ColumnInfo(name = "phone")
    private String phoneNumber;

    @ColumnInfo(name = "email")
    private String emailAddress;

    public Contact(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}K