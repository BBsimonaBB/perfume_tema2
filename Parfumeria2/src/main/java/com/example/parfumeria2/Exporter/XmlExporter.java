package com.example.parfumeria2.Exporter;
import com.example.parfumeria2.Model.Perfume;

import javafx.collections.ObservableList;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlExporter implements Exporter{
    @Override
    public void exportList(ObservableList<Perfume> perfumes) throws IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Perfume.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileWriter fileWriter = new FileWriter("data.xml");
            for(Perfume p: perfumes){
                marshaller.marshal(p, fileWriter);
            }
            fileWriter.close();
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }
}
