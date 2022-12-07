package com.example.android_ket_cau_soft.callback;

import android.text.Editable;
import android.text.TextWatcher;

public interface TextChangeListener extends TextWatcher {

      default void beforeTextChanged(CharSequence s, int start,
                                     int count, int after){

      }

      void onTextChanged(CharSequence s, int start, int before, int count);

        default void afterTextChanged(Editable s){

        }


}
