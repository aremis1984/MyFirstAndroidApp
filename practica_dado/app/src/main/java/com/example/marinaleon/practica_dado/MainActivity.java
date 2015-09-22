package com.example.marinaleon.practica_dado;

import android.app.Activity;
import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.Random;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void comenzar(View view) {
        Button start = (Button) findViewById(R.id.button2);
        Button lanzar = (Button) findViewById(R.id.button);
        Button recoger = (Button) findViewById(R.id.button3);
        recoger.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
        lanzar.setVisibility(View.VISIBLE);


    }

    public void ocultar_ultimo(View view){
        if(findViewById(R.id.imageView1).getVisibility() == View.VISIBLE){
            findViewById(R.id.imageView1).setVisibility(View.INVISIBLE);

        }else if(findViewById(R.id.imageView2).getVisibility() == View.VISIBLE){
            findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);

        }else if(findViewById(R.id.imageView3).getVisibility() == View.VISIBLE){
            findViewById(R.id.imageView3).setVisibility(View.INVISIBLE);

        }else if(findViewById(R.id.imageView4).getVisibility() == View.VISIBLE){
            findViewById(R.id.imageView4).setVisibility(View.INVISIBLE);

        }else if(findViewById(R.id.imageView5).getVisibility() == View.VISIBLE){
            findViewById(R.id.imageView5).setVisibility(View.INVISIBLE);

        }else if(findViewById(R.id.imageView6).getVisibility() == View.VISIBLE){
            findViewById(R.id.imageView6).setVisibility(View.INVISIBLE);
        }
    }

    public void lanzar_dado(final View view){

        ocultar_ultimo(view);

        Random r = new Random();
        int i = r.nextInt(7 - 1) + 1;

        TextView turno = (TextView) findViewById(R.id.control);
        String jugador = (String) turno.getText();

        if(i == 1){
            ImageView dado = (ImageView) findViewById(R.id.imageView1);
            dado.setVisibility(View.VISIBLE);

            //pierde turno y cambia sepuede crear una funcion aparte

            if (jugador == getString(R.string.uno)){

                findViewById(R.id.textView5).setVisibility(view.INVISIBLE);
                findViewById(R.id.textView8).setVisibility(view.VISIBLE);
                TextView flag = (TextView) findViewById(R.id.control);
                flag.setText(R.string.dos);

                reinicia(view);

            }else if(jugador == getString(R.string.dos)){

                findViewById(R.id.textView5).setVisibility(view.VISIBLE);
                findViewById(R.id.textView8).setVisibility(view.INVISIBLE);
                TextView flag = (TextView) findViewById(R.id.control);
                flag.setText(R.string.uno);

                reinicia(view);
            }

        }else if(i == 2){
            ImageView dado = (ImageView) findViewById(R.id.imageView2);
            dado.setVisibility(View.VISIBLE);

        }else if(i == 3){
            ImageView dado = (ImageView) findViewById(R.id.imageView3);
            dado.setVisibility(View.VISIBLE);

        }else if(i == 4){
            ImageView dado = (ImageView) findViewById(R.id.imageView4);
            dado.setVisibility(View.VISIBLE);

        }else if(i == 5){
            ImageView dado = (ImageView) findViewById(R.id.imageView5);
            dado.setVisibility(View.VISIBLE);

        }else if(i == 6){
            ImageView dado = (ImageView) findViewById(R.id.imageView6);
            dado.setVisibility(View.VISIBLE);

        }

        if(i != 1) {
            TextView puntos = (TextView) findViewById(R.id.acumulado);
            String suma = puntos.getText().toString();
            int val = Integer.parseInt(suma);
            val = val + i;
            String resultado = String.valueOf(val);
            puntos.setText(resultado);

            TextView puntosTotal = (TextView) findViewById(R.id.textView3);
            String sumaTotal = puntosTotal.getText().toString();
            int valTotal = Integer.parseInt(sumaTotal);

            int total = val + valTotal;
            if(total >= 100) {
                recoger(view);
            }
        }

    }
    public void reinicia(View view){

        TextView acumulado = (TextView) findViewById(R.id.acumulado);
        acumulado.setText(R.string.cero);

        Button start = (Button) findViewById(R.id.button2);
        Button lanzar = (Button) findViewById(R.id.button);
        Button recoger = (Button) findViewById(R.id.button3);
        recoger.setVisibility(View.INVISIBLE);
        start.setVisibility(View.VISIBLE);
        lanzar.setVisibility(View.INVISIBLE);
    }

    public void recoger(final View view){
        ProgressBar progressBar, progressBar2;
        TextView turno = (TextView) findViewById(R.id.control);
        String jugador = (String) turno.getText();
        int maxProgreso = 100;

        if (jugador == getString(R.string.uno)){
            //sumo puntos y cambio al dos

            //las funciones de sumar y de cambiar turnos pueden separarse en otras funciones, se repiten mucho

            TextView puntosTotal = (TextView) findViewById(R.id.textView3);
            String sumaTotal = puntosTotal.getText().toString();
            int valTotal = Integer.parseInt(sumaTotal);

            TextView puntos = (TextView) findViewById(R.id.acumulado);
            String suma = puntos.getText().toString();
            int val = Integer.parseInt(suma);
            val = val + valTotal;

            String resultado = String.valueOf(val);
            puntosTotal.setText(resultado);

            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setProgress((int)(val*100/maxProgreso) );

            findViewById(R.id.textView5).setVisibility(view.INVISIBLE);
            findViewById(R.id.textView8).setVisibility(view.VISIBLE);
            TextView flag = (TextView) findViewById(R.id.control);
            flag.setText(R.string.dos);

            comprueba_fin(val, view, jugador);


        }else if(jugador == getString(R.string.dos)){
            //sumo puntos y cambio al uno

            TextView puntosTotal = (TextView) findViewById(R.id.textView4);
            String sumaTotal = puntosTotal.getText().toString();
            int valTotal = Integer.parseInt(sumaTotal);

            TextView puntos = (TextView) findViewById(R.id.acumulado);
            String suma = puntos.getText().toString();
            int val = Integer.parseInt(suma);
            val = val + valTotal;

            String resultado = String.valueOf(val);
            puntosTotal.setText(resultado);

            progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
            progressBar2.setProgress((int)(val*100/maxProgreso) );

            findViewById(R.id.textView5).setVisibility(view.VISIBLE);
            findViewById(R.id.textView8).setVisibility(view.INVISIBLE);
            TextView flag = (TextView) findViewById(R.id.control);
            flag.setText(R.string.uno);

            comprueba_fin(val, view, jugador);

        }
    }

    public void comprueba_fin(int val, final View view, final String jugador){
        String cadena;

        if(val >= 100){

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            if(jugador == getString(R.string.uno)){
                builder.setMessage(R.string.gana_uno)
                        .setTitle(R.string.fin)
                        .setCancelable(false);
            }else{
                builder.setMessage(R.string.gana_dos)
                        .setTitle(R.string.fin)
                        .setCancelable(false);
            }

            builder.setPositiveButton(R.string.volver, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    TextView acumulado = (TextView) findViewById(R.id.acumulado);
                    acumulado.setText(R.string.cero);

                    ProgressBar progressBar, progressBar2;
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setProgress((int)(0));
                    progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
                    progressBar2.setProgress((int)(0));

                    if(jugador == getString(R.string.dos)){
                        findViewById(R.id.textView5).setVisibility(view.VISIBLE);
                        findViewById(R.id.textView8).setVisibility(view.INVISIBLE);
                    }

                    TextView flag = (TextView) findViewById(R.id.control);
                    flag.setText(R.string.uno);

                    TextView puntosTotal1 = (TextView) findViewById(R.id.textView3);
                    puntosTotal1.setText(R.string.cero);
                    TextView puntosTotal2 = (TextView) findViewById(R.id.textView4);
                    puntosTotal2.setText(R.string.cero);

                    ocultar_ultimo(view);
                    comenzar(view);
                }
            });
            builder.setNegativeButton(R.string.salir, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            reinicia(view);
        }

    }

}





