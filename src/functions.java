
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
	static String xpcc_current_version = "1.2.2"; // important for update checker

	public String createFile_startcontent(String planename, String providedname, String plgame) throws IOException {
		_planename = planename;

		String planename_id = planename.replaceAll("\\s", "_"); // replace all whitespaces with underline minus

		String xml_1 = "activity_act_";
		String xml_2 = plgame + "_";
		String xml_half = new StringBuilder(xml_1).append(xml_2).toString();
		String xml_full = new StringBuilder(xml_half).append(planename_id + ".xml").toString();
		xml_fullname = xml_full;
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + "<!--\r\n"
				+ "##########################################################################################################\r\n"
				+ "\r\n" + "\t\tTHIS CHECKLIST WAS CREATED WITH XPCHECKLIST - CHECKLIST CREATOR CREATED BY AKEGO\r\n"
				+ "\r\n"
				+ "DO NOT CHANGE ANYTHING BEFORE AND BEHIND THIS !!! The changable part of this file can be found at Line 62!\r\n"
				+ "\r\n"
				+ "##########################################################################################################\r\n"
				+ "-->\r\n" + "<ScrollView xmlns:android=\"http://schemas.android.com/apk/res/android\"\r\n"
				+ "    xmlns:app=\"http://schemas.android.com/apk/res-auto\"\r\n"
				+ "    xmlns:tools=\"http://schemas.android.com/tools\"\r\n"
				+ "    android:layout_width=\"match_parent\"\r\n" + "    android:layout_height=\"match_parent\">\r\n"
				+ "    <LinearLayout\r\n" + "		android:id=\"@+id/ll_checklist_main\"\r\n"
				+ "        android:layout_width=\"match_parent\"\r\n"
				+ "        android:layout_height=\"wrap_content\"\r\n" + "        android:orientation=\"vertical\">\r\n"
				+ "        <LinearLayout\r\n" + "            android:id=\"@+id/ll_checklist_horizontal\"\r\n"
				+ "            android:layout_width=\"match_parent\"\r\n"
				+ "            android:layout_height=\"wrap_content\"\r\n"
				+ "            android:orientation=\"horizontal\">\r\n" + "            <ImageView\r\n"
				+ "                android:id=\"@+id/iv_checklist_info_plane\"\r\n"
				+ "                android:layout_width=\"wrap_content\"\r\n"
				+ "                android:layout_height=\"50dp\"\r\n"
				+ "                android:layout_weight=\"1\"\r\n"
				+ "                android:adjustViewBounds=\"true\"\r\n"
				+ "                android:contentDescription=\"@string/text_empty\"\r\n"
				+ "                android:cropToPadding=\"true\"\r\n" + "                android:padding=\"5dp\"\r\n"
				+ "                app:srcCompat=\"@drawable/info\" />\r\n" + "            <ImageView\r\n"
				+ "                android:id=\"@+id/iv_checklist_reset\"\r\n"
				+ "                android:layout_width=\"wrap_content\"\r\n"
				+ "                android:layout_height=\"50dp\"\r\n"
				+ "                android:layout_weight=\"1\"\r\n"
				+ "                android:adjustViewBounds=\"true\"\r\n"
				+ "                android:contentDescription=\"@string/text_empty\"\r\n"
				+ "                android:cropToPadding=\"true\"\r\n" + "                android:padding=\"5dp\"\r\n"
				+ "                app:srcCompat=\"@drawable/reset\" />\r\n" + "        </LinearLayout>\r\n"
				+ "        <TextView\r\n" + "            android:id=\"@+id/tv_line_checkl1\"\r\n"
				+ "            android:layout_width=\"match_parent\"\r\n"
				+ "            android:layout_height=\"5dp\"\r\n"
				+ "            android:background=\"@color/colorPrimary\" />\r\n" + "        <Space\r\n"
				+ "            android:layout_width=\"match_parent\"\r\n"
				+ "            android:layout_height=\"15dp\" />   \r\n" + "	<!-- \r\n"
				+ "	######################\r\n" + "	\r\n" + "	BEGIN OF CHECKBOXES !!\r\n" + "		Editing allowed\r\n"
				+ "	\r\n" + "	######################\r\n" + "	-->\r\n" + "		<TextView\r\n"
				+ "            android:text=\"" + planename + "\"\r\n"
				+ "            android:layout_width=\"match_parent\"\r\n"
				+ "            android:layout_height=\"wrap_content\"\r\n"
				+ "            android:textAlignment=\"center\"\r\n" + "            android:textSize=\"20dp\"\r\n"
				+ "            android:textStyle=\"bold\"\r\n" + "            android:id=\"@+id/" + planename_id
				+ "_titel" + "\" />\r\n" + "\r\n" + "        <Space\r\n"
				+ "			android:layout_width=\"match_parent\"\r\n"
				+ "			android:layout_height=\"20dp\" />\r\n" + "\r\n" + "		<TextView\r\n"
				+ "			android:id=\"@+id/tv_providedby\"\r\n"
				+ "			android:layout_width=\"match_parent\"\r\n"
				+ "			android:layout_height=\"wrap_content\"\r\n" + "			android:text=\"" + providedname
				+ "\"\r\n" + "			android:textAlignment=\"center\"\r\n"
				+ "			android:textSize=\"12sp\" />\r\n" + "\r\n" + "		<Space\r\n"
				+ "			android:layout_width=\"match_parent\"\r\n"
				+ "			android:layout_height=\"20dp\" />\r\n" + "\r\n" + "		<Space\r\n"
				+ " 			 android:layout_width=\"match_parent\"\r\n"
				+ " 			 android:layout_height=\"20dp\" />";
		FileOutputStream out = new FileOutputStream(xml_full);
		out.write(data.getBytes());
		out.close();
		return xml_full;
	}

	public void createProcedures(String partname) {
		String id = new StringBuilder(_planename + "_").append(partname).toString();
		String procedures_id = id.replaceAll("\\s", "_"); // replace all whitespaces with underline minus
		String data = "		<Space\r\n" + " 			 android:layout_width=\"match_parent\"\r\n"
				+ " 			 android:layout_height=\"20dp\" />\r\n" + "\r\n" + "		<TextView\r\n"
				+ "            android:layout_width=\"match_parent\"\r\n"
				+ "            android:layout_height=\"wrap_content\"\r\n" + "            android:id=\"@+id/"
				+ procedures_id + "\"\r\n" + "            android:textAlignment=\"center\"\r\n"
				+ "            android:text=\"" + partname + "\" />\r\n" + "\r\n" + "        <Space\r\n"
				+ "            android:layout_width=\"match_parent\"\r\n"
				+ "            android:layout_height=\"20dp\" />\r\n\r\n";
		// write content into file (append file)
		try {
			Files.write(Paths.get(xml_fullname), data.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	public void createCheckbox(String checkboxname, int checkboxnr) {
		String checkbox_planename = _planename.replaceAll("\\s", "_");
		String data_checkbox = "		<CheckBox\r\n" + "            android:id=\"@+id/" + checkbox_planename
				+ "_checkbox_" + checkboxnr + "\"\r\n" + "            android:layout_width=\"match_parent\"\r\n"
				+ "            android:layout_height=\"50dp\"\r\n" + "            android:checked=\"false\"\r\n"
				+ "            android:text=\"" + checkboxname + "\" />\r\n" + "\r\n";
		// write content into file (append file)
		try {
			Files.write(Paths.get(xml_fullname), data_checkbox.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	public void createFile_endcontent() {
		String data_end = "<!-- \r\n" + "	###########\r\n" + "	\r\n" + "	Editing END\r\n" + "	\r\n"
				+ "	###########\r\n" + "	-->\r\n" + "	</LinearLayout>\r\n" + "</ScrollView>";
		// write content into file (append file)
		try {
			Files.write(Paths.get(xml_fullname), data_end.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	public void endoverview(String text) {
		try {
			Files.write(Paths.get("checklist_log.txt"), text.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
		}
	}

	public void createEndOverview() throws IOException {
		String loglocation = "checklist_log.txt";
		String data_overview = "" + "####################################" + "\r\n"
				+ " End Overview of created Checklist" + "\r\n" + "####################################" + "\r\n\r\n";
		FileOutputStream out = new FileOutputStream(loglocation);
		out.write(data_overview.getBytes());
		out.close();
	}

	public void openChecklistCreatorHTML() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(
				classLoader.getResource("pg/xpchecklist_checklistcreator.html").getFile().replaceAll("%20", " "));
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
			url = "mailTo:akego.dev@gmail.com" + "?subject=" + "[NewChecklist]%20XPChecklistCreator";
			mailTo = new URI(url);
			desktop.mail(mailTo);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// UpdateChecker --------------
	public String checkUpdates() {
		String versioninfo;
		try {
			if (xpcc_current_version.equals(getLatestVersion())) {
				versioninfo = "XPC-CC - Version " + xpcc_current_version;
			} else {
				versioninfo = "A newer version is available!";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			versioninfo = "!Checking for updates failed!";
		}
		return versioninfo;
	}

	public static String getLatestVersion() throws Exception {
		String data = getData(
				"https://github.com/EgoLeX/xpchecklistcreator/blob/master/src/updcheckler/curversion.txt");
		return data.substring(data.indexOf("[version]") + 9, data.indexOf("[/version]"));
	}

	private static String getData(String address) throws Exception {
		URL url = new URL(address);
		InputStream html = null;
		html = url.openStream();
		int c = 0;
		StringBuffer buffer = new StringBuffer("");
		while (c != -1) {
			c = html.read();
			buffer.append((char) c);
		}
		return buffer.toString();
	}
}
