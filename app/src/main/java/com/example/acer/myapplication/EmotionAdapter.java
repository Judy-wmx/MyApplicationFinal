package com.example.acer.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by acer on 2018/6/8.
 */

public class EmotionAdapter extends BaseAdapter {

    private Boolean visiblity = false;
    private List<Map<String, Object>> list;
    private LayoutInflater mInflater;
    private ViewHolder holder;

    public EmotionAdapter(Context context, List<Map<String, Object>> list) {
        super();
        // TODO Auto-generated constructor stub
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    static class ViewHolder {
        public CheckBox checkB;
        public TextView content;
        public TextView data;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
            holder.checkB = (CheckBox) convertView.findViewById(R.id.checkBox);
            holder.data = (TextView) convertView.findViewById(R.id.textView2);
            holder.content = (TextView) convertView
                    .findViewById(R.id.textView1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.content.setText(list.get(position).get("content").toString());
        holder.data.setText(list.get(position).get("filename").toString());
        holder.checkB.setChecked((Boolean) list.get(position).get("isChecked"));
        //设置checkbox监视器，并根据checkbox的ischecked属性更改list中对应值
        holder.checkB.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                list.get(position).put("isChecked", isChecked);
            }
        });
        if (visiblity)
            holder.checkB.setVisibility(View.VISIBLE);
        else
            holder.checkB.setVisibility(View.GONE);
        return convertView;
    }
    // 设置checkbox是否可见
    public void setVisiblity(Boolean b) {
        visiblity = b;
    }
}
