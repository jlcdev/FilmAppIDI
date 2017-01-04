package com.example.pr_idi.mydatabaseexample.filmdatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pr_idi.mydatabaseexample.filmdatabase.R;
import com.example.pr_idi.mydatabaseexample.filmdatabase.skeleton.Film;

import java.util.List;


public class SearchFilmAdapter extends BaseAdapter
{
    private List<Film> originalList;
    private List<Film> filteredList;
    LayoutInflater layoutInflater;

    public SearchFilmAdapter(Context context, List originalList)
    {
        this.originalList = originalList;
        this.filteredList = originalList;
        layoutInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = layoutInflater.inflate(R.layout.list_item_search_title, null);
        TextView textView = (TextView) convertView.findViewById(R.id.list_item_search_title_text);
        Film film = this.originalList.get(position);
        textView.setText(film.getTitle() + " - " + film.getDirector());
        ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.list_item_search_title_button);
        return convertView;
    }
}
