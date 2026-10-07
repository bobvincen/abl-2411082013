package com.habibi.order.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("produk_id")
    @JsonAlias({"produkId", "produk_id"})
    private Long produk_id;

    @JsonProperty("pelanggan_id")
    @JsonAlias({"pelangganId", "pelanggan_id"})
    private Long pelanggan_id;

    @JsonProperty("tgl_trans")
    @JsonAlias({"tglTrans", "tgl_trans"})
    private Date tgl_trans;

    private int jumlah;
    private double total;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getProduk_id() {
        return produk_id;
    }
    public void setProduk_id(Long produk_id) {
        this.produk_id = produk_id;
    }
    public Long getPelanggan_id() {
        return pelanggan_id;
    }
    public void setPelanggan_id(Long pelanggan_id) {
        this.pelanggan_id = pelanggan_id;
    }
    public Date getTgl_trans() {
        return tgl_trans;
    }
    public void setTgl_trans(Date tgl_trans) {
        this.tgl_trans = tgl_trans;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public Long getProdukId() {
        return produk_id;
    }
    public void setProdukId(Long produkId) {
        this.produk_id = produkId;
    }
    public Long getPelangganId() {
        return pelanggan_id;
    }
    public void setPelangganId(Long pelangganId) {
        this.pelanggan_id = pelangganId;
    }
    public Date getTglTrans() {
        return tgl_trans;
    }
    public void setTglTrans(Date tglTrans) {
        this.tgl_trans = tglTrans;
    }
}
