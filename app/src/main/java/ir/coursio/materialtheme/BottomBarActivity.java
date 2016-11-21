package ir.coursio.materialtheme;

import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;


import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import fragments.FirstFragment;
import fragments.FourthFragment;
import fragments.SecondFragment;
import fragments.ThirdFragment;


public class BottomBarActivity extends AppCompatActivity {
    FirstFragment fragment1;
    SecondFragment fragment2;
    ThirdFragment fragment3;
    FourthFragment fragment4;
    ViewGroup frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);

        frameLayout = (ViewGroup) findViewById(R.id.frameLayout);
        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();
        fragment3 = new ThirdFragment();
        fragment4 = new FourthFragment();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, fragment1);
        transaction.commit();


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (tabId) {
                    case R.id.tab_heart:
                        transaction.replace(R.id.frameLayout, fragment1);
                        transaction.commit();
                        break;

                    case R.id.tab_camera:
                        transaction.replace(R.id.frameLayout, fragment2);
                        transaction.commit();
                        break;

                    case R.id.tab_star:
                        transaction.replace(R.id.frameLayout, fragment3);
                        transaction.commit();
                        break;

                    case R.id.tab_info:
                        transaction.replace(R.id.frameLayout, fragment4);
                        transaction.commit();
                        break;
                }
            }
        });

    }
}
