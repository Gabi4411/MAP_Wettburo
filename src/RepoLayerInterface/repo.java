package RepoLayerInterface;

import java.util.List;


public interface repo<T> {

    void create(T obj);

    T get(Integer id);

    void update(T obj);

    void delete(Integer id);

    T find_by_ID(Integer id);

    List<T> getAll();
}