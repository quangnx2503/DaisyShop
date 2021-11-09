package com.system.daisy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderView;
import com.system.daisy.common.Constants;
import com.system.daisy.controller.ImageSliderAdapter;
import com.system.daisy.controller.SearchProductActivity;
import com.system.daisy.controller.TopProductAdapter;
import com.system.daisy.dao.AdDAO;
import com.system.daisy.dao.ProductDAO;
import com.system.daisy.entity.Advertisement;
import com.system.daisy.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link navigation_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class navigation_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SliderView sliderView;
    List<Advertisement> imageSliderModelList;

    //top sale
    RecyclerView topSaleRecyclerView;
    TopProductAdapter topSaleProductAdapter;
    List<Product> topSaleProductList;

    //top new
    RecyclerView topNewRecyclerView;
    TopProductAdapter topNewProductAdapter;
    List<Product> topNewProductList;

    //top selled
    RecyclerView topSellRecyclerView;
    TopProductAdapter topSellProductAdapter;
    List<Product> topSellProductList;
    //top selled 1
    RecyclerView topSell1RecyclerView;
    TopProductAdapter topSell1ProductAdapter;
    List<Product> topSell1ProductList;

    EditText search;

    public navigation_home() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ads auto slider
        imageSliderModelList = new ArrayList<>();
        sliderView = getView().findViewById(R.id.imageSlider);
        AdDAO adDAO = new AdDAO();

        imageSliderModelList = adDAO.getAdList();

        sliderView.setSliderAdapter(new ImageSliderAdapter(getActivity(), imageSliderModelList));

        //top sale
        topSaleRecyclerView = getView().findViewById(R.id.topSaleRecycler);
        topSaleProductList = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        topSaleProductList = productDAO.getTopProducts("sale");
        setTopSaleRecycler((ArrayList<Product>) topSaleProductList);

        //topNew
        topNewRecyclerView = getView().findViewById(R.id.topNewRecycler);

        topNewProductList = new ArrayList<>();
        topNewProductList = productDAO.getTopProducts("newest");
        setTopNewRecycler((ArrayList<Product>) topNewProductList);

        //top Sell
        topSellRecyclerView = getView().findViewById(R.id.topSellRecycler);
        topSellProductList = new ArrayList<>();
        topSellProductList = productDAO.getTopProducts("sell");
        setTopSellRecycler((ArrayList<Product>) topSellProductList);

        //Search
        search = getView().findViewById(R.id.editTextSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchProductActivity.class);
                getContext().startActivity(intent);
            }
        });

        //set quantity products in cart
        int cartQuantity;
        if (Constants.statusLogin.checkLogin) {
            cartQuantity = Constants.personalCart.cartQuantity(Constants.accountSave.emailAccount);
        } else {
            cartQuantity = 0;
        }
        TextView homeCartQuantity = getView().findViewById(R.id.txtDetailCartQuantity);
        homeCartQuantity.setText(Integer.toString(cartQuantity));
    }

    private void setTopSellRecycler(ArrayList<Product> topSellProductList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);//truot ngang hay doc
        topSellRecyclerView.setLayoutManager(layoutManager);
        topSellProductAdapter = new TopProductAdapter(getActivity(), topSellProductList);
        topSellRecyclerView.setAdapter(topSellProductAdapter);
    }

    private void setTopNewRecycler(ArrayList<Product> topNewproductList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);//truot ngang hay doc
        topNewRecyclerView.setLayoutManager(layoutManager);
        topNewProductAdapter = new TopProductAdapter(getActivity(), topNewproductList);
        topNewRecyclerView.setAdapter(topNewProductAdapter);
    }

    private void setTopSaleRecycler(ArrayList<Product> productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);//truot ngang hay doc
        topSaleRecyclerView.setLayoutManager(layoutManager);
        topSaleProductAdapter = new TopProductAdapter(getActivity(), productList);
        topSaleRecyclerView.setAdapter(topSaleProductAdapter);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment navigation_home.
     */
    // TODO: Rename and change types and number of parameters
    public static navigation_home newInstance(String param1, String param2) {
        navigation_home fragment = new navigation_home();
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_home, container, false);
    }
}