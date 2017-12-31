package com.example.waqasjutt.fitness_club.LeanBody;


import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.waqasjutt.fitness_club.CustomAdapterLean;
import com.example.waqasjutt.fitness_club.LeanBody.LastDetails.Chest_Lats_Monday_Details;
import com.example.waqasjutt.fitness_club.LeanBody.LastDetails.Shoulder_Legs_Tuesday_Details;
import com.example.waqasjutt.fitness_club.MainActivity;
import com.example.waqasjutt.fitness_club.OutOfMemoryfixer;
import com.example.waqasjutt.fitness_club.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Chest_Lats_Monday_Fragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private View view;
    private ListView listView;
    private Integer[] LeanImagId = {R.drawable.lean_fragment_pic};
    private String[] LeanBodyDescription =
            {
                    "Push Ups",
                    "Pull Ups",
                    "Flat Bench Press",
                    "Incline Dumbell Press",
                    "Decline Press",
                    "Incline Bench Press",
                    "Chest Dips",
                    "Dumbell bent arm pullover",
                    "Lat Pull Downs",
                    "Seated Cable Row",
                    "Close Grip Front Lat Pull Downs",
                    "Bent Over Barbell Row",
                    "T Bar Row",
                    "Dumbell Rows"
            };
    private LinearLayout linearLayout;
    private ImageView LeanImageView_Logo;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public Chest_Lats_Monday_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chest__lats__monday, container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Lean Fitness");

        sharedPreferences = getActivity().getSharedPreferences("Fitness Club", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        listView = (ListView) view.findViewById(R.id.Chest_Lats_Monday_ListView);
        fragmentManager = getActivity().getSupportFragmentManager();
        CustomAdapterLean adapter = new CustomAdapterLean(getActivity(), LeanBodyDescription);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), "You Clicked at " + LeanBodyDescription[position], Toast.LENGTH_SHORT).show();

                editor.putInt("position", position);
                editor.putString("textValue", "Note:\nSet1:  20 Repetetions\n\nSet2:  18 Repetetions\n\nSet3:  15 Repetetions");
                editor.commit();
                fragmentTransaction = fragmentManager.beginTransaction()
                        .replace(R.id.frameContainer, new Chest_Lats_Monday_Details());
                fragmentTransaction
                        .addToBackStack(null)
                        .commit();
            }
        });

        linearLayout = (LinearLayout) view.findViewById(R.id.Chest_Lats_Monday_BG);
        LeanImageView_Logo = (ImageView) view.findViewById(R.id.LeanImageViewLogo);

        //For fix image resolutions to avoid outofmemory error
        LeanImageView_Logo.setImageBitmap(decodeImage(LeanImagId[0]));

        getBG();

        return view;
    }

    private void getBG() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        if (Build.VERSION.SDK_INT >= 16) {
            Drawable d = new BitmapDrawable(getResources(),
                    new OutOfMemoryfixer().decodeSampledBitmapFromResource(getResources(),
                            R.drawable.bg,
                            300, 300));
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
