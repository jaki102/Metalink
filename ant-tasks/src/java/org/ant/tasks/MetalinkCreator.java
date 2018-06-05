package org.ant.tasks;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

public class MetalinkCreator extends Task{
    public String URL = "url.directory";
    public String FILE = "file.directory";
    private String url;
    private String file;
    private Vector<FileSet> filesets = new Vector<FileSet>();
    private MetalinkData metalink = new MetalinkData();

    @Override
    public void execute() throws BuildException {
        if (this.url == null)
            this.url = getProject().getProperty(URL);
        if (file == null)
            file = getProject().getProperty(FILE);

        for (Object obj : filesets) {
            if (obj instanceof FileSet)
				try {
					addFiles(((FileSet) obj).getDir());
				} catch (NoSuchAlgorithmException | IOException e) {
					e.printStackTrace();
				}
        }

        XMLCreator xmlCreator = new XMLCreator(metalink, new File(file));
        try {
        	xmlCreator.create();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new BuildException("Could not save file!");
        }
    }

    private void addFiles(File file) throws NoSuchAlgorithmException, IOException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
            	addMetalinkData(f);
            }
        }
    }

    private void addMetalinkData(File file) throws NoSuchAlgorithmException, IOException {
        String hashValue = MD5Util.createHash(file);
        Hash hash = new Hash("MD5", hashValue);
        FileData fileData = new FileData(file, hash);
        metalink.addToList(fileData);
    }
    
    public void addFileset(FileSet fileset) {
        filesets.add(fileset);
    }
    public void setUrl(String url) {
    	this.url = url;
    }
    public void setFile(String file) {
    	this.file = file;
    }
}
