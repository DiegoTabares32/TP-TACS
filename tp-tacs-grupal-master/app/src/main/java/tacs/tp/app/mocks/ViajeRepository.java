package tacs.tp.app.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tacs.tp.app.domain.Viaje;

/**
* No es thread safe
*/
public final class ViajeRepository {
	private static ViajeRepository instance = null;

	private ViajeRepository() {
		this.init();
	}

	public static ViajeRepository getInstance() {
		if (instance == null) {
			instance = new ViajeRepository();
		}

		return instance;
	}

	Map<Integer, Viaje> viajes = new HashMap<Integer, Viaje>();

	//Altas y bajas
	public void add(Integer codigo, Viaje viaje) {
		viajes.put(codigo, viaje);
	}
	
	//Seleccion de viajes
/*
	public Viaje getById(Integer codigo) {
		return viajes.get(codigo);
	}
*/

/*	
	public List<Viaje> getByOrigenDestino(String origen, String destino) {
		List<Viaje> lista = new ArrayList<Viaje>(viajes.values());
		List<Viaje> result = new ArrayList<Viaje>();
		
		for (Viaje viaje : lista) {
			if (viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino)) {
				result.add(viaje);
			}
		}
		return result;
	}
*/	
	//Listado
	public List<Viaje> get() {
		List<Viaje> lista = new ArrayList<Viaje>(viajes.values());
		return lista;
	}

	//Inicializacion del storage
	public void init() {
/*
		List<Segmento> segmentos_EZE_MEX_MIA = new ArrayList<Segmento>();
		segmentos_EZE_MEX_MIA.add(new Segmento("EZE", "MEX"));
		segmentos_EZE_MEX_MIA.add(new Segmento("MEX", "MIA"));
		viajes.put(1, new Viaje(1, "Buenos Aires", "Miami", segmentos_EZE_MEX_MIA));

		List<Segmento> segmentos_EZE_KEN_MIA = new ArrayList<Segmento>();
		segmentos_EZE_KEN_MIA.add(new Segmento("EZE", "KEN"));
		segmentos_EZE_KEN_MIA.add(new Segmento("KEN", "MIA"));
		viajes.put(2, new Viaje(2, "Buenos Aires", "Miami", segmentos_EZE_KEN_MIA));
		
		List<Segmento> segmentos_EZE_KEN_PAR = new ArrayList<Segmento>();
		segmentos_EZE_KEN_PAR.add(new Segmento("EZE", "KEN"));
		segmentos_EZE_KEN_PAR.add(new Segmento("KEN", "PAR"));
		viajes.put(3, new Viaje(3, "Buenos Aires", "Paris", segmentos_EZE_KEN_PAR));
*/
	}

}
