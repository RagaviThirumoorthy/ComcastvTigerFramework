package com.vTiger.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtility {
	
	public String getDataFromJson(String key) throws IOException, ParseException {
		
		FileInputStream fis = new FileInputStream("./configAppdata/CommonData.json");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(fis);
		String value = node.get(key).asText();
		
		FileReader reader = new FileReader("./configAppdata/CommonData.json");
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(reader);
		JSONObject jobj = (JSONObject)obj;
		String data = jobj.get(key).toString();
		
		return value;			
		
	}

}
