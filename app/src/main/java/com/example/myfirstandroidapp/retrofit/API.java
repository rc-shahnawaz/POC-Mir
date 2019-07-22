package com.example.myfirstandroidapp.retrofit;

import com.example.myfirstandroidapp.model.APIResponse;
import com.example.myfirstandroidapp.model.OrderHistory;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface API {
    @Headers({
            "Accept: application/json",
            "User-Agent: Your-App-Name",
            "Cache-Control: max-age=640000",
            "client_id: 9e5e0dae42324af0ba7a252a730d3919",
            "client_secret: 67f7fBbEDA064C33B1F699e70Ae6A6b2"
    })
    @GET("customer")
    Observable<List<APIResponse>> submit(@Query("customerId") String customerId, @Query("firstName") String firstName,
                                         @Query("zipCode") String zipCode, @Query("lastName") String lastName);

    @Headers({
            "Accept: application/json",
            "User-Agent: Your-App-Name",
            "Cache-Control: max-age=640000",
            "client_id: 9e5e0dae42324af0ba7a252a730d3919",
            "client_secret: 67f7fBbEDA064C33B1F699e70Ae6A6b2"
    })
    @GET("order")
    Observable<List<OrderHistory>> orders(@Query("customerId") String customerId);
}
