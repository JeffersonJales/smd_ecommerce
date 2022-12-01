/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import usuario.modelo.Usuario;

/**
 *
 * @author jeffe
 */
public final class Config {
    public static final String JDBC_DRIVER = getPropriedade("JDBC_DRIVER");
    public static final String JDBC_URL = getPropriedade("JDBC_URL");
    public static final String JDBC_USER = getPropriedade("JDBC_USER");
    public static final String JDBC_PASSWORD = getPropriedade("JDBC_PASSWORD");
    
    public static final String COOKIE_CARRINHOCOMPRA = getPropriedade("COOKIE_CARRINHOCOMPRA");

    /**
     * Construtor privado para não permitir instanciação
     */
    private Config() {}

    /**
     * Método utilizado para retornar os dados do arquivo de configuração
     *
     * @param chave
     * @return
     */
    private static String getPropriedade(String chave) {
        Properties properties = new Properties();
        try {
            properties.load(Config.class.getResourceAsStream("config.properties"));
            return properties.getProperty(chave);
        } catch (IOException ex) {
            return null;
        }
    }
    
    public static boolean isADM(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null) return false;
        
        Usuario usuario = (Usuario) session.getAttribute("cliente");
        if(usuario == null) return false;
        
        return usuario.isAdministrador();
    }
    
    public static void redirectNotAdm(HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException{
        req.setAttribute("mensagem", "Conteúdo exclusivo apenas para administradores");
        RequestDispatcher dispatcher = req.getRequestDispatcher(jsp);
        dispatcher.forward(req, res);
    }
    
    public static void redirectNotAdm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        redirectNotAdm(req, res, "index.jsp");
    }
}
