package com.example.myownshop.Admins;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myownshop.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddNewProductActivity extends AppCompatActivity {

    private ImageView select_product_image;
    private EditText name_of_product, description, price_of_product;
    private Button add_product;

    private static final int GALLRYPICK=1;
    private ProgressDialog loadingBar;

    private Uri ImageUri;
    private StorageReference ProductImageRef;
    private DatabaseReference ProductsRf;

    private String Description, Price, Pname, saveCurrentDate, saveCurrentTime, productRandomkey;
    private String DownloadImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        select_product_image=(ImageView) findViewById(R.id.select_product_image);
        name_of_product=(EditText) findViewById(R.id.name_of_product);
        description=(EditText) findViewById(R.id.description);
        price_of_product=(EditText) findViewById(R.id.price_of_product);

        add_product=(Button) findViewById(R.id.add_product);
        ProductImageRef= FirebaseStorage.getInstance().getReference().child("Product Images");
        ProductsRf=FirebaseDatabase.getInstance().getReference().child("Products");
        loadingBar=new ProgressDialog(this);

        select_product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }

        });

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });


    }

    private void OpenGallery() {
        Intent galleryIntent=new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLRYPICK);
    }

    private void ValidateProductData() {
        Description=description.getText().toString();
        Price=price_of_product.getText().toString();
        Pname=name_of_product.getText().toString();

        if(ImageUri==null)
        {
            Toast.makeText(this, "Добавьте изображение товара.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Добавьте описание товара.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Добавьте стоимость товара.", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Добавьте название товара.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }

    }


    private void StoreProductInformation() {

        loadingBar.setTitle("Загрузка данных");
        loadingBar.setMessage("Пожалуйста подождите...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime=currentTime.format(calendar.getTime());

        productRandomkey=saveCurrentDate+saveCurrentTime;

        StorageReference filePath=ProductImageRef.child(ImageUri.getLastPathSegment()+ productRandomkey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message =e.toString();
                Toast.makeText(AddNewProductActivity.this, "Ошибка " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddNewProductActivity.this, "Изображение успешно загружено!!!", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                       if(!task.isSuccessful())
                       {
                           throw task.getException();
                       }
                       DownloadImageUrl=filePath.getDownloadUrl().toString();
                       return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AddNewProductActivity.this, "Фото сохранено!", Toast.LENGTH_SHORT).show();

                            SaveProductInfoDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveProductInfoDatabase() {
        HashMap<String, Object> productMap=new HashMap<>();

        productMap.put("pid", productRandomkey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("Description", Description);
        productMap.put("image", DownloadImageUrl);
        productMap.put("price", Price);
        productMap.put("pname", Pname);

        ProductsRf.child(productRandomkey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {

                    loadingBar.dismiss();
                    Toast.makeText(AddNewProductActivity.this, "Товар добавлен!!!", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(AddNewProductActivity.this, AdminCategoryActivity.class);
                    startActivity(intent);
                }
                else
                {
                    String message = task.getException().toString();
                    Toast.makeText(AddNewProductActivity.this, "Ошибка "+ message, Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLRYPICK && resultCode==RESULT_OK && data!=null)
        {
            ImageUri=data.getData();
            select_product_image.setImageURI(ImageUri);
        }
    }
}