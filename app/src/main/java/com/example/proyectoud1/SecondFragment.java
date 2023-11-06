package com.example.proyectoud1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.proyectoud1.databinding.FragmentSecondBinding;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            Planetas planetitas = (Planetas) args.getSerializable("planetas");
            if (planetitas != null) {
                binding.txtplanetas.setText(planetitas.getExplanation());
             //   binding.explicacionplanetas.setText(planetitas.getExplanation().toUpperCase(Locale.ROOT));

                String imageUrl = planetitas.getHdurl();
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    Picasso.get().load(planetitas.getHdurl()).into(binding.imageViewPlanetas);
                } else {
                    System.out.println("NO SE HA ENCONTRADO NINGUNA IMAGEN");
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}