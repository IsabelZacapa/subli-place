/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subliplace.accesoadatos;
/**
 *
 * @author alexi
 */

import java.sql.*;

public class ComunDB {
   
    class TipoDB{
        static final int SQLSERVER = 1;
        static final int MYSQL = 2;
    }
    
    //Propiedades del SGBD que estamos utlilizando
    
    static int TIPODB = TipoDB.SQLSERVER;
    static String conectionURL = "jdbc:sqlserver://localhost:1433;"
            + "database=Publicaciones;"
            + "integratedSecurity=true";
    
    //Metodo utilizado para obtener la conexion abierta a un gestor de base de datos
    
    public static Connection obtenerConexion() throws SQLException 
    {
        DriverManager.registerDriver(new 
        com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Connection connect = DriverManager.getConnection(conectionURL);
        return connect;
    }
    
    //MEtodo createStatement lo utiliza para devolver un statement el cual permite ejecutar una consulta de INSERT, UPDATE, SELECT.
   
    public static Statement createStatement(Connection pCon) throws SQLException 
    {
        Statement statement = pCon.createStatement();
        return statement;
    }
    
    public static PreparedStatement createPrepared(Connection pCon, String pSql) throws SQLException 
    {
        PreparedStatement statement = pCon.prepareStatement(pSql);
        return statement;
    }
    
    public static ResultSet obtenerResultSet(Statement pStatement, String pSql) throws SQLException 
    {
        ResultSet resultSet = pStatement.executeQuery(pSql);
        return resultSet;
    }
    
    public static ResultSet obtenerResultSet(PreparedStatement pPreparedStatement) throws SQLException 
    {
        ResultSet resultSet = pPreparedStatement.executeQuery();
        return resultSet;
    }
    
        class UtilQuery{
        private String SQL;
        private PreparedStatement statement;
        private int numWhere;

        public UtilQuery() {
        }

        public UtilQuery(String SQL, PreparedStatement statement, int numWhere) {
            this.SQL = SQL;
            this.statement = statement;
            this.numWhere = numWhere;
        }

        public String getSQL() {
            return SQL;
        }

        public void setSQL(String SQL) {
            this.SQL = SQL;
        }

        public PreparedStatement getStatement() {
            return statement;
        }

        public void setStatement(PreparedStatement statement) {
            this.statement = statement;
        }

        public int getNumWhere() {
            return numWhere;
        }

        public void setNumWhere(int numWhere) {
            this.numWhere = numWhere;
        }
        
        public void AgregarWhereAnd(String pSql)
        {
            if(this.SQL != null)
            {
                if(this.numWhere == 0)
                {
                    this.SQL += " Where ";
                }
                else
                {
                    this.SQL += " And ";
                }
                this.SQL += pSql;
            }
            this.numWhere++;
        }
    }
    
}
