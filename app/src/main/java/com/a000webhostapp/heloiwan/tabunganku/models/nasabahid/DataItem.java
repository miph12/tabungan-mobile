package com.a000webhostapp.heloiwan.tabunganku.models.nasabahid;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class DataItem{

	@SerializedName("id_nasabah")
	private String idNasabah;

	@SerializedName("nama_ibu")
	private String namaIbu;

	@SerializedName("level")
	private String level;

	@SerializedName("id_session")
	private String idSession;

	@SerializedName("no_rekening")
	private String noRekening;

	@SerializedName("tahun_masuk")
	private String tahunMasuk;

	@SerializedName("saldo")
	private String saldo;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("tempat_lahir")
	private String tempatLahir;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("nama_ayah")
	private String namaAyah;

	@SerializedName("id_kelas")
	private String idKelas;

	@SerializedName("tanggal_lahir")
	private String tanggalLahir;

	@SerializedName("username")
	private String username;

	@SerializedName("status")
	private String status;

	public void setIdNasabah(String idNasabah){
		this.idNasabah = idNasabah;
	}

	public String getIdNasabah(){
		return idNasabah;
	}

	public void setNamaIbu(String namaIbu){
		this.namaIbu = namaIbu;
	}

	public String getNamaIbu(){
		return namaIbu;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public String getLevel(){
		return level;
	}

	public void setIdSession(String idSession){
		this.idSession = idSession;
	}

	public String getIdSession(){
		return idSession;
	}

	public void setNoRekening(String noRekening){
		this.noRekening = noRekening;
	}

	public String getNoRekening(){
		return noRekening;
	}

	public void setTahunMasuk(String tahunMasuk){
		this.tahunMasuk = tahunMasuk;
	}

	public String getTahunMasuk(){
		return tahunMasuk;
	}

	public void setSaldo(String saldo){
		this.saldo = saldo;
	}

	public String getSaldo(){
		return saldo;
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

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setTempatLahir(String tempatLahir){
		this.tempatLahir = tempatLahir;
	}

	public String getTempatLahir(){
		return tempatLahir;
	}

	public void setJenis(String jenis){
		this.jenis = jenis;
	}

	public String getJenis(){
		return jenis;
	}

	public void setNamaAyah(String namaAyah){
		this.namaAyah = namaAyah;
	}

	public String getNamaAyah(){
		return namaAyah;
	}

	public void setIdKelas(String idKelas){
		this.idKelas = idKelas;
	}

	public String getIdKelas(){
		return idKelas;
	}

	public void setTanggalLahir(String tanggalLahir){
		this.tanggalLahir = tanggalLahir;
	}

	public String getTanggalLahir(){
		return tanggalLahir;
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
			"id_nasabah = '" + idNasabah + '\'' + 
			",nama_ibu = '" + namaIbu + '\'' + 
			",level = '" + level + '\'' + 
			",id_session = '" + idSession + '\'' + 
			",no_rekening = '" + noRekening + '\'' + 
			",tahun_masuk = '" + tahunMasuk + '\'' + 
			",saldo = '" + saldo + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",tempat_lahir = '" + tempatLahir + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",nama_ayah = '" + namaAyah + '\'' + 
			",id_kelas = '" + idKelas + '\'' + 
			",tanggal_lahir = '" + tanggalLahir + '\'' + 
			",username = '" + username + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}