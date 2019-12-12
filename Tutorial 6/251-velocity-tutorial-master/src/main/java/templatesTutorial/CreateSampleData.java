package templatesTutorial;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class CreateSampleData {

	public static void main(String[] args) throws Exception {
		Person[] persons = new Person[]{
				new Person("Tim","MacDonald",Gender.MALE,new Address(100,"Main Street","Palmerston North")),
				new Person("Tom","Smith",Gender.MALE,new Address(42,"Cuba Street","Palmerston North")),
				new Person("Amy","Turner",Gender.FEMALE,new Address(3,"Worchester Street","Palmerston North")),
				new Person("Anne","O'Brian",Gender.FEMALE,new Address(4,"Manchester Street","Feilding")),
				new Person("Fred","Miller",Gender.MALE,new Address(55,"Salisbury Street","Ashhurst")),
				new Person("Max","Meyer",Gender.MALE,new Address(77,"Main Street","Palmerston North")),
				new Person("Walter","Mueller",Gender.MALE,new Address(22,"Cook Street","Palmerston North")),
				new Person("Kate","Wang",Gender.FEMALE,new Address(12,"Albert Street","Palmerston North"))
		};
		List<Person> list = new ArrayList<Person>();
		for (Person p:persons) list.add(p);
		
		XMLEncoder e = new XMLEncoder(
			    new BufferedOutputStream(
			        new FileOutputStream("friends.xml")));
		e.writeObject(list);
		e.close();
		
	    // 初始化模板引擎
	    VelocityEngine ve = new VelocityEngine();
	    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
	    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	    ve.init();
	    // 获取模板文件
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
