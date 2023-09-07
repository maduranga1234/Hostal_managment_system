package lk.ijse.orm_coursework.dao;

import java.sql.SQLException;

public interface CrudDao<T,ID> extends SuperDao{



   ID save(T entity) throws SQLException;



    void update(T entity);

    void delete(T entity);



}
