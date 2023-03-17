package android.com.demotablayout.adapter;


import android.com.demotablayout.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SprinnerAdapter extends BaseAdapter {

    private Context context;

    public SprinnerAdapter(Context context) {
        this.context = context;
    }

    private int[] arrImg = {R.drawable.samsung, R.drawable.asus,R.drawable.iphone,R.drawable.xiaomi};
    @Override
    public int getCount() {
        return arrImg.length;
    }

    @Override
    public Object getItem(int position) {
        return arrImg[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ImageView imgCat;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_sprint, parent, false);
        imgCat = v.findViewById(R.id.imgCat);
        imgCat.setImageResource(arrImg[position]);
        return v;
    }
}
