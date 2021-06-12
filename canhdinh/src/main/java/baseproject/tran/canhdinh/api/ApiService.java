/*
 * (c) Copyright GoTechCom 2016
 */

package baseproject.tran.canhdinh.api;

import retrofit2.Call;
import retrofit2.http.Body;

/**
 */
public interface ApiService
{
    Call<BaseApiResponse> call(@Body BaseApiParams params);
}
