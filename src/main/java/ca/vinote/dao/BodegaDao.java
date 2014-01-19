package ca.vinote.dao;

import ca.vinote.model.Bodega;

public class BodegaDao extends BaseDao<Bodega, Integer> implements IBodegaDao {

	public BodegaDao() {

	}

	public BodegaDao(boolean autoCommit) {
		super(autoCommit);
	}

	
}
