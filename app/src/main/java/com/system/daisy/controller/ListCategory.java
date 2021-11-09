package com.system.daisy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.daisy.R;
import com.system.daisy.adapter.RecyclerAdapterCategory;
import com.system.daisy.dao.CategoryDAO;
import com.system.daisy.entity.Categories;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCategory extends Fragment implements RecyclerAdapterCategory.OnViewSubCategoryListener {

    OnSelectCategoryListener callback;

    public void setOnSelectCategoryListener(OnSelectCategoryListener callback) {
        this.callback = callback;
    }

    public interface OnSelectCategoryListener {
        public void onCategorySelected(int id);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment category_left.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCategory newInstance(String param1, String param2) {
        ListCategory fragment = new ListCategory();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ListCategory() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_category_left, container, false);
    }

    RecyclerView recyclerView;

    ArrayList<Categories> categories;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewCategory);
        CategoryDAO categoryDAO = new CategoryDAO();
        categories = categoryDAO.getListCategories();

        RecyclerAdapterCategory adapter = new RecyclerAdapterCategory(categories, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        onViewSubCategoryClick(0);
    }

    @Override
    public void onViewSubCategoryClick(int position) {

        int categoryId = categories.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putInt("cId", categoryId);

        ListSubCategory listSubCategory = new ListSubCategory();
        listSubCategory.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.rightCategoryFragment, listSubCategory).commit();
    }
}