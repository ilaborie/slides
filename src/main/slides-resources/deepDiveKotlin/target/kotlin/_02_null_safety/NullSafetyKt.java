package _02_null_safety;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003"},
   d2 = {"nullsafety", "", "something", ""}
)
public final class NullSafetyKt {
   public static final void nullsafety() {
      String somethingNotNull = "aString";
      int length = somethingNotNull.length();
      String something = (String)null;
      int length = false;
      String var10000 = something();
      length = var10000 != null ? var10000.length() : 0;
      something = "aString";
      length = something.length();
   }

   @Nullable
   public static final String something() {
      return null;
   }
}
