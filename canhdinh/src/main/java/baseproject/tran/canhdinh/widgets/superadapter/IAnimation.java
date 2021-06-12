package baseproject.tran.canhdinh.widgets.superadapter;


import androidx.recyclerview.widget.RecyclerView;

import baseproject.tran.canhdinh.widgets.superadapter.animation.BaseAnimation;

interface IAnimation {

    void enableLoadAnimation();

    void enableLoadAnimation(long duration, BaseAnimation animation);

    void cancelLoadAnimation();

    void setOnlyOnce(boolean onlyOnce);

    void addLoadAnimation(RecyclerView.ViewHolder holder);

}
