package lu.uni.jea.exercises.xml2json;

import com.fasterxml.jackson.core.JsonProcessingException;
import lu.uni.jea.exercises.xml2json.ejb.XML2JSONEJBI;
import lu.uni.jea.exercises.xml2json.models.RootElement;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 4 - XML2JSON
 *
 */

@Named("xml2json")
@SessionScoped
public class XML2JSON implements Serializable {

    private static final Logger logger = Logger.getLogger ( XML2JSON.class );

    private RootElement deserializedData;
    private RootElement createdRootElement;
    private String returnedJSON;

    @EJB
    private XML2JSONEJBI xml2JSONEJBI;

    // Getters and setters

    public String getReturnedJSON() throws JsonProcessingException {

        RootElement deserializedData = new RootElement();

        // Deserialize XML file
        deserializedData = xml2JSONEJBI.deserializeFromXML();

        // Debug
        //xml2JSONEJBI.debug(deserializedData);

        // Create requested RootElement
        createdRootElement = xml2JSONEJBI.createRootElement(deserializedData);

        // Debug
        //xml2JSONEJBI.debug(createdRootElement);

        // Return corresponding Json file
        returnedJSON = xml2JSONEJBI.returnJson(createdRootElement);

        return returnedJSON;
    }



}
