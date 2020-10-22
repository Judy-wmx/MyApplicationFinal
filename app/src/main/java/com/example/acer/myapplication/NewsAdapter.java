package com.example.acer.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by acer on 2018/6/7.
 */

public class NewsAdapter extends BaseAdapter {
    private List<News> newsList;
    private View view;
    private Context mContext;
    private ViewHolder viewHolder;

    private final String TAG = "";

    public NewsAdapter(Context mContext, List<News> newsList) {
        this.newsList = newsList;
        this.mContext = mContext;
    }

    public int getCount() {
        return newsList.size();
    }

    public Object getItem(int position) {
        return newsList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.news_item,
                    null);
            viewHolder = new ViewHolder();
            viewHolder.newsTitle = (TextView) view.findViewById(R.id.news_title);
            viewHolder.newsDesc = (TextView) view.findViewById(R.id.news_desc);
            viewHolder.imageUrl = (ImageView) view.findViewById(R.id.news_image);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        //写入标题及简介
        viewHolder.newsTitle.setText(newsList.get(position).getNewsTitle());
        Log.i(TAG,"run:getNewsTitle:"+newsList.get(position).getNewsTitle());
        viewHolder.newsDesc.setText(newsList.get(position).getDesc());
        //写入图片
        Bitmap bitmap=newsList.get(position).getBitmap();
        if(bitmap==null){
            Log.i(TAG,"NewsAdapter: null bitmap");
        }else{
            Log.i(TAG,"NewsAdapter:not null bitmap");
        }
        viewHolder.imageUrl.setImageBitmap(bitmap);
        //viewHolder.imageUrl.setImageResource(R.drawable.pic1);
        //final String s=newsList.get(position).getImageUrl();
        //Log.i(TAG,"getImageUrl"+s);
       /* try {
            Runnable downloadRun = new Runnable(){
                URL url=new URL(s);
                @Override
                public void run() {
                    try {
                        Drawable drawable = Drawable.createFromStream(url.openStream(), "image.jpg");
                        if (drawable == null) {
                            Log.i(TAG, "null drawable");
                        } else {
                            Log.i(TAG, "not null drawable");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(downloadRun).start();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        //viewHolder.imageUrl.setImageBitmap(bitmap);


        /*viewHolder.imageUrl.post(new Runnable() {
            Drawable drawable = loadImageFromNetwork(s);
            @Override
            public void run() {
                viewHolder.imageUrl.setImageDrawable(drawable);
            }
        });*/
        return view;
    }

    class ViewHolder {
        TextView newsTitle;
        TextView newsDesc;
        ImageView imageUrl;
    }


   /* private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            try {
                URL url = new URL(imageUrl);
                drawable = Drawable.createFromStream(url.openStream(), "image.jpg");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.i(TAG, "null drawable");
        } else {
            Log.i(TAG, "not null drawable");
        }

        return drawable ;
    }*/
   /*public static Bitmap getBitmap(String path) throws IOException {

       URL url = new URL(path);
       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
       conn.setConnectTimeout(5000);
       conn.setRequestMethod("GET");
       if(conn.getResponseCode() == 200){
           InputStream inputStream = conn.getInputStream();
           Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
           return bitmap;
       }
       return null;
   }
*/


}
