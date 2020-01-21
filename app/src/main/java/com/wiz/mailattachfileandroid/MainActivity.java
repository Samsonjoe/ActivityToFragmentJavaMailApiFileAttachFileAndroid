package com.wiz.mailattachfileandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.wiz.mailattachfileandroid.fragments.DialogClaimRequestform;
import com.wiz.mailattachfileandroid.fragments.Fragment1;
import com.wiz.mailattachfileandroid.fragments.Fragment2;
import com.wiz.mailattachfileandroid.utils.SectionsStatePagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        //setup
        mViewPager =(ViewPager) findViewById(R.id.container);
        //setup the pager
        setupViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new Fragment1(),"Fragment1");
        adapter.addFragment(new DialogClaimRequestform(),"DialogFragment");
        adapter.addFragment(new Fragment2(),"Fragment2");
        //adapter.addFragment(new Fragment3(),"Fragment3");
        viewPager.setAdapter(adapter);
    }

    public void setViewpager (int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
