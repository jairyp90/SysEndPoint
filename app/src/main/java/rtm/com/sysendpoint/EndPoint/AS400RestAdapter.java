package rtm.com.sysendpoint.EndPoint;

import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import rtm.com.sysendpoint.Beans.ClienteBean;
import rtm.com.sysendpoint.Beans.EmpleadoBean;
import rtm.com.sysendpoint.Beans.RestData;
import rtm.com.sysendpoint.Beans.SolicitudCorreoBean;
import rtm.com.sysendpoint.Utils.GeneralError;

public class AS400RestAdapter {
    protected final String TAG = getClass().getSimpleName();
    private RestAdapter mRestAdapter;
    private AS400ApiServices mApi;
    private static final String WEATHER_URL = "http://190.116.7.136:8080/WebServicesAS400/1.0";

    public AS400RestAdapter() {
        mRestAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(WEATHER_URL)
                .setErrorHandler(new GeneralError())
                .build();
        mApi = mRestAdapter.create(AS400ApiServices.class); // create the interface

    }


    public ClienteBean testCliente(String Codigo){
        ClienteBean result = null;
        result=mApi.getClientes(Codigo);
        return  result;
    }

    public RestData testALLClientes(String Descripcion){

        return  mApi.getClientesAPI(Descripcion);

    }

    public void ArrayClientes(String Descripcion, Callback<RestData> callback){

          mApi.getClientesArray(Descripcion,callback);

    }

    public EmpleadoBean ValidarUsuario(String user,String contra,Callback<EmpleadoBean> callback){
        Log.d("ValidarUsuario", "Entre al ValidarUsuario");

       return  mApi.getValidaLogin(user,contra);

    }
    public SolicitudCorreoBean EnviarCorreoSolicitud(String nombre,String correo,String motivo,Callback<SolicitudCorreoBean> callback){
        return mApi.getEnviarSolicitudCorreo(nombre,correo,motivo);
    }
}
