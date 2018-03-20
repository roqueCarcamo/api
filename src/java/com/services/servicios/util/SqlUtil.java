package com.services.servicios.util;

import com.services.model.dataBase.ConfiguracionDataBase;
import com.services.model.dataBase.Database;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @descripcion: Clase de utilidad para consultas sql.
 * @autor: (Rodolfo Cárcamo)
 * @fechaCreacion: 15-03-2018
 */
public class SqlUtil {

    /**
     * Método para obtener conexión a la base de dato.
     *
     * @param conf
     * @return Connection connection
     */
    public static Connection obtenerConexion(ConfiguracionDataBase conf) {
        Database database = new Database(conf.getIp(), conf.getDatabase(), conf.getNombreUsuario(), conf.getPassword(), conf.getPort());
        return database.getConexion();
    }

    /**
     * Método para obtener consulta con paginación.
     *
     * @param sql
     * @return
     */
    public static String consultaPaginada(String sql) {
        sql = sql.replaceAll("[\n\r\t]", " ");
        StringBuilder sqlPag = new StringBuilder();
        sqlPag.append("SELECT * FROM (SELECT a.*, rownum AS rnum FROM ( ");
        sqlPag.append(sql);
        sqlPag.append(" ) a WHERE rownum <= ? ) WHERE rnum >= ? ");
        return sqlPag.toString();
    }

    /**
     * Método para obtener secuencia de una tabla.
     *
     * @param conx
     * @param sql
     * @return Long aLong
     */
    public static Long obtenerSecuencia(Connection conx, String sql) {
        Long aLong = null;
        try {
            {
                PreparedStatement preparedStatement = conx.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    aLong = resultSet.getLong("NEXTVAL");
                }
            }
            return aLong;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aLong;
    }

    /**
     * Método para obtener una lista de objetos json.
     *
     * @param rs
     * @return List<JSONObject> resList
     */
    public static List<JSONObject> getFormatedJsonArrayResult(ResultSet rs) {
        List<JSONObject> resList = new ArrayList<>();
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCnt; i++) {
                columnNames.add(rsMeta.getColumnName(i).toLowerCase());
            }
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++) {
                    String key = columnNames.get(i - 1);
                    Object value = rs.getObject(i);
                    if (value == null) {
                        obj.put(key, JSONObject.NULL);
                    } else if (value instanceof Clob) {
                        String clobToString = clobToString((Clob) value);
                        obj.put(key, clobToString);
                    } else if (value instanceof Blob) {
                        String blobToString = blodToString((Blob) value);
                        obj.put(key, blobToString);
                    } else {
                        obj.put(key, value);
                    }
                }
                resList.add(obj);
            }
        } catch (SQLException | JSONException e) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return resList;
    }

    /**
     * Método para obtener una lista de objetos json con llaves minusculas.
     *
     * @param rs
     * @return List<JSONObject> resList
     */
    public static List<JSONObject> getFormatedJsonArrayResult2(ResultSet rs) {
        List<JSONObject> resList = new ArrayList<>();
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCnt; i++) {
                columnNames.add(rsMeta.getColumnName(i));
            }
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++) {
                    String key = columnNames.get(i - 1);
                    Object value = rs.getObject(i);
                    if (value == null) {
                        obj.put(key, JSONObject.NULL);
                    } else {
                        obj.put(key, value);
                    }
                }
                resList.add(obj);
            }
        } catch (SQLException | JSONException e) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return resList;
    }

    public static List<JSONObject> convertirJsonToListaJson(JSONObject json) throws JSONException {
        List<JSONObject> resList = new ArrayList<>();
        Iterator llave = json.keys();
        while (llave.hasNext()) {
            JSONObject valor = new JSONObject();
            String keys = llave.next().toString();
            String value = json.optString(keys);
            valor.put(keys, value);
            resList.add(valor);
        }
        return resList;
    }

    /**
     * Método para obtener objeto json.
     *
     * @param rs
     * @return JSONObject obj
     */
    public static JSONObject getFormatedJsonResult(ResultSet rs) {
        JSONObject obj = new JSONObject();
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCnt; i++) {
                columnNames.add(rsMeta.getColumnName(i).toLowerCase());
            }
            while (rs.next()) {
                obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++) {
                    String key = columnNames.get(i - 1);
                    Object value = rs.getObject(i);
                    if (value == null) {
                        obj.put(key, JSONObject.NULL);
                    } else if (value instanceof Clob) {
                        String clobToString = clobToString((Clob) value);
                        obj.put(key, clobToString);
                    } else if (value instanceof Blob) {
                        byte[] bytes = rs.getBytes(key);
                        String stringFoto = StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false));
                        obj.put(key, stringFoto);
                    } else {
                        obj.put(key, value);
                    }
                }
            }
        } catch (SQLException | JSONException e) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return obj;
    }

    /**
     * Método para obtener objeto json con llaves en minusculas.
     *
     * @param rs
     * @return JSONObject obj
     */
    public static JSONObject getFormatedJsonResult2(ResultSet rs) {
        JSONObject obj = new JSONObject();
        try {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int columnCnt = rsMeta.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCnt; i++) {
                columnNames.add(rsMeta.getColumnName(i));
            }
            while (rs.next()) {
                obj = new JSONObject();
                for (int i = 1; i <= columnCnt; i++) {
                    String key = columnNames.get(i - 1);
                    Object value = rs.getObject(i);
                    if (value == null) {
                        obj.put(key, JSONObject.NULL);
                    } else {
                        obj.put(key, value);
                    }
                }
            }
        } catch (SQLException | JSONException e) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return obj;
    }

    /**
     * Método para obtener lista de objetos.
     *
     * @param rs
     * @return List<Object[]> lista
     */
    public static List<Object[]> getListObjectResult(ResultSet rs) {
        List<Object[]> lista = new ArrayList<>();
        try {
            List<Object> rowData;
            ResultSetMetaData rsmd = rs.getMetaData();
            Object[] obje = new Object[rsmd.getColumnCount()];
            while (rs.next()) {
                rowData = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    rowData.add(rs.getObject(i));
                }
                for (int colIndex = 0; colIndex < rsmd.getColumnCount(); colIndex++) {
                    Object columnObject = rowData.get(colIndex);
                    obje[colIndex] = columnObject;
                }
                lista.add(obje);
                obje = new Object[rsmd.getColumnCount()];
            }
        } catch (SQLException e) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return lista;
    }

    /**
     * Método para obtener un objeto de arrays.
     *
     * @param rs
     * @return Object[] obje
     */
    public static Object[] getObjectResult(ResultSet rs) {
        Object[] obje = new Object[0];
        try {
            List<Object> rowData;
            ResultSetMetaData rsmd = rs.getMetaData();
            obje = new Object[rsmd.getColumnCount()];
            while (rs.next()) {
                rowData = new ArrayList<>();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    rowData.add(rs.getObject(i));
                }
                for (int colIndex = 0; colIndex < rsmd.getColumnCount(); colIndex++) {
                    Object columnObject = rowData.get(colIndex);
                    obje[colIndex] = columnObject;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return obje;
    }

    /**
     * Método para obtener una lista de objetos arrays.
     *
     * @param rs
     * @return List<Object[]> lista
     */
    public static List<Object[]> resultSetToArrayObject(ResultSet rs) {
        List<Object[]> lista = new ArrayList<>();
        try {
            rs.last();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            int numFils = rs.getRow();
            Object obj[][] = new Object[numFils][numCols];
            int j = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int i = 0; i < numCols; i++) {
                    obj[j][i] = rs.getObject(i + 1);
                }
                j++;
            }
            lista.addAll(Arrays.asList(obj));
        } catch (Exception ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * Método para convertir un objeto clob a string
     *
     * @param cl
     * @return strOut
     */
    public static String clobToString(Clob cl) {
        if (cl == null) {
            return "";
        }
        StringBuilder strOut = new StringBuilder();
        String aux;
        BufferedReader br;
        try {
            br = new BufferedReader(cl.getCharacterStream());
            while ((aux = br.readLine()) != null) {
                strOut.append(aux);
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return strOut.toString();
    }

    /**
     * Método para convertir un objeto blob a string.
     *
     * @param bl
     * @return String str
     */
    public static String blodToString(Blob bl) {
        if (bl == null) {
            return "";
        }
        String str = "";
        try {
            str = StringUtils.newStringUtf8(Base64.encodeBase64(bl.getBytes(1l, (int) bl.length()), false));
        } catch (SQLException ex) {
            Logger.getLogger(SqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
}
