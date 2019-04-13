package com.t123i456x.stylechange;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder>{

    private List<Color> mColorList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView colorView;
        ImageView colorImage;
        TextView colorName;

        public ViewHolder(View view) {
            super(view);
            colorView = (CardView) view;
            colorImage = (ImageView) view.findViewById(R.id.color_image);
            colorName = (TextView) view.findViewById(R.id.color_name);
        }
    }

    public ColorAdapter(List<Color> colorList) {
        mColorList = colorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {mContext = parent.getContext();}
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.colorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Color color = mColorList.get(position);
                Toast.makeText(v.getContext(), "你点击了文字 " + color.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.colorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Color color = mColorList.get(position);
                Toast.makeText(v.getContext(), "你点击了图片 " + color.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Video.class);
                intent.putExtra(Video.COLOR_NAME, color.getName());
                intent.putExtra(Video.COLOR_IMAGE_ID, color.getImageId());
                mContext.startActivity(intent);
            }
        });
        holder.colorImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                Color color = mColorList.get(position);
                Toast.makeText(v.getContext(), "你长按了图片 " + color.getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Color color = mColorList.get(position);
        //holder.colorImage.setImageResource(color.getImageId());
        Glide.with(mContext).load(color.getImageId()).into(holder.colorImage);
        holder.colorName.setText(color.getName());
    }

    @Override
    public int getItemCount() {
        return mColorList.size();
    }
}
