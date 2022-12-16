package ingest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import ingest.helper.ParseXML;

public class AnalysisConnector {
    private static String csvHeaders = "";

    public static void createCSVHeaders(String csvString){
        csvHeaders = csvString;
    }

    private static void prepareForAnalysis(Day today){
        DatabaseConnection.fillSQLStrings();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ParseXML.parsePaths("sqlite"));
            

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Day");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            while(resultSet.next()){
                String buildRow = "";
                // Database is not indexed at 0
                for(int i = 1; i <= rsmd.getColumnCount(); i++){
                    if(i < rsmd.getColumnCount()){
                        buildRow += resultSet.getObject(i) + ",";
                    } else {
                        buildRow += resultSet.getObject(i);
                    }
                }
                File pythonFile = new File(ParseXML.parsePaths("python"));
                FileWriter writeToPython = new FileWriter(ParseXML.parsePaths("python"));
                if(pythonFile.createNewFile()){
                    System.out.println("[PYTHON]: raw data created");
                    writeToPython.write(csvHeaders);
                    writeToPython.write(buildRow);
                    writeToPython.close();

                } else {
                    System.out.println("[PYTHON*TODO]: raw data file already exists - need to append data");
                    writeToPython.append(buildRow);
                    writeToPython.close();
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch(IOException e){
            System.err.println(e.getMessage());
        } catch(Exception e){
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null){
                connection.close();
                } 
            } catch (SQLException e) {
            System.err.println(e.getMessage());
            }
        }
    }
}
