package lu.uni.jea.exercises.xml2json.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.log4j.Logger;

import java.util.List;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 4 - XML2JSON
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "Row") // We consider Row as the root element
public class Months {

    private static final Logger logger = Logger.getLogger ( Months.class );

    // Define XML property for element RowLabels
    @JacksonXmlProperty(localName = "RowLabels")
    private MonthLabels monthLabels;

    @JacksonXmlElementWrapper(localName = "Cells")
    @JacksonXmlProperty(localName = "C")
    private List<MonthCell> monthCell;

    // Default constructor
    public Months() {}

    // Constructor
    public Months(MonthLabels monthLabels, List<MonthCell> monthCell) {
        this.setMonthLabels(monthLabels);
        this.setMonthCell(monthCell);
    }

    // Getters and Setters

    public MonthLabels getMonthLabels() {
        return monthLabels;
    }

    public void setMonthLabels(MonthLabels monthLabels) {
        this.monthLabels = monthLabels;
    }

    public List<MonthCell> getMonthCell() {
        return monthCell;
    }

    public void setMonthCell(List<MonthCell> monthCell) {
        this.monthCell = monthCell;
    }
}
