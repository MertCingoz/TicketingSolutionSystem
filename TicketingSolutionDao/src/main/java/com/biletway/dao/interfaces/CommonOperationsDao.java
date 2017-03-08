package com.biletway.dao.interfaces;

public interface CommonOperationsDao {
	boolean insert(Object entity);

	Object insertAndGetEntity(Object entity);

	boolean update(Object entity);

	Object updateAndGetEntity(Object entity);

	boolean delete(Object entity);

}
