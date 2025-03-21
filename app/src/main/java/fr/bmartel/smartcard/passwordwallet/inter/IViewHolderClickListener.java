package fr.bmartel.smartcard.passwordwallet.inter;

import android.view.View;

/**
 * click listener for recyclerview item.
 *
 * @author Bertrand Martel
 */
public interface IViewHolderClickListener {

    /**
     * triggered when user click on packet in recycler view
     *
     * @param view
     */
    void onClick(View view);
}
