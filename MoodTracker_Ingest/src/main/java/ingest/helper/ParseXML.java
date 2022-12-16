package ingest.helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseXML {
  
  public static Map<String,String> parsKeys(String apiService) throws Exception{
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(parsePaths("keys"));

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("api");
    Map<String,String> keysDict = new HashMap<String,String>();

    for(int i = 0; i < nList.getLength(); i++){
      Node nNode = nList.item(i);
      if(nNode.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) nNode;
        if(apiService.equals(eElement.getElementsByTagName("service").item(0).getTextContent())){
            //Add API key to map
          keysDict.put("key", eElement.getElementsByTagName("key").item(0).getTextContent());
            //Add Secret key to map
          keysDict.put("secret", eElement.getElementsByTagName("secret").item(0).getTextContent());
        }
      }
    }
    return keysDict;
  }

  public static String parsePaths(String pathName) throws Exception{
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse("./src/main/resources/paths.xml");

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("path");
    for(int i = 0; i < nList.getLength(); i++){
      Node nNode = nList.item(i);
      if(nNode.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) nNode;
        if(pathName.equals(eElement.getElementsByTagName("name").item(0).getTextContent())){
          return eElement.getElementsByTagName("string").item(0).getTextContent();
        }
        
      }
    }
    return "[ERROR]: Path name does not exist";
  }

  public static LinkedList<String> parseQuestions(String questionType) throws Exception{
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(parsePaths("questions"));

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("question");
    LinkedList<String> questionList = new LinkedList<>();
    for(int i = 0; i < nList.getLength(); i++){
      Node nNode = nList.item(i);
      if(nNode.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) nNode;
        if(questionType.equals(eElement.getElementsByTagName("type").item(0).getTextContent())){
          questionList.add(eElement.getElementsByTagName("text").item(0).getTextContent());
        }
      }
    }
    return questionList;
  }

  public static LinkedList<String> parseQuestionHeaders(String questionType) throws Exception{
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(parsePaths("questions"));

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("question");
    LinkedList<String> questionList = new LinkedList<>();
    for(int i = 0; i < nList.getLength(); i++){
      Node nNode = nList.item(i);
      if(nNode.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) nNode;
        if(questionType.equals(eElement.getElementsByTagName("type").item(0).getTextContent())){
          questionList.add(eElement.getElementsByTagName("header").item(0).getTextContent());
        }
      }
    }
    return questionList;
  }

  public static String parseUser() throws Exception{
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(parsePaths("users"));

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("weatherinfo");
    for(int i = 0; i < nList.getLength(); i++){
      Node nNode = nList.item(i);
      if(nNode.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) nNode;
        System.out.println(eElement.getElementsByTagName("location").item(0).getTextContent());
        System.out.println(eElement.getElementsByTagName("lat").item(0).getTextContent());
        System.out.println(eElement.getElementsByTagName("long").item(0).getTextContent());
      }
    }
    return "";
  }

  public static void main(String[] args){
    try{
      for(String item: parseQuestions("bool")){
        System.out.println(item);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}
