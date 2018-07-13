package com.android.emery.roomfullofcontacts;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactRoomDatabase extends RoomDatabase {

    //Make the ContactRoomDatabase a singleton to prevent having multiple instances of the
    // database opened at the same time.
    private static ContactRoomDatabase INSTANCE;
    //    Callback to delete all the db entries on start up
//    This will be deleted in time
    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE);
                }
            };

    public static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Add the code to get a database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class,
                            "contact_database")
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ContactDao contactDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ContactDao dao;

        PopulateDbAsync(ContactRoomDatabase db) {
            dao = db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            Contact contact = new Contact(
                    "First Name",
                    "Last Name",
                    "Phone Number",
                    "Email Address");
            dao.insert(contact);
            return null;
        }
    }

}