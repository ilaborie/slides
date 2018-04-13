package _11_collections_2;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
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
public final class TuplesKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      Pair pair = new Pair(1, "x");
      int var2 = ((Number)pair.component1()).intValue();
      String b = (String)pair.component2();
      Triple triple = new Triple(2, "x", CollectionsKt.listOf((Object)null));
      int var5 = ((Number)triple.component1()).intValue();
      String var6 = (String)triple.component2();
      List e = (List)triple.component3();
   }
}
