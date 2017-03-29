package rtm.com.sysendpoint.Activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rtm.com.sysendpoint.Beans.SolicitudCorreoBean;
import rtm.com.sysendpoint.EndPoint.AS400RestAdapter;
import rtm.com.sysendpoint.R;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    private EditText mNameText;
    private EditText mCorreoText;
    private EditText mMotivoText;
    private Button btnEnviar;
    private AS400RestAdapter mAS400RestAdapter=new AS400RestAdapter();


    @InjectView(R.id.link_login) TextView _loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);
        mNameText = (EditText)findViewById(R.id.input_nombre);
        mCorreoText = (EditText)findViewById(R.id.input_correo);
        mMotivoText = (EditText)findViewById(R.id.input_motivo);
        btnEnviar = (Button)findViewById(R.id.btn_enviar);



        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void enviarSolicitud(View view) {
        Log.d(TAG, "Signup");
        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Enviando Correo...");
        progressDialog.show();

        SolicitudCorreoBean solicitud = new SolicitudCorreoBean();

        String nombre = mNameText.getText().toString();
        String correo = mCorreoText.getText().toString();
        String motivo = mMotivoText.getText().toString();
        solicitud = mAS400RestAdapter.EnviarCorreoSolicitud(nombre, correo, motivo, new Callback<SolicitudCorreoBean>() {
            @Override
            public void success(SolicitudCorreoBean solicitudCorreoBean, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                onError();
            }
        });

        btnEnviar.setEnabled(false);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onResultOK();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onResultOK() {
        btnEnviar.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(), "Correo Enviado", Toast.LENGTH_LONG).show();

    }

    public void onError() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnEnviar.setEnabled(true);
    }

}