/*
 * 
 * 	SOURCE CODE BY AKEGO - mainwindow.java
 * 
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class mainwindow extends Application {

	BorderPane bp = new BorderPane();
	HBox h_header = new HBox();
	HBox maincont = new HBox();
	VBox maincont_vert = new VBox();
	VBox f_footer = new VBox();
	String filename, versioninfo
	Tooltip totip_field = new Tooltip();
	Button btn_create;
	Button btn_finish = new Button();
	javafx.scene.control.TextField planename_field = new javafx.scene.control.TextField();;
	Image image_logo = new Image(mainwindow.class.getResourceAsStream("/img/logo_xpchecklist.png"));
	Image help_icon = new Image(mainwindow.class.getResourceAsStream("/img/helpicon.png"));
	Image twitter_icon = new Image(mainwindow.class.getResourceAsStream("/img/twittericon.png"));
	Image img_logo = new Image(mainwindow.class.getResourceAsStream("/img/plane_mainmenu.png"));
	functions f_class = new functions();
	Stage mainstage;
	int checkboxnr = 0;
	
	public static void main(String[] args) {		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		mainstage = primaryStage;
		f_class.createEndOverview();
		versioninfo = f_class.checkUpdates();
		
		//HBOX header
		h_header.setMinHeight(35);
	    h_header.setMaxHeight(35);
	    h_header.setStyle("-fx-border-radius: 15; -fx-background-color: #2a2a2a;");
	    h_header.setAlignment(Pos.CENTER_RIGHT);
	    h_header.setSpacing(10);
	    h_header.setPadding(new Insets(15));
		
	    ImageView iv_helpicon = new ImageView();
	    iv_helpicon.setImage(help_icon);
	    iv_helpicon.setFitHeight(25);
	    iv_helpicon.setPreserveRatio(true);
	    iv_helpicon.setSmooth(true);
	    
	    Tooltip totip_help = new Tooltip(); //hovertext
	    totip_help.setStyle("-fx-font-size: 12;");
	    totip_help.setText("Click here if youre\n"
	    		+ "not sure how to use\n"
	    		+ "Checklist Creator");
	    
	    Button btnhelp = new Button();
	    btnhelp.setStyle("-fx-background-color: #2a2a2a;");
	    btnhelp.setGraphic(iv_helpicon);
	    btnhelp.setTooltip(totip_help);
	    
	    //---------0
	    
	    ImageView iv_twittericon = new ImageView();
	    iv_twittericon.setImage(twitter_icon);
	    iv_twittericon.setFitHeight(25);
	    iv_twittericon.setPreserveRatio(true);
	    iv_twittericon.setSmooth(true);
	    
	    Button btntwitter = new Button();
	    btntwitter.setStyle("-fx-background-color: #2a2a2a;");
	    btntwitter.setGraphic(iv_twittericon);
	    
	    
	    h_header.getChildren().add(btntwitter);
	    h_header.getChildren().add(btnhelp);
	    
		//Main Content
		ImageView iv_logo = new ImageView();
	    iv_logo.setImage(img_logo);
	    iv_logo.setFitHeight(150);
		iv_logo.setPreserveRatio(true);
	    iv_logo.setSmooth(true);
	    
	    totip_field.setStyle("-fx-font-size: 12;");
	    totip_field.setText("Enter the Name of your Airplane here\n"
	    		+ "\te.g. Airbus A320\n"
	    		+ "(Please write it how shown in the example!)");
	    
	    btn_create = new Button("Create new Checklist");
	    btn_create.setStyle("-fx-background-color: #2271a9; -fx-font-family: sanf-serif; -fx-font-weight: bold;");
	    btn_create.setAlignment(Pos.CENTER);
	    btn_create.setMaxSize(230, 40);
	    btn_create.setMinSize(230, 40);
	    btn_create.setTextFill(Color.WHITE);
	    
	    planename_field.setTooltip(totip_field); 
	    planename_field.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
	    planename_field.setPromptText("Enter Aircraft Name here (e.g. Airbus A320)");
	    planename_field.setFocusTraversable(false);
		
	    providedby_field.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
	    providedby_field.setPromptText("Enter your Nickname here!");
	    providedby_field.setFocusTraversable(false);
	    
	    maincont_vert.setSpacing(10);
	    maincont_vert.setAlignment(Pos.CENTER);
	    maincont_vert.getChildren().add(planename_field);
	    maincont_vert.getChildren().add(providedby_field);
	    maincont_vert.getChildren().add(btn_create);
	    
	    maincont.setAlignment(Pos.CENTER_LEFT);
	    maincont.getChildren().add(iv_logo);
	    maincont.getChildren().add(maincont_vert);
		
		//VBOX footer
	    Separator s = new Separator();
	    s.setStyle("-fx-background-color: #2a2a2a");
	    s.setMaxHeight(5);
	    
	    HBox h_footer = new HBox();
	    h_footer.setAlignment(Pos.CENTER_LEFT);
	    h_footer.setSpacing(150);
	    
	    Label lbl_1 = new Label("Created by AkEgo:SkyGPDE");
	    lbl_1.setStyle("-fx-text-alignment: center; -fx-opacity: 0.44;");
	    Label lbl_version = new Label(versioninfo);
	    lbl_version.setStyle("-fx-text-alignment: center; -fx-opacity: 0.44;");
	    lbl_version.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				f_class.openWeb("https://github.com/EgoLeX/xpchecklistcreator");
			}
		});
	    
	    h_footer.getChildren().add(lbl_1);
	    h_footer.getChildren().add(lbl_version);
	    f_footer.getChildren().add(s);
	    f_footer.getChildren().add(h_footer);
		
		//set mainmenu design elements
		bp.setTop(h_header);
		bp.setBottom(f_footer);
		bp.setCenter(maincont);
		
		
		
		//
		//  ACTION Button create file
		//
		
		btn_create.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(planename_field.getText().equalsIgnoreCase("")) {
					planename_field.setPromptText("Please Enter a Planename here !!!");
				} else {
					try {
						//create File and get full data name back
						filename = f_class.createFile_startcontent(planename_field.getText());
						f_class.endoverview("[Checklist Creator]: Checklist "+planename_field.getText()+" was created"
								+ "\r\n");
						window_create_procedures();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		btnhelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override		
			public void handle(ActionEvent event) {
				f_class.openChecklistCreatorHTML();
			}
		});
		
		btntwitter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String twitter_skygp = "https://twitter.com/SkyGPDE";
				f_class.openWeb(twitter_skygp);
			}
		});
		
		//scene
		Scene scene = new Scene(bp, 550, 250);
	    primaryStage.getIcons().add(image_logo);
	    primaryStage.setTitle("XPChecklist - Checklist Creator");
	    primaryStage.setResizable(false);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public void window_create_procedures() {   // ÜBERARBEITEN !!!
		btn_finish.setText("Finish Checklist");
		btn_finish.setStyle("-fx-background-color: #2271a9; -fx-font-family: sanf-serif; -fx-font-weight: bold;");
		btn_finish.setAlignment(Pos.CENTER);
		btn_finish.setMaxSize(230, 40);
		btn_finish.setMinSize(230, 40);
		btn_finish.setTextFill(Color.WHITE);
		
		btn_create.setText("Create Procedures");
		
		totip_field.setText("Enter the Name of Procedure here\n"
	    		+ "\te.g. Cockpit Preperation\n"
	    		+ "(Please write it how shown in the example!)");
		planename_field.setTooltip(totip_field);
		planename_field.clear();
	    planename_field.setPromptText("Enter Procedures (e.g. Cockpit Preperation)");
	    planename_field.setFocusTraversable(false);
		
	    
	    // ADD FINISH BUTTON TO GUI
	    maincont_vert.getChildren().add(btn_finish);
		
		// ACTION FOR BUTTON
		
	    btn_create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(planename_field.getText().equalsIgnoreCase("")) {
					planename_field.setPromptText("Please enter a Procedures for the Checklist !!!");
				} else {
					String procedurename = planename_field.getText();
					f_class.createProcedures(planename_field.getText());
					f_class.endoverview("[Checklist Creator]: Procedures "+planename_field.getText()+" was created"
							+ "\r\n");
					window_create_checkboxes(procedurename);
				}
			}
		});
	    
	    btn_finish.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		f_class.createFile_endcontent();
	    		mainstage.close();
				EndWindow();
	    	}
		});
	}
	
	public void window_create_checkboxes(String procedure_name) {
		btn_finish.setText("Finish");
		
		btn_create.setText("Create Checkbox for Procedure:\n"+"\t> "+procedure_name);
		
		totip_field.setText("Enter the Name of Checkbox here\n"
	    		+ "\te.g. Engine 1   |   ON\n"
	    		+ "(Please write it how shown in the example!)");
		planename_field.setTooltip(totip_field);
		planename_field.clear();
		planename_field.setPromptText("Enter Checkbox to Procedure (e.g. Engine 1   |   ON)");
	    planename_field.setFocusTraversable(false);
	    
	    //
	    // ON BUTTON ACTION
	    //
	    
	    btn_create.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(planename_field.getText().equalsIgnoreCase("")) {
					planename_field.setPromptText("Please enter a Checkbox for the Procedure !!!");
				} else {
					checkboxnr++;
					f_class.createCheckbox(planename_field.getText(), checkboxnr);
					f_class.endoverview("[Checklist Creator]: Checkbox "+planename_field.getText()+" was created"
							+ "\r\n");
					planename_field.clear();
				}
			}
		});
	    
	    btn_finish.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				maincont_vert.getChildren().remove(btn_finish);
				f_class.endoverview("[Checklist Creator]: Checkboxes for Procedure "+procedure_name+" created and editing finished"
						+ "\r\n");
				window_create_procedures();
			}
		}); 
	}
	
	public void EndWindow() {
		Stage endwindow = new Stage();
		BorderPane bp_end = new BorderPane();
		TextArea ta = new TextArea();
		VBox vboxend = new VBox();
		HBox h_head = new HBox();
		Separator s = new Separator();
		Label filelocation = new Label("You can find your created Checklist in your program main directory.\nYou can send it now to us to integrate it into the XPChecklist App!\n");
		Label thanksforsupport = new Label("Thanks for your Support to make XPChecklist better :D");
		Hyperlink link = new Hyperlink();
		
		thanksforsupport.setStyle("-fx-background-color: #FFD44F;");
		ta.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
		
		//READ FILE FUNCTION
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(Paths.get("checklist_log.txt"));
			String text = new String(bytes, StandardCharsets.UTF_8);
		    ta.setText(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		link.setText("Send us your checklist");
		link.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		        f_class.sendFileMail();;
		    }
		});
		
		vboxend.setAlignment(Pos.CENTER);
		vboxend.getChildren().addAll(ta, s, filelocation, link, thanksforsupport);
		
		//HBOX header
		h_head.setMinHeight(35);
		h_head.setMaxHeight(35);
		h_head.setStyle("-fx-border-radius: 15; -fx-background-color: #2a2a2a;");
		h_head.setPadding(new Insets(15));
		
		bp_end.setTop(h_head);
		bp_end.setCenter(vboxend);
		bp_end.setBottom(f_footer);
		
		Scene scene = new Scene(bp_end, 550, 300);
		endwindow.getIcons().add(image_logo);
		endwindow.setTitle("XPChecklist - Checklist Creator - Overview");
		endwindow.setResizable(false);
		endwindow.setScene(scene);
		endwindow.show();
	}
	
	//Bad word filter : 
	
	public static List<Object> badWords = new ArrayList<>();
	    static {
		badWords.add("admin");
		badWords.add("developer");
		badWords.add("akego");
		badWords.add("publisher");
	    }

	public static boolean isBadWord(String word){
		return badWords.contains(word.toLowerCase());
	}

}
