package cc.bw.com.a20181123lirui.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import cc.bw.com.a20181123lirui.R;
import cc.bw.com.a20181123lirui.bean.MyData;

class MySAdapter extends BaseAdapter{
    public Context context;
    public ArrayList<MyData> list;

    public MySAdapter(Context context, ArrayList<MyData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder myHolder=null;
        if(view == null){
            view = View.inflate(context, R.layout.item0, null);
            myHolder = new MyHolder();
            myHolder.textView = view.findViewById(R.id.textone);
            myHolder.imageView = view.findViewById(R.id.image);
            view.setTag(myHolder);
        }else{
            myHolder = (MyHolder) view.getTag();
        }
        myHolder.textView.setText(list.get(i).getTitle());
        ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),myHolder.imageView);
        return view;
    }

    class MyHolder{
        TextView textView;
        ImageView imageView;
    }
}
