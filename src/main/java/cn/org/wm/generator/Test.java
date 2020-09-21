package cn.org.wm.generator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Test {

	public static void main(String[] args) {
		// getTableName();
		/*String path = "E:\\hop\\haierworkspace\\hrois\\hrois-biz\\src\\main\\resources\\mybatis\\mappers";
		Map<String,String> map=new HashMap<String, String>();
		//System.out.println(map);
		map.put("CdTechOrderOptionsMapper","CD_TECH_ORDER_OPTIONS");
		map.put("CdTechOrderItemMapper","CD_TECH_ORDER_ITEM");
		map.put("OperationLogMapper","OPERATION_LOG");
		String[] extens = { "xml" };
		for (Object xml : FileUtils.listFiles(new File(path), extens, true)) {
			try {
				File file=(File)xml;
				if(file.exists()){
				String tableName = map.get(file.getName().replaceAll(".xml", ""));
				if(tableName!=null){
				Pattern tableNamePattern = Pattern.compile("FROM\\s+"
						+ tableName + "\\s?");
				String content = FileUtils.readFileToString(file,
						"UTF-8");
				Matcher macher = tableNamePattern.matcher(content);
				String newContent = macher.replaceAll("FROM " + tableName
						+ " \\$\\{tableAlias\\} ");

				Pattern andNamePattern = Pattern
						.compile("AND\\s+([0-9a-zA-Z_]+)\\s?");
				Matcher macher2 = andNamePattern.matcher(newContent);
				StringBuffer sbuf = new StringBuffer();
				while (macher2.find()) {
					// System.out.println( macher2.group(1));
					macher2.appendReplacement(sbuf, "AND "
							+ " \\$\\{columAlias\\}" + macher2.group(1));
				}
				macher2.appendTail(sbuf);
				FileUtils.writeStringToFile(file, sbuf.toString(), "UTF-8");
				System.out.println(file.getName()+"is ok");
				//System.out.println(sbuf);
				// newContent=macher2.replaceAll("\\$\\{tableAlias\\}.");
				// System.out.println(newContent);
				// System.out.println(newContent);
				}else{
					//System.out.println(file.getName()+" is error");
				}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		System.out.println(getTableName());
	}

	public static Map<String, String> getTableName() {
		String basePath = "E:\\hop\\haierworkspace\\hrois\\hrois-service-client\\src\\main\\java\\com\\haier\\hrois";
		File floder = new File(basePath);
		Map<String, String> map = new HashMap<String, String>();
		// pack 包名
		for (File pack : floder.listFiles()) {
			File query = new File(pack, "domain");
			if (pack.getName().equals("order")) {
				for (File subFile : pack.listFiles()) {
					query = new File(subFile, "domain");
					parseJavaFile(map, query);
				}
			} else {
				parseJavaFile(map, query);
			}
		}
		return map;
		// System.out.println(map);
	}

	private static void parseJavaFile(Map<String, String> map, File query) {
		Pattern tableNamePattern = Pattern
				.compile("database\\s+table:\\s+(\\S*)");

		if (query.exists()) {
			for (File javaFile : query.listFiles()) {
				if (javaFile.getName().endsWith(".java")) {
					try {
						String content = FileUtils.readFileToString(javaFile,
								"UTF-8");
						Matcher matcher = tableNamePattern.matcher(content);
						if (matcher.find()) {
							// System.out.println(matcher.group(1)+"*****"+javaFile);
							map.put(javaFile.getName().replaceAll(".java", "")
									+ "Mapper", matcher.group(1).toUpperCase());
						} else {
							Pattern tableNamePattern2 = Pattern
									.compile("TABLE_ALIAS\\s?=\\s?\"(\\S*)\"");
							Matcher matcher2 = tableNamePattern2
									.matcher(content);
							if (matcher2.find()) {
								// System.out.println(matcher2.group(1)+"*****"+javaFile);
								map.put(javaFile.getName().replaceAll(".java",
										"")
										+ "Mapper", matcher2.group(1)
										.toUpperCase());
							} else {
								// System.out.println(javaFile);
							}
							// System.out.println(javaFile);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void addTableAlias() {

	}

}
