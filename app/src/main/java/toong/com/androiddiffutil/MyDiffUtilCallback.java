package toong.com.androiddiffutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

public class MyDiffUtilCallback extends DiffUtil.Callback {
    private ArrayList<Contact> newList;
    private ArrayList<Contact> oldList;

    public MyDiffUtilCallback(ArrayList<Contact> newList, ArrayList<Contact> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // check id
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Contact newContact = newList.get(newItemPosition);
        Contact oldContact = oldList.get(oldItemPosition);
        Bundle diff = new Bundle();
        if (!newContact.getPhoneNumber().equals(oldContact.getPhoneNumber())) {
            diff.putString("phone", newContact.getPhoneNumber());
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
