package com.example.ioc.evshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ioc.evshare.R;
import com.example.ioc.evshare.model.Event;

import java.util.List;

public class EventsListAdapter extends ArrayAdapter<Event> {

    static class ViewHolderEvent {
        private TextView eventNameTextView;
        private TextView eventLocationTextView;
        private TextView eventDateTextView;

        public TextView getEventNameTextView() {
            return eventNameTextView;
        }

        public void setEventNameTextView(TextView eventNameTextView) {
            this.eventNameTextView = eventNameTextView;
        }

        public TextView getEventLocationTextView() {
            return eventLocationTextView;
        }

        public void setEventLocationTextView(TextView eventLocationTextView) {
            this.eventLocationTextView = eventLocationTextView;
        }

        public TextView getEventDateTextView() {
            return eventDateTextView;
        }

        public void setEventDateTextView(TextView eventDateTextView) {
            this.eventDateTextView = eventDateTextView;
        }
    }

    public EventsListAdapter(Context context, int resource, List<Event> events) {
        super(context, resource, events);
    }


    public View getView(int position, View convertView, ViewGroup parrent) {
        ViewHolderEvent eventHolderItem;
        Event event = getItem(position);

        if (event == null) {
            throw new RuntimeException("Event from position " + position + " is null");
        }

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.events_list_item, parrent, false);
            eventHolderItem = new ViewHolderEvent();
            eventHolderItem.setEventNameTextView((TextView) convertView.findViewById(R.id.events_list_item_eventname));
            eventHolderItem.setEventLocationTextView((TextView) convertView.findViewById(R.id.events_list_item_eventlocation));
            eventHolderItem.setEventDateTextView((TextView) convertView.findViewById(R.id.events_list_item_eventdate));
            convertView.setTag(eventHolderItem);
        } else {
            eventHolderItem = (ViewHolderEvent) convertView.getTag();
        }

        eventHolderItem.getEventNameTextView().setText(event.getName());
        eventHolderItem.getEventLocationTextView().setText(event.getLocation());
        eventHolderItem.getEventDateTextView().setText(event.getDate());

        return convertView;
    }
}
