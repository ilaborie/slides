package _06_class_2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b6\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0006\u0003\u0004\u0005\u0006\u0007\b"},
   d2 = {"L_06_class_2/JsonValue;", "", "()V", "L_06_class_2/JsonObject;", "L_06_class_2/JsonArray;", "L_06_class_2/JsonString;", "L_06_class_2/JsonNumber;", "L_06_class_2/JsonBoolean;", "L_06_class_2/JsonNull;"}
)
public abstract class JsonValue {
   private JsonValue() {
      super();
   }

   // $FF: synthetic method
   public JsonValue(DefaultConstructorMarker $constructor_marker) {
      this();
   }
}
