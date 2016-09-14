package code.com.otpcontact;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener,ContactList.OnFragmentInteractionListener,SentList.OnFragmentInteractionListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private int tabSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBarToolbar();
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("CONTACTS"));
        tabLayout.addTab(tabLayout.newTab().setText("SENT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.pager);
        TabPager adapter = new TabPager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);
     //   tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabSelected = position;
                replaceFragment(tabSelected);
            }
        });
      //  tabLayout.setSmoothScrollingEnabled(true);

    }
    public void replaceFragment(int position) {
        try {

                switch (position)
                {
                    case 0:

                        break;
                    case 1:
                        break;
                    default:
                        break;
                }

        } catch (Exception ignored) {
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onPause() {
        super.onPause();
    }
    public Toolbar getActionBarToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.main_toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
        return toolbar;
    }
}
