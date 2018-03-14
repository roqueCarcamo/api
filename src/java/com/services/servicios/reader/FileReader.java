package com.services.servicios.reader;


import com.services.model.dataBase.ConfiguracionDataBase;
import com.services.servicios.listener.ContextoAplicacion;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @descripcion: Clase que permite leer el archivo llamado "config.xml" donde se encuentran las configuraciones de la base de dato.
* @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 12-04-2017
*/
public class FileReader implements Serializable {

    private static ConfiguracionDataBase configuracionDataBase; //Clase para obtener los valores de configuración de la base de datos.
    private static final String filename = "config.xml"; //Nombre del archivo donde se encuentran las configuraciones de las bases de datos.

    /**
     * Método constructor de la clase.
     *
     */
    public FileReader() {
        configuracionDataBase = null;
    }

    /**
     * Método para iniciar la ruta donde se encuentra el arhivo llamado "config.xml" para leer su contenido.
     *
     * @return Document document.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static Document read() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        File file = new File(ContextoAplicacion.getInstance().getRutaContextoAplicacion() + "WEB-INF/" + filename);
        Document document = builder.parse(file);
        return document;
    }

    /**
     * Método para leer el contenido del archivo llamado "config.xml".
     *
     * @param Node node
     * @param String userName
     */
    private static void readFileContent(Node node, String userName) {
        if (node != null) {
            String nodeName = node.getNodeName();
            if (nodeName.equals("Connection")) {
                ConfiguracionDataBase tmp = buildConnection(node);
                if (tmp.getNombreUsuario().compareToIgnoreCase(userName) == 0) {
                    configuracionDataBase = tmp;
                }

            } else {
                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        FileReader.readFileContent(child, userName);
                    }
                }
            }
        }
    }

    /**
     * Método para obtener las configuraciones de la base de datos.
     *
     * @param Node node
     * @return ConfiguracionDataBase configuracionDataBase
     */
    private static ConfiguracionDataBase buildConnection(Node node) {
        String nombre = "", ip = "", pass = "", database = "", port = "";
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            String nodeName = child.getNodeName();
            String nodeValue = child.getNodeValue();
            if (child.getChildNodes().getLength() > 0) {
                nodeValue = child.getChildNodes().item(0).getNodeValue();
            }
            if (nodeName.compareToIgnoreCase("username") == 0) {
                nombre = nodeValue;
            } else if (nodeName.compareToIgnoreCase("ip") == 0) {
                ip = nodeValue;
            } else if (nodeName.compareToIgnoreCase("pass") == 0) {
                pass = nodeValue;
            } else if (nodeName.compareToIgnoreCase("database") == 0) {
                database = nodeValue;
            } else if (nodeName.compareToIgnoreCase("port") == 0) {
                port = nodeValue;
            }
        }
        return new ConfiguracionDataBase(nombre, pass, ip, database, port);
    }

    /**
     * Método para obtener las configuraciones de la base de datos a partir del nombre del usuario.
     *
     * @param String username
     * @return ConfiguracionDataBase configuracionDataBase
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static ConfiguracionDataBase getConfigurationFromUserName(String username) throws ParserConfigurationException, SAXException, IOException {
        configuracionDataBase = null;
        Document document = read();
        readFileContent(document, username);
        return configuracionDataBase;
    }

}
