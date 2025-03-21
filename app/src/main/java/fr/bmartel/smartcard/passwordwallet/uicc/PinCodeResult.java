package fr.bmartel.smartcard.passwordwallet.uicc;

/**
 * Pin code response data model.
 *
 * @author Bertrand Martel
 */
public class PinCodeResult {

    /**
     * pin code state.
     */
    private boolean valid;

    /**
     * number of retry.
     */
    private int retry;

    public PinCodeResult(boolean valid, int retry) {
        this.valid = valid;
        this.retry = retry;
    }

    public boolean isValid() {
        return valid;
    }

    public int getRetry() {
        return retry;
    }
}