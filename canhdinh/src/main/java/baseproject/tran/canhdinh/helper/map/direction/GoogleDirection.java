package baseproject.tran.canhdinh.helper.map.direction;

import baseproject.tran.canhdinh.helper.map.direction.request.DirectionOriginRequest;

public class GoogleDirection
{
    public static DirectionOriginRequest withServerKey(String apiKey)
    {
        return new DirectionOriginRequest(apiKey);
    }
}
