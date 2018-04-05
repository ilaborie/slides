package _01_basic;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001"},
   d2 = {"tryString", "", "who"}
)
public final class String_templatesKt {
   @NotNull
   public static final String tryString(@NotNull String who) {
      Intrinsics.checkParameterIsNotNull(who, "who");
      return "Hello " + who + '!';
   }
}
