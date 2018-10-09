package spring.in.action.mwc.basic.service;

import java.util.List;

import spring.in.action.mwc.basic.model.Spittle;

public interface SpittleRepository {
	
	List<Spittle> findSpittles(long max, int count);
}
