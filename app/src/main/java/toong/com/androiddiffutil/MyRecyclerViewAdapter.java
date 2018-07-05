package toong.com.androiddiffutil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ContactViewHolder> {

    private ArrayList<Contact> data;

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name, phoneNumber;

        ContactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_contact_name);
            phoneNumber = itemView.findViewById(R.id.tv_contact_phone_number);
        }
    }

    MyRecyclerViewAdapter(ArrayList<Contact> data) {
        this.data = data;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.phoneNumber.setText(data.get(position).getPhoneNumber());
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("name")) {
                    Toast.makeText(holder.itemView.getContext(), "Contact " + position + " : Name Changed", Toast.LENGTH_SHORT).show();
                    holder.name.setText(data.get(position).getName());
                }
                if (key.equals("phone")) {
                    Toast.makeText(holder.itemView.getContext(), "Contact " + position + " : Phone Changed", Toast.LENGTH_SHORT).show();
                    holder.phoneNumber.setText(data.get(position).getPhoneNumber());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ArrayList<Contact> getData() {
        return data;
    }

    public void updateData(ArrayList<Contact> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallback(newData, data));
        diffResult.dispatchUpdatesTo(this);

        this.data.clear();
        this.data.addAll(newData);
    }
}