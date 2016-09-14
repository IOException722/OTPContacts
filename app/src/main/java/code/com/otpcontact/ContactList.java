package code.com.otpcontact;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Contacts> contactsArrayList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView contactList;
    private RecyclerView.Adapter contactAdapter;

    private OnFragmentInteractionListener mListener;

    public ContactList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactList.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactList newInstance(String param1, String param2) {
        ContactList fragment = new ContactList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        contactsArrayList = new ArrayList<>();
        loadContactsInArray();
        Log.v("Ascii", "size " + contactsArrayList.size());
        contactAdapter = new MyAdapter(contactsArrayList,0, getActivity());
        contactList = (RecyclerView)view.findViewById(R.id.contactList);
        layoutManager = new LinearLayoutManager(getActivity());
        contactList.setLayoutManager(layoutManager);
        contactList.setAdapter(contactAdapter);
        return  view;
    }

    private void loadContactsInArray()
    {
        try {
            JSONObject jsonObject = new JSONObject(getJSON(R.raw.contacts));
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext())
            {
                String key = iterator.next();
                contactsArrayList.add(new Contacts(key, jsonObject.getString(key)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private String getJSON(int resourceId){
        char[] buffer = new char[2048];
        try{
            InputStream inputStream = getResources().openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Writer writer = new StringWriter();
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            reader.close();
            inputStream.close();
            return writer.toString();

        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
