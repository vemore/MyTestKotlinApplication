package fr.bmartel.smartcard.passwordwallet.inter;

/**
 * Completion listener for updating the working mode.
 *
 * @author Bertrand Martel
 */
public interface ICompletionListener {

    /**
     * Called when migration from a mode to another has completed.
     */
    void onComplete();
}