package _09_structures.recusion;

import kotlin.Metadata;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001"},
   d2 = {"recFactorial", "", "n"}
)
public final class Rec_factorialKt {
   public static final int recFactorial(int n) {
      return n < 1 ? 1 : n * recFactorial(n - 1);
   }
}
