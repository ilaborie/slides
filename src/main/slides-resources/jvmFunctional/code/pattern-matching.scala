val greeting = stranger match {
  case Teacher            => "Hey professor!"
  case Director           => "Hey director."
  case Student("Richard") => "Still here Ricky?"
  case Student(name)      => s"Hey, $name."
}