package _09_structures.recusion;

import kotlin.Metadata;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001"},
   d2 = {"forFactorial", "", "n"}
)
public final class For_factorialKt {
   public static final int forFactorial(int n) {
      int acc = 1;
      int i = 1;
      int var3 = n;
      if (i <= n) {
         while(true) {
            acc *= i;
            if (i == var3) {
               break;
            }

            ++i;
         }
      }

      return acc;
   }
}
