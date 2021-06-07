package com.example.hopeart.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.hopeart.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ArtistOrderFragment extends Fragment {

    Context ctx;

    FrameLayout mViewPager;
    TabLayout mTabLayout;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean attachToRoot;
        return inflater.inflate(R.layout.artist_fragment_order, container, attachToRoot = false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout=view.findViewById(R.id.tablelayout);
        mViewPager=view.findViewById(R.id.frahContainer);

        mTabLayout.addTab(mTabLayout.newTab().setText("Artwork"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Custom"));

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.frahContainer,new ArtistArtworkOrderFragment());
        transaction.commitAllowingStateLoss();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        FragmentTransaction transaction=getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frahContainer,new ArtistArtworkOrderFragment());
                        transaction.commitAllowingStateLoss();
                        break;
                    case 1:
                        FragmentTransaction transaction2=getFragmentManager().beginTransaction();
                        transaction2.replace(R.id.frahContainer,new ArtistCustomizeOrderFrag());
                        transaction2.commitAllowingStateLoss();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

/*        ArtistOrderFragment.MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        myViewPagerAdapter.addFragment(new ArtistArtworkOrderFragment(),"ArtWork");
        myViewPagerAdapter.addFragment(new ArtistCustomizeOrderFrag(),"Custom");
        mViewPager.setAdapter(myViewPagerAdapter);
        myViewPagerAdapter.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);*/


    }

    class MyViewPagerAdapter extends FragmentPagerAdapter
    {

        final List<Fragment> listFragment = new ArrayList<>();
        final List<String> listTitles = new ArrayList<>();

        public MyViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ArtistArtworkOrderFragment();
                case 1:
                    return new ArtistCustomizeOrderFrag();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        public void addFragment(Fragment fragment, String title) {
            listFragment.add(fragment);
            listTitles.add(title);
        }
    }
}