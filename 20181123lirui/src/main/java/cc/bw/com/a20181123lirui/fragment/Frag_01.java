package cc.bw.com.a20181123lirui.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import cc.bw.com.a20181123lirui.NetWorkUtils;
import cc.bw.com.a20181123lirui.R;
import cc.bw.com.a20181123lirui.bean.MyBoss;
import cc.bw.com.a20181123lirui.bean.MyData;
import cc.bw.com.a20181123lirui.dao.Dao;

public class Frag_01 extends Fragment {

    ArrayList<MyData> list = new ArrayList<MyData>();
    private PullToRefreshListView pulllist;
    int a;
    private MyAdapter myAdapter;

    String urll = "http://www.xieast.com/api/news/news.php?page=";


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01, container, false);

        pulllist = view.findViewById(R.id.pulllist);
        pulllist.setMode(PullToRefreshBase.Mode.BOTH);
        pulllist.setEnabled(true);
        myAdapter = new MyAdapter();
        pulllist.setAdapter(myAdapter);
        pulllist.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                list.clear();
                a=1;
                inidata(a);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                inidata(++a);
            }
        });

        new MyAsyTask().execute(urll+1);

        return view;
    }

    private void inidata(int aa) {
        String ss = urll+aa;
        new MyAsyTask().execute(ss);
    }

    private class MyAsyTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
                String json = UtilData.getJson(strings[0]);
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(NetWorkUtils.isNetWorkAvailable(getActivity())){
                Dao dao = new Dao(getContext());
                ContentValues values = new ContentValues();
                values.put("data",s);
                dao.insert("people","data=?",values);
                Gson gson = new Gson();
                ArrayList<MyData> data = gson.fromJson(s, MyBoss.class).getData();
                list.addAll(data);
            }else{
                Toast.makeText(getActivity(),"无网状态!",1).show();
                Dao dao = new Dao(getContext());
                Cursor people = dao.select("people", null, null, null, null, null, null);
                if(people.moveToFirst()){
                    String data = people.getString(people.getColumnIndex("data"));
                    Gson gson = new Gson();
                    ArrayList<MyData> ss = gson.fromJson(data, MyBoss.class).getData();
                    list.addAll(ss);
                }
            }
            myAdapter.notifyDataSetChanged();
            pulllist.onRefreshComplete();
        }
    }

    private class MyAdapter extends BaseAdapter {

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
                view = View.inflate(getActivity(), R.layout.item0, null);
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

}
