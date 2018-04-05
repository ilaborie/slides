package _07_type_hierarchy;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000e\u001a\u0006\u0010\u0000\u001a\u00020\u0001"},
   d2 = {"doSomething", ""}
)
public final class TodoKt {
   @NotNull
   public static final String doSomething() {
      throw (Throwable)(new NotImplementedError((String)null, 1, (DefaultConstructorMarker)null));
   }
}
