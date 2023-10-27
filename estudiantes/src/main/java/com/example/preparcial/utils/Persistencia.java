package com.example.preparcial.utils;

/*import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.banco.bancouq.model.*;*/

import com.example.preparcial.model.Estudiante;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Persistencia {



//--------------------------------------RUTAS----------------------------------------
    public static final String RUTA_ARCHIVO_ESTUDIANTES = "C:\\Users\\juanj\\OneDrive\\Escritorio\\preparcial\\estudiantes\\src\\main\\resources\\com\\example\\preparcial\\archivos\\estudiantes";

       public static final String RUTA_ARCHIVO_LOG ="C:\\Users\\juanj\\OneDrive\\Escritorio\\preparcial\\estudiantes\\src\\main\\resources\\com\\example\\preparcial\\archivos\\log";




    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param
     * @throws IOException
     */
//-------------------------------------------GUARDAR ARCHIVOS------------------------------
    public static void guardarProdcutos(ArrayList<Estudiante> listaClientes) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Estudiante productos:listaClientes)
        {
            contenido+= productos.getNombre()+"--"+productos.getCodigo()+"--"+productos.getNota1()+"--"+productos.getNota2()+"--"+productos.getNota3()+"\n";

        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, false);
    }




//	----------------------CARGAR ARCHIVOS------------------------

    /**
     *
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */

    public static ArrayList<Estudiante> cargarProductos() throws FileNotFoundException, IOException
    {
        ArrayList<Estudiante> productos =new ArrayList<Estudiante>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ESTUDIANTES);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Estudiante producto = new Estudiante();
            producto.setNombre(linea.split("--")[0]);
            producto.setCodigo(linea.split("--")[1]);
            producto.setNota1(Float.parseFloat(linea.split("--")[2]));
            producto.setNota2(Float.parseFloat(linea.split("--")[3]));
            producto.setNota3(Float.parseFloat(linea.split("--")[4]));
            productos.add(producto);
        }
        return productos;
    }

    //------------------------------REGISTRO LOG-----------------------------------------
    public static void guardaRegistroLog(String mensaje, int nivel, String accion) {

        ArchivoUtil.guardarRegistroLog(mensaje, nivel, accion, RUTA_ARCHIVO_LOG);
    }













}
