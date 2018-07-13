package com.android.emery.roomfullofcontacts;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("Select * FROM contact_table ORDER BY last ASC")
        // Sort the contacts by last name
    LiveData<List<Contact>> getAllContacts();
}