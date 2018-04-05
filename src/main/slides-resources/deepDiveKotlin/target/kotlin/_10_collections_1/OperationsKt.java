package _10_collections_1;

import astronomy.Moon;
import astronomy.Planet;
import astronomy.SolarSystem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\u001a\u0019\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005"},
   d2 = {"main", "", "args", "", "", "([Ljava/lang/String;)V"}
)
public final class OperationsKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      Iterable $receiver$iv = (Iterable)SolarSystem.INSTANCE.getBodies();
      Collection destination$iv$iv = (Collection)(new ArrayList());
      Iterator var5 = $receiver$iv.iterator();

      Object element$iv$iv;
      while(var5.hasNext()) {
         element$iv$iv = var5.next();
         if (element$iv$iv instanceof Planet) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      $receiver$iv = (Iterable)((List)destination$iv$iv);
      destination$iv$iv = (Collection)(new ArrayList());
      var5 = $receiver$iv.iterator();

      while(var5.hasNext()) {
         element$iv$iv = var5.next();
         Planet planet = (Planet)element$iv$iv;
         Iterable list$iv$iv = (Iterable)planet.getMoons();
         CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
      }

      $receiver$iv = (Iterable)((List)destination$iv$iv);
      destination$iv$iv = (Collection)(new ArrayList());
      var5 = $receiver$iv.iterator();

      while(var5.hasNext()) {
         element$iv$iv = var5.next();
         Moon it = (Moon)element$iv$iv;
         if (!StringsKt.startsWith$default(it.getName(), "S/", false, 2, (Object)null)) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      $receiver$iv = (Iterable)((List)destination$iv$iv);
      Comparator var12 = (Comparator)(new OperationsKt$main$$inlined$sortedBy$1());
      String s = CollectionsKt.joinToString$default((Iterable)CollectionsKt.sortedWith($receiver$iv, var12), (CharSequence)",\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null.INSTANCE, 30, (Object)null);
      System.out.println(s);
   }
}
