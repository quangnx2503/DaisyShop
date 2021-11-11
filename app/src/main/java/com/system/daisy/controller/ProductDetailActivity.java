package com.system.daisy.controller;

import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderView;
import com.system.daisy.R;
import com.system.daisy.common.Common;
import com.system.daisy.common.Constants;
import com.system.daisy.dao.CommentDAO;
import com.system.daisy.dao.ProductDAO;
import com.system.daisy.dao.RatingFavoriteDAO;
import com.system.daisy.entity.CartItem;
import com.system.daisy.entity.Comment;
import com.system.daisy.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    RatingFavoriteDAO ratingFavoriteDAO = new RatingFavoriteDAO();
    ProductDAO productDao = new ProductDAO();
    SliderView sliderView;
    List<String> imageSliderModelList;
    TextView name;
    TextView realPrice;
    TextView price;
    TextView sale;
    TextView producer;
    TextView origin;
    TextView gurantee;
    TextView description;
    TextView descriptionDetail;
    int productId;
    Product product;

    //for comment
    RecyclerView commemtRecyclerView;
    CommentAdapter commentAdapter;
    List<Comment> commentList;
    CommentDAO commentDAO;
    EditText makeComment;
    Button buttonSend;
    Button btnFavouriteConfirm;
    Common common = new Common();

    RatingBar ratingBar;

    ImageView imageViewSearch, imageViewHome, imageViewCart;
    String lastComment;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        name = findViewById(R.id.textViewName);
        realPrice = findViewById(R.id.textViewRealPRice);
        price = findViewById(R.id.textViewPrice);
        sale = findViewById(R.id.textViewSale);
        producer = findViewById(R.id.textViewProducer);
        origin = findViewById(R.id.textViewOrigin);
        gurantee = findViewById(R.id.textViewGuarantee);
        description = findViewById(R.id.textViewDescription);
        descriptionDetail = findViewById(R.id.textViewDescriptionDetail);
        makeComment = findViewById(R.id.makeComment);
        buttonSend = findViewById(R.id.buttonSend);
        btnFavouriteConfirm = findViewById(R.id.btnFavourite);
        Intent intent = getIntent();
        productId = intent.getIntExtra("productId", -1);
        lastComment = intent.getStringExtra("lastComment")== null ? "" : intent.getStringExtra("lastComment");
        ProductDAO productDAO = new ProductDAO();
        product = productDAO.getProductDetail(productId);
        name.setText(product.getName());
        if (product.getSale() != 0) {
            realPrice.setText(common.formatPrice((int) Math.ceil(product.getPrice() * (1 - product.getSale() / 100) / 1000) * 1000));
            String strikePrice = "<strike>" + common.formatPrice(product.getPrice()) + "</strike>";
            price.setText(android.text.Html.fromHtml(strikePrice));
            sale.setText("-" + product.getSale() + "%");
        } else {
            realPrice.setText(common.formatPrice(product.getPrice()));
        }
        producer.setText("Producer: " + product.getProducer());
        origin.setText("Origin: " + product.getOrigin());
        gurantee.setText("Guarantee: " + product.getGuarantee());
        descriptionDetail.setText(product.getDescription());
        makeComment.setText(lastComment);

        //ads auto slider
        imageSliderModelList = new ArrayList<>();
        sliderView = findViewById(R.id.imageSlider);
        imageSliderModelList = productDAO.getImageUrls(productId);

        sliderView.setSliderAdapter(new ProductImageSliderAdapter(this, imageSliderModelList));
        sliderView.setSliderAdapter(new ProductImageSliderAdapter(this, imageSliderModelList));

        //comments
        commemtRecyclerView = findViewById(R.id.commentsRecyclerView);
        commentDAO = new CommentDAO();
        commentList = commentDAO.getCommentsByProductId(productId);
        setCommentRecyclerView(commentList);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = makeComment.getText().toString();
                if (Constants.statusLogin.checkLogin) {
                    if (comment.isEmpty()) {
                        showMakeCommentAlert();
                    } else {
                        String email = Constants.accountSave.emailAccount;
                        commentDAO.insertComment(email, productId, comment);
                        commentList = commentDAO.getCommentsByProductId(productId);
                        setCommentRecyclerView(commentList);
                        makeComment.setText("");
                    }
                } else {
                    showLoginAlert();
                }
            }
        });
        boolean check = ratingFavoriteDAO.checkFavorite(productId,Constants.accountSave.emailAccount);
        if (check) {
//            @android:drawable/btn_star_big_on

            btnFavouriteConfirm.setBackground(Drawable.createFromPath("drawable/btn_star_big_on"));

        }

            btnFavouriteConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //            @android:color/secondary_text_dark
                    String email = Constants.accountSave.emailAccount;
                    if(ratingFavoriteDAO.checkFavorite(productId,Constants.accountSave.emailAccount)){

                        ProductDetailActivity.this.ratingFavoriteDAO.unFavorite(productId,email);
//                        btnFavouriteConfirm.setBackgroundColor(Integer.parseInt("color/text_color_primary.xml"));
                        btnFavouriteConfirm.setBackground(Drawable.createFromPath("drawable/background_holo_dark.xml"));
                        Toast toast = Toast.makeText(getApplicationContext(), "Un-Favourited Successful ", Toast.LENGTH_LONG);
                        toast.show();
                    }else {
                    ProductDetailActivity.this.ratingFavoriteDAO.favouriteProduct(productId,email);

                    btnFavouriteConfirm.setBackground(Drawable.createFromPath("drawable/btn_star_big_on"));
                    Toast toast = Toast.makeText(getApplicationContext(), "Successful Favourited", Toast.LENGTH_LONG);
                    toast.show();
                }}
            });



        //rating
        ratingBar = findViewById(R.id.ratingBar);
        RatingFavoriteDAO ratingFavoriteDAO = new RatingFavoriteDAO();
        float dataStars = ratingFavoriteDAO.rateProduct(productId);
        ratingBar.setRating(dataStars);

        //header
        imageViewSearch = findViewById(R.id.imageViewSearch);
        imageViewHome = findViewById(R.id.imageViewHome);
        imageViewCart = findViewById(R.id.imageViewCart);

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, SearchProductActivity.class);
                startActivity(intent);
            }
        });

        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (Constants.statusLogin.checkLogin) {
                    intent = new Intent(ProductDetailActivity.this, ShoppingCartActivity.class);
                } else {
                    intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });

        int cartQuantity;
        if (Constants.statusLogin.checkLogin) {
            cartQuantity = Constants.personalCart.cartQuantity(Constants.accountSave.emailAccount);
        } else {
            cartQuantity = 0;
        }
        TextView detailCartQuantity = findViewById(R.id.txtDetailCartQuantity);
        detailCartQuantity.setText(Integer.toString(cartQuantity));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    private void showMakeCommentAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Comment must not be empty");
        builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create();
        builder.show();
    }

    private void showLoginAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have to login first");
        builder.setPositiveButton("Go to log in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent loginIntent = new Intent(ProductDetailActivity.this, MainActivity.class);
                loginIntent.putExtra("lastActivity", "productDetail");
                loginIntent.putExtra("productId", productId);
                loginIntent.putExtra("lastComment",makeComment.getText().toString());
                startActivity(loginIntent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create();
        builder.show();
    }

    private void setCommentRecyclerView(List<Comment> commentList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        commemtRecyclerView.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(this, commentList);
        commemtRecyclerView.setAdapter(commentAdapter);
    }

    public void onAddProductToCartClick(View view) {
        String email;
        if (!Constants.accountSave.emailAccount.equalsIgnoreCase("")) {
            email = Constants.accountSave.emailAccount;
            int itemId = productId;
            String itemName = product.getName();
            String itemImageUrl = product.getAvatar();
            int itemQuantity = 1;
            int itemPrice = product.getPrice();
            int salePrice = (int) Math.ceil(product.getPrice() * (1 - product.getSale() / 100) / 1000) * 1000;
            CartItem newItem = new CartItem(itemId, itemName, itemImageUrl, itemQuantity, itemPrice, salePrice);
            Constants.personalCart.addItemToCart(newItem, email);

            Gson gson = new Gson();
            String json = gson.toJson(Constants.personalCart.listPersonalCartItems);
            prefs = getSharedPreferences("dataStore", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("jsonListPersonalCart", json);
            editor.commit();
        }
        Intent intent;
        if (Constants.statusLogin.checkLogin) {
            Toast toast = Toast.makeText(this, "Add successfully", Toast.LENGTH_LONG);
            toast.show();
            recreate();
        } else {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String checkHome = getIntent().getStringExtra("checkHome");
        if (checkHome != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}