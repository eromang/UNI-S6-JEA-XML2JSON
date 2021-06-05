# UNI-S6-JEA-XML2JSON

UNI S6 JEA Exercise 4 XML to JSON

## Exercise 4 - XML and JSON

Write an EJB that can read statistical data in XML format given in this file about employment in Luxembourg taken from this Statec site.  
Note that the format of the XML file is a bit weird, and you might have to "investigate" on the format with the help of the provided website to understand how data is stored in that XML file.

Then write a view where the users can enter a set of years in the format "2012,2013,2015" or "2010-2018";  
the backend should then read the data from the XML file and output the data in JSON format as text on screen (you can choose the keys on your own).  
There should also be a link provided on the view where the same result data can be downloaded as file (in the same JSON format).

### Statec XML file analysis

- 9 cols id from L0 to L9
    - **L0**: 1. Resident borderers
    - **L1**: 2. Non-resident borderers
    - **L2**: 3. National wage-earners
    - **L3**: 4. Domestic wage-earners (3 + 2 - 1)
    - **L4**: 5. National self-employment
    - **L5**: 6. Domestic self-employment
    - **L6**: 7. National employment (3 + 5)
    - **L7**: 8. Domestic employment (4 + 6)
    - **L8**: 9. Number of unemployed
    - **L9**: 10. Active population (7 + 9)
    

- Each row is composed of a row id, e.g. ``id="L10"`` corresponding to a Month of a Year, e.g. :

```xml
<RowLabel member="0" id="L10" isParent="false" isChild="false" handle="0">April 2021</RowLabel>
```

- Each row cell is composed of headers composed by the row id and by the col id, e.g. :

```xml
<C headers="L10 L1 " f="210 667" v="210666.559220441"/>
```

- A full row start with is included into a Row tag and cells of the row into Cells tag, e.g. :

```xml
<Row>
    <RowLabels>
        <RowLabel member="0" id="L10" isParent="false" isChild="false" handle="0">April 2021</RowLabel>
    </RowLabels>
    <Cells>
        <C headers="L10 L0 " f="13 261" v="13261.4730171566"/>
        <C headers="L10 L1 " f="210 667" v="210666.559220441"/>
        <C headers="L10 L2 " f="258 244" v="258244.267456206"/>
        <C headers="L10 L3 " f="455 649" v="455649.35365949"/>
        <C headers="L10 L4 " f="23 047" v="23047.1422211075"/>
        <C headers="L10 L5 " f="28 873" v="28873.2877587315"/>
        <C headers="L10 L6 " f="281 291" v="281291.409677314"/>
        <C headers="L10 L7 " f="484 523" v="484522.641418222"/>
        <C headers="L10 L8 " f="18 249" v="18249"/>
        <C headers="L10 L9 " f="299 540" v="299540.409677314"/>
    </Cells>
</Row>
```

- Row cols values are delimited in ``f="13 261" v="13261.4730171566"`` where f represent a rounded version of v

### Models

- Months
  - monthID : ```id="L10"``` from RowLabel
  - monthName : ```April 2021``` from RowLabel
  
- MonthDetails
  - monthL0 : ```headers="L10 L0 "``` from Cells
  - monthL0Value : ```v="13261.4730171566"```
  
### 

- RootElement(String lang, MonthsData monthsData) 
- MonthsData(int rows, List<Months> months)
- Months(MonthLabels monthLabels, List<MonthCell> monthCell)
- MonthLabels(MonthLabel monthLabel)
- MonthLabel(String id, String monthLabelValue)
- MonthCell(String cellHeader, double cellValue)
