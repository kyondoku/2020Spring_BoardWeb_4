package com.koreait.pjt.vo;

public class BoardDomain extends BoardVO {
	private String nm;
	private int yn_like;
	private int eldx;
	private int sldx;
	private int page;
	private int record_cnt; // 페이지당 나오는 레코드 수(글 수)
	private String searchText;
	private String profile_img;
	private String like_cnt;
	private String cmt_cnt;
	private String searchType;
	
	
	
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(String like_cnt) {
		this.like_cnt = like_cnt;
	}

	public String getCmt_cnt() {
		return cmt_cnt;
	}

	public void setCmt_cnt(String cmt_cnt) {
		this.cmt_cnt = cmt_cnt;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getEldx() {
		return eldx;
	}

	public void setEldx(int eldx) {
		this.eldx = eldx;
	}

	public int getSldx() {
		return sldx;
	}

	public void setSldx(int sldx) {
		this.sldx = sldx;
	}

	public int getRecord_cnt() {
		return record_cnt;
	}

	public void setRecord_cnt(int record_cnt) {
		this.record_cnt = record_cnt;
	}

	public int getYn_like() {
		return yn_like;
	}

	public void setYn_like(int yn_like) {
		this.yn_like = yn_like;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
}
