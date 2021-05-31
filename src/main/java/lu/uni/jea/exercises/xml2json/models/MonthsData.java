package lu.uni.jea.exercises.xml2json.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.log4j.Logger;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "Data") // We consider Data as the root element
public class MonthsData {

    private static final Logger logger = Logger.getLogger ( MonthsData.class );

    @JacksonXmlProperty(isAttribute = true)
    private int rows;

    @JacksonXmlElementWrapper(localName = "Rows")
    @JacksonXmlProperty(localName = "Row")
    private List<Months> months;

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
