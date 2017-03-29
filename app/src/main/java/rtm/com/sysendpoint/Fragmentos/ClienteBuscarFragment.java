package rtm.com.sysendpoint.Fragmentos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rtm.com.sysendpoint.Beans.ClienteBean;
import rtm.com.sysendpoint.Beans.RestData;
import rtm.com.sysendpoint.EndPoint.AS400RestAdapter;
import rtm.com.sysendpoint.R;

/**
 * Fragment for the main content
 */
public class ClienteBuscarFragment extends Fragment {
    /**
     * This argument represents the title for each section
     */
    public static final String ARG_SECTION_TITLE = "section_number";
    private ListView ListaClientes;
    private  TextView textDescripcion;
    private AS400RestAdapter mAS400RestAdapter=new AS400RestAdapter();

    /**
     * Crea una instancia prefabricada de {@link ClienteBuscarFragment}
     *
     * @param sectionTitle Título usado en el contenido
     * @return Instancia dle fragmento
     */
    public static ClienteBuscarFragment newInstance(String sectionTitle) {
        ClienteBuscarFragment fragment = new ClienteBuscarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public ClienteBuscarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment, container, false);
        View viewBuscar = inflater.inflate(R.layout.section_cliente_buscar,container,false);
        // Ubicar argumento en el text view de section_fragment.xml
        String title = getArguments().getString(ARG_SECTION_TITLE);
        if(title.equalsIgnoreCase("Inicio")){
            Button btnBuscar = (Button)viewBuscar.findViewById(R.id.btn_buscar);
             textDescripcion = (TextView)viewBuscar.findViewById(R.id.input_descripcion);
            ListaClientes = (ListView)viewBuscar.findViewById(R.id.listclientes);
            ProgressBar progressBar = (ProgressBar)viewBuscar.findViewById(R.id.progress_bar_id);
            btnBuscar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard(getActivity(), textDescripcion.getWindowToken());
            String city = textDescripcion.getText().toString();
            mAS400RestAdapter.ArrayClientes(city, new Callback<RestData>() {
                @Override
                public void success(RestData clienteBeans, Response response) {

                    AdaptadorClientes adapter = new AdaptadorClientes(getActivity(), clienteBeans.getClienteBean());
                    ListaClientes.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
    });


            return viewBuscar;
        }
        TextView titulo = (TextView) view.findViewById(R.id.title);
        titulo.setText(title);
        return view;
    }



    public static void hideKeyboard(Activity activity,
                                    IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) activity.getSystemService
                (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    private class AdaptadorClientes extends ArrayAdapter<ClienteBean> {
        private List<ClienteBean> listaClienteBean;

        public AdaptadorClientes(Context context,List<ClienteBean> Listacli){
            super(context,R.layout.list_item,Listacli);
            listaClienteBean = Listacli;
        }

        @NonNull
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            View item = layoutInflater.inflate(R.layout.list_item, null);

            TextView txtclicve = (TextView)item.findViewById(R.id.txtclicve);
            txtclicve.setText(listaClienteBean.get(position).getCLICVE());

            TextView txtclimon = (TextView)item.findViewById(R.id.txtclimon);
            txtclimon.setText(listaClienteBean.get(position).getCLINOM());

            TextView txtcliruc = (TextView)item.findViewById(R.id.txtcliruc);
            txtcliruc.setText(listaClienteBean.get(position).getCLIRUC());

            TextView txtclnide = (TextView)item.findViewById(R.id.txtclnide);
            txtclnide.setText(listaClienteBean.get(position).getCLNIDE());

            TextView txtcltide = (TextView)item.findViewById(R.id.txtcltide);
            txtcltide.setText(listaClienteBean.get(position).getCLTIDE());

            return item;
        }
    }

}
