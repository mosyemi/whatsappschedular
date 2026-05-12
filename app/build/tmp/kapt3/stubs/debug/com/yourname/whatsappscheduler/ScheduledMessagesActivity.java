package com.yourname.whatsappscheduler;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0014R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity$MessageAdapter;", "database", "Lcom/yourname/whatsappscheduler/data/AppDatabase;", "loadMessagesJob", "Lkotlinx/coroutines/Job;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "loadScheduledMessages", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "MessageAdapter", "app_debug"})
public final class ScheduledMessagesActivity extends androidx.appcompat.app.AppCompatActivity {
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private com.yourname.whatsappscheduler.ScheduledMessagesActivity.MessageAdapter adapter;
    private com.yourname.whatsappscheduler.data.AppDatabase database;
    @org.jetbrains.annotations.Nullable
    private kotlinx.coroutines.Job loadMessagesJob;
    
    public ScheduledMessagesActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    private final void loadScheduledMessages() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0014B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J \u0010\u000e\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016J\u0014\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity$MessageAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity$MessageAdapter$MessageViewHolder;", "Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity;", "messages", "", "Lcom/yourname/whatsappscheduler/data/Message;", "(Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity;Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateMessages", "newMessages", "MessageViewHolder", "app_debug"})
    public final class MessageAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.yourname.whatsappscheduler.ScheduledMessagesActivity.MessageAdapter.MessageViewHolder> {
        @org.jetbrains.annotations.NotNull
        private java.util.List<com.yourname.whatsappscheduler.data.Message> messages;
        
        public MessageAdapter(@org.jetbrains.annotations.NotNull
        java.util.List<com.yourname.whatsappscheduler.data.Message> messages) {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public com.yourname.whatsappscheduler.ScheduledMessagesActivity.MessageAdapter.MessageViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull
        com.yourname.whatsappscheduler.ScheduledMessagesActivity.MessageAdapter.MessageViewHolder holder, int position) {
        }
        
        @java.lang.Override
        public int getItemCount() {
            return 0;
        }
        
        public final void updateMessages(@org.jetbrains.annotations.NotNull
        java.util.List<com.yourname.whatsappscheduler.data.Message> newMessages) {
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity$MessageAdapter$MessageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/yourname/whatsappscheduler/ScheduledMessagesActivity$MessageAdapter;Landroid/view/View;)V", "btnDelete", "Landroid/widget/Button;", "btnResend", "tvMessage", "Landroid/widget/TextView;", "tvPhoneNumber", "tvStatus", "tvTime", "bind", "", "message", "Lcom/yourname/whatsappscheduler/data/Message;", "app_debug"})
        public final class MessageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull
            private final android.widget.TextView tvPhoneNumber = null;
            @org.jetbrains.annotations.NotNull
            private final android.widget.TextView tvMessage = null;
            @org.jetbrains.annotations.NotNull
            private final android.widget.TextView tvTime = null;
            @org.jetbrains.annotations.NotNull
            private final android.widget.TextView tvStatus = null;
            @org.jetbrains.annotations.NotNull
            private final android.widget.Button btnResend = null;
            @org.jetbrains.annotations.NotNull
            private final android.widget.Button btnDelete = null;
            
            public MessageViewHolder(@org.jetbrains.annotations.NotNull
            android.view.View itemView) {
                super(null);
            }
            
            public final void bind(@org.jetbrains.annotations.NotNull
            com.yourname.whatsappscheduler.data.Message message) {
            }
        }
    }
}