package com.example.homework11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework11.databinding.FragmentPerformerBinding;


public class PerformerFragment extends Fragment {
    private FragmentPerformerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerformerBinding.inflate(inflater, container, false);
        setTextView();
        binding.toolbarPerformer.getRoot().setTitle("Performer");
        clickBackToolbar();
        setHasOptionsMenu(true);
        binding.bottomNavigationPerformer.setSelectedItemId(R.id.bottomNavigationActionPerformer);
        clickItemBottomNavigationView();

        return binding.getRoot();
    }
    private void setTextView(){
        if (getArguments() != null) {
            Song song = (Song)getArguments().getSerializable("Song");
            if (song != null) {
                String nameSong ="Song: " + song.getName();
                String performer ="Performer: " + song.getPerformer();
                binding.textViewNameSongPerformer.setText(nameSong);
                binding.textViewPerformer.setText(performer);
            }
        }


    }
    private void loadFragment(Fragment fragment){
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
    private void clickBackToolbar(){
        AppCompatActivity activity = (AppCompatActivity)requireActivity();
        activity.setSupportActionBar(binding.toolbarPerformer.getRoot());
        if(activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarPerformer.getRoot().setNavigationOnClickListener(v -> activity.getOnBackPressedDispatcher().onBackPressed());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.toolbarNavigationHome) {
            loadFragment(new PlayListFragment());
        } else if (itemId == R.id.toolbarNavigationSetting) {
            loadFragment(new SettingFragment());
        }
        return super.onOptionsItemSelected(item);
    }

    private void clickItemBottomNavigationView(){
        binding.bottomNavigationPerformer.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.bottomNavigationActionSetting){
                loadFragment(new SettingFragment());
            } else if (itemId == R.id.bottomNavigationActionPlayList) {
                loadFragment(new PlayListFragment());
            }
            return true;
        });
    }
}