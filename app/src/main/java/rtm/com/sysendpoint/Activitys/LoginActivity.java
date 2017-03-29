package rtm.com.sysendpoint.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rtm.com.sysendpoint.Beans.EmpleadoBean;
import rtm.com.sysendpoint.EndPoint.AS400RestAdapter;
import rtm.com.sysendpoint.R;

public class LoginActivity extends AppCompatActivity  {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private AS400RestAdapter mAS400RestAdapter=new AS400RestAdapter();
    private EditText mInputUser;
    private EditText mInputPass;
    private Button _loginButton;
    private TextView _signupLink;
    protected static EmpleadoBean empleado;

    public LoginActivity() {
        empleado = new EmpleadoBean();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        mInputUser = (EditText) findViewById(R.id.input_user);
        mInputPass = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button)findViewById(R.id.btn_login);
        _signupLink = (TextView)findViewById(R.id.link_signup);


        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Validando...");
        progressDialog.show();


        String user = mInputUser.getText().toString();
        String password = mInputPass.getText().toString();

        // Logica del Login
        empleado =  mAS400RestAdapter.ValidarUsuario(user, password, new Callback<EmpleadoBean>() {
            @Override
            public void success(EmpleadoBean returns, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


        if(empleado.getStrUsuario().isEmpty() || !empleado.getStrUsuario().trim().equalsIgnoreCase(user)){
            mInputUser.setError("Usuario No Existe");
            progressDialog.dismiss();
            return;

        }else {
            mInputUser.setError(null);
        }
        if (empleado.getStrClave().isEmpty()|| !empleado.getStrClave().trim().equalsIgnoreCase(password)) {
            mInputPass.setError("Contraseña Incorrecta");
            progressDialog.dismiss();
            return;
        }else {
            mInputPass.setError(null);
        }

        if(empleado.getStrResponse().equalsIgnoreCase("exito")){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);

        }else{
            progressDialog.dismiss();
            onLoginFailed();
            return;
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Usuario o Contraseña Incorrecta", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


}
