package com.example.waqasjutt.fitness_club;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Fitness Club");

        fragmentManager = getSupportFragmentManager();
//        getSupportActionBar().setTitle("Promo Codes");

        //For go back to previous fragment
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new MainFragment(),
                            Utils.MainFragment).commit();
        }

        // On close icon click finish activity
//        findViewById(R.id.close_activity).setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View arg0) {
//                        finish();
//                    }
//                });
    }

    // Replace Login Fragment with animation
    protected void replaceMainFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new MainFragment(),
                        Utils.MainFragment).commit();
    }

    int i;

    @Override
    public void onBackPressed() {
        // Find the tag of signup and forgot password fragment
        Fragment LeanBody_Fragment = fragmentManager
                .findFragmentByTag(Utils.LeanBody_Fragment);

        Fragment BulkBody_Fragment = fragmentManager
                .findFragmentByTag(Utils.BulkBody_Fragment);

        Fragment Chest_Lats_Monday_Fragment = fragmentManager
                .findFragmentByTag(Utils.Chest_Lats_Monday_Fragment);

        Fragment Pull_Ups_Fragment = fragmentManager
                .findFragmentByTag(Utils.PullUps_Fragment);

        Fragment Push_Ups_Fragment = fragmentManager
                .findFragmentByTag(Utils.PushUps_Fragment);

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
        else if (LeanBody_Fragment != null) {
            i = 0;
            replaceMainFragment();
        } else if (BulkBody_Fragment != null) {
            i = 0;
            replaceMainFragment();
        }
        else {
            CustomDialogClass cdd = new CustomDialogClass(MainActivity.this);
            cdd.show();
        }
    }

    public void Show_Dialog(View v) {
        CustomDialogClass cdd = new CustomDialogClass(MainActivity.this);
        cdd.show();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}