package com.example.waqasjutt.fitness_club;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private View view;
    private LinearLayout linearLayout;
    private ImageView imageView_Logo, imageView_LeanBody, imageView_BulkBody;
    private Integer[] imagId = {R.drawable.lm1,R.drawable.lean_new,R.drawable.bulknew};
    private static FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_main, container, false);

        //Disable the Back button on ActionBar
        if (((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

        fragmentManager = getActivity().getSupportFragmentManager();
        imageView_Logo=(ImageView)view.findViewById(R.id.ImageViewLogo);
        imageView_LeanBody=(ImageView)view.findViewById(R.id.imageView_LeanBody);
        imageView_BulkBody=(ImageView)view.findViewById(R.id.imageView_BulkBody);

        //For fix image resolutions to avoid outofmemory error
        imageView_Logo.setImageBitmap((decodeImage(imagId[0])));
        imageView_LeanBody.setImageBitmap((decodeImage(imagId[1])));
        imageView_BulkBody.setImageBitmap((decodeImage(imagId[2])));

        //For fix image resolutions to avoid outofmemory error
        linearLayout = (LinearLayout) view.findViewById(R.id.BackGround_Pic);
        getBG();

        imageView_LeanBody.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    imageView_LeanBody.setColorFilter(Color.argb(50, 0, 0, 0));
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    imageView_LeanBody.setColorFilter(Color.argb(0, 0, 0, 0));
                    fragmentTransaction =fragmentManager
                            .beginTransaction()
                            .replace(R.id.frameContainer,
                                    new LeanBody_Fragment(),
                                    Utils.LeanBody_Fragment);
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    if(!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())){
                        imageView_LeanBody.setColorFilter(Color.argb(0, 0, 0, 0));
                    }
                }

                return true;
            }
        });

        imageView_BulkBody.setOnTouchListener(new View.OnTouchListener() {
            private Rect rectt;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    imageView_BulkBody.setColorFilter(Color.argb(50, 0, 0, 0));
                    rectt = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    imageView_BulkBody.setColorFilter(Color.argb(0, 0, 0, 0));
                    fragmentTransaction =fragmentManager
                            .beginTransaction()
                            .replace(R.id.frameContainer,
                                    new BulkBody_Fragment(),
                                    Utils.BulkBody_Fragment);
                    fragmentManager
                            .beginTransaction()
                            .addToBackStack(null);
                    fragmentTransaction.commit();
                }
                if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    if(!rectt.contains(view.getLeft() + (int) motionEvent.getX(), view.getTop() + (int) motionEvent.getY())){
                        imageView_BulkBody.setColorFilter(Color.argb(0, 0, 0, 0));
                    }
                }

                return true;
            }
        });

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
                            R.drawable.bg1,
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
