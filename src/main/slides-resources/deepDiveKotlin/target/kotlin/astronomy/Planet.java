package astronomy;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\nHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013"},
   d2 = {"Lastronomy/Planet;", "Lastronomy/AstronomicalBody;", "name", "", "kind", "Lastronomy/PlanetKind;", "moons", "", "Lastronomy/Moon;", "ring", "", "(Ljava/lang/String;Lastronomy/PlanetKind;Ljava/util/Collection;Z)V", "getKind", "()Lastronomy/PlanetKind;", "getMoons", "()Ljava/util/Collection;", "getName", "()Ljava/lang/String;", "getRing", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString"}
)
public final class Planet implements AstronomicalBody {
   @NotNull
   private final String name;
   @NotNull
   private final PlanetKind kind;
   @NotNull
   private final Collection moons;
   private final boolean ring;

   @NotNull
   public String getName() {
      return this.name;
   }

   @NotNull
   public final PlanetKind getKind() {
      return this.kind;
   }

   @NotNull
   public final Collection getMoons() {
      return this.moons;
   }

   public final boolean getRing() {
      return this.ring;
   }

   public Planet(@NotNull String name, @NotNull PlanetKind kind, @NotNull Collection moons, boolean ring) {
      Intrinsics.checkParameterIsNotNull(name, "name");
      Intrinsics.checkParameterIsNotNull(kind, "kind");
      Intrinsics.checkParameterIsNotNull(moons, "moons");
      super();
      this.name = name;
      this.kind = kind;
      this.moons = moons;
      this.ring = ring;
   }

   @NotNull
   public final String component1() {
      return this.getName();
   }

   @NotNull
   public final PlanetKind component2() {
      return this.kind;
   }

   @NotNull
   public final Collection component3() {
      return this.moons;
   }

   public final boolean component4() {
      return this.ring;
   }

   @NotNull
   public final Planet copy(@NotNull String name, @NotNull PlanetKind kind, @NotNull Collection moons, boolean ring) {
      Intrinsics.checkParameterIsNotNull(name, "name");
      Intrinsics.checkParameterIsNotNull(kind, "kind");
      Intrinsics.checkParameterIsNotNull(moons, "moons");
      return new Planet(name, kind, moons, ring);
   }

   public String toString() {
      return "Planet(name=" + this.getName() + ", kind=" + this.kind + ", moons=" + this.moons + ", ring=" + this.ring + ")";
   }

   public int hashCode() {
      String var10000 = this.getName();
      int var1 = (((var10000 != null ? var10000.hashCode() : 0) * 31 + (this.kind != null ? this.kind.hashCode() : 0)) * 31 + (this.moons != null ? this.moons.hashCode() : 0)) * 31;
      byte var10001 = this.ring;
      if (this.ring) {
         var10001 = 1;
      }

      return var1 + var10001;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof Planet) {
            Planet var2 = (Planet)var1;
            if (Intrinsics.areEqual(this.getName(), var2.getName()) && Intrinsics.areEqual(this.kind, var2.kind) && Intrinsics.areEqual(this.moons, var2.moons) && this.ring == var2.ring) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
