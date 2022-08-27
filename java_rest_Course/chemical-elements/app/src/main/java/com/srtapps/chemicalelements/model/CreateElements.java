package com.srtapps.chemicalelements.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.srtapps.chemicalelements.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.srtapps.chemicalelements.helper.StaticStore.alreadyExecuted;
import static com.srtapps.chemicalelements.helper.StaticStore.basicElements;
import static com.srtapps.chemicalelements.helper.StaticStore.chemicalElements;
import static com.srtapps.chemicalelements.helper.StaticStore.listOfLists;
import static com.srtapps.chemicalelements.helper.StaticStore.sophisticatedElements;

public class CreateElements {

    public static void xCreateElements(Context context) {
        if(!alreadyExecuted) {
            alreadyExecuted = true;

            try {
                BufferedReader jsonReader = new BufferedReader(
                        new InputStreamReader(context.getResources()
                                .openRawResource(R.raw.elements)));
                StringBuilder jsonBuilder = new StringBuilder();
                for (String line = null; (line = jsonReader.readLine()) != null; ) {
                    jsonBuilder.append(line).append("\n");
                }
                Gson gson = new Gson(); //json’u parse etmek için Gson kütüphanesini kullanıyoruz
                chemicalElements = gson.fromJson(jsonBuilder.toString(),ChemicalElements.class);
            }
            catch (FileNotFoundException e) {
                Log.e("jsonFile", "file not found");
            }
            catch (IOException e) {
                Log.e("jsonFile", "ioerror");
            }

            for (int i = 0; i < chemicalElements.getElements().size(); i++) {
                if (!chemicalElements.getElements().get(i).getName().equals("")) {
                    List<Object> objects = new ArrayList<>();
                    objects.add(chemicalElements.getElements().get(i).getAtomicMass());
                    objects.add(chemicalElements.getElements().get(i).getAtomicNumber());
                    objects.add(chemicalElements.getElements().get(i).getCpkHexColor());
                    objects.add(chemicalElements.getElements().get(i).getName());
                    objects.add(chemicalElements.getElements().get(i).getSymbol());
                    listOfLists.add(objects);

                    if (i < 83) {
                        basicElements.add(objects);
                    } else sophisticatedElements.add(objects);
                }
            }
        }
    }
}
