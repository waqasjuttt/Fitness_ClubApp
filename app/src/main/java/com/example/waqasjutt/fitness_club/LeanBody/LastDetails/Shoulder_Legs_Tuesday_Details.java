package com.example.waqasjutt.fitness_club.LeanBody.LastDetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.waqasjutt.fitness_club.R;

/**
 * Created by Waqas Jutt on 10/31/2017.
 */

public class Shoulder_Legs_Tuesday_Details extends Fragment {

    private SharedPreferences sharedPreferences;
    private View view;
    private ImageView imageView;
    private TextView textView;
    private Integer[] ImagId = {R.drawable.shoulders1, R.drawable.shoulders2, R.drawable.shoulders3, R.drawable.shoulders4, R.drawable.shoulders5, R.drawable.shoulders6, R.drawable.squats1, R.drawable.squats2, R.drawable.squats3, R.drawable.squats4, R.drawable.squats5, R.drawable.squats6, R.drawable.squats7};
    private int x;

    public Shoulder_Legs_Tuesday_Details() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.lean_body_all, container, false);

        sharedPreferences = getActivity().getSharedPreferences("Fitness Club", Context.MODE_PRIVATE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textView = (TextView) view.findViewById(R.id.textView);

        switch (sharedPreferences.getInt("position", x)) {
            case 0:
                imageView.setImageBitmap(decodeImage(ImagId[0]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 1:
                imageView.setImageBitmap(decodeImage(ImagId[1]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 2:
                imageView.setImageBitmap(decodeImage(ImagId[2]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 3:
                imageView.setImageBitmap(decodeImage(ImagId[3]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 4:
                imageView.setImageBitmap(decodeImage(ImagId[4]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 5:
                imageView.setImageBitmap(decodeImage(ImagId[5]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 6:
                imageView.setImageBitmap(decodeImage(ImagId[6]));
                textView.setText(sharedPreferences.getString("textValue", null));
                params.setMargins(180, 150 - 300, 0, 100);
                textView.setLayoutParams(params);
                break;
            case 7:
                imageView.setImageBitmap(decodeImage(ImagId[7]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 8:
                imageView.setImageBitmap(decodeImage(ImagId[8]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 9:
                imageView.setImageBitmap(decodeImage(ImagId[9]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 10:
                imageView.setImageBitmap(decodeImage(ImagId[10]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 11:
                imageView.setImageBitmap(decodeImage(ImagId[11]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 12:
                imageView.setImageBitmap(decodeImage(ImagId[12]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
            case 13:
                imageView.setImageBitmap(decodeImage(ImagId[13]));
                textView.setText(sharedPreferences.getString("textValue", null));
                break;
        }

        return view;
    }

    public Bitmap decodeImage(int resourceId) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), resourceId, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 300; // you are free to modify size as your requirement

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeResource(getResources(), resourceId, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
