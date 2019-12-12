package templatesTutorial;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class tutorial {
	public static void main(String[] args) throws FileNotFoundException {
		List<Person> list = new ArrayList<Person>();
		XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                    new FileInputStream("friends.xml")));
		list = (List<Person>) d.readObject();
		d.close();

		
	    // 初始化模板引擎
	    VelocityEngine ve = new VelocityEngine();
	    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	    ve.init();
	    // 获取模板文件
	    Template t = ve.getTemplate("hellovelocity.vm");
	    // 设置变量
	    VelocityContext ctx = new VelocityContext();
	    ctx.put("p1", Gender.MALE);
	    ctx.put("p2", Gender.FEMALE);
	    
	    ctx.put("name0", "");
	    ctx.put("name1", "\r\n");	    
	
	    ctx.put("list", list);
	    // 输出
	    StringWriter sw = new StringWriter();
	    t.merge(ctx,sw);
	    System.out.println(sw.toString());
	    
	    String address = "invitations.txt";
		try {
			//Allocate space for fileWrite 		
			FileWriter fileWriter = new FileWriter(address);
			//Allocate space for bufferedWrite 	
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);		
			//Save in 
//			System.out.println(this.jTextArea.getText());
			bufferedWriter.write(sw.toString());	
			bufferedWriter.close();		
			fileWriter.close();
		} catch (Exception e2) {		
			// TODO: handle exception		
			e2.printStackTrace();	
		}
	}

}
