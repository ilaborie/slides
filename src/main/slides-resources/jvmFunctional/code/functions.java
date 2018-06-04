class Utils {
  
  public static String doSomething(String arg) {
    throw new RuntimeException("Not yet impletmented");
  }
  
  public static Function<String, String> asValue = Utils::doSomething;
  
  public static Function<String, String> aLambda = (String s) -> {
    throw new RuntimeException("Not yet impletmented")
  };
  
}