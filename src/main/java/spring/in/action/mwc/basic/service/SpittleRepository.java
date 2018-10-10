package spring.in.action.mwc.basic.service;

import java.util.List;

import spring.in.action.mwc.basic.model.Spittle;

public interface SpittleRepository {
	
	public List<Spittle> findSpittles(long max, int count);
	
	public Spittle findOne(long id);
	
	public Spittle save(Spittle spittle);
	
	public Spittle findByUsername(String userName);
}
