package com.lostomates.mascotas;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ContactoActivity extends AppCompatActivity {
    private EditText nombre, correo, mensaje;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_contacto);
        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        bar.setTitle(R.string.contacto);
        setSupportActionBar(bar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nombre = (EditText) findViewById(R.id.nombre);
        correo = (EditText) findViewById(R.id.correo);
        mensaje = (EditText) findViewById(R.id.mensaje);
        findViewById(R.id.enviar).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { pedirSmtp(); }
        });
    }

    private String valor(EditText edit) { return edit.getText().toString().trim(); }

    private void pedirSmtp() {
        if (TextUtils.isEmpty(valor(nombre))) { nombre.setError("Escribe el nombre"); return; }
        if (!Patterns.EMAIL_ADDRESS.matcher(valor(correo)).matches()) { correo.setError("Correo inválido"); return; }
        if (TextUtils.isEmpty(valor(mensaje))) { mensaje.setError("Escribe un mensaje"); return; }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            new AlertDialog.Builder(this).setTitle("Correo en Android 4.0.3")
                .setMessage("JavaMail para Android requiere API 19. En API 15 puedes usar una aplicación de correo instalada para enviar el comentario.")
                .setNegativeButton("CANCELAR", null)
                .setPositiveButton("ABRIR CORREO", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) { abrirClienteCorreo(); }
                }).show();
            return;
        }
        final View form = LayoutInflater.from(this).inflate(R.layout.dialog_smtp, null);
        new AlertDialog.Builder(this)
            .setTitle("Configuración JavaMail")
            .setMessage("Necesitas un servidor SMTP y credenciales válidas. Se usan solo para este envío.")
            .setView(form)
            .setNegativeButton("CANCELAR", null)
            .setPositiveButton("ENVIAR", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                String host = valor((EditText) form.findViewById(R.id.host));
                String portStr = valor((EditText) form.findViewById(R.id.port));
                String user = valor((EditText) form.findViewById(R.id.username));
                String password = ((EditText) form.findViewById(R.id.password)).getText().toString();
                String to = valor((EditText) form.findViewById(R.id.to));
                if (host.length() == 0 || user.length() == 0 || password.length() == 0 || !Patterns.EMAIL_ADDRESS.matcher(to).matches()) {
                    mostrarError("Completa el servidor, usuario, contraseña y destinatario válido.");
                    return;
                }
                int port;
                try { port = Integer.parseInt(portStr); } catch (NumberFormatException ex) { mostrarError("Puerto SMTP inválido."); return; }
                if (port < 1 || port > 65535) { mostrarError("Puerto SMTP fuera de rango."); return; }
                enviarReal(host, port, user, password, to);
                }
            }).show();
    }

    private void abrirClienteCorreo() {
        String asunto = "Comentario de Casa Mascotas Los Tomates";
        String cuerpo = "Nombre: " + valor(nombre) + "\nCorreo: " + valor(correo) + "\n\n" + valor(mensaje);
        Uri uri = Uri.parse("mailto:?subject=" + Uri.encode(asunto) + "&body=" + Uri.encode(cuerpo));
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT, cuerpo);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            mostrarError("Instala un cliente de correo que permita redactar mensajes.");
        }
    }

    private void enviarReal(final String host, final int port, final String user, final String password, final String to) {
        final String n = valor(nombre), e = valor(correo), m = valor(mensaje);
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Enviando correo por JavaMail...");
        progress.setCancelable(false);
        progress.show();
        new Thread(new Runnable() {
            @Override public void run() {
                String error = null;
                try { EmailSender.enviar(host, port, user, password, to, n, e, m); }
                catch (Exception ex) { error = ex.getClass().getSimpleName() + ": " + ex.getMessage(); }
                final String finalError = error;
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        if (isFinishing()) return;
                        progress.dismiss();
                        if (finalError == null) {
                            new AlertDialog.Builder(ContactoActivity.this).setTitle("Envío completado")
                                .setMessage("El servidor SMTP aceptó el mensaje.")
                                .setPositiveButton("ACEPTAR", null).show();
                        } else {
                            mostrarError("No se pudo enviar el correo.\n" + finalError + "");
                        }
                    }
                });
            }
        }).start();
    }

    private void mostrarError(String text) {
        new AlertDialog.Builder(this).setTitle("JavaMail").setMessage(text).setPositiveButton("ACEPTAR", null).show();
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
