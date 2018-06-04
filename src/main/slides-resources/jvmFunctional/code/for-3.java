public static List<Event> funErrors(List<Event> events, int size) {
    return events.stream()
            .filter(Event::isError)
            .limit(size)
            .collect(Collectors.toList());
}