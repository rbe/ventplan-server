
package com.ventplan.prinzipskizze.wsclient.wac202;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für prinzipskizze complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="prinzipskizze">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aussenluft" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fortluft" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zentralgerat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="abluft1" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="abluft2" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="abluft3" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="zuluft1" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="zuluft2" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="zuluft3" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prinzipskizze", propOrder = {
    "aussenluft",
    "fortluft",
    "zentralgerat",
    "abluft1",
    "abluft2",
    "abluft3",
    "zuluft1",
    "zuluft2",
    "zuluft3"
})
public class Prinzipskizze {

    protected String aussenluft;
    protected String fortluft;
    protected String zentralgerat;
    protected List<String> abluft1;
    protected List<String> abluft2;
    protected List<String> abluft3;
    protected List<String> zuluft1;
    protected List<String> zuluft2;
    protected List<String> zuluft3;

    /**
     * Ruft den Wert der aussenluft-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAussenluft() {
        return aussenluft;
    }

    /**
     * Legt den Wert der aussenluft-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAussenluft(String value) {
        this.aussenluft = value;
    }

    /**
     * Ruft den Wert der fortluft-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFortluft() {
        return fortluft;
    }

    /**
     * Legt den Wert der fortluft-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFortluft(String value) {
        this.fortluft = value;
    }

    /**
     * Ruft den Wert der zentralgerat-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZentralgerat() {
        return zentralgerat;
    }

    /**
     * Legt den Wert der zentralgerat-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZentralgerat(String value) {
        this.zentralgerat = value;
    }

    /**
     * Gets the value of the abluft1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abluft1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbluft1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAbluft1() {
        if (abluft1 == null) {
            abluft1 = new ArrayList<String>();
        }
        return this.abluft1;
    }

    /**
     * Gets the value of the abluft2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abluft2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbluft2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAbluft2() {
        if (abluft2 == null) {
            abluft2 = new ArrayList<String>();
        }
        return this.abluft2;
    }

    /**
     * Gets the value of the abluft3 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abluft3 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbluft3().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAbluft3() {
        if (abluft3 == null) {
            abluft3 = new ArrayList<String>();
        }
        return this.abluft3;
    }

    /**
     * Gets the value of the zuluft1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zuluft1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZuluft1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getZuluft1() {
        if (zuluft1 == null) {
            zuluft1 = new ArrayList<String>();
        }
        return this.zuluft1;
    }

    /**
     * Gets the value of the zuluft2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zuluft2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZuluft2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getZuluft2() {
        if (zuluft2 == null) {
            zuluft2 = new ArrayList<String>();
        }
        return this.zuluft2;
    }

    /**
     * Gets the value of the zuluft3 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zuluft3 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZuluft3().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getZuluft3() {
        if (zuluft3 == null) {
            zuluft3 = new ArrayList<String>();
        }
        return this.zuluft3;
    }

}
