
package persistencia;
import modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Excepciones.*;

public class ImpArchivoTexto implements IPublicacionDao{
    private File archivo;
    private FileWriter modoEscritura;
    private Scanner modoLectura;
    
    public ImpArchivoTexto(){
        this("Registro.txt");
    }
    public ImpArchivoTexto(String ruta){
        this.archivo=new File(ruta);
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public FileWriter getModoEscritura() {
        return modoEscritura;
    }

    public void setModoEscritura(FileWriter modoEscritura) {
        this.modoEscritura = modoEscritura;
    }

    public Scanner getModoLectura() {
        return modoLectura;
    }

    public void setModoLectura(Scanner modoLectura) {
        this.modoLectura = modoLectura;
    }

    @Override
    public void insertarPublicacion(Publicacion p)throws ExcepcionArchivo{
        PrintWriter pw=null;
        try {
            this.modoEscritura=new FileWriter(archivo,true);
            pw= new PrintWriter(this.modoEscritura);
            pw.println(p.getDataStringFormat());
        } catch (IOException ioe) {
            throw new ExcepcionArchivo("El archivo en modo escritura no existe o no puede ser creado");
        }finally{
            if (pw!=null){
                pw.close();
            }
        }
        

    }
    private Publicacion cagarDatos(String[] data){
        String idbn=data[0];
        String titulo=data[1];
        String autor=data[2];
        int anio=Integer.valueOf(data[3]);
        double costo=Double.valueOf(data[4]);
        return new Publicacion(idbn, titulo, autor, anio, costo) {
            @Override
            public String getDataStringFormat() {
                return this.idbn+";"+this.titulo+";"+this.autor+";"+this.anio+";"+this.costo;
                
            }

            @Override
            public String getStringFormat() {
                return this.idbn+";"+this.titulo+";"+this.autor+";"+this.anio+";"+this.costo;
  
            }
        };
        
    }


    @Override
    public List<Publicacion> leerPublicaciones()throws ExcepcionArchivo{
        List<Publicacion>lista;
        try {
            this.modoLectura=new Scanner(this.archivo);
            lista=new ArrayList();
            while(this.modoLectura.hasNext()){
                String datos[]=this.modoLectura.nextLine().split(";");
                Publicacion p=this.cagarDatos(datos);
                lista.add(p);
            }
            return lista;
        } catch (FileNotFoundException ioe) {
            throw new ExcepcionArchivo("Error al abrir archivo en modo lectura, no existe");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionArchivo {
        Publicacion buscado=null;
        try {
            this.modoLectura=new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String datos[]=this.modoLectura.nextLine().split(";");
                Publicacion aux=this.cagarDatos(datos);
                if(aux.getIdbn()==p.getIdbn()){
                    buscado=aux;
                    break;
                }
            }
            return buscado;
        } catch (FileNotFoundException ioe) {
            throw new ExcepcionArchivo("Error al abrir archivo en modo lectura, no existe");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }

    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p)throws ExcepcionArchivo{
        Publicacion eliminado=null;
        ImpArchivoTexto archivoTmp=new ImpArchivoTexto("Registro.txt");
        try {
            this.modoLectura=new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String datos[]=this.modoLectura.nextLine().split(";");
                Publicacion aux=this.cagarDatos(datos);
                if(aux.getIdbn()!=p.getIdbn()){
                    archivoTmp.insertarPublicacion(aux);
                }
                else{
                    eliminado=aux;
                }
            }
            this.modoLectura.close();
            return eliminado;
        } catch (FileNotFoundException ioe) {
            throw new ExcepcionArchivo("Error al abrir archivo en modo lectura, no existe");
        }
        catch(IOException e){
            throw new ExcepcionArchivo(e.getMessage());
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }

    }
    
    
}
