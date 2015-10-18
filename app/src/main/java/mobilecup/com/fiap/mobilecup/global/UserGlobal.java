package mobilecup.com.fiap.mobilecup.global;

import android.app.Application;

/**
 * Created by User on 19/09/2015.
 */
public class UserGlobal extends Application{

    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
