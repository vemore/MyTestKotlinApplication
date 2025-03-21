package fr.bmartel.smartcard.passwordwallet.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import fr.bmartel.smartcard.passwordwallet.R;
import fr.bmartel.smartcard.passwordwallet.application.PasswordApplication;
import fr.bmartel.smartcard.passwordwallet.db.PasswordReaderDbHelper;
import fr.bmartel.smartcard.passwordwallet.inter.IFragmentOptions;
import fr.bmartel.smartcard.passwordwallet.model.Password;
import fr.bmartel.smartcard.passwordwallet.utils.HexUtils;

/**
 * Password item Fragment.
 *
 * @author Bertrand Martel
 */
public class PasswordItemFragment extends MainFragmentAbstr implements IFragmentOptions {

    private EditText mPasswordTitleEt;

    private EditText mPasswordUsernameEt;

    private EditText mDecryptedPasswordEt;

    private TextView mEncryptedPasswordTv;

    private CheckBox mTextObsufactionToggle;

    private int mPasswordIndex = -1;

    public PasswordItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.password_item_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        mPasswordIndex = (args != null) ? args.getInt("index", -1) : -1;

        mPasswordTitleEt = view.findViewById(R.id.password_title);
        mPasswordTitleEt.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        mPasswordUsernameEt = view.findViewById(R.id.password_username);
        mPasswordUsernameEt.setInputType(android.text.InputType.TYPE_CLASS_TEXT);

        mDecryptedPasswordEt = view.findViewById(R.id.password_decrypted);
        mEncryptedPasswordTv = view.findViewById(R.id.password_encrypted);

        mTextObsufactionToggle = view.findViewById(R.id.text_obfuscation_toggle);

        PasswordApplication app = (PasswordApplication) getActivity().getApplication();

        if (mPasswordIndex == -1) {
            mEncryptedPasswordTv.setVisibility(View.GONE);
            mTextObsufactionToggle.setText(R.string.text_obfuscation_toggle_caption);
        } else {
            mEncryptedPasswordTv.setVisibility(View.VISIBLE);
            mTextObsufactionToggle.setText(R.string.text_obfuscation_toggle_caption_decrypt);
        }

        mTextObsufactionToggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean hidePassword = mTextObsufactionToggle.isChecked();
                if (!hidePassword) {
                    mDecryptedPasswordEt.setTransformationMethod(new android.text.method.PasswordTransformationMethod());
                } else {
                    mDecryptedPasswordEt.setTransformationMethod(null);
                }
            }
        });

        mTextObsufactionToggle.setChecked(false);

        if (mTextObsufactionToggle.isChecked()) {
            mDecryptedPasswordEt.setTransformationMethod(new android.text.method.PasswordTransformationMethod());
        }

        if (mPasswordIndex != -1) {
            getRootActivity().setToolbarTitle(getString(R.string.title_edit_password));
            Password password = getRootActivity().getPassword(mPasswordIndex);

            if (password == null) {
                return;
            }
            mPasswordTitleEt.setText(password.getTitle());
            mPasswordUsernameEt.setText(password.getUsername());

            if (app.mode == PasswordApplication.MODE_APP_STORAGE) {
                mEncryptedPasswordTv.setText(HexUtils.byteArrayToHexString(password.getPassword()));

                String pass = getRootActivity().decrypt(password.getPassword());
                if (pass != null) {
                    mDecryptedPasswordEt.setText(pass);
                } else {
                    Toast.makeText(getActivity(), "password decryption failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                mDecryptedPasswordEt.setText(new String(password.getPassword()));
            }
        } else {
            getRootActivity().setToolbarTitle(getString(R.string.title_create_password));
        }
        onUpdateToolbar();
    }

    @Override
    public void onUpdateToolbar() {
        getRootActivity().hideMenuButton();
        Toolbar toolbar = getRootActivity().getToolbar();
        MenuItem saveButton = toolbar.getMenu().findItem(R.id.button_save);
        MenuItem deleteButton = toolbar.getMenu().findItem(R.id.button_delete);
        saveButton.setVisible(true);
        deleteButton.setVisible(false);

        saveButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                final String title = mPasswordTitleEt.getText().toString().trim();
                final String username = mPasswordUsernameEt.getText().toString().trim();
                final String password = mDecryptedPasswordEt.getText().toString().trim();

                if (title.isEmpty()) {
                    Toast.makeText(getActivity(), "title can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (username.isEmpty()) {
                    Toast.makeText(getActivity(), "username can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (password.isEmpty()) {
                    Toast.makeText(getActivity(), "password value can't be empty", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (title.length() > PasswordReaderDbHelper.TITLE_MAX_SIZE) {
                    Toast.makeText(getActivity(), "title max length is " + PasswordReaderDbHelper.TITLE_MAX_SIZE + " characters", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (username.length() > PasswordReaderDbHelper.USERNAME_MAX_SIZE) {
                    Toast.makeText(getActivity(), "username max length is " + PasswordReaderDbHelper.USERNAME_MAX_SIZE + " characters", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (password.length() > PasswordReaderDbHelper.PASSWORD_MAX_SIZE) {
                    Toast.makeText(getActivity(), "password max length is " + PasswordReaderDbHelper.PASSWORD_MAX_SIZE + " characters", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (mPasswordIndex == -1 && getRootActivity().checkDuplicatePassword(title)) {
                    Toast.makeText(getActivity(), "password title " + title + " already exist", Toast.LENGTH_SHORT).show();
                } else if (mPasswordIndex == -1) {

                    byte[] res = getRootActivity().saveNewPassword(title, username, password);
                    if (res != null) {
                        Toast.makeText(getActivity(), "password " + title + " has been saved", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getActivity(), "operation failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String formerTitle = getRootActivity().getPasswordList().get(mPasswordIndex).getTitle();
                    getRootActivity().getPasswordList().get(mPasswordIndex).setTitle(title);
                    getRootActivity().getPasswordList().get(mPasswordIndex).setUsername(username);
                    byte[] res = getRootActivity().saveExistingPassword(formerTitle, title, username, password);
                    if (res != null) {
                        getRootActivity().getPasswordList().get(mPasswordIndex).setPassword(res);
                        Toast.makeText(getActivity(), "password " + title + " has been saved", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    } else {
                        Toast.makeText(getActivity(), "operation failed", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
    }
}