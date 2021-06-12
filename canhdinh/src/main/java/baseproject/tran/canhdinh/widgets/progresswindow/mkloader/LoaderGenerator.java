package baseproject.tran.canhdinh.widgets.progresswindow.mkloader;

import baseproject.tran.canhdinh.widgets.progresswindow.mkloader.type.ClassicSpinner;
import baseproject.tran.canhdinh.widgets.progresswindow.mkloader.type.FishSpinner;
import baseproject.tran.canhdinh.widgets.progresswindow.mkloader.type.LineSpinner;
import baseproject.tran.canhdinh.widgets.progresswindow.mkloader.type.LoaderView;

public class LoaderGenerator {

    public static LoaderView generateLoaderView(int type) {
        switch (type) {
            case 0:
                return new ClassicSpinner();
            case 1:
                return new FishSpinner();
            case 2:
                return new LineSpinner();
            default:
                return new ClassicSpinner();
        }
    }

    public static LoaderView generateLoaderView(String type) {
        switch (type) {
            case "ClassicSpinner":
                return new ClassicSpinner();
            case "FishSpinner":
                return new FishSpinner();
            case "LineSpinner":
                return new LineSpinner();
            default:
                return new ClassicSpinner();
        }
    }
}
