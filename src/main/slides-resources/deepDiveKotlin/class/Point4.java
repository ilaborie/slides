package _06_class_1;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n"},
   d2 = {"L_06_class_1/Point4;", "", "pair", "Lkotlin/Pair;", "", "(Lkotlin/Pair;)V", "x", "y", "(II)V", "getX", "()I", "getY"}
)
public final class Point4 {
   private final int x;
   private final int y;

   public final int getX() {
      return this.x;
   }

   public final int getY() {
      return this.y;
   }

   public Point4(int x, int y) {
      super();
      this.x = x;
      this.y = y;
      String var3 = "" + '(' + this.x + ", " + this.y + ')';
      System.out.println(var3);
   }

   public Point4(@NotNull Pair pair) {
      Intrinsics.checkParameterIsNotNull(pair, "pair");
      this(((Number)pair.getFirst()).intValue(), ((Number)pair.getSecond()).intValue());
   }
}
