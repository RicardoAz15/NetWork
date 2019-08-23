package com.example.montepio_campaign_json;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.montepio_campaign_json.NetWork.ResponseContent;

import java.util.List;

/**
 * Created by E493 on 27/03/2017.
 */

public class Adapter extends
        RecyclerView.Adapter<Adapter.ViewHolder> {

    private final static String TAG = Adapter.class.getSimpleName();

    private List<ResponseContent.ResponseContentResult> contentResultList;
    private Integer index;
    private AppCompatActivity activity;

    private int layout;
    private View view;


    public Adapter(AppCompatActivity activity, int layout,
                   List<ResponseContent.ResponseContentResult> resultList, Integer index) {
        this.activity = activity;
        this.layout = layout;
        this.contentResultList = resultList;
        this.index = index;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        view = LayoutInflater.from(activity).inflate(layout, parent, false);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 final int position) {
        if (contentResultList != null) {
            holder.contentResult = contentResultList.get(position);
            holder.title.setText(holder.contentResult.getTitle());
            holder.description.setText(holder.contentResult.getDescription());
            String link = holder.contentResult.getContentImage().getmUrl().getmLarge();
            Glide.with(activity).load(link).
                    apply(RequestOptions.circleCropTransform()).into(holder.image);
            final String link2 = holder.contentResult.getLink();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link2));
                    activity.startActivity(browserIntent);
                }
            });
        } else {
            holder.title.setText("");
            holder.description.setText("");
        }

    }

    @Override
    public int getItemCount() {
        if (contentResultList == null) {
            return 0;
        } else {
            return contentResultList.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,description;
        ResponseContent.ResponseContentResult contentResult;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image_id);
            title = (TextView) itemView.findViewById(R.id.title_id);
            description = (TextView) itemView.findViewById(R.id.description);
        }

    }
}
