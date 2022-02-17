package com.a000webhostapp.heloiwan.tabunganku.models.login;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataItem{

	@SerializedName("tamat")
	private String tamat;

	@SerializedName("nuptk")
	private String nuptk;

	@SerializedName("ijazah_terahir")
	private String ijazahTerahir;

	@SerializedName("level")
	private String level;

	@SerializedName("kode_jabatan")
	private String kodeJabatan;

	@SerializedName("id_session")
	private String idSession;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("password")
	private String password;

	@SerializedName("tempat")
	private String tempat;

	@SerializedName("nama")
	private String nama;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("id_pegawai")
	private String idPegawai;

	@SerializedName("username")
	private String username;

	@SerializedName("status")
	private String status;

	public void setTamat(String tamat){
		this.tamat = tamat;
	}

	public String getTamat(){
		return tamat;
	}

	public void setNuptk(String nuptk){
		this.nuptk = nuptk;
	}

	public String getNuptk(){
		return nuptk;
	}

	public void setIjazahTerahir(String ijazahTerahir){
		this.ijazahTerahir = ijazahTerahir;
	}

	public String getIjazahTerahir(){
		return ijazahTerahir;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setKodeJabatan(String kodeJabatan){
		this.kodeJabatan = kodeJabatan;
	}

	public String getKodeJabatan(){
		return kodeJabatan;
	}

	public void setIdSession(String idSession){
		this.idSession = idSession;
	}

	public String getIdSession(){
		return idSession;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setTempat(String tempat){
		this.tempat = tempat;
	}

	public String getTempat(){
		return tempat;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setJenis(String jenis){
		this.jenis = jenis;
	}

	public String getJenis(){
		return jenis;
	}

	public void setIdPegawai(String idPegawai){
		this.idPegawai = idPegawai;
	}

	public String getIdPegawai(){
		return idPegawai;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
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
			"DataItem{" + 
			"tamat = '" + tamat + '\'' + 
			",nuptk = '" + nuptk + '\'' + 
			",ijazah_terahir = '" + ijazahTerahir + '\'' + 
			",level = '" + level + '\'' + 
			",kode_jabatan = '" + kodeJabatan + '\'' + 
			",id_session = '" + idSession + '\'' + 
			",tgl_lahir = '" + tglLahir + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",password = '" + password + '\'' + 
			",tempat = '" + tempat + '\'' + 
			",nama = '" + nama + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",id_pegawai = '" + idPegawai + '\'' + 
			",username = '" + username + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}