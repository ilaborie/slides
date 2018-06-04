val greeting = when (stranger) {
    is Teacher         -> "Hey professor!"
    is Director        -> "Hey director."
    Student("Richard") -> "Still here Ricky?"
    is Student         -> "Hey, ${stranger.name}."
}