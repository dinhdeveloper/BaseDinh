package baseproject.tran.canhdinh.event;

import baseproject.tran.canhdinh.helper.BusHelper;

public class GetCurrentPositionSuccess {
    public static void post() {
        BusHelper.post(new GetCurrentPositionSuccess());
    }
}
