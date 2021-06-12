package baseproject.tran.canhdinh.event;


import baseproject.tran.canhdinh.helper.BusHelper;

public class GetUserAddressSuccessEvent {

    public static void post() {
        BusHelper.post(new GetUserAddressSuccessEvent());
    }
}
