package _06_class_2;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016"},
   d2 = {"L_06_class_2/Animal;", "", "()V", "talk", ""}
)
public class Animal {
   @NotNull
   public String talk() {
      return "???";
   }

   public Animal() {
      super();
   }
}
