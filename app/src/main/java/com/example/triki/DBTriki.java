package com.example.triki;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTriki extends SQLiteOpenHelper {
    String sqlCreate = "CREATE TABLE triki(codigo INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, hora TEXT, ganador TEXT )";
    public DBTriki(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        // genera varios registros de la base de datosC REATE TABLE triki(codigo INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, hora TEXT, ganador TEXT)
        db.execSQL("INSERT INTO triki (fecha, hora, ganador) VALUES ('2023-10-27', '10:00', 'Julian')");
        db.execSQL("INSERT INTO triki (fecha, hora, ganador) VALUES ('2023-10-27', '11:30', 'Julian')");
        db.execSQL("INSERT INTO triki (fecha, hora, ganador) VALUES ('2023-10-28', '09:15', 'Trigos')");
        db.execSQL("INSERT INTO triki (fecha, hora, ganador) VALUES ('2023-10-28', '14:45', 'Empate')");
    }

    //private void registrarResultado(String ganador) {
    //        String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    //        String horaActual = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    //        String sql = "INSERT INTO historial (ganador, fecha, hora) VALUES ('" + ganador + "', '" + fechaActual + "', '" + horaActual + "')";
    //        db.execSQL(sql);
    //    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
