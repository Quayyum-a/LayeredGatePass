package data.repository;

public interface CrudRepository<T, ID> {
    T save(T entity);
    T findById(ID id);
    void deleteById(ID id);
    void delete(T entity);
    Iterable<T> findAll();
    long count();
}