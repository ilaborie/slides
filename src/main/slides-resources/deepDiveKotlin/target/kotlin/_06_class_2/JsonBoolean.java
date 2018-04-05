package _06_class_2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006"},
   d2 = {"L_06_class_2/JsonBoolean;", "L_06_class_2/JsonValue;", "value", "", "(Z)V", "getValue", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", ""}
)
public final class JsonBoolean extends JsonValue {
   private final boolean value;

   public final boolean getValue() {
      return this.value;
   }

   public JsonBoolean(boolean value) {
      super((DefaultConstructorMarker)null);
      this.value = value;
   }

   public final boolean component1() {
      return this.value;
   }

   @NotNull
   public final JsonBoolean copy(boolean value) {
      return new JsonBoolean(value);
   }

   public String toString() {
      return "JsonBoolean(value=" + this.value + ")";
   }

   public int hashCode() {
      byte var10000 = this.value;
      if (this.value) {
         var10000 = 1;
      }

      return var10000;
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof JsonBoolean) {
            JsonBoolean var2 = (JsonBoolean)var1;
            if (this.value == var2.value) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }
}
