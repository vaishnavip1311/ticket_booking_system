package com.ticketbooking.util;

import java.util.Comparator;

import com.ticketbooking.bean.Event;

public class EventNameLocationComparator implements Comparator<Event>{
	
	@Override
    public int compare(Event e1, Event e2) {
        int nameCompare = e1.getEventName().compareToIgnoreCase(e2.getEventName());
        if (nameCompare == 0) {
            return e1.getVenue().getAddress().compareToIgnoreCase(e2.getVenue().getAddress());
        }
        return nameCompare;
    }

}
