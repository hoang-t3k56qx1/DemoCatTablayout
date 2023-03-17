package android.com.demotablayout.fragment;

import android.app.AlertDialog;
import android.com.demotablayout.MainActivity;
import android.com.demotablayout.R;
import android.com.demotablayout.adapter.CatAdapter;
import android.com.demotablayout.adapter.SprinnerAdapter;
import android.com.demotablayout.model.Cat;
import android.com.demotablayout.model.DeleteListener;
import android.com.demotablayout.model.UpdateListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    Spinner mSpinner;
    RecyclerView rView;
    EditText edtName, edtPrice, edtDiscribe;
    Button btnAdd, btnUpdate;
    ArrayList<Cat> catArrayList;

    SprinnerAdapter spAdapter;
    CatAdapter catAdapter;
    int i;

    public FragmentHome() {
        this.catArrayList = MainActivity.catArrayList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
        spAdapter = new SprinnerAdapter(getContext());
        mSpinner.setAdapter(spAdapter);

        btnUpdate.setEnabled(false);
        btnAdd.setEnabled(true);

        catAdapter = new CatAdapter(getContext(), new DeleteListener() {
            @Override
            public void listener(int postion) {
                deleteCat(postion);
            }
        }, new UpdateListener() {
            @Override
            public void listener(int postion) {
                btnUpdate.setEnabled(true);
                btnAdd.setEnabled(false);
                i= postion;
                Cat cat = catArrayList.get(postion);
                setTextForm(cat.getName(), String.valueOf(cat.getPrice()), cat.getDiscribe());
                mSpinner.setSelection(postion);
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(catAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCat();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCat(i);
                btnUpdate.setEnabled(false);
                btnAdd.setEnabled(true);
            }
        });
        return v;
    }





    private void deleteCat(int postion) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Delete");
        dialog.setMessage("Bạn chắc muốn xóa " + catArrayList.get(postion).getName() + " chứ ?");

        dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cat cat = catAdapter.getArrayCat().get(postion);
                for(int d = 0; d<catArrayList.size(); d++){
                    if(cat.getName().equals(catArrayList.get(d).getName())){
                        catArrayList.remove(d);
                        catAdapter.setArrayCat(catArrayList);
                        break;
                    }
                }

            }
        });

        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void updateCat(int postion) {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        String discribe = edtDiscribe.getText().toString();

        if (name.isEmpty() || name.equals("")
                || discribe.isEmpty() || discribe.equals("")
                || price.isEmpty() || price.equals("")) {

            Toast.makeText(getContext(), "Vui lòng điềm đủ thông tin !!!", Toast.LENGTH_SHORT).show();
        } else {
            Cat cat = new Cat(Integer.parseInt(mSpinner.getSelectedItem().toString())
                    , name, Double.parseDouble(price), discribe);

            for(int d = 0; d<catArrayList.size(); d++){
                if(name.equals(catArrayList.get(d).getName())){
                    catArrayList.remove(d);
                    catAdapter.setArrayCat(catArrayList);
                    break;
                }
            }
            catArrayList.set(i, cat);
            catAdapter.setArrayCat(catArrayList);
            setTextForm("", "", "");
            Toast.makeText(getContext(), "Thay đổi thành công", Toast.LENGTH_SHORT).show();
        }

    }

    private void addCat() {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        String discribe = edtDiscribe.getText().toString();

        if (name.isEmpty() || name.equals("")
                || discribe.isEmpty() || discribe.equals("")
                || price.isEmpty() || price.equals("")) {

            Toast.makeText(getContext(), "Vui lòng điềm đủ thông tin !!!", Toast.LENGTH_SHORT).show();
        } else {
            Cat cat = new Cat(Integer.parseInt(mSpinner.getSelectedItem().toString())
                    , name, Double.parseDouble(price), discribe);
            catArrayList.add(cat);
            catAdapter.setArrayCat(catArrayList);
            setTextForm("", "", "");
            //Test du lieu

                Log.d("hoangdev", "so luong " + MainActivity.catArrayList.size());

        }
    }

    private void setTextForm(String name, String price, String discribe) {
        edtName.setText(name);
        edtPrice.setText(price);
        edtDiscribe.setText(discribe);
    }

    private void initView(View v) {


        mSpinner = v.findViewById(R.id.spinner);
        rView= v.findViewById(R.id.rcView);


        edtName = v.findViewById(R.id.edtName);
        edtPrice = v.findViewById(R.id.edtPrice);
        edtDiscribe = v.findViewById(R.id.edtDescribe);
        btnAdd = v.findViewById(R.id.btnAdd);
        btnUpdate = v.findViewById(R.id.btnUpdate);
    }
}
