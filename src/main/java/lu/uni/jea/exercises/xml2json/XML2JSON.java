package lu.uni.jea.exercises.xml2json;

import com.fasterxml.jackson.core.JsonProcessingException;
import lu.uni.jea.exercises.xml2json.ejb.XML2JSONEJBI;
import lu.uni.jea.exercises.xml2json.models.RootElement;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private static final String SEARCH = "search";

    private RootElement deserializedData;
    private RootElement createdRootElement;
    private String returnFullJSON;
    private String returnFilteredJSON;

    private String searchedYears;
    private List<String> searchedYearsList;

    @EJB
    private XML2JSONEJBI xml2JSONEJBI;

    // Getters and setters

    public String getReturnFullJSON() throws JsonProcessingException {

        RootElement deserializedData = new RootElement();

        // Deserialize XML file
        deserializedData = xml2JSONEJBI.deserializeFromXML();

        // Debug
        //xml2JSONEJBI.debug(deserializedData);

        // Return corresponding Json file
        returnFullJSON = xml2JSONEJBI.returnJson(deserializedData);

        return returnFullJSON;
    }

    public String returnFilteredJSON() throws JsonProcessingException {

        searchedYearsList = new ArrayList<String>();

        this.setSearchedYears(searchedYears);

        String strComma[] = searchedYears.split(",");
        String strDash[] = searchedYears.split("-");

        // Chek which kind of separator is used
        logger.info("Comma separated : " + strComma.length);
        logger.info("Dash separated : " + strDash.length);

        if (strComma.length > 1) {
            logger.info("Comma separated");
            searchedYearsList = Arrays.asList(strComma);
        } else if (strDash.length > 1) {
            logger.info("Dash separated");
            int start = Integer.parseInt(strDash[0]);
            int end = Integer.parseInt(strDash[1]);

            int yearsDiff = end - start;

            for (int i = start; i <= end; i++ ) {
                logger.info("Year : " + i);
                searchedYearsList.add(String.valueOf(i));
            }
            //searchedYearsList = Arrays.asList(strDash);
        } else {
            logger.info("Not comma or dash separated");
            searchedYearsList.add(searchedYears);
        }

        RootElement deserializedData = new RootElement();

        // Deserialize XML file
        deserializedData = xml2JSONEJBI.deserializeFromXML();

        // Debug
        //xml2JSONEJBI.debug(deserializedData);

        // Create requested RootElement
        createdRootElement = xml2JSONEJBI.createRootElement(deserializedData, searchedYearsList);

        // Debug
        //xml2JSONEJBI.debug(createdRootElement);

        // Return corresponding Json file
        returnFilteredJSON = xml2JSONEJBI.returnJson(createdRootElement);
        setReturnFilteredJSON(returnFilteredJSON);

        return SEARCH;
    }

    public String getSearchedYears() {
        return searchedYears;
    }

    public void setSearchedYears(String searchedYears) {
        this.searchedYears = searchedYears;
    }

    public String getReturnFilteredJSON() {
        return returnFilteredJSON;
    }

    public void setReturnFilteredJSON(String returnFilteredJSON) {
        this.returnFilteredJSON = returnFilteredJSON;
    }
}
