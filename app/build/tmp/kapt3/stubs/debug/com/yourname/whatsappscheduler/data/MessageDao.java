package com.yourname.whatsappscheduler.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\r\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/yourname/whatsappscheduler/data/MessageDao;", "", "delete", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMessages", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/yourname/whatsappscheduler/data/Message;", "getPendingMessages", "getPendingMessagesAfter", "now", "insert", "message", "(Lcom/yourname/whatsappscheduler/data/Message;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStatus", "status", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface MessageDao {
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.yourname.whatsappscheduler.data.Message message, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM messages WHERE status = \'pending\' ORDER BY scheduledTime ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.yourname.whatsappscheduler.data.Message>> getPendingMessages();
    
    @androidx.room.Query(value = "SELECT * FROM messages ORDER BY scheduledTime DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.yourname.whatsappscheduler.data.Message>> getAllMessages();
    
    @androidx.room.Query(value = "SELECT * FROM messages WHERE status = \'pending\' AND scheduledTime > :now ORDER BY scheduledTime ASC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPendingMessagesAfter(long now, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.yourname.whatsappscheduler.data.Message>> $completion);
    
    @androidx.room.Query(value = "UPDATE messages SET status = :status WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateStatus(long id, @org.jetbrains.annotations.NotNull
    java.lang.String status, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM messages WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object delete(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}