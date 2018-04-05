package _06_class_2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002"},
   d2 = {"L_06_class_2/JsonNull;", "L_06_class_2/JsonValue;", "()V"}
)
public final class JsonNull extends JsonValue {
   public static final JsonNull INSTANCE;

   private JsonNull() {
      super((DefaultConstructorMarker)null);
   }

   static {
      JsonNull var0 = new JsonNull();
      INSTANCE = var0;
   }
}
