package fr.bmartel.smartcard.passwordwallet.inter;

import androidx.appcompat.widget.Toolbar;
import android.widget.ProgressBar;

import java.util.List;

import fr.bmartel.smartcard.passwordwallet.model.Password;

/**
 * Interface used for the fragment to communicate with the main activity.
 *
 * @author Bertrand Martel
 */
public interface IBaseActivity extends IDialog {

    /**
     * Get password list.
     *
     * @return
     */
    List<Password> getPasswordList();

    /**
     * Get toolbar object.
     *
     * @return
     */
    Toolbar getToolbar();

    /**
     * Set toolbar title.
     *
     * @param title
     */
    void setToolbarTitle(String title);

    /**
     * hide button in toolbar.
     */
    void hideMenuButton();

    /**
     * Set the deletion listener to be called when user click on delete button.
     *
     * @param listener
     */
    void setDeletionListener(IDeletionListener listener);

    /**
     * called when a new password should be created.
     *
     * @param title    password title
     * @param username username
     * @param password password value
     * @return data payload
     */
    byte[] saveNewPassword(String title, String username, String password);

    /**
     * called when a password should be updated.
     *
     * @param formerTitle former password title
     * @param newTitle    new password title
     * @param username    new username value
     * @param password    new password value
     * @return data payload
     */
    byte[] saveExistingPassword(String formerTitle, String newTitle, String username, String password);

    /**
     * Delete password entry.
     *
     * @param title password title
     */
    void deletePassword(String title);

    /**
     * Check if password is duplicate.
     *
     * @param title password tittle
     * @return true if password is duplicated
     */
    boolean checkDuplicatePassword(String title);

    /**
     * Decrypt password.
     *
     * @param password encrypted password
     * @return clear text password
     */
    String decrypt(byte[] password);

    /**
     * Set working mode.
     *
     * @param mode     working mode
     * @param progress progress bar
     * @param listener deletion listener
     */
    void setMode(byte mode, ProgressBar progress, ICompletionListener listener);

    /**
     * called when correct pin code have been set and password fragment should be opened.
     */
    void onReady();

    /**
     * Get password entry.
     *
     * @param index index in password list
     * @return password entry
     */
    Password getPassword(int index);
}