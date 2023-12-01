package ejemploabc;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;


public class abc_ejem extends JFrame implements ActionListener {
	Connection con;
    JButton btnAgregar, btnModificar, btnBorrar, btnConsultar;
    JTextField txtClave, txtNombre;
    
    // Librería de MySQL
    public String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "abcJava";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "3307";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "1234";
    Connection conn = null;
    
    public abc_ejem()
    {




       
            

            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        
        
        
        
        
        
        
        
        
               Container c;
        JPanel panelSuperior = new JPanel();
        JPanel panelBotones = new JPanel(); 
        
        
        //*ABRIR CONEXI�N CON BASE DE DATOS (INICIALIZACI�N DE CONEXI�N CON BD)
        //try {
            //Crea la conexi�n con la base de datos
            //Class.forName("com.mysql.cj.jdbc.Driver"); //Carga el driver de sql server en memoria
            //String connectionUrl = "jdbc:mysql://localhost:3307;databaseName=abcJava;user=root;password=1234"; //Crea la cadena de conexión
            //con = DriverManager.getConnection(connectionUrl); //Abre la conexi�n
          //  JOptionPane.showMessageDialog(null,"La conexi�n se llevo a cabo con �xito :P");
        //} catch (SQLException e) {
            //JOptionPane.showMessageDialog(null,"Error al conectarse con la bd: "+ e.toString());
        //} catch (ClassNotFoundException cE) {
         //   JOptionPane.showMessageDialog(null,"Class Not Found Exception: "+ cE.toString());
       // }
        
        //ESTABLECER LAYOUT Y CREAR BOTONES (INICIALIZACI�N DE INTERFAZ DE USUARIO)
        
        c = getContentPane();
        BorderLayout bl = new BorderLayout();
        c.setLayout(bl);                          //Layout del formulario
        
        c.add(panelSuperior, BorderLayout.NORTH);
        c.add(panelBotones, BorderLayout.SOUTH);

        GridLayout gl = new GridLayout(2,2);
        panelSuperior.setLayout(gl);              //Layout del panel superior

        FlowLayout fl = new FlowLayout();
        panelBotones.setLayout(fl);               //Layout del panel botones

        //Crear componentes de la interfaz de usuario
        JLabel lblClave = new JLabel("Clave");
        JLabel lblNombre = new JLabel("Nombre");

        txtClave = new JTextField();
        txtNombre = new JTextField();

        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this); // btnAgregar.addActionListener(new MiClase());
        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(this);
        btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(this);

        //Agregando componentes de interfaz de usuario
        panelSuperior.add(lblClave); panelSuperior.add(txtClave);
        panelSuperior.add(lblNombre); panelSuperior.add(txtNombre);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnConsultar);

    }

	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource()==btnAgregar) {
			try {
				String strSQL = "INSERT INTO paises (clave,nombre) VALUES ('" + txtClave.getText() + "','" + txtNombre.getText() + "')";
			Statement stmt= conn.createStatement();
			int rowsEffected=stmt.executeUpdate(strSQL); 
			JOptionPane.showMessageDialog(null,rowsEffected + "Rows effected" );
			
			
			}catch (SQLException ex) {
				JOptionPane.showMessageDialog(null,"Error al conectarse a la BD: " + ex.toString() );
			}
		}
		
		if(e.getSource()==btnBorrar)
		{
			try
			{
				String strSQL = "Delete from paises where clave = '" + txtClave.getText() + "'";
				Statement stmt =conn.createStatement();
				int rowsEffected = stmt.executeUpdate(strSQL);
				JOptionPane.showMessageDialog(null, rowsEffected + " Rows effected");
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al conectarse con la BD " + ex.toString());
			}
			
			
		}
				
		if (e.getSource()== btnModificar)
		{
			try
			{
				String strSQL = "UPDATE paises SET nombre = '"+ txtNombre.getText()	+"' where clave = '" + txtClave.getText() +"'";
				Statement stmt= conn.createStatement();
				int rowsEffected=stmt.executeUpdate(strSQL);
				JOptionPane.showMessageDialog(null, rowsEffected + "rows Effected");
			}catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al conectarse a la BD: " + ex.toString());
				
			}
		}
		if (e.getSource()== btnConsultar) {
			try
			{
			String SQL = "SELECT * FROM paises where clave =  '"+ txtClave.getText() +  "'";
			Statement stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(SQL);
			rs.next();
			txtNombre.setText(rs.getString("Nombre"));
			
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null,"Error al conectarse con la bd "+ ex.toString());
			}
		}
			
	}
}

	