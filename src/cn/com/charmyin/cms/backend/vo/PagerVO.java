package cn.com.charmyin.cms.backend.vo;

import java.util.List;

public class PagerVO {
	private List datas;
	private int total;
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
