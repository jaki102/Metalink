package org.ant.tasks;

import java.io.File;
import javax.xml.bind.*;

public class XMLCreator {
	
    public File file;
    public MetalinkData metalink;

    public XMLCreator(MetalinkData metalink, File file) {
        this.file = file;
        this.metalink = metalink;
    }
    
    public void create() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(metalink.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(metalink, file);
    }
}
