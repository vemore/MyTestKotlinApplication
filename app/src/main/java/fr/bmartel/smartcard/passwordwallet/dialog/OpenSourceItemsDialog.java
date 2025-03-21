package fr.bmartel.smartcard.passwordwallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.ListView;

import fr.bmartel.smartcard.passwordwallet.R;
import fr.bmartel.smartcard.passwordwallet.adapter.OpenSourceItemsAdapter;

/**
 * open source components dialog.
 *
 * @author Bertrand Martel
 */
public class OpenSourceItemsDialog extends AlertDialog {

    public OpenSourceItemsDialog(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        ListView listview = (ListView) inflater.inflate(R.layout.open_source_list, null);
        listview.setAdapter(new OpenSourceItemsAdapter(context));

        setView(listview);
        setTitle(R.string.open_source_items);
        setPositiveButton(context.getResources().getString(R.string.dialog_ok), (OnClickListener) null);
    }
}
