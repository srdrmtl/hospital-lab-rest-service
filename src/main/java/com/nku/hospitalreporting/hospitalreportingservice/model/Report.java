package com.nku.hospitalreporting.hospitalreportingservice.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.Type;


/**
 * Rapor Model Sınıfı
 * @version 0.0.1
 * 
 * FIXME: Değişken isimleri daha düzgün olabilir mi? Bilmiyorum ya acayip şeyler
 * olduğu için emin olamadım böyle de kalsa olur.
 * 
 * @author serdar
 */
@Entity
@Table(name = "rapor")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long raporId;

    @Basic(optional = true)
    @Column(name = "dosya_no")
    private String dosyaNo;
    @Basic(optional = true)
    @Column(name = "myloblast")
    private int myloblast;
    @Basic(optional = true)
    @Column(name = "promyelosit")
    private int promyelosit;
    @Basic(optional = true)
    @Column(name = "myelosit")
    private int myelosit;
    @Basic(optional = true)
    @Column(name = "metamyelosit")
    private int metamyelosit;
    @Basic(optional = true)
    @Column(name = "comak")
    private int comak;
    @Basic(optional = true)
    @Column(name = "parcali")
    private int parcali;
    @Basic(optional = true)
    @Column(name = "bazofilik_seri")
    private int bazofilikSeri;
    @Basic(optional = true)
    @Column(name = "eozinofilik_seri")
    private int eozinofilikSeri;
    @Basic(optional = true)
    @Column(name = "lenfosit")
    private int lenfosit;
    @Basic(optional = true)
    @Column(name = "promonosit")
    private int promonosit;
    @Basic(optional = true)
    @Column(name = "monosit")
    private int monosit;
    @Basic(optional = true)
    @Column(name = "plazma_hucresi")
    private int plazmaHucresi;
    @Basic(optional = true)
    @Column(name = "proeritroblast")
    private int proeritroblast;
    @Basic(optional = true)
    @Column(name = "bazofilik_erit")
    private int bazofilikErit;
    @Basic(optional = true)
    @Column(name = "polikromalofilik_erit")
    private int polikromalofilikErit;
    @Basic(optional = true)
    @Column(name = "ortokromantofilik_erit")
    private int ortokromantofilikErit;
    @Basic(optional = true)
    @Column(name = "megakaryositler")
    private String megakaryositler;
    @Basic(optional = true)
    @Column(name = "sellulerite")
    private String sellulerite;
    @Basic(optional = true)
    @Column(name = "tani")
    private String tani;
    @Basic(optional = true)
    @Column(name = "rapor_eden")
    private String raporEden;
    @Lob
    @Column(name = "rapor")
    @Type(type = "text")
    private String rapor;
    @Basic(optional = true)
    @Column(name = "tarih")
    private String tarih;
    @Column(name = "resim1")
    private String resim1;
    @Column(name = "resim2")
    private String resim2;
    @Column(name = "resim3")
    private String resim3;

    public Report() {
    }

    public Report(Long raporId) {
        this.raporId = raporId;
    }

    public Report(Long raporId, String dosyaNo, int myloblast, int promyelosit, int myelosit, int metamyelosit, int comak, int parcali, int bazofilikSeri, int eozinofilikSeri, int lenfosit, int promonosit, int monosit, int plazmaHucresi, int proeritroblast, int bazofilikErit, int polikromalofilikErit, int ortokromantofilikErit, String megakaryositler, String sellulerite, String tani, String raporEden, String tarih) {
        this.raporId = raporId;
        this.dosyaNo = dosyaNo;
        this.myloblast = myloblast;
        this.promyelosit = promyelosit;
        this.myelosit = myelosit;
        this.metamyelosit = metamyelosit;
        this.comak = comak;
        this.parcali = parcali;
        this.bazofilikSeri = bazofilikSeri;
        this.eozinofilikSeri = eozinofilikSeri;
        this.lenfosit = lenfosit;
        this.promonosit = promonosit;
        this.monosit = monosit;
        this.plazmaHucresi = plazmaHucresi;
        this.proeritroblast = proeritroblast;
        this.bazofilikErit = bazofilikErit;
        this.polikromalofilikErit = polikromalofilikErit;
        this.ortokromantofilikErit = ortokromantofilikErit;
        this.megakaryositler = megakaryositler;
        this.sellulerite = sellulerite;
        this.tani = tani;
        this.raporEden = raporEden;
        this.tarih = tarih;
    }

    public Long getRaporId() {
        return raporId;
    }

    public void setRaporId(Long raporId) {
        this.raporId = raporId;
    }

    public String getDosyaNo() {
        return dosyaNo;
    }

    public void setDosyaNo(String dosyaNo) {
        this.dosyaNo = dosyaNo;
    }

    public int getMyloblast() {
        return myloblast;
    }

    public void setMyloblast(int myloblast) {
        this.myloblast = myloblast;
    }

    public int getPromyelosit() {
        return promyelosit;
    }

    public void setPromyelosit(int promyelosit) {
        this.promyelosit = promyelosit;
    }

    public int getMyelosit() {
        return myelosit;
    }

    public void setMyelosit(int myelosit) {
        this.myelosit = myelosit;
    }

    public int getMetamyelosit() {
        return metamyelosit;
    }

    public void setMetamyelosit(int metamyelosit) {
        this.metamyelosit = metamyelosit;
    }

    public int getComak() {
        return comak;
    }

    public void setComak(int comak) {
        this.comak = comak;
    }

    public int getParcali() {
        return parcali;
    }

    public void setParcali(int parcali) {
        this.parcali = parcali;
    }

    public int getBazofilikSeri() {
        return bazofilikSeri;
    }

    public void setBazofilikSeri(int bazofilikSeri) {
        this.bazofilikSeri = bazofilikSeri;
    }

    public int getEozinofilikSeri() {
        return eozinofilikSeri;
    }

    public void setEozinofilikSeri(int eozinofilikSeri) {
        this.eozinofilikSeri = eozinofilikSeri;
    }

    public int getLenfosit() {
        return lenfosit;
    }

    public void setLenfosit(int lenfosit) {
        this.lenfosit = lenfosit;
    }

    public int getPromonosit() {
        return promonosit;
    }

    public void setPromonosit(int promonosit) {
        this.promonosit = promonosit;
    }

    public int getMonosit() {
        return monosit;
    }

    public void setMonosit(int monosit) {
        this.monosit = monosit;
    }

    public int getPlazmaHucresi() {
        return plazmaHucresi;
    }

    public void setPlazmaHucresi(int plazmaHucresi) {
        this.plazmaHucresi = plazmaHucresi;
    }

    public int getProeritroblast() {
        return proeritroblast;
    }

    public void setProeritroblast(int proeritroblast) {
        this.proeritroblast = proeritroblast;
    }

    public int getBazofilikErit() {
        return bazofilikErit;
    }

    public void setBazofilikErit(int bazofilikErit) {
        this.bazofilikErit = bazofilikErit;
    }

    public int getPolikromalofilikErit() {
        return polikromalofilikErit;
    }

    public void setPolikromalofilikErit(int polikromalofilikErit) {
        this.polikromalofilikErit = polikromalofilikErit;
    }

    public int getOrtokromantofilikErit() {
        return ortokromantofilikErit;
    }

    public void setOrtokromantofilikErit(int ortokromantofilikErit) {
        this.ortokromantofilikErit = ortokromantofilikErit;
    }

    public String getMegakaryositler() {
        return megakaryositler;
    }

    public void setMegakaryositler(String megakaryositler) {
        this.megakaryositler = megakaryositler;
    }

    public String getSellulerite() {
        return sellulerite;
    }

    public void setSellulerite(String sellulerite) {
        this.sellulerite = sellulerite;
    }

    public String getTani() {
        return tani;
    }

    public void setTani(String tani) {
        this.tani = tani;
    }

    public String getRaporEden() {
        return raporEden;
    }

    public void setRaporEden(String raporEden) {
        this.raporEden = raporEden;
    }

    public String getRapor() {
        return rapor;
    }

    public void setRapor(String rapor) {
        this.rapor = rapor;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getResim1() {
        return resim1;
    }

    public void setResim1(String resim1) {
        this.resim1 = resim1;
    }

    public String getResim2() {
        return resim2;
    }

    public void setResim2(String resim2) {
        this.resim2 = resim2;
    }

    public String getResim3() {
        return resim3;
    }

    public void setResim3(String resim3) {
        this.resim3 = resim3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raporId != null ? raporId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.raporId == null && other.raporId != null) || (this.raporId != null && !this.raporId.equals(other.raporId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.spring.rest.model.Report[ raporId=" + raporId + " ]";
    }

}
