package com.yourname.whatsappscheduler.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ.\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\b\u00a8\u0006\u000f"}, d2 = {"Lcom/yourname/whatsappscheduler/utils/SchedulingHelper;", "", "()V", "cancelMessage", "", "context", "Landroid/content/Context;", "messageId", "", "scheduleMessage", "", "phoneNumber", "", "messageText", "scheduledTimeMillis", "app_debug"})
public final class SchedulingHelper {
    @org.jetbrains.annotations.NotNull
    public static final com.yourname.whatsappscheduler.utils.SchedulingHelper INSTANCE = null;
    
    private SchedulingHelper() {
        super();
    }
    
    public final boolean scheduleMessage(@org.jetbrains.annotations.NotNull
    android.content.Context context, long messageId, @org.jetbrains.annotations.NotNull
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull
    java.lang.String messageText, long scheduledTimeMillis) {
        return false;
    }
    
    public final void cancelMessage(@org.jetbrains.annotations.NotNull
    android.content.Context context, long messageId) {
    }
}