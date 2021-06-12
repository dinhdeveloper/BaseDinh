package baseproject.tran.canhdinh.widgets.progresswindow;


import androidx.annotation.ColorRes;

public class ProgressWindowConfiguration {

    public @ColorRes
    int backgroundColor, progressColor, titleColor;

    public String title;

    public TYPE type;

    public ProgressWindowConfiguration() {

    }

    public enum TYPE {
        ClassicSpinner,
        FishSpinner,
        LineSpinner,
        None
    }

}
