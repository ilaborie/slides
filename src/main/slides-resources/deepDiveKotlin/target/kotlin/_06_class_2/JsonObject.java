package _06_class_2;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u001f\u0010\t\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007"},
   d2 = {"L_06_class_2/JsonObject;", "L_06_class_2/JsonValue;", "attributes", "", "", "(Ljava/util/Map;)V", "getAttributes", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString"}
)
public final class JsonObject extends JsonValue {
   @NotNull
   private final Map attributes;

   @NotNull
   public final Map getAttributes() {
      return this.attributes;
   }

   public JsonObject(@NotNull Map attributes) {
      Intrinsics.checkParameterIsNotNull(attributes, "attributes");
      super((DefaultConstructorMarker)null);
      this.attributes = attributes;
   }

   @NotNull
   public final Map component1() {
      return this.attributes;
   }

   @NotNull
   public final JsonObject copy(@NotNull Map attributes) {
      Intrinsics.checkParameterIsNotNull(attributes, "attributes");
      return new JsonObject(attributes);
   }

   public String toString() {
      return "JsonObject(attributes=" + this.attributes + ")";
   }

   public int hashCode() {
      return this.attributes != null ? this.attributes.hashCode() : 0;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof JsonObject) {
            JsonObject var2 = (JsonObject)var1;
            if (Intrinsics.areEqual(this.attributes, var2.attributes)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
