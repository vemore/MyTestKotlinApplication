package fr.bmartel.smartcard.passwordwallet.inter;

import android.app.Dialog;

/**
 * interface keeping track of opened dialog.
 *
 * @author Bertrand Martel
 */
public interface IDialog {

    /**
     * set opened dialog in activity
     *
     * @param dialog
     */
    void setCurrentDialog(Dialog dialog);
}
