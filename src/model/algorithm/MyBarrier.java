package model.algorithm;

import model.Constants;

public class MyBarrier extends MyTile
{
	public MyBarrier() {
		this.point = Constants.POINT_BARRIER;
	}
	@Override
	public String toString() {
		return "*";
	}
}