
package com.mycompany.controllerimpl;

import com.mycompany.modell.Cliente;
import com.mycompany.configbd.Conexionbd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.dao.ClienteDAO;

public class ClienteCtrlImpl implements ClienteDAO {

    Conexionbd cx;
    String sql;
    ResultSet rs;
    ArrayList<Cliente> listDatos;

    public ClienteCtrlImpl() {
        cx = new Conexionbd();
    }

    @Override
    public boolean createCliente(Cliente cli) {
        sql = "INSERT INTO public.cliente(id_cliente, nombres_razon_social, telefono, correo , dni_ruc)"
                + "VALUES("+cli.getId_cliente()+",'"+cli.getNombres_razon_social()+"','"+cli.getTelefono()+",'"+cli.getCorreo()+",'"+cli.getDni_ruc()+"')";
        return cx.executeInsertUpdate(sql);
    }

    @Override
    public ArrayList<Cliente> readCliente() {
        listDatos = new ArrayList<>();
        sql = "SELECT id_cliente, nombres_razon_social, telefono, correo , dni_ruc "
                + "    FROM public.cliente";
        rs = cx.executeQuery(sql);
        try {
            while (rs.next()) {
                listDatos.add(new Cliente(rs.getString("id_cliente"),rs.getString("nombres_razon_social"),rs.getString("telefono"),rs.getString("correo"),rs.getString("dni")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteCtrlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDatos;
    }

    @Override
    public boolean updateliente(Cliente cli) {
        sql = "UPDATE public.cliente SET"
                + " nombres_razon_social = '"+cli.getNombres_razon_social()+"' ,telefono = '"+cli.getTelefono()+"' ,correo= '"+cli.getCorreo()+"' , dni_ruc = '"+cli.getDni_ruc()+"'"
                + " WHERE id_cliente = "+cli.getId_cliente()+" ";
        return cx.executeInsertUpdate(sql);
    }

  @Override
    public boolean deleteCliente(String id) {       
        sql = "DELETE public.cliente "
                + "WHERE id_cliente = "+id+" ";
        return cx.executeInsertUpdate(sql);
    }

}
