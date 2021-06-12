
package baseproject.tran.canhdinh.widgets.calendar.adapter;


import androidx.annotation.LayoutRes;

import baseproject.tran.canhdinh.R;

public class SampleVagueAdapter extends VagueAdapter {

    public SampleVagueAdapter() {
        super(R.layout.def_date_layout);
    }

    /**
     * Initialization adapter.
     *
     * @param dayLayout layout for date
     */
    public SampleVagueAdapter(@LayoutRes int dayLayout) {
        super(dayLayout);
    }

}
