package com.example.my_json_review;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class product_Category_Add_sqlit extends SQLiteOpenHelper {
    private static int DB_VERSHON=4;

    private static String DB_NAME="My_Category";
    private static String  DB_TABLE="Add_Category";
    private static final  String  KEY_ID="pd_id";
    private static final  String  KEY_NAME="Name";
    private static final  String  KEY_PRICE="sell_price";
    private static final  String  KEY_QUANTITY="sell_quanitiy";
    private static final  String  KEY_IMAGE="pd_image";
    private static final  String  KEY_DISCOUNT="discount";
    private static final  String  KEY_SIZE="pd_size";

    private static final  String  USER_GMAIL="User_gmail";
    private static final  String  PRODUCT_CATEGORY_NAME="category_Name";
    public product_Category_Add_sqlit(@Nullable Context context) {
     super(context, DB_NAME, null, DB_VERSHON);
    }

    @Override
    public void onCreate(SQLiteDatabase liteDatabase) {
 String my_table=" CREATE TABLE Add_Category (pd_id text,Name text,sell_price integer,sell_quanitiy integer,discount integer, pd_image text, pd_size text,User_gmail text,category_Name text)";
  liteDatabase.execSQL(my_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS Add_Category ");
     onCreate(db);
    }
    long MyAddData(pd_Category_showModel category_showModel){
      SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(KEY_ID,category_showModel.getPid());
        contentValues.put(KEY_NAME,category_showModel.getPdName());
        contentValues.put(KEY_PRICE,category_showModel.getSelling_price());
        contentValues.put(KEY_QUANTITY,category_showModel.getSell_quanitiy());
        contentValues.put(KEY_DISCOUNT,category_showModel.getPd_discount());
        contentValues.put(KEY_IMAGE,category_showModel.getPdImage());
        contentValues.put(KEY_SIZE,category_showModel.getPdSize());
      contentValues.put(PRODUCT_CATEGORY_NAME,category_showModel.getCategoryName());
        contentValues.put(USER_GMAIL,category_showModel.getUser_gmail());

       return sqLiteDatabase.insert(DB_TABLE,null,contentValues);
    }

    long Update_data(pd_Category_showModel Item){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(KEY_ID,Item.getPid());
        contentValues.put(KEY_QUANTITY,Item.getSell_quanitiy());
        return sqLiteDatabase.update(DB_TABLE,contentValues,KEY_ID+"=?",new String[]{String.valueOf(Item.getPid())});
    }


    long Delete_data(pd_Category_showModel Item){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(KEY_ID,Item.getPid());
        return sqLiteDatabase.delete(DB_TABLE,KEY_ID+"=?",
                new String[]{String.valueOf(Item.getPid())});
    }

    public List<pd_Category_showModel>getAllData(){

        List<pd_Category_showModel> prodcutList=new ArrayList<>();
        String select_data="SELECT *FROM "+DB_TABLE;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(select_data,null);

        if(cursor.moveToFirst()){
            do {
                pd_Category_showModel item_product=new  pd_Category_showModel();

                item_product.setPid(cursor.getString(0));
                item_product.setPdName(cursor.getString(1));
                item_product.setSelling_price(cursor.getInt(2));
                item_product.setSell_quanitiy(cursor.getInt(3));
                item_product.setPd_discount(cursor.getInt(4));
                item_product.setPdImage(cursor.getString(5));
                item_product.setPdSize(cursor.getString(6));
                item_product.setCategoryName(cursor.getString(8));
                item_product.setUser_gmail(cursor.getString(7));
                prodcutList.add(item_product);
            }while (cursor.moveToNext());
        }
        return prodcutList;
    }
}
