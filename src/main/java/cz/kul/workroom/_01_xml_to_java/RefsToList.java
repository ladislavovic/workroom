package cz.kul.workroom._01_xml_to_java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RefsToList {

	public static void main(String[] args) throws Exception {

		try (InputStream is = RefsToList.class.getClassLoader().getResourceAsStream("refs.txt")) {
			Reader reader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(reader);

			String regex = ".*bean=\"(.*)\".*";
			Pattern pattern = Pattern.compile(regex);

			while (true) {
				String line = br.readLine();
				if (StringUtils.isBlank(line))
					break;

				Matcher matcher = pattern.matcher(line);
				matcher.matches();

				String bean = matcher.group(1);

				System.out.println(String.format("converters.add(%s());", bean));

			}

		}

	}

}
