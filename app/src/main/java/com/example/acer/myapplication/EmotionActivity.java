package com.example.acer.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmotionActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private Button add_bt,shanchu,quxiao;
    private ListView listV;
    private EmotionAdapter adapter;
    private List<Map<String, Object>> data_list;
    private FragmentManager manager;
    private OptionMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);
        init();
        RefreshItem();
    }
    //重新加载listview
    private void RefreshItem() {
        data_list.clear();
        String filePath = this.getFilesDir().toString();
        File folder = new File(filePath);
        File[] file = folder.listFiles();
        ArrayList<String> filenamelist = new ArrayList<String>();
        for (int i = 0; i < file.length; i++) {
            filenamelist.add(file[i].getAbsolutePath());
        }
        //降序
        //Collections.sort(filenamelist,Collections.reverseOrder());
        //升序
        Collections.sort(filenamelist);
        for (int i = 0; i < file.length; i++) {
            File filex = new File(filenamelist.get(i));
            String content = RWFile.readFile(filex);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", content);
            map.put("filename", getFileName(filex));
            map.put("isChecked", Boolean.FALSE);
            data_list.add(map);
        }
        adapter.notifyDataSetChanged();
    }

    //获取文件名
    public String getFileFullName(File file) {
        String path = file.getAbsolutePath();
        int index = path.lastIndexOf("/") + 1;

        String filefullname = path.substring(index);
        return filefullname;
    }

    public String getFileName(File file) {
        String filefullname = getFileFullName(file);
        int index = filefullname.lastIndexOf(".");
        String filename = filefullname.substring(0, index);
        return filename;
    }
    //初始化数据
    private void init() {
        add_bt = (Button) findViewById(R.id.add_bt);
        add_bt.setOnClickListener(this);
       shanchu = (Button) findViewById(R.id.shanchu);
       shanchu.setOnClickListener(this);
       quxiao = (Button) findViewById(R.id.quxiao);
       quxiao.setOnClickListener(this);
       //shanchu.setVisibility(View.GONE);
       //quxiao.setVisibility(View.GONE);

        listV = (ListView) findViewById(R.id.emotion_ListView);
        data_list = new ArrayList<Map<String, Object>>();
        adapter = new EmotionAdapter(this, data_list);
        listV.setAdapter(adapter);
        listV.setOnItemClickListener(this);
        //listV.setOnItemLongClickListener(this);
        this.registerForContextMenu(listV);
        manager = getFragmentManager();
    }

    //设置按钮点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_bt: {
                Intent intent = new Intent(this, EditorActivity.class);
                startActivityForResult(intent, 0);
                break;
            }
        }
    }

    // 根据返回值执行不同事件
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        switch (resultCode) {
            case 0: {
                RefreshItem();
                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    //设置item点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) adapter
                .getItem(position);
        Intent intent = new Intent(EmotionActivity.this, EditorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("filename", map.get("filename").toString());
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }


    //设置item长按事件
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        menu = new OptionMenu();
        if(manager.getBackStackEntryCount()==0){
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.LinearLayout, menu);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        adapter.setVisiblity(true);
        adapter.notifyDataSetChanged();
        add_bt.setVisibility(View.GONE);
        shanchu.setVisibility(View.VISIBLE);
        quxiao.setVisibility(View.VISIBLE);
        return true;
    }

    //按钮事件
    public void doclick(View view) {
        switch (view.getId()) {

            case R.id.shanchu: {
                for (int i = 0; i < data_list.size(); i++) {
                    if ((Boolean) data_list.get(i).get("isChecked")) {
                        String filepath = this.getFilesDir().toString() + "/"
                                + data_list.get(i).get("filename") + ".txt";
                        File file = new File(filepath);
                        file.delete();
                    }
                }
                RefreshItem();
                adapter.notifyDataSetChanged();
            }
            case R.id.quxiao: {
                add_bt.setVisibility(View.VISIBLE);
                onBackPressed();
                adapter.setVisiblity(false);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    //设置返回按钮事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                onBackPressed();
                add_bt.setVisibility(View.VISIBLE);
                adapter.setVisiblity(false);
                adapter.notifyDataSetChanged();
                break;
        }
        return false;
    }

    //底部按钮事件
    public void btnClick(View view) {
        switch (view.getId()) {

            case R.id.btn_Emotion: {
                Intent intent = new Intent(this, EmotionActivity.class);
                startActivityForResult(intent, 0);
                break;
            }

            case R.id.btn_Find:{
                Intent intent = new Intent(this, FindActivity.class);
                startActivityForResult(intent, 0);
                break;
            }
            case R.id.btn_Me:{
                Intent intent = new Intent(this, MeActivity.class);
                startActivityForResult(intent, 0);
                break;
            }

        }
    }

}