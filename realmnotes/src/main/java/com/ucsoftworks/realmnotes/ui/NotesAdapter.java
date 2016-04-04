package com.ucsoftworks.realmnotes.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucsoftworks.realmnotes.R;
import com.ucsoftworks.realmnotes.database.Note;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by pasencukviktor on 05/04/16
 */
public class NotesAdapter extends RealmBaseAdapter<Note> {
    public NotesAdapter(Context context, RealmResults<Note> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NotesViewHolder viewHolder;
        if (convertView == null) {
            Log.d("ADAPTER", "CREATE VIEW");
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_note, parent, false);

            viewHolder = new NotesViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (NotesViewHolder) convertView.getTag();

        final Note item = getItem(position);

        viewHolder.date.setText(String.valueOf(item.getDate()));
        viewHolder.note.setText(item.getNote());

        return convertView;
    }

    static class NotesViewHolder {
        @Bind(R.id.note)
        TextView note;
        @Bind(R.id.date)
        TextView date;

        NotesViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
