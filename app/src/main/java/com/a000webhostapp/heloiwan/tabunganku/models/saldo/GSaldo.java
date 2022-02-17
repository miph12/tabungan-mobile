package com.a000webhostapp.heloiwan.tabunganku.models.saldo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GSaldo{

	@SerializedName("data")
	private int data;

	@SerializedName("status")
	private boolean status;

	public void setData(int data){
		this.data = data;
	}

	public int getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"GSaldo{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}