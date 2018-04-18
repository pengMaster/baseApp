package com.mtm.bean;

import java.io.Serializable;

/**
 * ***    ***     **********   ***    ***
 * ****   ****    **********   ****   ****
 * *** *  * ***       ***      *** *  * ***
 * ***  * *  ***      ***      ***  * *  ***
 * ***   **   ***     ***      ***   **   ***
 * ******************************************************************
 * Created by MTM on 2016/12/8.
 * Description: 用于Pop window显示列表数据bean
 * Android:minSdkVersion: API
 * Author:mzp
 * Version:V1.0
 * ******************************************************************
 */

public class PopListBean implements Serializable {
    private String name;
    private String rank;//当前 List 级别 默认 1级
    private String id;//二级 id
    private boolean select = false;//当前 item 是否被选中


    public PopListBean(String id, String name, String rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isSelect() {
		return select;
	}


	public void setSelect(boolean select) {
		this.select = select;
	}
    
}