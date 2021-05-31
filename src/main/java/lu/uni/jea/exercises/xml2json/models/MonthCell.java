package lu.uni.jea.exercises.xml2json.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.log4j.Logger;

@JsonIgnoreProperties(ignoreUnknown = true)
//@JacksonXmlRootElement(localName = "C") // We consider C as the root element
public class MonthCell {

    private static final Logger logger = Logger.getLogger ( MonthCell.class );

    // Define XML property for attribute lang
    @JacksonXmlProperty(localName = "headers", isAttribute = true)
    private String cellHeader;

    @JacksonXmlProperty(localName = "v", isAttribute = true)
    private double cellValue;

    // Getters and Setters

    public String getCellHeader() {
        return cellHeader;
    }

    public void setCellHeader(String cellHeader) {
        this.cellHeader = cellHeader;
    }

    public double getCellValue() {
        return cellValue;
    }

    public void setCellValue(double cellValue) {
        this.cellValue = cellValue;
    }
}