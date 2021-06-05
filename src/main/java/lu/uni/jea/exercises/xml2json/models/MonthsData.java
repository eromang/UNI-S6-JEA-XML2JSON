package lu.uni.jea.exercises.xml2json.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 4 - XML2JSON
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "Data") // We consider Data as the root element
public class MonthsData {

    private static final Logger logger = Logger.getLogger ( MonthsData.class );

    // Define XML property for attribute rows
    @JacksonXmlProperty(isAttribute = true)
    @JsonProperty("total_months_number")
    private int rows;

    // Define wrapper to use for months collection types
    @JacksonXmlElementWrapper(localName = "Rows")
    @JacksonXmlProperty(localName = "Row")
    private List<Months> months;

    // Empty Constructor

    public MonthsData() {}

    // Constructor

    public MonthsData(int rows, List<Months> months) {
        this.setRows(rows);
        this.setMonths(months);
    }

    // Getters and setters

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Months> getMonths() {
        return months;
    }

    public void setMonths(List<Months> months) {
        this.months = months;
    }
}
