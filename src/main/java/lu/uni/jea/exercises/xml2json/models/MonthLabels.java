package lu.uni.jea.exercises.xml2json.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.log4j.Logger;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 4 - XML2JSON
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "RowLabels")
public class MonthLabels {

    private static final Logger logger = Logger.getLogger ( MonthLabels.class );

    @JacksonXmlProperty(localName = "RowLabel")
    @JsonProperty("month_label")
    private MonthLabel monthLabel;

    // Empty constructor

    public MonthLabels() {}

    // Constructor

    public MonthLabels(MonthLabel monthLabel) {
        this.setMonthLabel(monthLabel);
    }

    // Getters and Setters

    public MonthLabel getMonthLabel() {
        return monthLabel;
    }

    public void setMonthLabel(MonthLabel monthLabel) {
        this.monthLabel = monthLabel;
    }
}
