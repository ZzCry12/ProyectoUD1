package com.example.proyectoud1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface PlanetasDAO {
    @Query("select * from Planetas")
    LiveData<List<Planetas>> getPlanetas();

    @Insert
    void añadirPlaneta(Planetas planetas);

    @Insert
    void añadirPlaneta(List<Planetas> planetas);

    @Delete
    void borrarPlaneta(Planetas planetas);

    @Query("DELETE FROM planetas")
    void deletePlanetas();
}
