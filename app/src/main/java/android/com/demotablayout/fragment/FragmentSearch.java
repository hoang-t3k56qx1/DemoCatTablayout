package android.com.demotablayout.fragment;

import android.com.demotablayout.MainActivity;
import android.com.demotablayout.R;
import android.com.demotablayout.adapter.CatAdapter;
import android.com.demotablayout.model.Cat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentSearch extends Fragment {
    SearchView searchView;
    RecyclerView rView;
    CatAdapter catAdapter;
    ArrayList<Cat> filterList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        rView = v.findViewById(R.id.rcViewSearch);
        searchView = v.findViewById(R.id.search);
        catAdapter = new CatAdapter(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(catAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
//                if(!s.isEmpty()){
//                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//                }

                return false;
            }
        });
        return v;
    }
    private void filter(String s) {
        Log.e("hoangdev", "so luong " + MainActivity.catArrayList.size());
        filterList = new ArrayList<>();
        for(Cat i : MainActivity.catArrayList){
            filterList.add(i);
//            if(i.getName().toLowerCase().contains(s.toLowerCase())){
//                filterList.add(i);
//            }
        }

        if(filterList.isEmpty()){
            Toast.makeText(getContext(), "No data found!", Toast.LENGTH_SHORT).show();
        } else{
            catAdapter.setArrayCat(filterList);
        }
    }
}
