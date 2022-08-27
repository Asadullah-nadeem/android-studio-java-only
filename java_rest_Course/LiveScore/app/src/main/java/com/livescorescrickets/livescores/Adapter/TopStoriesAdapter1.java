package com.livescorescrickets.livescores.Adapter;
import com.livescorescrickets.livescores.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.livescorescrickets.livescores.Activity.NewsDetailsActivity;
import com.livescorescrickets.livescores.Pojo.NewsPackage.Article;
import com.livescorescrickets.livescores.Pojo.NewsPackage.NewsModel;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import soup.neumorphism.NeumorphCardView;

public class TopStoriesAdapter1 extends RecyclerView.Adapter<TopStoriesAdapter1.ViewHolder> {
    public final Context context;
    private final NewsModel newsModel;

    public TopStoriesAdapter1(Context context2, NewsModel newsModel2) {
        this.context = context2;
        this.newsModel = newsModel2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        try {
            viewHolder.setData(this.newsModel.getArticles().get(i));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.newsModel.getArticles().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView latestTime;
        TextView news_description;
        PorterShapeImageView news_image;
        TextView news_title;
        NeumorphCardView parent;

        public ViewHolder(View view) {
            super(view);
            this.news_image = (PorterShapeImageView) view.findViewById(R.id.news_image);
            this.news_title = (TextView) view.findViewById(R.id.news_title);
            this.news_description = (TextView) view.findViewById(R.id.news_description);
            this.latestTime = (TextView) view.findViewById(R.id.latestTime);
            this.parent = (NeumorphCardView) view.findViewById(R.id.parent);
        }

        public void setData(final Article article) throws ParseException {
            Glide.with(TopStoriesAdapter1.this.context).load(article.getUrlToImage()).into(this.news_image);
            this.news_title.setText(article.getTitle());
            this.news_description.setText(article.getDescription());
            this.parent.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    Intent intent = new Intent(TopStoriesAdapter1.this.context, NewsDetailsActivity.class);
                    intent.putExtra("data", article);
                    TopStoriesAdapter1.this.context.startActivity(intent);
                }
            });
            this.latestTime.setText(new SimpleDateFormat("MMM d, h:mm a").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(article.getPublishedAt())));
        }
    }
}
