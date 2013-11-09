
package com.ventplan.prinzipskizze.wsclient.wac202;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ventplan.prinzipskizze.wsclient.wac202 package. 
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

    private final static QName _PrinzipskizzeResponse_QNAME = new QName("http://service.ventplan.com/prinzipskizze", "prinzipskizzeResponse");
    private final static QName _Prinzipskizze_QNAME = new QName("http://service.ventplan.com/prinzipskizze", "prinzipskizze");
    private final static QName _PrinzipskizzeResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ventplan.prinzipskizze.wsclient.wac202
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Prinzipskizze }
     * 
     */
    public Prinzipskizze createPrinzipskizze() {
        return new Prinzipskizze();
    }

    /**
     * Create an instance of {@link PrinzipskizzeResponse }
     * 
     */
    public PrinzipskizzeResponse createPrinzipskizzeResponse() {
        return new PrinzipskizzeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrinzipskizzeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ventplan.com/prinzipskizze", name = "prinzipskizzeResponse")
    public JAXBElement<PrinzipskizzeResponse> createPrinzipskizzeResponse(PrinzipskizzeResponse value) {
        return new JAXBElement<PrinzipskizzeResponse>(_PrinzipskizzeResponse_QNAME, PrinzipskizzeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Prinzipskizze }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ventplan.com/prinzipskizze", name = "prinzipskizze")
    public JAXBElement<Prinzipskizze> createPrinzipskizze(Prinzipskizze value) {
        return new JAXBElement<Prinzipskizze>(_Prinzipskizze_QNAME, Prinzipskizze.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = PrinzipskizzeResponse.class)
    public JAXBElement<byte[]> createPrinzipskizzeResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_PrinzipskizzeResponseReturn_QNAME, byte[].class, PrinzipskizzeResponse.class, ((byte[]) value));
    }

}
