package com.anand.bhat.utility;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import io.cucumber.java.it.Ma;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ExcelData {

    /**
     * @auther: anand
     * @created: 17-01-2022
     **/


    protected static Logger logger = LogManager.getLogger(ExcelData.class.getName());

    public static Map<String, Map<String, String>> extractExcelData() throws FilloException {

        String sheetName = System.getProperty("sheetName");
        String testCaseID = System.getProperty("testCaseID");

        System.setProperty("ROW", "1");
        System.setProperty("COLUMN", "1");
        Fillo fillo = new Fillo();
        Map<String, Map<String, String>> superMap = new HashMap<>();
        Map<String, String> excelMap = new HashMap<>();

        Connection connection = fillo.getConnection(System.getProperty("user.dir") + "/src/main/resources/TestData/TestData.xlsx");

        String query = "SELECT * FROM " + sheetName + " WHERE TestCaseID= '" + testCaseID + "'";
        logger.info("query executed is : {}", query);
        Recordset recordset = connection.executeQuery(query);

        while (recordset.next()) {
            ArrayList<String> collectionSet = recordset.getFieldNames();
            int iterator;
            int size = collectionSet.size();
            for (iterator = 0; iterator <= size - 1; iterator++) {
                String columnName = collectionSet.get(iterator);
                String columnValue = recordset.getField(columnName);
                excelMap.put(columnName, columnValue);
            }
            superMap.put("TestData", excelMap);


            ObjectMapper objectMapper = new ObjectMapper();

            try {
                objectMapper.writeValue(new File(testCaseID.concat(".json")), superMap);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }


        }
        recordset.close();
        connection.close();
        return superMap;
    }


    public static String getData(String key) {
        Map<String, String> excelMap = null;
        try {
            excelMap = extractExcelData().get("TestData");
        } catch (FilloException e) {
            e.printStackTrace();
        }
        assert excelMap != null;
        return excelMap.get(key);
    }

}
