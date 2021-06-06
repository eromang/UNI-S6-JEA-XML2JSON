package lu.uni.jea.exercises.xml2json.ejb;

import com.fasterxml.jackson.core.JsonProcessingException;
import lu.uni.jea.exercises.xml2json.models.RootElement;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 4 - XML2JSON
 *
 */

public interface XML2JSONEJBI {
    public RootElement deserializeFromXML();
    public String returnJson(RootElement deserializedData) throws JsonProcessingException;
    public void debug(RootElement deserializedData);
    public RootElement createRootElement(RootElement deserializedData);
}
