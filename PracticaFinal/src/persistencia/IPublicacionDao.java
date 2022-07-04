
package persistencia;

import modelo.Publicacion;
import java.util.List;
import Excepciones.*;


public interface IPublicacionDao {
    
    void insertarPublicacion(Publicacion p)throws ExcepcionArchivo;
    List<Publicacion> leerPublicaciones()throws  ExcepcionArchivo;
    Publicacion buscarPublicacion(Publicacion p)throws ExcepcionArchivo;
    Publicacion eliminarPublicacion(Publicacion p)throws ExcepcionArchivo;
   
}
