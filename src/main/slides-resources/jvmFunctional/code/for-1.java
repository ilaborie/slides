public static List<Event> notFunErrors1(List<Event> events, int size) {
    List<Event> result = new ArrayList<>();
    for (int i = 0; i < result.size(); i++) {
        Event event = events.get(i);
        if (event.isError()) {
            result.add(event);
        }
        if (result.size() >= size) {
            return result;
        }
    }
    return result;
}