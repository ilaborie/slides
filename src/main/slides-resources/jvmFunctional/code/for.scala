  def notFunErrors(events: List[Event], size: Int): List[Event] = {
    for {
      event <- events
      if event.isError
    } yield event
  }.take(size)

  def funErrors(events: List[Event], size: Int): List[Event] =
    events
      .filter(_.isError)
      .take(size)