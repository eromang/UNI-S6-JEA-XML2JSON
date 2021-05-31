package lu.uni.jea.exercises.xml2json.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import org.apache.log4j.Logger;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "RowLabel")
public class MonthLabel {

    private static final Logger logger = Logger.getLogger ( MonthLabel.class );

    @JacksonXmlProperty(isAttribute = true)
    private String id;

    @JacksonXmlText
    private String monthLabelValue;

    // Getters and setters

    public String getId() {
        return id;
    }

    public String getMonthLabelValue() {
        return monthLabelValue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMonthLabelValue(String monthLabelValue) {
        this.monthLabelValue = monthLabelValue;
    }
}
