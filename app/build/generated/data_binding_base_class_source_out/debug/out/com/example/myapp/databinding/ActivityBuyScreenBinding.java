// Generated by view binder compiler. Do not edit!
package com.example.myapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityBuyScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonSubmit;

  @NonNull
  public final Spinner dropdownBuy;

  @NonNull
  public final TextView towerCost;

  @NonNull
  public final TextView towerHealth;

  private ActivityBuyScreenBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonSubmit,
      @NonNull Spinner dropdownBuy, @NonNull TextView towerCost, @NonNull TextView towerHealth) {
    this.rootView = rootView;
    this.buttonSubmit = buttonSubmit;
    this.dropdownBuy = dropdownBuy;
    this.towerCost = towerCost;
    this.towerHealth = towerHealth;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBuyScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBuyScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_buy_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBuyScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button_submit;
      Button buttonSubmit = ViewBindings.findChildViewById(rootView, id);
      if (buttonSubmit == null) {
        break missingId;
      }

      id = R.id.dropdownBuy;
      Spinner dropdownBuy = ViewBindings.findChildViewById(rootView, id);
      if (dropdownBuy == null) {
        break missingId;
      }

      id = R.id.towerCost;
      TextView towerCost = ViewBindings.findChildViewById(rootView, id);
      if (towerCost == null) {
        break missingId;
      }

      id = R.id.towerHealth;
      TextView towerHealth = ViewBindings.findChildViewById(rootView, id);
      if (towerHealth == null) {
        break missingId;
      }

      return new ActivityBuyScreenBinding((ConstraintLayout) rootView, buttonSubmit, dropdownBuy,
          towerCost, towerHealth);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
