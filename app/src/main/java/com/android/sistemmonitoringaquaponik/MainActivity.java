package com.android.sistemmonitoringaquaponik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.android.sistemmonitoringaquaponik.databinding.ActivityMainBinding;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String auto = snapshot.child("auto").getValue().toString();
                String pompaAir = snapshot.child("pompa_air").getValue().toString();
                String pompaTanaman = snapshot.child("pompa_tanaman").getValue().toString();
                String ph = snapshot.child("ph").getValue().toString();
                String tds = snapshot.child("tds").getValue().toString();
                String ntu = snapshot.child("ntu").getValue().toString();
                String cahaya = snapshot.child("cahaya").getValue().toString();

                if (auto.equalsIgnoreCase("0")) {
                    binding.labelSwitchModelOtomatis.setOn(false);
                    binding.labelSwitchPompaAir.setEnabled(true);
                    binding.labelSwitchPompaTanaman.setEnabled(true);
                    binding.textViewModelOtomatis.setTextColor(Color.parseColor("#77838F"));
                    binding.materialCardViewModelOtomatis.setCardBackgroundColor(Color.parseColor("#F4F6FF"));
                } else {
                    binding.labelSwitchModelOtomatis.setOn(true);
                    binding.labelSwitchPompaAir.setEnabled(false);
                    binding.labelSwitchPompaTanaman.setEnabled(false);
                    binding.textViewModelOtomatis.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.materialCardViewModelOtomatis.setCardBackgroundColor(Color.parseColor("#405FF2"));
                }

                if (pompaAir.equalsIgnoreCase("0")) {
                    binding.labelSwitchPompaAir.setOn(false);
                    binding.imageViewPompaAir.setImageResource(R.drawable.ic_pompa_air_unselected);
                    binding.textViewStatusPompaAir.setText("Off");
                    binding.textViewStatusPompaAir.setTextColor(Color.parseColor("#77838F"));
                    binding.textViewPompaAir.setTextColor(Color.parseColor("#77838F"));
                    binding.materialCardViewPompaAir.setCardBackgroundColor(Color.parseColor("#F4F6FF"));
                } else {
                    binding.labelSwitchPompaAir.setOn(true);
                    binding.imageViewPompaAir.setImageResource(R.drawable.ic_pompa_air_selected);
                    binding.textViewStatusPompaAir.setText("On");
                    binding.textViewStatusPompaAir.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.textViewPompaAir.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.materialCardViewPompaAir.setCardBackgroundColor(Color.parseColor("#405FF2"));
                }

                if (pompaTanaman.equalsIgnoreCase("0")) {
                    binding.labelSwitchPompaTanaman.setOn(false);
                    binding.imageViewPompaTanaman.setImageResource(R.drawable.ic_pompa_tanaman_unselected);
                    binding.textViewStatusPompaTanaman.setText("Off");
                    binding.textViewStatusPompaTanaman.setTextColor(Color.parseColor("#77838F"));
                    binding.textViewPompaTanaman.setTextColor(Color.parseColor("#77838F"));
                    binding.materialCardViewModelPompaTanaman.setCardBackgroundColor(Color.parseColor("#F4F6FF"));
                } else {
                    binding.labelSwitchPompaTanaman.setOn(true);
                    binding.imageViewPompaTanaman.setImageResource(R.drawable.ic_pompa_tanaman_selected);
                    binding.textViewStatusPompaTanaman.setText("On");
                    binding.textViewStatusPompaTanaman.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.textViewPompaTanaman.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.materialCardViewModelPompaTanaman.setCardBackgroundColor(Color.parseColor("#405FF2"));
                }

                binding.textViewPh.setText(ph);
                binding.textViewTDS.setText(tds + " ppm");
                binding.textViewNTU.setText(ntu);
                binding.textViewCahaya.setText(cahaya + "%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.labelSwitchModelOtomatis.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                binding.labelSwitchPompaAir.setEnabled(!isOn);
                binding.labelSwitchPompaTanaman.setEnabled(!isOn);
                if (isOn) {
                    binding.textViewModelOtomatis.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.materialCardViewModelOtomatis.setCardBackgroundColor(Color.parseColor("#405FF2"));

                    databaseReference.child("auto").setValue(1);
                } else {
                    binding.textViewModelOtomatis.setTextColor(Color.parseColor("#77838F"));
                    binding.materialCardViewModelOtomatis.setCardBackgroundColor(Color.parseColor("#F4F6FF"));

                    databaseReference.child("auto").setValue(0);
                }
            }
        });

        binding.labelSwitchPompaAir.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                if (isOn) {
                    binding.imageViewPompaAir.setImageResource(R.drawable.ic_pompa_air_selected);
                    binding.textViewStatusPompaAir.setText("On");
                    binding.textViewStatusPompaAir.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.textViewPompaAir.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.materialCardViewPompaAir.setCardBackgroundColor(Color.parseColor("#405FF2"));

                    databaseReference.child("pompa_air").setValue(1);
                } else {
                    binding.imageViewPompaAir.setImageResource(R.drawable.ic_pompa_air_unselected);
                    binding.textViewStatusPompaAir.setText("Off");
                    binding.textViewStatusPompaAir.setTextColor(Color.parseColor("#77838F"));
                    binding.textViewPompaAir.setTextColor(Color.parseColor("#77838F"));
                    binding.materialCardViewPompaAir.setCardBackgroundColor(Color.parseColor("#F4F6FF"));

                    databaseReference.child("pompa_air").setValue(0);
                }
            }
        });

        binding.labelSwitchPompaTanaman.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(ToggleableView toggleableView, boolean isOn) {
                if (isOn) {
                    binding.imageViewPompaTanaman.setImageResource(R.drawable.ic_pompa_tanaman_selected);
                    binding.textViewStatusPompaTanaman.setText("On");
                    binding.textViewStatusPompaTanaman.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.textViewPompaTanaman.setTextColor(Color.parseColor("#FFFFFF"));
                    binding.materialCardViewModelPompaTanaman.setCardBackgroundColor(Color.parseColor("#405FF2"));

                    databaseReference.child("pompa_tanaman").setValue(1);
                } else {
                    binding.imageViewPompaTanaman.setImageResource(R.drawable.ic_pompa_tanaman_unselected);
                    binding.textViewStatusPompaTanaman.setText("Off");
                    binding.textViewStatusPompaTanaman.setTextColor(Color.parseColor("#77838F"));
                    binding.textViewPompaTanaman.setTextColor(Color.parseColor("#77838F"));
                    binding.materialCardViewModelPompaTanaman.setCardBackgroundColor(Color.parseColor("#F4F6FF"));

                    databaseReference.child("pompa_tanaman").setValue(0);
                }
            }
        });
    }
}