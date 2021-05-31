package lu.uni.jea.exercises.xml2json.ejb;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lu.uni.jea.exercises.xml2json.models.MonthCell;
import lu.uni.jea.exercises.xml2json.models.Months;
import lu.uni.jea.exercises.xml2json.models.RootElement;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

@Stateless(name = "XML2JSONEJB")
public class XML2JSONEJB implements XML2JSONEJBI {

    // Logging
    private static final Logger logger = Logger.getLogger ( XML2JSONEJB.class );

    //  The XML file
    private static final String xmlFileName = "/res/statec.xml";
    private String xmlFileNameInput;

    private Months months;
    private int monthListSize;

    /**
     * Read and deserialize XML file
     */

    public String deserializeFromXML() {

        logger.info("Read XML File : " + xmlFileName);

        try (InputStream in = getClass().getResourceAsStream(xmlFileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))){

            XmlMapper xmlMapper = new XmlMapper();

            // read file and put contents into the string
            xmlFileNameInput = bf.lines().collect(Collectors.joining());

            // deserialize from the XML into a Months object
            RootElement deserializedData = xmlMapper.readValue(xmlFileNameInput, RootElement.class);

            logger.info("Deserialized data lang: " + deserializedData.getLang());
            logger.info("Nbr of rows: " + deserializedData.getMonthsData().getRows());
            logger.info("Nbr of months: " + deserializedData.getMonthsData().getMonths().size());

            monthListSize = deserializedData.getMonthsData().getMonths().size();

            // Iterate through the months

            Iterator<Months> monthsIterator = deserializedData.getMonthsData().getMonths().iterator();
            int i = 0;

            while(monthsIterator.hasNext()) {

                i++;

                logger.info("--------" + i + "----------");

                Months months = new Months(monthsIterator.next().getMonthLabels(),
                        monthsIterator.next().getMonthCell());

                logger.info("Month ID: " + months.getMonthLabels().getMonthLabel().getId());
                logger.info("Month value: " + months.getMonthLabels().getMonthLabel().getMonthLabelValue());

                // Iterator through the Cells (C) of the month

                Iterator<MonthCell> monthCellIterator = months.getMonthCell().iterator();

                while (monthCellIterator.hasNext()) {
                    logger.info("Month cell header " + monthCellIterator.next().getCellHeader());
                    logger.info("Month cell value " + monthCellIterator.next().getCellValue());
                }

            }

            in.close();
            bf.close();

        } catch (Exception e) {
            //logger.info("File access error: " + xmlFileName);
            logger.info("Error message: " + e.getMessage());
        }

        return xmlFileNameInput;
    }


}
