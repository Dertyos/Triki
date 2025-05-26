package com.example.triki;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends Activity {
    int turno = 0;

    Button jbtn1,jbtn2,jbtn3,jbtn4,jbtn5,jbtn6,jbtn7,jbtn8,jbtn9;
    TextView resultado;
    EditText jnombre1, jnombre2;
    String nom1,nom2;
    boolean gano = false;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombres);

        jnombre1 = (EditText)findViewById(R.id.nombre1);
        jnombre2 = (EditText)findViewById(R.id.nombre2);

        DBTriki tkdb = new DBTriki(this, "Historial", null, 1);
        db = tkdb.getWritableDatabase();
    }

    public void funHistorial(){
        TextView lst = (TextView)findViewById(R.id.history_text_view);
        StringBuilder historialCompleto = new StringBuilder();
        String sql = "SELECT ganador, fecha, hora FROM triki ORDER BY fecha DESC, hora DESC";
        Cursor c = db.rawQuery(sql, null);
        try {
            if (c.moveToFirst()) {
                do {
                    String ganador = c.getString(0);
                    String fecha = c.getString(1);
                    String hora = c.getString(2);
                    historialCompleto.append("Ganador: ").append(ganador).append(" | ").append(fecha).append(" ").append(hora).append("\n");
                } while (c.moveToNext());
            }
            lst.setText(historialCompleto.toString());
            c.close();
        } catch (Exception e) {
            Log.e("SQLite", "Error al mostrar historial: " + e.getMessage());
        }
    }
    public void Iniciar(View v)
    {
        nom1 = jnombre1.getText().toString();
        nom2 =jnombre2.getText().toString();
        setContentView(R.layout.activity_main);

        jbtn1 = (Button) findViewById(R.id.button1);
        jbtn2 = (Button) findViewById(R.id.button2);
        jbtn3 = (Button) findViewById(R.id.button3);
        jbtn4 = (Button) findViewById(R.id.button4);
        jbtn5 = (Button) findViewById(R.id.button5);
        jbtn6 = (Button) findViewById(R.id.button6);
        jbtn7 = (Button) findViewById(R.id.button7);
        jbtn8 = (Button) findViewById(R.id.button8);
        jbtn9 = (Button) findViewById(R.id.button9);
        resultado = (TextView) findViewById(R.id.resultado);

        resultado.setText("Juega " + nom1);
    }

    public void Jugar(View v)
    {
        if (turno % 2 ==0){
            Imprimir('X',nom1 ,v);
            if(!gano && turno < 9)
                resultado.setText("juega " + nom2);
        }
        else {
            Imprimir('O',nom2,v);
            if(!gano && turno < 9)
                resultado.setText("juega " + nom1);
        }

    }

    public void Imprimir(char letra,String nombre, View v)
    {
        Context context = getApplicationContext();
        turno++;
        Toast.makeText(context, "juega " + nombre+ "Turno " + turno, Toast.LENGTH_SHORT).show();
        resultado.setText("juega " + nombre + "Turno " + turno);
        if(v.getId() == R.id.button1){
            jbtn1.setText(""+letra);
            jbtn1.setEnabled(false);
        }
        else if(v.getId() == R.id.button2){
            jbtn2.setText(""+letra);
            jbtn2.setEnabled(false);
        }
        else if(v.getId() == R.id.button3){
            jbtn3.setText(""+letra);
            jbtn3.setEnabled(false);
        }
        else if(v.getId() == R.id.button4){
            jbtn4.setText(""+letra);
            jbtn4.setEnabled(false);
        }
        else if(v.getId() == R.id.button5){
            jbtn5.setText(""+letra);
            jbtn5.setEnabled(false);
        }
        else if(v.getId() == R.id.button6){
            jbtn6.setText(""+letra);
            jbtn6.setEnabled(false);
        }
        else if(v.getId() == R.id.button7){
            jbtn7.setText(""+letra);
            jbtn7.setEnabled(false);
        }
        else if(v.getId() == R.id.button8){
            jbtn8.setText(""+letra);
            jbtn8.setEnabled(false);
        }
        else if(v.getId() == R.id.button9){
            jbtn9.setText(""+letra);
            jbtn9.setEnabled(false);
        }

        Verifica(letra, nombre);

    }

    public void Verifica (char letra, String nombre)
    {

        if(jbtn1.getText().toString().equals(letra+"")&&jbtn2.getText().toString().equals(letra+"")&&jbtn3.getText().toString().equals(letra+"")){
            gano = true;
        }
        if(jbtn4.getText().toString().equals(letra+"")&&jbtn5.getText().toString().equals(letra+"")&&jbtn6.getText().toString().equals(letra+"")){
            gano = true;
        }
        if(jbtn7.getText().toString().equals(letra+"")&&jbtn8.getText().toString().equals(letra+"")&&jbtn9.getText().toString().equals(letra+"")){
            gano = true;
        }
        if(jbtn1.getText().toString().equals(letra+"")&&jbtn4.getText().toString().equals(letra+"")&&jbtn7.getText().toString().equals(letra+"")){
            gano = true;
        }
        if(jbtn2.getText().toString().equals(letra+"")&&jbtn5.getText().toString().equals(letra+"")&&jbtn8.getText().toString().equals(letra+"")){
            gano = true;
        }
        if(jbtn3.getText().toString().equals(letra+"")&&jbtn6.getText().toString().equals(letra+"")&&jbtn9.getText().toString().equals(letra+"")){
            gano = true;
        }
        if(jbtn1.getText().toString().equals(letra+"")&&jbtn5.getText().toString().equals(letra+"")&&jbtn9.getText().toString().equals(letra+"")){
            gano = true;
        }
        if(jbtn3.getText().toString().equals(letra+"")&&jbtn5.getText().toString().equals(letra+"")&&jbtn7.getText().toString().equals(letra+"")){
            gano = true;
        }
        String ganadorActual = "";
        if(gano == true){
            Inactivar();
            resultado.setText("Ganador " + nombre );
            ganadorActual = nombre;
            registrarResultado(ganadorActual);
            funHistorial();
        }
        else if(turno == 9){
            resultado.setText("Juego Empatado");
            ganadorActual = "Empate";
            registrarResultado(ganadorActual);
        }

    }
    private void registrarResultado(String ganador) {
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String horaActual = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        String sql = "INSERT INTO triki (ganador, fecha, hora) VALUES ('" + ganador + "', '" + fechaActual + "', '" + horaActual + "')";
        db.execSQL(sql);
    }

    public void Inactivar()
    {
        jbtn1.setEnabled(false);
        jbtn2.setEnabled(false);
        jbtn3.setEnabled(false);
        jbtn4.setEnabled(false);
        jbtn5.setEnabled(false);
        jbtn6.setEnabled(false);
        jbtn7.setEnabled(false);
        jbtn8.setEnabled(false);
        jbtn9.setEnabled(false);
    }

    public void Reset (View v){
        jbtn1.setEnabled(true);
        jbtn2.setEnabled(true);
        jbtn3.setEnabled(true);
        jbtn4.setEnabled(true);
        jbtn5.setEnabled(true);
        jbtn6.setEnabled(true);
        jbtn7.setEnabled(true);
        jbtn8.setEnabled(true);
        jbtn9.setEnabled(true);
        jbtn1.setText("");
        jbtn2.setText("");
        jbtn3.setText("");
        jbtn4.setText("");
        jbtn5.setText("");
        jbtn6.setText("");
        jbtn7.setText("");
        jbtn8.setText("");
        jbtn9.setText("");
        turno = 0;
        resultado.setText("juega " + nom1);
        gano = false;

    }

    public void volverNombres(View v) {
        setContentView(R.layout.activity_nombres);
        jnombre1 = (EditText)findViewById(R.id.nombre1);
        jnombre2 = (EditText)findViewById(R.id.nombre2);
    }

}
