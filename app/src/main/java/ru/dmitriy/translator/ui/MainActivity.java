package ru.dmitriy.translator.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.dmitriy.translator.R;
import ru.dmitriy.translator.ui.adapters.ViewPagerAdapter;
import ru.dmitriy.translator.ui.views.HistoryFragment;
import ru.dmitriy.translator.ui.views.TranslatorFragment;

/**
 * Created by Dmitriy on 05.01.2018.
 */

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    TranslatorFragment translatorFragment;
    HistoryFragment historyFragment;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewpager);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_item1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.action_item2:
                    viewPager.setCurrentItem(1);
                    break;
            }
            return true;
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        translatorFragment = new TranslatorFragment();
        historyFragment = new HistoryFragment();
        adapter.addFragment(translatorFragment);
        adapter.addFragment(historyFragment);
        viewPager.setAdapter(adapter);
    }
}
