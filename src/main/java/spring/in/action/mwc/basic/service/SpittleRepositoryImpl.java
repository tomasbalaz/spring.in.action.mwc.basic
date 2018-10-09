package spring.in.action.mwc.basic.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spring.in.action.mwc.basic.model.Spittle;

public class SpittleRepositoryImpl implements SpittleRepository {
	
	private final int REPOSITARY_COUNT = 20;
	
	private final List<Spittle> spittles = new ArrayList<Spittle>();
	
	public SpittleRepositoryImpl() {
		createSpittleList(REPOSITARY_COUNT);
	}

	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private List<Spittle> createSpittleList(int count) {
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return spittles;
	}
}
