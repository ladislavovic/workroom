package cz.kul.workroom._01_xml_to_java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class MainXmlToJava {

    public static void main(String[] args) throws Exception {
    	
    	try (InputStream is = MainXmlToJava.class.getClassLoader().getResourceAsStream("beans.txt")) {
    		Reader reader = new InputStreamReader(is);
    		BufferedReader br = new BufferedReader(reader);

    		String regex = ".*(name|id)=\"(.*)\".*class=\".*\\.(.*)\".*";
    		Pattern pattern = Pattern.compile(regex);
    		
    		String line = null;
    		while (true) {
    			line = br.readLine();
    			if (StringUtils.isBlank(line)) break;
    			
    			Matcher matcher = pattern.matcher(line);
    			matcher.matches();
    			
    			String name = matcher.group(2);
    			String clazz = matcher.group(3);
    			String nameWithLowerCase = name.substring(0, 1).toLowerCase() + name.substring(1);
    			
    			System.out.println(String.format("@Bean(name = \"%s\")", name));
    			System.out.println(String.format("public %s %s() {", clazz, nameWithLowerCase));
    			System.out.println(String.format("%s bean = new %s();", clazz, clazz));
    			System.out.println("return bean;");
    			System.out.println("}");
    			System.out.println();
    			
    		}
    		
    	}
        
    }

}
