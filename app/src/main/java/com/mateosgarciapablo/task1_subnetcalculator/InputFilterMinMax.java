//Pablo Mateos García

package com.mateosgarciapablo.task1_subnetcalculator;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMax implements InputFilter{

    //Filter that checks that the maximum values per octect is 255 and the minimum is 0

    private int min, max;

    public InputFilterMinMax(int min, int max){
        this.min = min;
        this.max = max;
    }

    private boolean isInRange(int a, int b, int c){
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend){
        try{
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        }catch (NumberFormatException nfe) {}
        return "";
    }
}
