package baseproject.tran.canhdinh.base;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import baseproject.tran.canhdinh.helper.MyLog;


public class BaseUiContainer {
    protected void bind(View baseView) {
        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {
            UiElement annotation = field.getAnnotation(UiElement.class);

            if (annotation != null) {
                try {
                    View view = baseView.findViewById(annotation.value());
                    field.set(this, view);
                } catch (Exception e) {
                    MyLog.e(e);
                }
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface UiElement {
        @IdRes int value();
    }
}
