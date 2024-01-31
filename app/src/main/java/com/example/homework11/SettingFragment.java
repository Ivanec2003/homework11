package com.example.homework11;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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

import com.example.homework11.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        setInfoPlayList();
        binding.toolbarSetting.getRoot().setTitle("Setting");
        clickBackToolbar();
        setHasOptionsMenu(true);
        binding.bottomNavigationSetting.setSelectedItemId(R.id.bottomNavigationActionSetting);
        clickItemBottomNavigationView();


        return binding.getRoot();
    }
    private void setInfoPlayList(){
        PackageManager packageManager = requireActivity().getPackageManager();
        String packageName ="Name of program:" + requireActivity().getPackageName();
        String versionName = "Version of program:";
        String versionCode = "Version of code";

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            versionName += packageInfo.versionName;
            versionCode = "Version of code: " + packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            versionName += "Unknown version";
            versionCode += "-1";
        }
        binding.textViewNameProgram.setText(packageName);
        binding.textViewVersionProgram.setText(versionName);
        binding.textViewVersionCode.setText(versionCode);
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
        activity.setSupportActionBar(binding.toolbarSetting.getRoot());
        if(activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarSetting.getRoot().setNavigationOnClickListener(v -> activity.getOnBackPressedDispatcher().onBackPressed());
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void clickItemBottomNavigationView(){
        binding.bottomNavigationSetting.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.bottomNavigationActionPlayList){
                loadFragment(new PlayListFragment());
            } else if (itemId == R.id.bottomNavigationActionPerformer) {
                loadFragment(new PerformerFragment());
            }
            return true;
        });
    }
}