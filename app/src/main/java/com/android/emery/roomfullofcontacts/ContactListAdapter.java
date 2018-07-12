package com.android.emery.roomfullofcontacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private final LayoutInflater inflater;
    private List<Contact> contacts; // Cached copy of contacts

    ContactListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (contacts != null) {
            Contact thisContact = contacts.get(position);
            holder.firstNameTextView.setText(thisContact.getFirstName());
            holder.lastNameTextView.setText(thisContact.getLastName());
            holder.phoneTextView.setText(thisContact.getPhoneNumber());
            holder.emailTextView.setText(thisContact.getEmailAddress());
        } else {
            // no data available
            holder.firstNameTextView.setText("No Data");
        }
    }

    void setContacts(List<Contact> contacts) {
        contacts = this.contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (contacts != null) return contacts.size();
        else return 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final TextView firstNameTextView;
        private final TextView lastNameTextView;
        private final TextView phoneTextView;
        private final TextView emailTextView;

        private ContactViewHolder(View view) {
            super(view);
            firstNameTextView = view.findViewById(R.id.first_name);
            lastNameTextView = view.findViewById(R.id.last_name);
            phoneTextView = view.findViewById(R.id.phone);
            emailTextView = view.findViewById(R.id.email);
        }

    }
}