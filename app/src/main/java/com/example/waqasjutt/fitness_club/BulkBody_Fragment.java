package com.example.waqasjutt.fitness_club;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.waqasjutt.fitness_club.BulkBody.Chest_Monday_Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BulkBody_Fragment extends Fragment {

    private View view;
    private LinearLayout linearLayout;
    private ImageView BulkImageView_Logo;
    private Integer[] BulkImagId = {R.drawable.bulk_fragment_pic};
    private Integer[] BulkBodyId = {R.drawable.chest, R.drawable.shoulder, R.drawable.lats, R.drawable.bicep, R.drawable.tricep, R.drawable.squats, R.drawable.sixpack_list};
    private String[] BulkBodyDescription = {"Chest (Monday)", "Shoulder (Tuesday)", "Lats (Wednesday)", "Bicep (Thursday)", "tricep (Friday)", "Squats (Saturday)", "Six Pack"};
    private ListView listView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public BulkBody_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_bulk_body, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Bulk Fitness");

        //For go back to previous fragment
        if (((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fragmentManager = getActivity().getSupportFragmentManager();
        linearLayout=(LinearLayout)view.findViewById(R.id.Bulk_BG);
        BulkImageView_Logo=(ImageView)view.findViewById(R.id.BulkImageViewLogo);

        listView = (ListView) view.findViewById(R.id.Bulk_ListView);
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), BulkBodyDescription, BulkBodyId);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), "You Clicked at "
                        + BulkBodyDescription[position], Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        fragmentTransaction = fragmentManager.beginTransaction()
                                .replace(R.id.frameContainer, new Chest_Monday_Fragment(),
                                        Utils.Chest_Monday_Fragment);
                        fragmentTransaction
                                .addToBackStack(null)
                                .commit();
                        break;
                }
            }
        });

        //For fix image resolutions to avoid outofmemory error
        BulkImageView_Logo.setImageBitmap((decodeImage(BulkImagId[0])));

        getBG();
        return view;
    }

    public void getBG(){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        if (Build.VERSION.SDK_INT >= 16) {
            Drawable d = new BitmapDrawable(getResources(),
                    new OutOfMemoryfixer().decodeSampledBitmapFromResource(getResources(),
                            R.drawable.bg,
                            300,300));
            linearLayout.setBackground(d);
        }
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
