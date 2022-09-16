package com.bytesbee.firebase.chat.activities.fcm;

import com.bytesbee.firebase.chat.activities.fcmmodels.MyResponse;
import com.bytesbee.firebase.chat.activities.fcmmodels.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization: key= AAAAKw68R9k:APA91bGmJVoQl5yjmEePMHNyUuQMimgQMZyKV62vXnRlcpzA0lOrLKKUnVzuPrauwLaVX8lmyx9Qi0J-5r4TSmTcbzLes3hrR3hFhmPv0ozp-lWR0kwzpxrIFjlPXoQyGb0AbxWp6sbA"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
