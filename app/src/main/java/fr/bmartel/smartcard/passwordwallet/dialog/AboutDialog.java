package fr.bmartel.smartcard.passwordwallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import fr.bmartel.smartcard.passwordwallet.BuildConfig;
import fr.bmartel.smartcard.passwordwallet.R;

/**
 * About dialog
 *
 * @author Bertrand Martel
 */
public class AboutDialog extends AlertDialog {

    public AboutDialog(Context context) {
        super(context);

        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.about_dialog, null);
        setView(dialoglayout);

        TextView name = dialoglayout.findViewById(R.id.name);
        TextView copyright = dialoglayout.findViewById(R.id.copyright);
        TextView github_link = dialoglayout.findViewById(R.id.github_link);

        name.setText(context.getResources().getString(R.string.app_name) + " v" + BuildConfig.VERSION_NAME);
        copyright.setText(R.string.copyright);
        github_link.setText(R.string.github_link);

        setTitle(R.string.about);
        setPositiveButton(context.getResources().getString(R.string.dialog_ok), (OnClickListener) null);
    }
}
