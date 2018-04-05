package _10_collections_1;

import astronomy.Moon;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005"},
   d2 = {"main", "", "args", "", "", "([Ljava/lang/String;)V"}
)
public final class Immutable_mutableKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      List earthMoon = CollectionsKt.listOf(new Moon("moon"));
      List add = CollectionsKt.plus((Collection)earthMoon, new Moon("moon 2"));
      String var3 = "earthMoon: " + earthMoon;
      System.out.println(var3);
      var3 = "add: " + add;
      System.out.println(var3);
      var3 = "reference equality: " + (earthMoon == add);
      System.out.println(var3);
      var3 = "\n";
      System.out.println(var3);
      List earthMoon2 = CollectionsKt.mutableListOf(new Moon[]{new Moon("moon")});
      boolean add2 = earthMoon2.add(new Moon("moon 2"));
      String var5 = "earthMoon2: " + earthMoon2;
      System.out.println(var5);
      var5 = "add2: " + add2;
      System.out.println(var5);
   }
}
