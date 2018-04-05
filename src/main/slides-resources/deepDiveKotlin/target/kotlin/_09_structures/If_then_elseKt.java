package _09_structures;

import astronomy.AstronomicalBody;
import astronomy.Planet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 2,
   d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003"},
   d2 = {"handleAstronomicalBody", "", "body", "Lastronomy/AstronomicalBody;"}
)
public final class If_then_elseKt {
   public static final void handleAstronomicalBody(@NotNull AstronomicalBody body) {
      Intrinsics.checkParameterIsNotNull(body, "body");
      String message = body instanceof Planet && Intrinsics.areEqual(body.getName(), "Earth") ? "Welcome Earth" : "Welcome martian";
      System.out.println(message);
   }
}
