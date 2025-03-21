package fr.bmartel.smartcard.passwordwallet.inter;

public interface IServiceConnection {
    void onServiceConnected(ComponentName name, IBinder service);
}