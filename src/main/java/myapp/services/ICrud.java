package myapp.services;

import java.util.List;

public interface ICrud<T, K> {

	T add(T o);

	T update(T o);

	void delete(T o);

	List<T> selectAll();

	T selectOne(K id);
}
