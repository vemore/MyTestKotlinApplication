package fr.bmartel.smartcard.passwordwallet.model;

/
 * Password model.
 *
 * @author Bertrand Martel
 */
public class Password {

    private String mTitle;

    private String mUsername;

    private byte[] mPassword;

    public Password(String title, String username, byte[] password) {
        mTitle = title;
        mUsername = username;
        mPassword = password;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUsername() {
        return mUsername;
    }

    public byte[] getPassword() {
        return mPassword;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setPassword(byte[] password) {
        mPassword = password;
    }
}