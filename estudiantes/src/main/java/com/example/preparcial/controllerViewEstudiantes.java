package com.example.preparcial;

import com.example.preparcial.model.Estudiante;
import com.example.preparcial.utils.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controllerViewEstudiantes implements Initializable {
    //-----------------------------atributo globales-----------------------------

    //listas
    private ObservableList<Estudiante> listaPrincipal = FXCollections.observableArrayList();
    private ArrayList<Estudiante> listaEstudiantes =new ArrayList<>();
    private Estudiante estudiante;



    //---------------------------------atributos fxml-----------------------------


    @FXML
    private Button btnListar;
    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField Buscador;



    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;



    @FXML
    private TextField txtNota1;
    @FXML
    private TextField txtNota2;

    @FXML
    private Label lblNota2;

    @FXML
    private TextField txtNota3;



    @FXML
    private Pane paneBotones;

    @FXML
    private Pane paneCampos;

    @FXML
    private Pane panePrincipal;

    @FXML
    private Pane paneTable;

    @FXML
    private TableView<Estudiante> tableDatos;

    @FXML
    private TableColumn<Estudiante, String> columnaUno;

    @FXML
    private TableColumn<Estudiante, String> columnaDos;

    @FXML
    private TableColumn<Estudiante, String> columnaTres;

    @FXML
    private TableColumn<Estudiante, String> columnaCuatro;

    @FXML
    private TableColumn<Estudiante, String> columnaCinco;



    // -----------------------------atributos java---------------------------------------

    //se crean atributos para obtener campos de texto
    private String  nombre="";
    private String  codigo="";
    private float nota1=0;
    private  float nota2=0;
    private float nota3=0;

    // se crea un objeto para poder manipular, la diferentes acciones





//---------------------------------------------------------------ACTUALIZAR--------------------------------------------------


    //------------------------------------------------------AGREGAR-------------------------------------------------------------
    @FXML
    void agregar(ActionEvent event) throws IOException {

        //se guarda lo que se escribio en los txt
        nombre= txtNombre.getText();
        codigo= txtCodigo.getText();
        //se validan que todos los campos se hallan rellenado
        if (datosValidos() ) {
            try {
                //se captura exception si  en el campo de valor ingresan letras
                nota1 = Float.parseFloat(txtNota1.getText());
                nota2 = Float.parseFloat(txtNota2.getText());
                nota3 = Float.parseFloat(txtNota3.getText());

                // se verifica que el prodcuto no halla sido creado

                estudiante= new Estudiante(nombre,codigo,nota1,nota2,nota3);
                listaEstudiantes =Persistencia.cargarProductos();
                listaEstudiantes.add(estudiante);
                listaPrincipal.clear();
                listaPrincipal.addAll(listaEstudiantes);

                //se agrega la informaciona  la tabla
                columnaUno.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("nombre"));
                columnaDos.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("codigo"));
                columnaTres.setCellValueFactory(new PropertyValueFactory<Estudiante,String>("nota1"));
                columnaCuatro.setCellValueFactory(new PropertyValueFactory<Estudiante,String>("nota2"));
                columnaCinco.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("nota3"));
                tableDatos.setItems(listaPrincipal);

                //se guarda en el txt
                Persistencia.guardarProdcutos(listaEstudiantes);







                    //se registra en el log que  se guardo un producto
                    registrarAcciones("Producto agregado",1, "Agregar Producto");
                    mostrarMensaje("Crear Producto","Producto creado","El producto fue creado ",Alert.AlertType.CONFIRMATION);





                // se envian mesnajes y registro del log, del manejo de la exception
            } catch (NumberFormatException e) {
                mostrarMensaje("Datos invalidos","campo valor","no se pueden ingresar letras \nen el campo valor debe ingresar un valor numerico", Alert.AlertType.ERROR);
                registrarAcciones("Exception Producto campo numerico",1, "En un campo llamado valor en la view de prodcuto se lanza exception por que ingresan letras y deben ser numeros");
            }
        }




    }
//-----------------------------------------------------------------ELIMINAR-----------------------------------


    //----------------------------------------------IMPORTAR-----------------------------------------------------------------








    //-------------------------------------------------SELECCIONAR--------------------------------------------------------


    //--------------------------------------------------INITIALIZABLE----------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        try {
            //se carga en txt
            listaEstudiantes =Persistencia.cargarProductos();
            listaPrincipal.clear();
            listaPrincipal.addAll(listaEstudiantes);


            //se agrega la informaciona  la tabla
            columnaUno.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("nombre"));
            columnaDos.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("codigo"));
            columnaTres.setCellValueFactory(new PropertyValueFactory<Estudiante,String>("nota1"));
            columnaCuatro.setCellValueFactory(new PropertyValueFactory<Estudiante,String>("nota2"));
            columnaCinco.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("nota3"));
            tableDatos.setItems(listaPrincipal);

            //se guarda en el txt
            Persistencia.guardarProdcutos(listaEstudiantes);


            registrarAcciones("Productos cargados",1, "se carga la informacion del txt");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    //-----------------------------------------------log---------------------------------------------------------------

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);

    }

    //------------------------------codigo reutilizable---------------------------------------
    //limpiar campos de texto


    //validar que no esten en campos nulls o vacios
    private boolean datosValidos() {
        String mensaje = "";
        if (txtNombre.getText() == null || txtNombre.getText() .equals(""))
            mensaje += "El campo del nombre debe rellnarlo  \n";
        if (txtCodigo.getText() == null || txtCodigo.getText().equals(""))
            mensaje += "El campo de la descripcion debe rellenarlo \n";
        if (txtNota1.getText() == null || txtNota1.getText().equals(""))
            mensaje += "El campo de url debe rellenarlo \n";
        if (txtNota2.getText() == null || txtNota2.getText().equals(""))
            mensaje += "El campo de url debe rellenarlo \n";
        if (txtNota3.getText() == null || txtNota3.getText().equals(""))
            mensaje += "El campo de url debe rellenarlo \n";
        if (mensaje.equals("")) {
            return true;
        } else {
            mostrarMensaje("Notificación cliente", "Datos invalidos", mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    @FXML
    void listar(ActionEvent event) {
        try {
            //se carga en txt
            listaEstudiantes =Persistencia.cargarProductos();
            listaPrincipal.clear();
            listaPrincipal.addAll(listaEstudiantes);


            //se agrega la informaciona  la tabla
            columnaUno.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("nombre"));
            columnaDos.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("codigo"));
            columnaTres.setCellValueFactory(new PropertyValueFactory<Estudiante,String>("nota1"));
            columnaCuatro.setCellValueFactory(new PropertyValueFactory<Estudiante,String>("nota2"));
            columnaCinco.setCellValueFactory(new PropertyValueFactory<Estudiante, String>("nota3"));
            tableDatos.setItems(listaPrincipal);

            //se guarda en el txt
            Persistencia.guardarProdcutos(listaEstudiantes);


            registrarAcciones("en listar productos",1, "se carga la informacion del txt");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }

    @FXML
    void buscar(ActionEvent event) throws IOException {
        listaEstudiantes =Persistencia.cargarProductos();
        boolean bandera= true;
        for (Estudiante e1: listaEstudiantes) {
            if(e1.getCodigo().equalsIgnoreCase(Buscador.getText())){
                listaPrincipal.clear();
                listaPrincipal.addAll(e1);



                tableDatos.setItems(listaPrincipal);
                tableDatos.refresh();


                //se guarda en el txt
                Persistencia.guardarProdcutos(listaEstudiantes);


                registrarAcciones(" Estudiante encontrado",1, "se encontro el estudiantes buscado por el codigo");
                bandera=false;
                break;

            }

        }
        if(bandera==true){
            registrarAcciones("No se encontro",1, "No se encontro al usuario");
            mostrarMensaje("No se encontro", "No existe el estudiante", "no se encontro al estudiante", Alert.AlertType.WARNING);

        }


    }

    //enviamos un mensaje
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }


}