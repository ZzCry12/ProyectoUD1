package com.example.proyectoud1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.proyectoud1.databinding.FragmentFirstBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {
    // PROYECTO UNIDAD 1 CON LA NASA
    // https://api.nasa.gov/planetary/apod?api_key=mBZ5Hr3Glv3ZkdE3xApJVbUoRKJF8MaOkG7UAdPt
    private FragmentFirstBinding binding;
    private ArrayAdapter<Planetas> adapter;

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
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String type = preferences.getString("tipo", "");
        Toast.makeText(getContext(), "Tipo: " + type, Toast.LENGTH_LONG).show();

        ArrayList<Planetas> items = new ArrayList<>();

        adapter = new ArrayAdapter<Planetas>(
                getContext(),
                R.layout.lv_planetas_row,
                items
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View itemView = convertView;
                if (itemView == null) {
                    itemView = getLayoutInflater().inflate(R.layout.lv_planetas_row, parent, false);
                }

                Planetas planetitas = items.get(position);

                ImageView imageView = itemView.findViewById(R.id.imageViewPlanetas);
                TextView txtListName = itemView.findViewById(R.id.txtplanetas);

                txtListName.setText(planetitas.getTitle());

                Picasso.get().load(planetitas.getHdurl()).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // La imagen se ha cargado con éxito, ahora está en caché
                    }

                    @Override
                    public void onError(Exception e) {
                        // Maneja errores si la descarga falla
                    }
                });

                return itemView;
            }
        };

        binding.listviewprincipal.setAdapter(adapter);

        refresh();
        setHasOptionsMenu(true);
    }

    private void refresh() {
        Toast.makeText(getContext(), "Refrescando...", Toast.LENGTH_LONG).show();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            PlanetasAPI api = new PlanetasAPI();
            ArrayList<Planetas> planetas = api.getPlanetas();
            handler.post(() -> {
                adapter.clear();
                adapter.addAll(planetas);
            });

            binding.listviewprincipal.setOnItemClickListener((adapter, fragment, position, id) -> {
                Planetas planetitas = (Planetas) adapter.getItemAtPosition(position);
                Bundle Args = new Bundle();
                Args.putSerializable("planetas", planetitas);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, Args);
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}