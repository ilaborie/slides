public static List<Event> notFunErrors2(List<Event> events, int size) {
    List<Event> result = new ArrayList<>();
    for (Event event: events) { 
        if (event.isError()) {
            result.add(event);
        }
        if (result.size() >= size) {
            return result;
        }
    }
    return result;
}