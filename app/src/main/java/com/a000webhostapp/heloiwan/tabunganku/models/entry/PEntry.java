package com.a000webhostapp.heloiwan.tabunganku.models.entry;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class PEntry{

	@SerializedName("messages")
	private String messages;

	@SerializedName("status")
	private String status;

	public void setMessages(String messages){
		this.messages = messages;
	}

	public String getMessages(){
		return messages;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"PEntry{" + 
			"messages = '" + messages + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}