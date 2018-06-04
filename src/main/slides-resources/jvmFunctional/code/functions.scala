package object apackage {
  
  def doSomething(arg: String): String = ???

  val asValue: String => String = doSomething
  
  val aLambda: String => String = (s: String) => ???
}