package fr.bmartel.smartcard.passwordwallet.fragment;

import fr.bmartel.smartcard.passwordwallet.inter.IBaseActivity;

import androidx.fragment.app.Fragment;

/**
 * Common fragment abstract class for All fragments.
 *
 * @author Bertrand Martel
 */
public class MainFragmentAbstr extends Fragment {

    protected IBaseActivity getRootActivity() {
        return (IBaseActivity) getActivity();
    }
}