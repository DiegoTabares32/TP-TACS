package tacs.tp.app.resources;

import java.util.List;

import tacs.tp.app.domain.*;
import tacs.tp.app.mocks.*;
import tacs.tp.app.helpers.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/{id_usuario}")
public class UsuariosResource {
	
	@GET @Path("/perfil")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario doFilterById(@PathParam("id_usuario") Integer id) {
		UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();
		return usuarioRepository.getById(id);		
	}
	
    @GET
    @Path("/query")
    public  String getTokenById( @NotNull @QueryParam("id_usuario") Integer id) {    	
    	UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();
		return usuarioRepository.getById(id).getToken();    	    	
	}

	@GET @Path("/viajes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Viaje> doGetViajesUsuario(@PathParam("id_usuario") Integer id) {
		UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();

		
		return usuarioRepository.getById(id).getViajes();
	}


	@POST @Path("/viajes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addViaje(@PathParam("id_usuario") Integer id, Viaje viaje) {
		UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();

		
		Usuario usuario = usuarioRepository.getById(id);
		usuario.addViaje(viaje);
		
		CustomResponseObject responseObject = new CustomResponseObject();
		responseObject.setStatus("200");
		responseObject.setMessage("Viaje agregado");
		return Response.status(200).entity(responseObject).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUsuario(Usuario usuario){
		UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();
		usuarioRepository.add(0, usuario);
      return Response.status(201)
      .entity("Creado nuevo usuario")
      .build();
	}
	

  
  @POST
  @Path("/{token}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response nuevoPasajero(@PathParam("id_usuario") Integer id, @PathParam("token") String token) {
	  UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();
	  usuarioRepository.postUsuarioByIdAndToken(id, token);
    return Response.status(201)
    .entity("Logueado correctamente")
    .build(); 
  }
}
