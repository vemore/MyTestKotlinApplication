package fr.bmartel.smartcard.passwordwallet.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.List;

import fr.bmartel.smartcard.passwordwallet.adapter.PasswordAdapter;
import fr.bmartel.smartcard.passwordwallet.model.Password;

/**
 * Common fragment.
 *
 * @author Bertrand Martel
 */
public abstract class ListFragmentAbstr extends MainFragmentAbstr {

    protected RecyclerView mPasswordListView;

    protected PasswordAdapter mPasswordAdapter;

    protected List<Password> mPasswordList;

    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected FrameLayout mEmptyFrame;

    protected RelativeLayout mDisplayFrame;

    protected void setTitle(String title) {
        getRootActivity().setToolbarTitle(title);
    }
}
