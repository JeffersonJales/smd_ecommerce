/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.IOException;
import java.util.Properties;

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
}
