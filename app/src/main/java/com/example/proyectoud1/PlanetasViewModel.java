package com.example.proyectoud1;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class PlanetasViewModel extends AndroidViewModel {
    private final Application app;
    private final PlanetasDB appDatabase;
    private final PlanetasDAO planetasDAO;
    private LiveData<List<Planetas>> planetas;

    public PlanetasViewModel(Application application) {
        super(application);

        this.app = application;
        this.appDatabase = PlanetasDB.getDatabase(
                this.getApplication());
        this.planetasDAO = appDatabase.getPlanetasDao();
    }

    public LiveData<List<Planetas>> getPlanetas() {
        return planetasDAO.getPlanetas();
    }

    public void reload(){
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                    app.getApplicationContext()
            );
            PlanetasAPI api = new PlanetasAPI();
            ArrayList<Planetas> result = null;
            String tipusConsulta = null;
            if (tipusConsulta != null){
                result = api.getPlanetas();
            }else{
                System.out.printf("No se ha encontrado anda en el JSON");
            }
            
            planetasDAO.deletePlanetas();
            planetasDAO.añadirPlaneta(result);
            
            return null;
        }
    }
}
