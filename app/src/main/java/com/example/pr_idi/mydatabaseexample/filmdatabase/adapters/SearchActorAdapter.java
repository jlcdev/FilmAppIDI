package com.example.pr_idi.mydatabaseexample.filmdatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.filters.ActorFilter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.SearchByActor;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

import java.util.List;


public class SearchActorAdapter extends BaseAdapter
{
    private List<Film> originalList;
    LayoutInflater layoutInflater;
    private ActorFilter actorFilter;
    private Context context;
    private SearchByActor searchByActor;

    public SearchActorAdapter(SearchByActor baseClass, Context context, List originalList)
    {
        this.originalList = originalList;
        this.context = context;
        this.searchByActor = baseClass;
        layoutInflater = LayoutInflater.from(context);
        getFilter();
    }

    @Override
    public int getCount() {
        return originalList.size();
    }

    @Override
    public Object getItem(int position) {
        return originalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        convertView = layoutInflater.inflate(R.layout.list_item_search_actor, null);
        TextView textView = (TextView) convertView.findViewById(R.id.list_item_search_actor_text);
        final Film film = this.originalList.get(position);
        textView.setText(film.getProtagonist());
        return convertView;
    }

    public ActorFilter getFilter(){
        if(actorFilter == null) actorFilter = new ActorFilter(this);
        return actorFilter;
    }

    public void updateAdapter(List<Film> filtered){
        this.originalList = filtered;
        this.notifyDataSetChanged();
    }
}
