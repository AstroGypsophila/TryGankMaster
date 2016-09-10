package com.gypsophila.trygank.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gypsophila.commonlib.utils.ImageLoaderUtils;
import com.gypsophila.trygank.R;
import com.gypsophila.trygank.news.model.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 * Author : AstroGypsophila
 * Github  : https://github.com/AstroGypsophila
 * Date   : 2016/8/27
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private LayoutInflater mInflater;
    private Context mContext;
    private List<NewsBean> mData;

    public NewsAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = new ArrayList<>();
    }

    public void setData(List<NewsBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View item = mInflater.inflate(R.layout.recycler_item, parent, false);
            return new NewsListItemViewHolder(item);
        } else if (viewType == TYPE_FOOTER) {
            View footer = mInflater.inflate(R.layout.footer_layout, null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footer.setLayoutParams(params);
            return new FooterViewHolder(footer);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsListItemViewHolder) {
            NewsBean bean = mData.get(position);
            ImageLoaderUtils.loadImage(mContext, ((NewsListItemViewHolder) holder).mNewsImage, bean.getImgsrc());
            ((NewsListItemViewHolder) holder).mNewsTitle.setText(bean.getTitle());
            ((NewsListItemViewHolder) holder).mNewsDigest.setText(bean.getDigest());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size() + 1;
    }

    class NewsListItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mNewsImage;
        TextView mNewsTitle;
        TextView mNewsDigest;

        public NewsListItemViewHolder(View itemView) {
            super(itemView);
            mNewsImage = (ImageView) itemView.findViewById(R.id.news_image);
            mNewsTitle = (TextView) itemView.findViewById(R.id.news_title);
            mNewsDigest = (TextView) itemView.findViewById(R.id.news_digest);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }

}
