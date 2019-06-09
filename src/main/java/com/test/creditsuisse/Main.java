package com.test.creditsuisse;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        EventService eventService = new EventService();
        Map<String, Set<Event>> map = eventService.getEventMap(args[0]);
        eventService.persistEvents(map);
    }

}