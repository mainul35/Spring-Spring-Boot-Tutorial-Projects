package com.springtutorials.JdbcManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;

public class JdbcManagerService implements JdbcManager {

    Connection connection = null;
    

    public JdbcManagerService(JdbcTemplate jdbcTemplate) {
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public JdbcManagerService(Connection connection) {
        this.connection = connection;
    }
    

    @Override
    public String insertOrUpdateWithStoredProcedure(String storedProcSql, List<String> storedProcParams)
            throws QueryException {
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(storedProcSql);
            int i = 1;
            for (String value : storedProcParams) {
                callableStatement.setString(i, value);
                i++;
            }
            callableStatement.executeUpdate();
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    return "success";
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "failure";
                }
            }
        }
    }

    @Override
    public List<HashMap<String, Object>> getQueryDataWithStoredProcedure(String storedProcSql, List<String> storedProcParams)
            throws QueryException, Exception {
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        List<HashMap<String, Object>> list = null;
        try {
            callableStatement = connection.prepareCall(storedProcSql);
            int i = 1;
            for (String value : storedProcParams) {
                callableStatement.setString(i, value);
                i++;
            }

            rs = callableStatement.executeQuery();
            list = resultSetToArrayList(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;

    }

    @Override
    public String insertOrUpdate(String sql, List<String> params) throws QueryException {
        int count = 0;
        try {
            PreparedStatement pstmt = null;
            pstmt = connection.prepareStatement(sql);
            int i = 1;
            for (String value : params) {
                pstmt.setObject(i, value);
                i++;
            }
            count = pstmt.executeUpdate();
            connection.close();
            if(count>0){
                return "success";
            }else{
                return "Failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public List<HashMap<String, Object>> getQueryData(String sql, List<String> param)
            throws QueryException, Exception {
        PreparedStatement pstmt = null;
        pstmt = connection.prepareStatement(sql);
        int i = 1;
        for (String value : param) {
            pstmt.setObject(i++, value);
        }
        ResultSet rs = pstmt.executeQuery();
        
        List<HashMap<String, Object>> maps = resultSetToArrayList(rs);
        connection.close();
        return maps;
    }

    private List<HashMap<String, Object>> resultSetToArrayList(ResultSet rs) throws SQLException{
        ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        while (rs.next()){
           HashMap<String,Object> row = new HashMap<String,Object>(columns);
           for(int i=1; i<=columns; ++i){           
            row.put(md.getColumnName(i),rs.getObject(i));
           }
            list.add(row);
        }

       return list;
      }
}
