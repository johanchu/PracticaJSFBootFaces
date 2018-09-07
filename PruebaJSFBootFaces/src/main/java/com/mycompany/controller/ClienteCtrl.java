
package com.mycompany.controller;

import com.mycompany.modell.Cliente;
import com.mycompany.configbd.Conexionbd;
import com.mycompany.controllerimpl.ClienteCtrlImpl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.dao.ClienteDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="clienteCtrl")
@SessionScoped

public class ClienteCtrl implements Serializable {
    
    private Cliente selected;
    private boolean est;
    ClienteDAO catdao;
    
    private ArrayList<Cliente> list = new ArrayList<>();

    public ClienteCtrl() {
        this.selected = new Cliente();
        this.catdao = new ClienteCtrlImpl();
    }
    
    public void createCategoria(){
        
        if(catdao.createCliente(selected)){
            System.out.println("Insertado");
        } else{
            System.out.println("Error al momento de insertar");
        }
        
    }
    
    ArrayList<Cliente> listDatos;

    public ArrayList<Cliente> getList(){
        
        catdao = new ClienteCtrlImpl();
        list = catdao.readCliente();
        return list;
        
    }
    
    
     public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }
    
    
    

}
