
package core;

import java.io.Serializable;

/**
 *
 * @author Angeles
 */
public class Mensaje implements Serializable{
    private char opCode; //operacion se hara, o causa de error
    private String userOrPermit;//usuario, permiso o dueno del archivo
    private String passOrArchive;//password,nombreArchivo
    boolean confirmacion;//indica si se a podido realizar la operacion, o si es la primeravez que se guarda
    String [] otherData;//Diferentes listas
    int tamArchivo;
    
    
    public Mensaje(char oc, String p,int t, String [] sA)
    {
        opCode = oc; userOrPermit = p; tamArchivo = t; otherData = sA;
        /*
            notificacionGuardar(opCode, quienLoGuardo, datosArchivo)
            notificacionActualizar(opCode, quienLoActualizo, datosArchivo + id)
        */
    }
    
    public Mensaje(char oc, String r, String s)
    {   
        opCode = oc;    userOrPermit = r;   passOrArchive = s;  
        /*Utilizado para:
        iniciar Sesion(opCode, usuario, password)
        pedirListaColaboradores(opCode, dueno, archivo)
        pedirListaUsuarios(opCode, dueno, archivo)
        notificarEliminacion(opCode, dueno, archivo)
    */
    }
    
    public Mensaje(char oc, String r, String [] sA)
    {
        opCode = oc; userOrPermit = r; otherData = sA;
        /*
            cerrarSesion(opCode, usuario, [archivo, dueno])
            notificarAgregacion(opCode, permit, [archivo,dueno])
        */
    }
    
    public Mensaje(boolean conf, String r, String [] sA)
    {
        confirmacion = conf; userOrPermit = r; otherData = sA;
        /*
            respAbrirArchivo(opCode, usuario, [archivo, dueno])
        */
    }
    
    public Mensaje(char oc, String r, String [] sA, boolean ft)
    {
        opCode = oc; userOrPermit = r; otherData = sA; confirmacion = ft;
        /*
            guardar(opCode, usuarioQueGuarda, [archivo, dueno], firstTimeSaving)
        */
    }
    
    public Mensaje(char oc, String s1, String s2, String[] sA)
    {
        opCode = oc;  passOrArchive = s1; userOrPermit = s2; otherData = sA;
        /*
        registrar(opCode, password, user, [nombre, apellidos, email])
        eliminarColaborador(opCode, archivo, dueno,[usuarios a eliminar] )
        */
    }
    
    public Mensaje(boolean b, String [] sA)
    {
        confirmacion = b; otherData = sA;
        /*
            confirmacionIniciarSesion(confirmacion, [lista de archivos donde fue invitado / removido])
        */
    }
    
    public Mensaje(boolean co, char op)
    {
        confirmacion = co; opCode = op;
        /*
            respuesta de error de registro, guardar, abrir,agregar y eliminar Colaboradores
            (respuesta, errorCode
        */
    }
    
    public Mensaje(boolean co)
    {
        confirmacion = co;
        /*
            confirmaciones de registro, guardar, agregar y eliminar Colaboradores y negacion de iniciarSesion
        */
    }
    
    public Mensaje(char oc, String t)
    {
        opCode = oc; userOrPermit = t;
        /*
            peticionDeArchivosDisponibles(opCode, quien solicita)
            
        */
    }
    
    public Mensaje(String [] sA)
    {
        otherData = sA;
        /*
            enviarArchivosDisponibles(archivosDisponibles)
            enviarUsuarios(usuarios)
        */
    }
    
    public Mensaje(String s1)
    {
        this.passOrArchive = s1;
        /*
            enviarQueArchivoAbrir(archivo)
        */
    }
    
    public Mensaje(String [] sA, String s)
    {
        otherData = sA;
        userOrPermit = s;
        /*
            envairListaUsuariosAAgregar(usuariosPorAgregar, permiso)
        */
    }
    
    /*public Mensaje(boolean b, String s1, String s2, String [] sA)
    {
        confirmacion = b; userOrPermit = s1;  passOrArchive = s2;//el dueño otherData = sA;*/
        /*
            respuesta de abrirArchivo(confirmacion, permiso, dueno, [editores/observadores]
        */
    //}
    
    public Mensaje(char op)
    {
        opCode = op;
        /*
            notificarErrorServer(op)
        */
    }
    /*public Mensaje(boolean b, String r, String s)
    {confirmacion = b; remitente = r; sData = s;}
    
    public Mensaje(int oc, String r, Object o)//Registro, el objeto son los datos extras, tambien se usa para obtener la lista de archivos
    {
        opCode = oc;
        remitente = r;
        multiusos = o;
    }
    
    
    
    public Mensaje(int oc, String r, String nom, boolean ft)
    {
        opCode = oc; remitente = r; sData = nom; confirmacion = ft;
    }
    
    public Mensaje(int oc, String r, String nom, Object o)
    {
        opCode = oc; remitente = r; sData = nom; multiusos = o;
    }*/
  
    public char getOpCode()
    {return opCode;}
    
    public String getUsuario()
    {return userOrPermit;}

    public String getPassword()
    {return passOrArchive;}    
    
    public String [] getOtherData()
    {return otherData;}
    
    public String getArchivo()
    {return passOrArchive;}
    
    public String[] getArchivosDisponibles()
    {return otherData;}
    
    public boolean getConfirmacion()
    {return confirmacion;}
    
    public String getPermiso()
    {return userOrPermit;}
    
    public String [] getColaborators()
    {return otherData;}
    
    public boolean isFirstTime()
    {return confirmacion;}
    
    public String [] getUsers()
    {return otherData;}
    
    public String getOwner()
    {return userOrPermit;}
    
    public int getTamFile()
    {return tamArchivo;}
    /*public byte [] getFileData()
    {return archivo;}*/
    
}
