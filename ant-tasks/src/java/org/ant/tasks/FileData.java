package org.ant.tasks;

import java.io.File;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class FileData {
	
    @XmlAttribute(name = "name")
    public String name;
    
    public String url;
    public Hash hash;
    public long size;

    public FileData() {}

    public FileData(String name, String url, Hash hash, long size) {
        this.name = name;
        this.url = url;
        this.hash = hash;
        this.size = size;
    }

    public FileData(File file, Hash hash) {
        this.name = file.getName();
        this.url = file.getAbsolutePath();
        this.hash = hash;
        this.size = file.length();
    }
}