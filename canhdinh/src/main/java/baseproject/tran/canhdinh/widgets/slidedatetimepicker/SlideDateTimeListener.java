package baseproject.tran.canhdinh.widgets.slidedatetimepicker;

import java.util.Date;

public abstract class SlideDateTimeListener {
    public abstract void onDateTimeSet(Date date);

    public void onDateTimeCancel() {}
}
