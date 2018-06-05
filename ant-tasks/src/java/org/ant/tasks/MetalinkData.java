package org.ant.tasks;

import java.util.*;
import javax.xml.bind.annotation.*;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class MetalinkData {
    @XmlElement(name = "published")
    Date date;
    @XmlElement(name = "file")
    List<FileData> files;

    MetalinkData() {
        date = new Date();
        files = new ArrayList<>();
    }

    public void addToList(FileData data) {
        files.add(data);
    }
}