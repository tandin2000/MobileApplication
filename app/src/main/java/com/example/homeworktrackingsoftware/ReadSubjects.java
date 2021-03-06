package com.example.homeworktrackingsoftware;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.homeworktrackingsoftware.Subject.SubjectEntry.SUBJECT_NAME;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadSubjects#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadSubjects extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ListView listViewsubject;
    private String mParam1;
    private String mParam2;

    public ReadSubjects() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReadSubjects.
     */
    // TODO: Rename and change types and number of parameters
    public static ReadSubjects newInstance(String param1, String param2) {
        ReadSubjects fragment = new ReadSubjects();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_read_subjects, container, false);
        //Initializing the View
        listViewsubject = (ListView) view.findViewById(R.id.listViewSubject);
        //Calling the method
        readSubject();
        return view;
    }

    //Create a Read Method
    public void readSubject(){
        //Object from the DatabaseHelper Class
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());

        //Getting readable database
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        //Call read method from the databaseHelper class and get data
        Cursor cursor = databaseHelper.readSubjects(sqLiteDatabase);

        //Creating an ArrayList
        ArrayList<String> list = new ArrayList<String>();
        String subject_name;
        //Loop
        while(cursor.moveToNext()){
            subject_name = cursor.getString(cursor.getColumnIndex(SUBJECT_NAME));
            list.add(subject_name);
        }
        //Creating an ArrayAdapter
        ListAdapter adapter = new ArrayAdapter<>(requireActivity(),android.R.layout.simple_list_item_1,list);
        listViewsubject.setAdapter(adapter);
        //Newly Created.
        //Further updates. -Git
        //New version
    }
}