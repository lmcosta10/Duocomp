package com.lapps.duocomp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class ScheduleAlarm {


    public static void setDailyAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        // Currently for testing: set up alarm for 10 sec from now
        // (takes a little longer, 1 to 2 minutes)
        // TODO: change to everyday at 19:00
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent );

        // TODO: delete logs later
        System.out.println("Scheduled alarm");
    }
}
