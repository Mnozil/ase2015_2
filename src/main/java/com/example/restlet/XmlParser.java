package com.example.restlet;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlParser<T> {
	private JAXBContext jc;
	private Marshaller ms;
	private T rawData;
	private String xmlData;
	public XmlParser(JAXBContext jc, T rawData) {
		this.rawData = rawData;
		this.jc = jc;
		try {
			ms = jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseToXml() {
		StringWriter sw = new StringWriter();
		try {
			ms.marshal(rawData, sw);
			xmlData = sw.toString();
			sw.flush();
			sw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getXmlData() {
		return xmlData;
	}	
}
