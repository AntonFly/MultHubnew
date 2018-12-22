package service;

import exception.DBException;

import java.util.List;


abstract class AbstractService<E,K>  {

    abstract boolean create(E item) throws DBException;

    abstract boolean update(E item) throws DBException;

    abstract boolean delete(K id) throws DBException;

    abstract List<E> getAll() throws DBException;

    abstract E get(K id) throws DBException;

}