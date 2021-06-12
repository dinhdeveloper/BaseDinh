package baseproject.tran.canhdinh.helper.map.direction;

import baseproject.tran.canhdinh.helper.map.direction.model.Direction;

public interface DirectionCallback {
    void onDirectionSuccess(Direction direction, String rawBody);
    void onDirectionFailure(Throwable t);
}
