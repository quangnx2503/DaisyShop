package com.system.daisy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.system.daisy.common.Constants;
import com.system.daisy.controller.HomeActivity;
import com.system.daisy.controller.MainActivity;
import com.system.daisy.controller.FavoriteProducts;
import com.system.daisy.controller.OrderHistoryActivity;
import com.system.daisy.dao.UserDAO;
import com.system.daisy.entity.User;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link navigation_personal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class navigation_personal extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public navigation_personal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment navigation_personal.
     */
    // TODO: Rename and change types and number of parameters
    public static navigation_personal newInstance(String param1, String param2) {
        navigation_personal fragment = new navigation_personal();
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return inflater.inflate(R.layout.fragment_navigation_personal, container, false);

    }

    ArrayAdapter<String> adapter;
    ArrayList<String> attributes = new ArrayList<>(Arrays.asList("Management Order", "Purchased Product", "Viewed Products", "Favorite Products", "Deals for bank card holders", "Setting"));

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView personalName, personalEmail;
        personalName = view.findViewById(R.id.personalName);
        personalEmail = view.findViewById(R.id.personalEmail);

        ImageView personalImage = view.findViewById(R.id.personalImage);
        personalImage.setImageResource(R.drawable.icon_user);

        ListView listView = view.findViewById(R.id.listViewItem);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, attributes);
        listView.setAdapter(adapter);

        Boolean checkLogin = Constants.statusLogin.checkLogin;

        if (checkLogin == true) {
            attributes.add("Log out");
            String email = Constants.accountSave.emailAccount;
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getInfo(email);
            personalName.setText(user.getName());
            personalEmail.setText(user.getEmail());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 3) {
                        Intent intent = new Intent(getActivity().getApplication(), FavoriteProducts.class);
                        startActivity(intent);
                    } else if (position == 0) {
                        Intent intent = new Intent(getActivity().getApplication(), OrderHistoryActivity.class);
                        startActivity(intent);
                    } else if (position == 6) {
                        SharedPreferences prefs = requireActivity().getSharedPreferences("dataStore", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.remove("isLogin");
                        editor.remove("emailAccount");
                        editor.commit();
                        Intent intent = new Intent(getActivity().getApplication(), HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } else {
            personalName.setText("Welcome to Gear");
            personalName.setTextSize(14);
            personalEmail.setTextColor(Color.BLUE);
            personalEmail.setTextSize(18);
            personalEmail.setText("Click here to login or register");
            personalEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                    startActivity(intent);
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}