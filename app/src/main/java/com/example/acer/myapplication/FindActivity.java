package com.example.acer.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FindActivity extends AppCompatActivity {

    private List<News> newsList;
    private NewsAdapter adapter;
    private Handler handler;
    private ListView lv;

    private final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        newsList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.news_lv);
        getNews();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    adapter = new NewsAdapter(FindActivity.this,newsList);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News news = newsList.get(position);
                            Intent intent = new Intent(FindActivity.this,NewsDisplayActivity.class);
                            intent.putExtra("news_url",news.getNewsUrl());
                            startActivity(intent);
                        }
                    });

                }
            }
        };
    }

    private void getNews() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String title = "";
                String uri = "";
                String desc = "";
                String imageUrl = "";
                Bitmap bitmap=null;

                Document doc = null;
                try {
                    doc = Jsoup.connect("http://www.ledu365.com/xinling/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Log.i(TAG, "run: " + doc.title());
                Elements e1 = doc.select("ul.e2");
                //Log.i(TAG, "run: " + e1.select("li"));
                Elements e2 = e1.select("li");
                for (int j = 0; j < e2.size(); j++) {
                    title = e2.get(j).select("a.title").text();
                    uri = "http://www.ledu365.com" + e2.get(j).select("a.title").attr("href");
                    desc = e2.get(j).select("p").text();
                    Elements e3 = e2.get(j).select("a.preview");
                    imageUrl = e3.select("img").attr("src");//获取图片链接
                    Log.i(TAG, "run: title:" + title + "----->uri:" + uri + "desc:" + desc + "imageUrl:" + imageUrl);

                    try {
                        URL imaURL = new URL(imageUrl);
                        Log.i(TAG,"run:imaURL="+imaURL);
                        InputStream inputStream=imaURL.openStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    News news = new News(title, uri, desc, bitmap);
                    newsList.add(news);

                }


                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }


        }).start();


    }

    //底部按钮事件
    public void btn2Click(View view) {
        switch (view.getId()) {

            case R.id.btn_Emotion2: {
                Intent intent = new Intent(this, EmotionActivity.class);
                startActivityForResult(intent, 0);
                break;
            }

            case R.id.btn_Find2:{
                Intent intent = new Intent(this, FindActivity.class);
                startActivityForResult(intent, 0);
                break;
            }
            case R.id.btn_Me2:{
                Intent intent = new Intent(this, MeActivity.class);
                startActivityForResult(intent, 0);
                break;
            }

        }
    }
}
