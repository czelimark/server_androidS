package Reposiory;

import java.util.List;

public interface IRepository<T,ID>
{
    T get(ID id);
    T add(T t);
    boolean remove(ID id);
    T update(T t);
    List<T> getAll();
}
