package com.srtapps.chemicalelements.helper;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.srtapps.chemicalelements.R;
import com.srtapps.chemicalelements.model.CreateShape;

import static com.srtapps.chemicalelements.helper.StaticStore.chemicalElements;

public class ElementViewAdapter extends BaseAdapter {
    private Context context;
    private int size, height;
    private boolean bool;

    public ElementViewAdapter(Context context, int size, boolean bool) {
        this.context = context;
        this.size = size;
        this.height = (Resources.getSystem().getDisplayMetrics().widthPixels / 18) - 1;
        this.bool = bool;
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            textView = new TextView(context);
            textView.setLayoutParams(new GridView.LayoutParams(height, height));
        }
        else {
            textView = (TextView) view;
        }
        if (bool) {
            if (!chemicalElements.getElements().get(i).getName().equals("")) {
                textView.setText(chemicalElements.getElements().get(i).getSymbol());
                textView.setGravity(Gravity.CENTER);
                textView.setBackground(CreateShape.shapeCreator(context.getResources().getColor(context.getResources().getIdentifier(chemicalElements
                                .getElements()
                                .get(i)
                                .getCpkHexColor(), "color", context.getPackageName())),
                        0,
                        0,
                        context.getResources().getColor(R.color.colorBlack)));
            }
            else {
                textView.setEnabled(false);
                textView.setVisibility(View.INVISIBLE);
            }
        }
        else {
            textView.setText(chemicalElements.getElements().get(i + 126).getSymbol());
            textView.setGravity(Gravity.CENTER);
            textView.setBackground(CreateShape.shapeCreator(context.getResources().getColor(context.getResources().getIdentifier(chemicalElements
                            .getElements()
                            .get(i + 126)
                            .getCpkHexColor(), "color", context.getPackageName())),
                    0,
                    0,
                    context.getResources().getColor(R.color.colorBlack)));


        }
        textView.getLayoutParams().height = height;
        textView.getLayoutParams().width = height;

        return textView;
    }
}
