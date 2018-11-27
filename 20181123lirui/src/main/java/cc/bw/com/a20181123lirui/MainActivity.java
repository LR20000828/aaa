package cc.bw.com.a20181123lirui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import cc.bw.com.a20181123lirui.fragment.Frag_01;
import cc.bw.com.a20181123lirui.fragment.Frag_02;
import cc.bw.com.a20181123lirui.fragment.Frag_03;

public class MainActivity extends AppCompatActivity {


    private FragmentManager m;
    private FragmentTransaction fragmentTransaction;
    private RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicreate();

        iniData();
    }

    private void iniData() {
        m = getSupportFragmentManager();
        fragmentTransaction = m.beginTransaction();
        final Frag_01 frag_01 = new Frag_01();
        final Frag_02 frag_02 = new Frag_02();
        final Frag_03 frag_03 = new Frag_03();
        fragmentTransaction.add(R.id.frame,frag_01);
        fragmentTransaction.add(R.id.frame,frag_02);
        fragmentTransaction.add(R.id.frame,frag_03);
        fragmentTransaction.show(frag_01);
        fragmentTransaction.hide(frag_02);
        fragmentTransaction.hide(frag_03);
        fragmentTransaction.commit();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction transaction = m.beginTransaction();
                transaction.hide(frag_01);
                transaction.hide(frag_02);
                transaction.hide(frag_03);
                switch (i){
                    case 1:transaction.show(frag_01);break;
                    case 2:transaction.show(frag_02);break;
                    case 3:transaction.show(frag_03);break;
                }
                transaction.commit();
            }
        });
    }

    private void inicreate() {
        radiogroup = findViewById(R.id.radiogroup);
    }
}
