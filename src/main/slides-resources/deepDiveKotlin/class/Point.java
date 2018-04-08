package _06_class_1;

import kotlin.Metadata;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0007\"\u0004\b\t\u0010\n"},
   d2 = {"L_06_class_1/Point;", "", "x", "", "y", "(II)V", "getX", "()I", "getY", "setY", "(I)V"}
)
public final class Point {
   private final int x;
   private int y;

   public final int getX() {
      return this.x;
   }

   public final int getY() {
      return this.y;
   }

   public final void setY(int var1) {
      this.y = var1;
   }

   public Point(int x, int y) {
      super();
      this.x = x;
      this.y = x;
   }
}
