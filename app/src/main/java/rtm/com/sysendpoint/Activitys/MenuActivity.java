package rtm.com.sysendpoint.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import rtm.com.sysendpoint.EndPoint.AS400RestAdapter;
import rtm.com.sysendpoint.R;


public class MenuActivity extends AppCompatActivity {

    private AS400RestAdapter mAS400RestAdapter = new AS400RestAdapter();
   // protected EmpleadoBean empleado = LoginActivity.empleado;
    private Button btnCliente;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupage);
    btnCliente= (Button)findViewById(R.id.btn_cliente);


    }

    public void ClickbtnCliente(View v){
        Intent intent = new Intent(this, ClienteActivity.class);
        startActivity(intent);
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
      /*  if (id == R.id.btnsalir) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
