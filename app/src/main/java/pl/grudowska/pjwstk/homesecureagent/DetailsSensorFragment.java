package pl.grudowska.pjwstk.homesecureagent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by s.grudowska on 20.07.2015
 */
public class DetailsSensorFragment extends Fragment {
    /**
     * Create a new instance of DetailsSensorFragment, initialized to
     * show the text at 'index'.
     */
    public static DetailsSensorFragment newInstance(int index) {

        DetailsSensorFragment detailFragment = new DetailsSensorFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        /*text.setText(Shakespeare.DIALOGUE[getShownIndex()]);*/
        text.setText("test test test");
        return scroller;
    }
}
