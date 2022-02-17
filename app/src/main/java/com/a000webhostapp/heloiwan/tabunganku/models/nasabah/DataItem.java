package com.a000webhostapp.heloiwan.tabunganku.models.nasabah;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataItem{

	@SerializedName("no_rekening")
	private String noRekening;

	public void setNoRekening(String noRekening){
		this.noRekening = noRekening;
	}

	public String getNoRekening(){
		return noRekening;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"no_rekening = '" + noRekening + '\'' + 
			"}";
		}
}