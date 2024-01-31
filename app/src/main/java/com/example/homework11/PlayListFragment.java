package com.example.homework11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.homework11.databinding.FragmentPlayListBinding;

import java.util.ArrayList;

public class PlayListFragment extends Fragment {

    private FragmentPlayListBinding binding;
    private final ArrayList<Song> playList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayListBinding.inflate(inflater, container, false);
        if(playList.isEmpty()) {
            setPlayList();
        }
        initAdapter();
        clickItemPlayList();
        binding.toolbarPlayList.getRoot().setTitle("PlayList");
        clickBackToolbar();
        setHasOptionsMenu(true);
        clickItemBottomNavigationView();

        return binding.getRoot();
    }
    private void setPlayList(){
        playList.add(new Song("Music 1","Author 1", R.drawable.image_music ));
        playList.add(new Song("Music 2","Author 2", R.drawable.image_music ));
        playList.add(new Song("Music 3","Author 3", R.drawable.image_music ));
        playList.add(new Song("Music 4","Author 4", R.drawable.image_music ));
        playList.add(new Song("Music 5","Author 5", R.drawable.image_music ));
        playList.add(new Song("Music 6","Author 6", R.drawable.image_music ));
        playList.add(new Song("Music 7","Author 7", R.drawable.image_music ));
        playList.add(new Song("Music 8","Author 8", R.drawable.image_music ));
        playList.add(new Song("Music 9","Author 9", R.drawable.image_music ));
        playList.add(new Song("Music 10","Author 10", R.drawable.image_music ));
    }
    private void initAdapter(){
        adapter = new MyAdapter(playList);
        binding.recyclerViewPlayList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewPlayList.setAdapter(adapter);
    }
    private void clickItemPlayList(){
        adapter.setOnClickListener((position, song) -> {
            PerformerFragment fragment = new PerformerFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Song", song);
            fragment.setArguments(bundle);
            loadFragment(fragment);
        });
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
        activity.setSupportActionBar(binding.toolbarPlayList.getRoot());
        if(activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarPlayList.getRoot().setNavigationOnClickListener(v -> activity.getOnBackPressedDispatcher().onBackPressed());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.toolbarNavigationSetting) {
            loadFragment(new SettingFragment());
        }
        return super.onOptionsItemSelected(item);
    }
    private void clickItemBottomNavigationView(){
        binding.bottomNavigationPlayList.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.bottomNavigationActionSetting){
                loadFragment(new SettingFragment());
            } else if (itemId == R.id.bottomNavigationActionPerformer) {
                loadFragment(new PerformerFragment());
            }
            return true;
        });
    }
}