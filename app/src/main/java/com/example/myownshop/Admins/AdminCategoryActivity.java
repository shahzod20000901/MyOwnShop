package com.example.myownshop.Admins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myownshop.R;

public class AdminCategoryActivity extends AppCompatActivity {

    public ImageView books, female_dresses, glasses, hats, headphones, laptops, mobiles, purses_bags, shoes, sports, sweather, tshirts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        books=(ImageView) findViewById(R.id.category_books);
        female_dresses=(ImageView) findViewById(R.id.category_female_dresses);
        glasses=(ImageView) findViewById(R.id.category_glasses);
        hats=(ImageView) findViewById(R.id.category_hats);
        headphones=(ImageView) findViewById(R.id.category_headphones);
        laptops=(ImageView) findViewById(R.id.category_laptops);
        mobiles=(ImageView) findViewById(R.id.category_mobiles);
        purses_bags=(ImageView) findViewById(R.id.category_purses_bags);
        shoes=(ImageView) findViewById(R.id.category_shoes);
        sports=(ImageView) findViewById(R.id.category_sports);
        sweather=(ImageView) findViewById(R.id.category_sweather);
        tshirts=(ImageView) findViewById(R.id.category_tshirts);

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        female_dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        purses_bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        sweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategoryActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

    }
}