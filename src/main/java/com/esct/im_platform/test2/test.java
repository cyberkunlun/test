package com.esct.im_platform.test2;

import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.PushNotificationResponse;
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.eatthepath.pushy.apns.util.TokenUtil;
import com.eatthepath.pushy.apns.util.concurrent.PushNotificationFuture;

import java.io.File;

public class test {
    public static void main(String[] args) {
        try {
            final ApnsClient apnsClient = new ApnsClientBuilder()
                    .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
                    .setClientCredentials(new File("D:\\code\\ims\\OneCloudPush_dev2.p12"), "123456")
                    .build();
            final ApnsPayloadBuilder payloadBuilder = new SimpleApnsPayloadBuilder();
            payloadBuilder.setAlertBody("Example!");

            final String payload = payloadBuilder.build();
            final String token = TokenUtil.sanitizeTokenString("9e9230c12238df839e3c4cd2a0261742c514f3ea1f4bcc57e8fda71f8fb4a044");

            final SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token, "com.example.myApp", payload);

            final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
                    sendNotificationFuture = apnsClient.sendNotification(pushNotification);


            final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
                    sendNotificationFuture.get();

            if (pushNotificationResponse.isAccepted()) {
                System.out.println("Push notification accepted by APNs gateway.");
            } else {
                System.out.println("Notification rejected by the APNs gateway: " +
                        pushNotificationResponse.getRejectionReason());

                pushNotificationResponse.getTokenInvalidationTimestamp().ifPresent(timestamp -> {
                    System.out.println("\t…and the token is invalid as of " + timestamp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
