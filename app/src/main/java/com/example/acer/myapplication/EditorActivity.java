package com.example.acer.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditorActivity extends Activity implements View.OnClickListener {

    private Button save_bt, del_bt;
    private EditText editT;
    private String filename;
    private String content;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        intent = getIntent();
        init();
        getFileName();
    }
    //获取从MainActivity中传过来的文件名
    private void getFileName() {
        // TODO Auto-generated method stub
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            filename = bundle.get("filename") + ".txt";
            File file = new File(this.getFilesDir() + "/" + filename);
            editT.setText(RWFile.readFile(file));
            editT.setSelection(editT.getText().length());
        }
    }
    //初始化数据
    private void init() {
        // TODO Auto-generated method stub

        save_bt = (Button) findViewById(R.id.save_bt);
        del_bt = (Button) findViewById(R.id.del_bt);
        save_bt.setOnClickListener(this);
        del_bt.setOnClickListener(this);
        editT = (EditText) findViewById(R.id.EditText);
        //获取当前时间，并设置为文件名
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd-hh-mm-ss");
        String date = sDateFormat.format(new Date());
        filename = date + ".txt";
    }
    //按钮点击的事件
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.save_bt: {
                content = editT.getText().toString();
                if (!content.equals("")) {
                    RWFile.writeFile(this.getFilesDir() + "/" + filename, content);
                    setResult(0);
                } else
                    setResult(1);
                finish();
                break;
            }
            case R.id.del_bt: {
                setResult(1);
                finish();
                break;
            }
        }
    }
    //如果内容不为空，则保存为txt文件
    public void saveText(String str) throws IOException {
        if (!str.equals("")) {
            FileOutputStream output = openFileOutput(filename, MODE_PRIVATE);
            output.write(str.getBytes());
            output.close();
        }

    }
    //设置返回键的事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK: {
                setResult(1, intent);
                finish();
                break;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
