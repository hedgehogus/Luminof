package com.example.hedgehog.luminof;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hedgehog.luminof.fragment.CatalogFragment;
import com.example.hedgehog.luminof.fragment.ContactsFragment;
import com.example.hedgehog.luminof.fragment.FeedBackFragment;
import com.example.hedgehog.luminof.fragment.MainFragment;
import com.example.hedgehog.luminof.fragment.PaymentFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    FragmentManager fragmentManager;
    MainFragment mainFragment;

    final static String MAIN_FRAGMENT_TAG = "mainfragment";

    ArrayList<Item> menuTitles = new ArrayList<>();

    {
        menuTitles.add(Item.CATALOG);
        menuTitles.add(Item.PAYMENT);
        menuTitles.add(Item.CONTACTS);
        menuTitles.add(Item.FEEDBACK);
    }

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, mainFragment, MAIN_FRAGMENT_TAG);
        fragmentTransaction.commit();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new MenuAdapter(this, menuTitles));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, menuTitles.get(position).getFragment(), menuTitles.get(position).getFragmentTag());
                fragmentTransaction.commit();
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (!mainFragment.isVisible()){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, mainFragment, MAIN_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public enum Item {
        CATALOG(R.string.catalog, new CatalogFragment(), "catalog"),
        PAYMENT(R.string.payment, new PaymentFragment(), "payment"),
        CONTACTS(R.string.contacts, new ContactsFragment(), "contacts"),
        FEEDBACK(R.string.feedback, new FeedBackFragment(), "feedback");
        private final int resource;
        private final Fragment fragment;
        private final String fragmentTag;

        Item(int resource, Fragment fragment, String fragmentTag) {
            this.resource = resource;
            this.fragment = fragment;
            this.fragmentTag = fragmentTag;
        }

        public int getResource() {
            return resource;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public final String getFragmentTag() {
            return fragmentTag;
        }
    }


}

