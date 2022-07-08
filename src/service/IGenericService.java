package service;

import java.util.List;


public interface IGenericService<T> {

    void save(T t);

    void edit(int index, T t);

    boolean delete(String id, T t);

    List<T> findAll();

    T findById(String id);

    void displayById(String id);

    void displayALl();

}
