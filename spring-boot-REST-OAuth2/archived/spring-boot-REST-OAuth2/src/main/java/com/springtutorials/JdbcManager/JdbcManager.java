package com.springtutorials.JdbcManager;

import java.util.HashMap;
import java.util.List;

public interface JdbcManager {
    
    public String insertOrUpdateWithStoredProcedure(String storedProcSql, List<String> storedProcParams) throws QueryException;
    public List<HashMap<String, Object>> getQueryDataWithStoredProcedure(String storedProcSql, List<String> storedProcParams) throws QueryException, Exception;
    public String insertOrUpdate(String sql, List<String> storedProcParams) throws QueryException;
    public List<HashMap<String, Object>> getQueryData(String sql, List<String> param)  throws QueryException, Exception;
}
