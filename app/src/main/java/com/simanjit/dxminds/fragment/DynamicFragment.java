package com.simanjit.dxminds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.simanjit.dxminds.R;
import com.simanjit.dxminds.activity.NewsWevView;
import com.simanjit.dxminds.model.Articles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DynamicFragment extends Fragment {
    View view;

    public static DynamicFragment newInstance(List<Articles> val, int position) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putSerializable("value", (Serializable) val);
        args.putInt("position", position);

        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<Articles> val;
    TextView title, description, url, publishedTime;
    ImageView thumbnail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        val = (ArrayList<Articles>) getArguments().getSerializable("value");

        thumbnail = view.findViewById(R.id.thumbnail);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        url = view.findViewById(R.id.url);
        publishedTime = view.findViewById(R.id.publishedTime);

        Glide.with(view.getContext())
                .load(val.get(getArguments().getInt("position", 0)).getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .error(R.drawable.noimage)
                .into(thumbnail);


        title.setText(getString(R.string.stringTitle, val.get(getArguments().getInt("position", 0)).getTitle()));
        description.setText(getString(R.string.stringDescription, val.get(getArguments().getInt("position", 0)).getDescription()));
        url.setText(getString(R.string.stringUrl, val.get(getArguments().getInt("position", 0)).getUrl()));
        publishedTime.setText(getString(R.string.stringPublished, val.get(getArguments().getInt("position", 0)).getPublishedAt()));

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsWevView.class);
                intent.putExtra("URL", val.get(getArguments().getInt("position", 0)).getUrl());
                startActivity(intent);
            }
        });

        return view;
    }
}