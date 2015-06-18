package tacs.tp.app.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import tacs.tp.app.domain.Usuario;

/**
* No es thread safe
*/
public final class UsuarioRepository {
	private static UsuarioRepository instance = null;

	private UsuarioRepository() {
		this.init();
	}

	public static UsuarioRepository getInstance() {
		if (instance == null) {
			instance = new UsuarioRepository();
		}

		return instance;
	}

	Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

	//Altas y bajas
	public void add(Integer id, Usuario usuario) {
		usuarios.put(id, usuario);//este es el id de usuario, no es hash!
	}
	
	public Usuario getById(Integer id) {
		return usuarios.get(id);
	}
	
	//Seleccion de usuarios
	public Usuario getByUsername(String username) {
		List<Usuario> lista = new ArrayList<Usuario>(usuarios.values());
		for (Usuario usuario : lista) {
			if (usuario.getUsername().equals(username)) {
				return usuario;
			}
		}
		return null;
	}
	
	//Listado de usuarios
	public List<Usuario> getAll() {
		List<Usuario> lista = new ArrayList<Usuario>(usuarios.values());
		return lista;
	}

	//Inicializacion del storage
	private void init() {
		usuarios.put(0, new Usuario(0, "Anonimo", "Anonimo", "Anonimo"));
//		usuarios.put(1, new Usuario(1, "juan4ever", "Juan", "Perez"));
//		usuarios.put(2, new Usuario(2, "rockero123", "Rock", "Star"));
//		usuarios.put(3, new Usuario(3, "aero", "Aero", "Smith"));
//	
//		this.getById(0).addAmigo(this.getById(1));
	}
    public Usuario postUsuarioByIdAndToken(long id, String shortToken) {
        //deberiamos llamar al DAO de usuario para este metodo
    	 Usuario buscado = null;
         for (Usuario user : getAll()) {
        	 //virifico si es un usuario real de FB
             if (user.getId() == id) {
                 ClientConfig config = new ClientConfig().register(new JacksonFeature());
                 Client client = ClientBuilder.newClient(config);
                 WebTarget target = client.target("https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=1439890256312274&client_secret=be52304601958885a77610f61b8ccbf6&fb_exchange_token=" + shortToken);
                 Invocation.Builder invocationBuilder = target.request();
                 Response response = invocationBuilder.get();
                 String longToken = response.readEntity(new GenericType<String>() {
                 });
                 longToken = longToken.substring(13);
                 longToken = longToken.split("&", 2)[0];
                 buscado = user;
                 buscado.setToken(longToken);
             }
         }
         if (buscado == null) {
        	 //nuevo usario
             ClientConfig config = new ClientConfig().register(new JacksonFeature());
             Client client = ClientBuilder.newClient(config);
             WebTarget target = client.target("https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=1439890256312274&client_secret=be52304601958885a77610f61b8ccbf6&fb_exchange_token=" + shortToken);
             Invocation.Builder invocationBuilder = target.request();
             Response response = invocationBuilder.get();
             String longToken = response.readEntity(new GenericType<String>() {
             });
             longToken = longToken.substring(13);
             longToken = longToken.split("&", 2)[0];

             target = client.target("https://graph.facebook.com/" + id + "?fields=first_name&access_token=" + longToken);
             invocationBuilder = target.request();
             response = invocationBuilder.get();
             String nombre = response.readEntity(new GenericType<String>() {
             });

             target = client.target("https://graph.facebook.com/" + id + "?fields=last_name&access_token=" + longToken);
             invocationBuilder = target.request();
             response = invocationBuilder.get();
             String apellido = response.readEntity(new GenericType<String>() {
             });
             
             Usuario usuario = new Usuario (id,"", nombre, apellido, longToken,new ArrayList<Usuario>());
             //obtenerAmigos
             //add user
             return usuario;

         }
         return buscado;
     }
}
