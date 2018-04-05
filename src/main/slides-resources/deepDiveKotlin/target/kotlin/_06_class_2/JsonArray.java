package _06_class_2;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u0019\u0010\b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006"},
   d2 = {"L_06_class_2/JsonArray;", "L_06_class_2/JsonValue;", "values", "", "(Ljava/util/List;)V", "getValues", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", ""}
)
public final class JsonArray extends JsonValue {
   @NotNull
   private final List values;

   @NotNull
   public final List getValues() {
      return this.values;
   }

   public JsonArray(@NotNull List values) {
      Intrinsics.checkParameterIsNotNull(values, "values");
      super((DefaultConstructorMarker)null);
      this.values = values;
   }

   @NotNull
   public final List component1() {
      return this.values;
   }

   @NotNull
   public final JsonArray copy(@NotNull List values) {
      Intrinsics.checkParameterIsNotNull(values, "values");
      return new JsonArray(values);
   }

   public String toString() {
      return "JsonArray(values=" + this.values + ")";
   }

   public int hashCode() {
      return this.values != null ? this.values.hashCode() : 0;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof JsonArray) {
            JsonArray var2 = (JsonArray)var1;
            if (Intrinsics.areEqual(this.values, var2.values)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
