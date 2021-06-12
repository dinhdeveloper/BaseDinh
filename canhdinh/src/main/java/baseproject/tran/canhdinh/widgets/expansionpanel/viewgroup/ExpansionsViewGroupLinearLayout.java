package baseproject.tran.canhdinh.widgets.expansionpanel.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import baseproject.tran.canhdinh.R;

public class ExpansionsViewGroupLinearLayout extends LinearLayout {

    private final ExpansionViewGroupManager expansionViewGroupManager = new ExpansionViewGroupManager(this);

    public ExpansionsViewGroupLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public ExpansionsViewGroupLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpansionsViewGroupLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpansionsViewGroupLinearLayout);
            if (a != null) {
                expansionViewGroupManager.setOpenOnlyOne(a.getBoolean(R.styleable.ExpansionsViewGroupLinearLayout_expansion_openOnlyOne, false));
                a.recycle();
            }
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        expansionViewGroupManager.onViewAdded();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        expansionViewGroupManager.onViewAdded();
    }
}
