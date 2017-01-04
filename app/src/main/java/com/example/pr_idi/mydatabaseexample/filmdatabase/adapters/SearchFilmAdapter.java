package com.example.pr_idi.mydatabaseexample.filmdatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.filters.FilmFilter;
import com.example.pr_idi.mydatabaseexample.filmdatabase.fragments.SearchByTitle;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

import java.util.List;


public class SearchFilmAdapter extends BaseAdapter
{
    private List<Film> originalList;
    LayoutInflater layoutInflater;
    private FilmFilter filmFilter;
    private Context context;
    private SearchByTitle searchByTitle;

    public SearchFilmAdapter(SearchByTitle baseClass, Context context, List originalList)
    {
        this.originalList = originalList;
        this.context = context;
        this.searchByTitle = baseClass;
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
        convertView = layoutInflater.inflate(R.layout.list_item_search_title, null);
        TextView textView = (TextView) convertView.findViewById(R.id.list_item_search_title_text);
        final Film film = this.originalList.get(position);
        textView.setText(film.getTitle());
        ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.list_item_search_title_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                searchByTitle.delete(position);
            }
        });
        return convertView;
    }

    public FilmFilter getFilter(){
        if(filmFilter == null) filmFilter = new FilmFilter(this);
        return filmFilter;
    }

    public void updateAdapter(List<Film> filtered){
        this.originalList = filtered;
        this.notifyDataSetChanged();
    }
}
