package com.a000webhostapp.heloiwan.tabunganku.models.login;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class PLogin{

	@SerializedName("is_logged_in")
	private boolean isLoggedIn;

	@SerializedName("data")
	private List<DataItem> data;

	public void setIsLoggedIn(boolean isLoggedIn){
		this.isLoggedIn = isLoggedIn;
	}

	public boolean isIsLoggedIn(){
		return isLoggedIn;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"PLogin{" + 
			"is_logged_in = '" + isLoggedIn + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}