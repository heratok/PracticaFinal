
package modelo;

import persistencia.*;
import java.util.List;
import Excepciones.*;


public class ListaPublicacion implements IPublicacionDao {
    
    private IPublicacionDao registroPublicaciones;

    public ListaPublicacion() {
        //this.registroPublicaciones = new ImpArchivoTexto();
        this.registroPublicaciones=new ImpArchivoObjeto();
    }
    
    
    @Override
    public void insertarPublicacion(Publicacion p)throws  ExcepcionArchivo{
        
        this.registroPublicaciones.insertarPublicacion(p);
        
    }

    @Override
    public List<Publicacion> leerPublicaciones()throws ExcepcionArchivo{
        
        return this.registroPublicaciones.leerPublicaciones();
        
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p)throws ExcepcionArchivo{
        
        return this.registroPublicaciones.buscarPublicacion(p);
        
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p)throws ExcepcionArchivo{
        
        return this.registroPublicaciones.eliminarPublicacion(p);
        
    }
    
}
