package lu.uni.jea.exercises.xml2json.ejb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lu.uni.jea.exercises.xml2json.models.*;
import org.apache.log4j.Logger;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 4 - XML2JSON
 *
 */

@Stateful(name = "XML2JSONEJB")
public class XML2JSONEJB implements XML2JSONEJBI {

    // Logging
    private static final Logger logger = Logger.getLogger ( XML2JSONEJB.class );

    //  The XML file
    private static final String xmlFileName = "/res/statec.xml";
    private String xmlFileNameInput;
    private RootElement deserializedData;
    private RootElement createdRootElement;
    private String json;

    private Months months;
    private int monthListSize;

    /**
     * Read and deserialize XML file
     * Return RootElement object deserializedData
     */

    public RootElement deserializeFromXML() {

        // Initialize
        deserializedData = new RootElement();

        // Debug
        // logger.info("Read XML File : " + xmlFileName);

        try (InputStream in = getClass().getResourceAsStream(xmlFileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))){

            XmlMapper xmlMapper = new XmlMapper();

            // read file and put contents into the string
            xmlFileNameInput = bf.lines().collect(Collectors.joining());

            // deserialize from the XML into a RootElement object
            deserializedData = xmlMapper.readValue(xmlFileNameInput, RootElement.class);

            in.close();
            bf.close();

        } catch (Exception e) {
            //logger.info("File access error: " + xmlFileName);
            logger.info("Error message: " + e.getMessage());
        }
        return deserializedData;
    }

    public void debug(RootElement deserializedData) {

        logger.info("Deserialized data lang: " + deserializedData.getLang());
        logger.info("Nbr of rows: " + deserializedData.getMonthsData().getRows());
        logger.info("Nbr of months: " + deserializedData.getMonthsData().getMonths().size());

        monthListSize = deserializedData.getMonthsData().getMonths().size();

        // Iterate through the months

        int i = 0;

        while (i < monthListSize) {

            Months months = new Months(deserializedData.getMonthsData().getMonths().get(i).getMonthLabels(),
                deserializedData.getMonthsData().getMonths().get(i).getMonthCell());

            logger.info("Month ID: " + months.getMonthLabels().getMonthLabel().getId());
            logger.info("Month value: " + months.getMonthLabels().getMonthLabel().getMonthLabelValue());

            // Iterator through the Cells (C) of the month

            int nbrMonthCells = months.getMonthCell().size();

            logger.info("Month cells nbr : " + nbrMonthCells);

            int j = 0;

            while(j < nbrMonthCells) {
                logger.info("Month cell header " + months.getMonthCell().get(j).getCellHeader());
                logger.info("Month cell value " + months.getMonthCell().get(j).getCellValue());

                j++;
            }

            i++;
        }
    }

    public RootElement createRootElement(RootElement toCreatedRootElement, String searchedYear) {

        // For testing purpose
        //String searchedYear = "2018";

        List<Months> monthsListToAdd = new ArrayList<>();
        int nbrMatchingMonths = 0;

        MonthsData monthsDataToAdd = new MonthsData();

        // Iterate through the months

        monthListSize = toCreatedRootElement.getMonthsData().getMonths().size();
        //logger.info("Total month list size is : " + monthListSize);

        int i = 0;

        while (i < monthListSize) {

            Months months = new Months(toCreatedRootElement.getMonthsData().getMonths().get(i).getMonthLabels(),
                    toCreatedRootElement.getMonthsData().getMonths().get(i).getMonthCell());

            //logger.info("Process " + months.getMonthLabels().getMonthLabel().getMonthLabelValue());

            if (months.getMonthLabels().getMonthLabel().getMonthLabelValue().contains(searchedYear)) {

                nbrMatchingMonths++;
                //logger.info("nbrMatchingMonths : " + nbrMatchingMonths);

                String monthLabelID = months.getMonthLabels().getMonthLabel().getId();
                String monthLabelValue = months.getMonthLabels().getMonthLabel().getMonthLabelValue();

                MonthLabel monthLabelToAdd = new MonthLabel(monthLabelID,monthLabelValue);

                MonthLabels monthLabelsToAdd = new MonthLabels(monthLabelToAdd);

                // Iterate through the Cells (C) of the month

                int nbrMonthCells = months.getMonthCell().size();

                int j = 0;
                List<MonthCell> monthCellListToAdd = new ArrayList<>();

                while(j < nbrMonthCells) {

                    String monthCellHeader = months.getMonthCell().get(j).getCellHeader();
                    Double monthCellValue = months.getMonthCell().get(j).getCellValue();

                    MonthCell monthCellToAdd = new MonthCell(monthCellHeader, monthCellValue);

                    monthCellListToAdd.add(monthCellToAdd);

                    j++;
                }

                Months monthsToAdd = new Months(monthLabelsToAdd, monthCellListToAdd);
                monthsListToAdd.add(monthsToAdd);

            }

            i++;
        }

        monthsDataToAdd = new MonthsData(nbrMatchingMonths, monthsListToAdd);

        RootElement createdRootElement = new RootElement("en", monthsDataToAdd);

        return createdRootElement;
    }

    public String returnJson(RootElement deserializedData) throws JsonProcessingException {
        // Write JSON from XML
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        json = mapper.writeValueAsString(deserializedData);

        return json;
    }
}
