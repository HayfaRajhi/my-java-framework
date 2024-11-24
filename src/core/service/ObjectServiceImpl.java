package core.service;

import core.dao.IObjectDAO;

public class ObjectServiceImpl implements IObjectDAOImpl{
	IObjectDAO iObjectDAO;
	@Override
	public void serviceSave() {
		iObjectDAO.SAVE();
		
	}

}
