package _10_collections_1;

import astronomy.Moon;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005"},
   d2 = {"main", "", "args", "", "", "([Ljava/lang/String;)V"}
)
public final class Break_immutableKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      byte var2 = 1;
      Iterable $receiver$iv = (Iterable)(new IntRange(var2, 9));
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($receiver$iv, 10)));
      Iterator var5 = $receiver$iv.iterator();

      while(var5.hasNext()) {
         int item$iv$iv = ((IntIterator)var5).nextInt();
         Moon var12 = new Moon("Moon #" + item$iv$iv);
         destination$iv$iv.add(var12);
      }

      List moons = CollectionsKt.toList((Iterable)((List)destination$iv$iv));
      Class var14 = moons.getClass();
      System.out.println(var14);
      Method[] var10000 = moons.getClass().getMethods();
      Intrinsics.checkExpressionValueIsNotNull(var10000, "moons.javaClass.methods");
      Object[] var15 = (Object[])var10000;
      Object[] var3 = var15;
      int var17 = var15.length;
      int var18 = 0;

      Object var20;
      while(true) {
         if (var18 >= var17) {
            var20 = null;
            break;
         }

         Object var19 = var3[var18];
         Method it = (Method)var19;
         Intrinsics.checkExpressionValueIsNotNull(it, "it");
         if (Intrinsics.areEqual(it.getName(), "add") && it.getParameterCount() == 1) {
            var20 = var19;
            break;
         }

         ++var18;
      }

      Method var21 = (Method)var20;
      if ((Method)var20 != null) {
         var21.invoke(moons, new Moon("XXX"));
      }

      String var16 = CollectionsKt.joinToString$default((Iterable)moons, (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
      System.out.println(var16);
   }
}
