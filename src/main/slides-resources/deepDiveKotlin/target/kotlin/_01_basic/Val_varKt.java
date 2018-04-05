package _01_basic;

import kotlin.Metadata;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u001a\u0006\u0010\u0000\u001a\u00020\u0001"},
   d2 = {"tryValVar", ""}
)
public final class Val_varKt {
   public static final void tryValVar() {
      int x = 10;
      int y = 3;
      int x = x + 4;
      int var2 = x * y;
      System.out.println(var2);
   }
}
