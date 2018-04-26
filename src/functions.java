/*
 * 
 * 	SOURCE CODE BY AKEGO - functions.java
 * 
 */

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class functions {

	String _planename;
	String xml_fullname;
	static String xpcc_current_version = "1.1";  //important for update checker
	
	public String createFile_startcontent(String planename) throws IOException {
		_planename = planename;
		
		String planename_id = planename.replaceAll("\\s", "_"); //replace all whitespaces with underline minus
		
		String xml_1 = "activity_act_checklist_";
		String xml_full = new StringBuilder(xml_1).append(planename_id+".xml").toString();
		xml_fullname = xml_full;
		String data = "## FIRST LINES OF CHECKLIST ##";
        FileOutputStream out = new FileOutputStream(xml_full);
        out.write(data.getBytes());
        out.close();
        return xml_full;
	}
	
	public void createProcedures(String partname) {
		String id = new StringBuilder(_planename+"_").append(partname).toString();
		String procedures_id = id.replaceAll("\\s", "_"); //replace all whitespaces with underline minus
		String data = "## PROCEDURE PARTS OF CHECKLIST ##";
		//write content into file (append file)
		try {
		    Files.write(Paths.get(xml_fullname), data.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	
	public void createCheckbox(String checkboxname, int checkboxnr) {
		String checkbox_planename = _planename.replaceAll("\\s", "_");
		String data_checkbox = "## SINGLE CHECKBOX FOR PROCEDURE ##";
		//write content into file (append file)
		try {
		    Files.write(Paths.get(xml_fullname), data_checkbox.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	
	public void createFile_endcontent () {
		String data_end = "## ENDING LINES OF CHECKLIST ##";
		//write content into file (append file)
		try {
		    Files.write(Paths.get(xml_fullname), data_end.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	
	public void endoverview(String text) {
		try {
		    Files.write(Paths.get("checklist_log.txt"), text.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		}
	}
	
	public void createEndOverview() throws IOException {
		String loglocation = "checklist_log.txt";
		String data_overview = "## ENDING OF CHECKLIST ##";
		FileOutputStream out = new FileOutputStream(loglocation);
        out.write(data_overview.getBytes());
        out.close();
	}
	
	public void openChecklistCreatorHTML() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("pg/xpchecklist_checklistcreator.html").getFile().replaceAll("%20", " "));
		try {
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openWeb(String link) {
		Desktop desktop = java.awt.Desktop.getDesktop();
		try {
			URI _url = new URI(link);
			desktop.browse(_url);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendFileMail() {
		Desktop desktop = Desktop.getDesktop();  
		String url = "";
		URI mailTo;  
		try {  
		url = "mailTo:email@email.email" + "?subject=" + "[NewChecklist]%20XPChecklistCreator";  
		mailTo = new URI(url);  
		desktop.mail(mailTo);  
		} catch (URISyntaxException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}
	
	//UpdateChecker --------------
	public String checkUpdates() {
		String versioninfo;
		try {
			if(xpcc_current_version.equals(getLatestVersion())) {
				versioninfo="XPC-CC - Version "+xpcc_current_version;
			} else {
				versioninfo="A newer version is available!";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			versioninfo="!Checking for updates failed!";
		}
		return versioninfo;
	}
	
	public static String getLatestVersion() throws Exception{
		 String data = getData("https://github.com/EgoLeX/xpchecklistcreator/blob/master/src/updcheckler/curversion.txt");
		 return data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]"));
	}

	private static String getData(String address)throws Exception{
       URL url = new URL(address);
       InputStream html = null;
       html = url.openStream();
       int c = 0;
       StringBuffer buffer = new StringBuffer("");
       while(c != -1) {
           c = html.read();
       buffer.append((char)c);
       }
       return buffer.toString();
	}
}
