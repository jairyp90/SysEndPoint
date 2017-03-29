package rtm.com.sysendpoint.EndPoint;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rtm.com.sysendpoint.Beans.ClienteBean;
import rtm.com.sysendpoint.Beans.EmpleadoBean;
import rtm.com.sysendpoint.Beans.RestData;
import rtm.com.sysendpoint.Beans.SolicitudCorreoBean;


public interface AS400ApiServices {


    @GET("/1.0/clienteservice/ObtenerClientForID")
    ClienteBean getClientes(@Query("Cod") String codigo);

    @GET("/1.0/clienteservice/DataByDescription")
    RestData getClientesAPI
            (@Query("Desc") String Desc);

    @GET("/1.0/clienteservice/DataByDescription")
    void getClientesArray(@Query("Desc") String Desc, Callback<RestData> callback);

    @FormUrlEncoded
    @POST("/1.0/loginservice/LoginSystem")
    EmpleadoBean getValidaLogin(@Field("User")String usuario,@Field("Pass")String contra);

    @GET("/1.0/correoservice/EnviarSolicitud")
    SolicitudCorreoBean getEnviarSolicitudCorreo(@Query("Nombre")String nombre,@Query("Correo")String correo,@Query("Motivo")String motivo);

}
