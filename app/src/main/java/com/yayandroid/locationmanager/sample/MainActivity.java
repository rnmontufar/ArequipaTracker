package com.yayandroid.locationmanager.sample;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.yayandroid.locationmanager.sample.service.SampleService;
import com.yayandroid.locationmanager.sample.service.SampleServiceActivity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import static android.widget.Toast.LENGTH_SHORT;
import static com.android.volley.Request.*;
import static com.android.volley.Request.Method.*;
import com.yayandroid.locationmanager.sample.service.*;

public class MainActivity extends AppCompatActivity {

    private static  Switch ser_Track;
    boolean statusTrack = true;
    boolean statusAuto = true;
    boolean statusLoca = true;

    String msg;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    private RequestQueue rqt;
    private String url = "http://desastrererremoto.esy.es/proceso.php";
    private Context ctx;
    private StringRequest strq;

    private final int REQUEST_ACCES_FINE = 0;

    EditText imput;
    String datos_usuario = "";
    Date date = new Date();

    double lat = 0.0;
    double lng = 0.0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maina);
    }

    private void crear() {

        strq = new StringRequest(POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rta_servidor", response);
                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                    }
                },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();
                date = new Date();
                parametros.put("dato", datos_usuario);
                parametros.put("date", date + "");
                parametros.put("gps", lat + ", " + lng);
                parametros.put("id_campo", "1");
                parametros.put("operacion", "c");

                return parametros;
            }
        };

        rqt.add(strq);
    }

    private void actualizar() {

        StringRequest strq = new StringRequest(POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rta_servidor", response);
                        Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("dato", datos_usuario);
                parametros.put("date", date + "");
                parametros.put("gps", lat + ", " + lng);
                parametros.put("id_campo", "1");
                parametros.put("operacion", "u");

                return parametros;
            }
        };

        rqt.add(strq);

    }

    public void inTrackClick(View view) {
        if(statusTrack){
            msg = "Servicio de Tracking activado";
            statusTrack = false;
            crear();
        }else{
            msg = "Servicio de Tracking desactivado";
            statusTrack = true;
        }
        Snackbar mySnackbar = Snackbar.make(view, msg, LENGTH_SHORT);
        mySnackbar.show();
        startActivity(new Intent(this, SampleActivity.class));
    }
    public void inEnvioAutomaticoClick(View view) {
        if(statusAuto){
            msg = "Envio Automático activado";
            statusAuto = false;
            onLocalVoiceInteractionStarted();
            actualizar();
        }else{
            msg = "Envio Automático desactivado";
            statusAuto = true;
            onLocalVoiceInteractionStopped();
        }
        Snackbar mySnackbar = Snackbar.make(view, msg, LENGTH_SHORT);
        mySnackbar.show();
        startActivity(new Intent(this, SampleActivity.class));
    }
    public void inLocalizacionClick(View view) {
        if(statusLoca){
            msg = "Localización activada";
            statusLoca = false;
            actualizar();
        }else{
            msg = "Localización desactivado";
            statusLoca = true;
        }
        Snackbar mySnackbar = Snackbar.make(view, msg, LENGTH_SHORT);
        mySnackbar.show();
        startActivity(new Intent(this, SampleActivity.class));
    }
    public void inEnvInfoClick(View view) {
        startActivity(new Intent(this, SampleServiceActivity.class));
    }
}
