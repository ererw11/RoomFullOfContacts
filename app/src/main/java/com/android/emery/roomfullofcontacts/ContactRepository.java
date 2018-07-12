package com.android.emery.roomfullofcontacts;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    ContactRepository(Application application) {
        ContactRoomDatabase database = ContactRoomDatabase.getDatabase(application);
        contactDao = database.contactDao();
        allContacts = contactDao.getAllContacts();
    }

    LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public void insert(Contact contact) {
        // Must call this on a non-UI thread
        new insertAsyncTask(contactDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {

        private  ContactDao asyncTaskDao;

        insertAsyncTask (ContactDao  dao ) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... contacts) {
            asyncTaskDao.insert(contacts[0]);
            return null;
        }
    }
}
