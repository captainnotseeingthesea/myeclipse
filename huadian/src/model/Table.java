package model;

import java.util.ArrayList;

public class Table<T> {
	private int count;
	private ArrayList<T> list;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<T> getList() {
		return list;
	}
	public void setList(ArrayList<T> list) {
		this.list = list;
	}
	
}
