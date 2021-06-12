package baseproject.tran.canhdinh.event;

import baseproject.tran.canhdinh.helper.BusHelper;

public class UserAcceptLocationPermissionEvent {

    public static void post() {
        BusHelper.post(new UserAcceptLocationPermissionEvent());
    }
}
