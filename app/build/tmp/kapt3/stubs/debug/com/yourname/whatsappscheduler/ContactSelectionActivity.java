package com.yourname.whatsappscheduler;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0003\u0014\u0015\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u0012\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/yourname/whatsappscheduler/ContactSelectionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactAdapter;", "btnSelectAll", "Landroid/widget/Button;", "rvContacts", "Landroidx/recyclerview/widget/RecyclerView;", "tvSelectedCount", "Landroid/widget/TextView;", "finishWithSelectedContacts", "", "loadContacts", "", "Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "updateSelectedCount", "Companion", "ContactAdapter", "ContactItem", "app_debug"})
public final class ContactSelectionActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.TextView tvSelectedCount;
    private androidx.recyclerview.widget.RecyclerView rvContacts;
    private android.widget.Button btnSelectAll;
    private com.yourname.whatsappscheduler.ContactSelectionActivity.ContactAdapter adapter;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String EXTRA_CONTACT_NAMES = "contact_names";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String EXTRA_CONTACT_NUMBERS = "contact_numbers";
    @org.jetbrains.annotations.NotNull
    public static final com.yourname.whatsappscheduler.ContactSelectionActivity.Companion Companion = null;
    
    public ContactSelectionActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.util.List<com.yourname.whatsappscheduler.ContactSelectionActivity.ContactItem> loadContacts() {
        return null;
    }
    
    private final void finishWithSelectedContacts() {
    }
    
    private final void updateSelectedCount() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/yourname/whatsappscheduler/ContactSelectionActivity$Companion;", "", "()V", "EXTRA_CONTACT_NAMES", "", "EXTRA_CONTACT_NUMBERS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001cB!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u0006\u0010\u0013\u001a\u00020\u0011J\u001c\u0010\u0014\u001a\u00020\b2\n\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0011H\u0016J\u001c\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0011H\u0016J\u0006\u0010\u001b\u001a\u00020\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactAdapter$ContactViewHolder;", "contacts", "", "Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactItem;", "onSelectionChanged", "Lkotlin/Function0;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "visibleContacts", "areAllVisibleContactsSelected", "", "filter", "query", "", "getItemCount", "", "getSelectedContacts", "getVisibleContactCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "toggleSelectAllVisible", "ContactViewHolder", "app_debug"})
    public static final class ContactAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.yourname.whatsappscheduler.ContactSelectionActivity.ContactAdapter.ContactViewHolder> {
        @org.jetbrains.annotations.NotNull
        private final java.util.List<com.yourname.whatsappscheduler.ContactSelectionActivity.ContactItem> contacts = null;
        @org.jetbrains.annotations.NotNull
        private final kotlin.jvm.functions.Function0<kotlin.Unit> onSelectionChanged = null;
        @org.jetbrains.annotations.NotNull
        private java.util.List<com.yourname.whatsappscheduler.ContactSelectionActivity.ContactItem> visibleContacts;
        
        public ContactAdapter(@org.jetbrains.annotations.NotNull
        java.util.List<com.yourname.whatsappscheduler.ContactSelectionActivity.ContactItem> contacts, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function0<kotlin.Unit> onSelectionChanged) {
            super();
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public com.yourname.whatsappscheduler.ContactSelectionActivity.ContactAdapter.ContactViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull
        com.yourname.whatsappscheduler.ContactSelectionActivity.ContactAdapter.ContactViewHolder holder, int position) {
        }
        
        @java.lang.Override
        public int getItemCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<com.yourname.whatsappscheduler.ContactSelectionActivity.ContactItem> getSelectedContacts() {
            return null;
        }
        
        public final int getVisibleContactCount() {
            return 0;
        }
        
        public final boolean areAllVisibleContactsSelected() {
            return false;
        }
        
        public final void toggleSelectAllVisible() {
        }
        
        public final void filter(@org.jetbrains.annotations.NotNull
        java.lang.String query) {
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactAdapter$ContactViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactAdapter;Landroid/view/View;)V", "cbSelected", "Landroid/widget/CheckBox;", "tvContactName", "Landroid/widget/TextView;", "tvContactPhone", "bind", "", "contact", "Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactItem;", "app_debug"})
        public final class ContactViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull
            private final android.widget.CheckBox cbSelected = null;
            @org.jetbrains.annotations.NotNull
            private final android.widget.TextView tvContactName = null;
            @org.jetbrains.annotations.NotNull
            private final android.widget.TextView tvContactPhone = null;
            
            public ContactViewHolder(@org.jetbrains.annotations.NotNull
            android.view.View itemView) {
                super(null);
            }
            
            public final void bind(@org.jetbrains.annotations.NotNull
            com.yourname.whatsappscheduler.ContactSelectionActivity.ContactItem contact) {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lcom/yourname/whatsappscheduler/ContactSelectionActivity$ContactItem;", "", "name", "", "phoneNumber", "isSelected", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "()Z", "setSelected", "(Z)V", "getName", "()Ljava/lang/String;", "getPhoneNumber", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
    public static final class ContactItem {
        @org.jetbrains.annotations.NotNull
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String phoneNumber = null;
        private boolean isSelected;
        
        public ContactItem(@org.jetbrains.annotations.NotNull
        java.lang.String name, @org.jetbrains.annotations.NotNull
        java.lang.String phoneNumber, boolean isSelected) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getPhoneNumber() {
            return null;
        }
        
        public final boolean isSelected() {
            return false;
        }
        
        public final void setSelected(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.yourname.whatsappscheduler.ContactSelectionActivity.ContactItem copy(@org.jetbrains.annotations.NotNull
        java.lang.String name, @org.jetbrains.annotations.NotNull
        java.lang.String phoneNumber, boolean isSelected) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
}