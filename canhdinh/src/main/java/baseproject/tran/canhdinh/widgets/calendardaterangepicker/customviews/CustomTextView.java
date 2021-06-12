package baseproject.tran.canhdinh.widgets.calendardaterangepicker.customviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(final Context context) {
        super(context);
        initViews(context, null, 0);
    }

    public CustomTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs, 0);
    }

    public CustomTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        initViews(context, attrs, defStyle);
    }

    private void initViews(final Context context, final AttributeSet attrs, final int defStyle) {
        if (isInEditMode()) {
            return;
        }
        setFonts(attrs, defStyle);
    }

    private void setFonts(final AttributeSet attrs, final int defStyle) {
    }


}
