package fr.bmartel.smartcard.passwordwallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import fr.bmartel.smartcard.passwordwallet.R;

/**
 * Adapter for open source projects
 *
 * @author Bertrand Martel
 */
public class OpenSourceItemsAdapter extends BaseAdapter {

    private static final String[][] COMPONENTS = new String[][]{
            {"Lollipin", "https://github.com/omadahealth/LolliPin"},
            {"javacard tutorial", "https://github.com/bertrandmartel/javacard-tutorial"},
            {"(dev) pcsc emulator", "https://github.com/bertrandmartel/pcsc-android-emulator"},
            {"(dev) JavaCard Gradle plugin", "https://github.com/bertrandmartel/javacard-gradle-plugin"},
            {"(dev) seek for Android", " https://github.com/seek-for-android/pool"}
    };

    private LayoutInflater mInflater;

    public OpenSourceItemsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return COMPONENTS.length;
    }

    @Override
    public Object getItem(int position) {
        return COMPONENTS[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.open_source_items, parent, false);
        }

        TextView title = convertView.findViewById(R.id.title);
        TextView url = convertView.findViewById(R.id.url);

        title.setText(COMPONENTS[position][0]);
        url.setText(COMPONENTS[position][1]);

        return convertView;
    }
}
