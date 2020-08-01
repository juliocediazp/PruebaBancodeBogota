package net.itinajero.app.service;

import java.util.List;
import java.util.Optional;
import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Registramos esta clase como un Bean en nuestro Root ApplicationContext.
@Service
public class NoticiasServiceJPA implements INoticiasService{
	
	// Inyectamos una instancia desde nuestro Root ApplicationContext.
    @Autowired
	private NoticiasRepository noticiasRepo;
		
   
	public List<Noticia> buscarUltimas() {
		List<Noticia> noticias = noticiasRepo.findTop3ByEstatusOrderByIdDesc("Activa");		
		return noticias;
	}


	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);		
	}


	public List<Noticia> buscarTodas() {		
		return noticiasRepo.findAll();
	}

	
	public void eliminar(int idNoticia) {
		// noticiasRepo.delete(idNoticia); // Spring 4.3
		noticiasRepo.deleteById(idNoticia);
	}

	// Spring 4.3
//	@Override
//	public Noticia buscarPorId(int idNoticia) {
//		return noticiasRepo.findOne(idNoticia); 
//	}
	
	
	public Noticia buscarPorId(int idNoticia) {
		Optional<Noticia> optional = noticiasRepo.findById(idNoticia);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
