package lu.uni.jea.exercises.xml2json.ejb;

import org.apache.log4j.Logger;

import javax.ejb.Stateless;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 4 - XML2JSON
 *
 */

@Stateless(name = "XML2JSONEJB")
public class XML2JSONEJB implements XML2JSONEJBI {

    // Logging
    private static final Logger logger = Logger.getLogger ( XML2JSONEJB.class );

    //  The XML file
    private static final String fileName = "/res/statec.xml";

}
