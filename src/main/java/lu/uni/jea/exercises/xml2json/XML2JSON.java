package lu.uni.jea.exercises.xml2json;

import lu.uni.jea.exercises.xml2json.ejb.XML2JSONEJBI;
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

    @EJB
    private XML2JSONEJBI xml2JSONEJBI;

}
