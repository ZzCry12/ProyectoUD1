package com.example.proyectoud1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*CLASE DE LA BASE DE DATOS*/
@Database(entities = {Planetas.class}, version = 1)
public abstract class PlanetasDB extends RoomDatabase {
    private static PlanetasDB INSTANCE;

    public static PlanetasDB getDatabase(Context context){
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PlanetasDB.class, "db").build();
        }
        return INSTANCE;
        }
    public abstract PlanetasDAO getPlanetasDao();
}
