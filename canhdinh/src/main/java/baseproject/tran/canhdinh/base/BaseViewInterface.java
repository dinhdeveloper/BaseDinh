package baseproject.tran.canhdinh.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

public interface BaseViewInterface<C extends BaseUiContainer> {
    View inflate(LayoutInflater inflater, ViewGroup container);

    C getUiContainer();

    int getViewId();

    String getString(@StringRes int resId);

    String getString(@StringRes int resId, Object... formatArgs);

    int getColor(@ColorRes int resId);

    LayoutInflater getInflater();

    View findViewById(@IdRes int id);

    void setVisible(View view);

    void setInvisible(View view);

    void setGone(View view);

    Context getContext();

    View getView();
}