package code.com.otpcontact;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {

    private ArrayList<Contacts> contactsArrayList;
    private int tabselected;
    private Context context;
    public MyAdapter()
    {

    }
    public MyAdapter(ArrayList<Contacts> contactArrayList,int position, Context ctx)
    {
        this.contactsArrayList = contactArrayList;
        this.tabselected = position;
        this.context = ctx;
    }
    public MyAdapter(int position)
    {
        this.tabselected = position;
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contact;

        public ViewHolder(View v) {
            super(v);
            contact = (TextView) v.findViewById(R.id.contact_name);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        switch (tabselected)
        {
            case 1:
                break;
            case 0:
                holder.contact.setText(contactsArrayList.get(position).name);
                holder.contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("Ascii", "cicking on contact"+contactsArrayList.get(position).phoneNumber);
                        Intent i = new Intent(context, ContactInfo.class);
                        i.putExtra("name",contactsArrayList.get(position).name);
                        i.putExtra("phone",contactsArrayList.get(position).phoneNumber);
                        context.startActivity(i);
                    }
                });
                break;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contactsArrayList==null?0:contactsArrayList.size();        }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}