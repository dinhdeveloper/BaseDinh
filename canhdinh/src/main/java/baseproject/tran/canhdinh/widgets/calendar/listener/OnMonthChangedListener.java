package baseproject.tran.canhdinh.widgets.calendar.listener;


import baseproject.tran.canhdinh.widgets.calendar.entity.Date;

public interface OnMonthChangedListener {

    /**
     * Call back when current showing month changed.
     *
     * @param date showing date
     */
    void onMonthChanged(Date date);

}
