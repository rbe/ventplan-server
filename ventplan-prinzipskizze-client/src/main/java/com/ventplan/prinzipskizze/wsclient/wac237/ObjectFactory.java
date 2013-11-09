
package com.ventplan.prinzipskizze.wsclient.wac237;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ventplan.prinzipskizze.wsclient.wac237 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PrinzipskizzeWAC237ResponseReturn_QNAME = new QName("", "return");
    private final static QName _PrinzipskizzeWAC237Response_QNAME = new QName("http://service.ventplan.com/prinzipskizze/wac237", "prinzipskizzeWAC237Response");
    private final static QName _PrinzipskizzeWAC237_QNAME = new QName("http://service.ventplan.com/prinzipskizze/wac237", "prinzipskizzeWAC237");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ventplan.prinzipskizze.wsclient.wac237
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrinzipskizzeWAC237 }
     * 
     */
    public PrinzipskizzeWAC237 createPrinzipskizzeWAC237() {
        return new PrinzipskizzeWAC237();
    }

    /**
     * Create an instance of {@link PrinzipskizzeWAC237Response }
     * 
     */
    public PrinzipskizzeWAC237Response createPrinzipskizzeWAC237Response() {
        return new PrinzipskizzeWAC237Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = PrinzipskizzeWAC237Response.class)
    public JAXBElement<byte[]> createPrinzipskizzeWAC237ResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_PrinzipskizzeWAC237ResponseReturn_QNAME, byte[].class, PrinzipskizzeWAC237Response.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrinzipskizzeWAC237Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ventplan.com/prinzipskizze/wac237", name = "prinzipskizzeWAC237Response")
    public JAXBElement<PrinzipskizzeWAC237Response> createPrinzipskizzeWAC237Response(PrinzipskizzeWAC237Response value) {
        return new JAXBElement<PrinzipskizzeWAC237Response>(_PrinzipskizzeWAC237Response_QNAME, PrinzipskizzeWAC237Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrinzipskizzeWAC237 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ventplan.com/prinzipskizze/wac237", name = "prinzipskizzeWAC237")
    public JAXBElement<PrinzipskizzeWAC237> createPrinzipskizzeWAC237(PrinzipskizzeWAC237 value) {
        return new JAXBElement<PrinzipskizzeWAC237>(_PrinzipskizzeWAC237_QNAME, PrinzipskizzeWAC237 .class, null, value);
    }

}
