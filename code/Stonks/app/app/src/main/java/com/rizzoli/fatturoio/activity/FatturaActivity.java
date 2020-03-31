package com.rizzoli.fatturoio.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.rizzoli.fatturoio.R;

public class FatturaActivity extends AppCompatActivity {

    private FatturaStatePagerAdapter mStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fattura);

        mStatePagerAdapter = new FatturaStatePagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container_fattura);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        FatturaStatePagerAdapter mAdapter = new FatturaStatePagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new FragmentFatturaLista());
        mAdapter.addFragment(new FragmentFatturaDettaglio());
        mViewPager.setAdapter(mAdapter);
    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
