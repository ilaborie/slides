package _13_advanced_function;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 1, 9},
   bv = {1, 0, 2},
   k = 1,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0002H\u0004\"\u0006\b\u0000\u0010\u0004\u0018\u0001H\u0086\b¢\u0006\u0002\u0010\u0005J\u001f\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007¢\u0006\u0002\u0010\b"},
   d2 = {"L_13_advanced_function/JavaBeanBuilder;", "", "()V", "createBean", "T", "()Ljava/lang/Object;", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;"}
)
public final class JavaBeanBuilder {
   public static final JavaBeanBuilder INSTANCE;

   public final Object createBean(@NotNull Class clazz) {
      Intrinsics.checkParameterIsNotNull(clazz, "clazz");
      return clazz.newInstance();
   }

   private final Object createBean() {
      Intrinsics.reifiedOperationMarker(4, "T");
      return this.createBean(Object.class);
   }

   private JavaBeanBuilder() {
      super();
   }

   static {
      JavaBeanBuilder var0 = new JavaBeanBuilder();
      INSTANCE = var0;
   }
}
