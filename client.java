import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;	
import java.net.*;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.*;

public class client extends Application{
	Group root = new Group();
	Stage window = new Stage();
	Scene r = new Scene(root,1200,600);
	Socket socketClient;Scanner in;PrintWriter out;
	int cpts=0;String wints="";String opplay="Ntg yet";
	int pla;
	ArrayList<Integer> cclients = new ArrayList<Integer>();
	HBox[] h;Button[]b;Text[] t;Boolean game=false;
	int pts=0;
	VBox rt;Text point;
	ArrayList<Line> cbox = new ArrayList<Line>();//Array of Lines to be shown in game
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Dots and Boxes Game!! (Client)");
		primaryStage.setScene(r);window = primaryStage;

		//Hbox for ip addr
		Label IP = new Label("IP Address:    ");
		TextField addrField = new TextField ();
		addrField.setText("127.0.0.1");
		HBox hb = new HBox();
		hb.getChildren().addAll(IP, addrField);
		root.getChildren().add(hb);hb.setAlignment(Pos.CENTER_LEFT);
		hb.setTranslateX(200);hb.setTranslateY(350);hb.setSpacing(10);
		
		//Hbox fro port
		Label port = new Label("Port Number:");
		Button ports = new Button();
		ports.setText("Submit IP and Port");
		TextField portField = new TextField ();
		portField.setText("5555");
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(port, portField,ports);
		root.getChildren().add(hb1);hb1.setTranslateX(200);hb1.setTranslateY(400);hb1.setSpacing(10);
		
		//Submit button click
		ports.setOnAction(e->{
		String p = portField.getText();String i = addrField.getText();//TextField values
		
		if((p!=""||p!=null)&&(i!=""||i!=null)) {
			//connect to server and wait on different thread
			Platform.runLater(new Runnable() {public void run() {try {
				clientConnect(Integer.parseInt(p),i);//server connect function
			} catch (ConnectException e) {
				// handle the error if the server and IP is not running
				hb.setVisible(false);
				hb1.setVisible(false);
				Text refused = new Text();
				refused.setText("Server is not running now please try later or with a different IP and port!");
				refused.setX(150);refused.setY(200);
				Button con = new Button();con.setLayoutX(250);con.setLayoutY(250);
				con.setText("Continue to IP and port Fields");
				root.getChildren().addAll(refused,con);
				con.setOnAction(e1->{
					root.getChildren().removeAll(refused,con);
					hb.setVisible(true);
					hb1.setVisible(true);
				});
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}}});
			
			//change scene to wait for server connection
			//if(e==null) { 
			//Text wait = new Text();
			//wait.setX(150);wait.setY(300);
			//wait.setText("Connected to Server..Game Has started now ERABE");}
		}
		else {
			//display message to enter something in the textfields
		}
		});
		
		primaryStage.show();
	}
	
	
public void initpt() {
	for(int i=0;i<9;i++) {
		Text p = new Text();p.setText(Integer.toString(pla));p.setVisible(false);
		pt.add(p);
	}
}

public void init() {
	   l1 = new Line(50,10,80,10);
	   l1.setTranslateY(20);
	   l2 = new Line(50,10,80,10);
	    l2.setTranslateY(20);
	    l1.setVisible(false);
	    l2.setVisible(false);
	    
	    l3 = new Line(50,10,80,10);
	    l3.setTranslateY(20);
	    l3.setVisible(false);

	    l4 = new Line(50,10,80,10);
	    l4.setTranslateY(20);
	    l4.setVisible(false);
	    
	    l5 = new Line(50,10,80,10);
	    l5.setTranslateY(20);
	    l5.setVisible(false);

	    l6 = new Line(50,10,80,10);
	    l6.setTranslateY(20);
	    l6.setVisible(false);

	    l7 = new Line(50,10,80,10);
	    l7.setTranslateY(20);
	    l7.setVisible(false);

	    l8 = new Line(50,10,80,10);
	    l8.setTranslateY(20);
	    l8.setVisible(false);

	    l9 = new Line(50,10,80,10);
	    l9.setTranslateY(20);
	    l9.setVisible(false);

	    l10 = new Line(50,10,80,10);
	    l10.setTranslateY(20);
	    l10.setVisible(false);

	    l11 = new Line(50,10,80,10);
	    l11.setTranslateY(20);
	    l11.setVisible(false);

	    l12 = new Line(50,10,80,10);
	    l12.setTranslateY(20);
	    l12.setVisible(false);

	    l13 = new Line(10,50,10,80);
	    l13.setTranslateX(20);
	    l13.setVisible(false);

	    l14 = new Line(10,50,10,80);
	    l14.setTranslateX(80);
	    l14.setVisible(false);

	    l15 = new Line(10,50,10,80);
	    l15.setTranslateX(140);
	    l15.setVisible(false);

	    l16 = new Line(10,50,10,80);
	    l16.setTranslateX(200);
	    l16.setVisible(false);

	    l17 = new Line(10,50,10,80);
	    l17.setTranslateX(20);
	    l17.setVisible(false);
	    l18 = new Line(10,50,10,80);
	    l18.setTranslateX(80);
	    l18.setVisible(false);
	    l19 = new Line(10,50,10,80);
	    l19.setTranslateX(140);
	    l19.setVisible(false);
	    l20 = new Line(10,50,10,80);
	    l20.setTranslateX(200);
	    l20.setVisible(false);
	    l21 = new Line(10,50,10,80);
	    l21.setTranslateX(20);
	    l21.setVisible(false);
	    l22 = new Line(10,50,10,80);
	    l22.setTranslateX(80);
	    l22.setVisible(false);
	    l23 = new Line(10,50,10,80);
	    l23.setTranslateX(140);
	    l23.setVisible(false);
	    l24 = new Line(10,50,10,80);
	    l24.setTranslateX(200);
	    l24.setVisible(false);
}
public void stgame() {
	//build basic structure of the game screen like images textfields and stuff -DWIJ

	root.getChildren().clear();
	
    Button quit = new Button("Quit");

    Image dot = new Image("file:///home/lohit/eclipse-workspace/342p3/hands/r1c1.png");
    quit.setOnAction(e->{System.exit(0);});
    Image r1c1Pic = dot;
    ImageView r1c1View = new ImageView(r1c1Pic);
    r1c1View.setFitHeight(40);
    r1c1View.setFitWidth(40);
    r1c1View.setPreserveRatio(true);
    r1c1View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r1c1View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(1,1);
    	System.out.println("selected 11");}
    	else {x= new Point(1,1);
    		passL(new gline(a,x));}}});

    Image r1c2Pic = dot;
    ImageView r1c2View = new ImageView(r1c2Pic);
    r1c2View.setFitHeight(40);
    r1c2View.setFitWidth(40);
    r1c2View.setPreserveRatio(true);
    r1c2View.setOnMouseClicked( e-> {  
    	if(turn==true) {
    	r1c2View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(1,2);}
    	else {x= new Point(1,2);System.out.println("Selected 12");
    		passL(new gline(a,x));//passline to server
    		}}
    	} );

    Image r1c3Pic = dot;
    ImageView r1c3View = new ImageView(r1c3Pic);
    r1c3View.setFitHeight(40);
    r1c3View.setFitWidth(40);
    r1c3View.setPreserveRatio(true);

    r1c3View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r1c3View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(1,3);}
    	else {x= new Point(1,3);
    		passL(new gline(a,x));//passline to server
    		}
    			}	
    });

    Image r1c4Pic = dot;
    ImageView r1c4View = new ImageView(r1c4Pic);
    r1c4View.setFitHeight(40);
    r1c4View.setFitWidth(40);
    r1c4View.setPreserveRatio(true);

    r1c4View.setOnMouseClicked( e-> {
    	if(turn==true) {
    	r1c4View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(1,4);}
    	else {x= new Point(1,4);
    		passL(new gline(a,x));//passline to server
    		}
    			}	
    } );

    Image r1c5Pic =  dot;
    ImageView r1c5View = new ImageView(r1c5Pic);
    r1c5View.setFitHeight(40);
    r1c5View.setFitWidth(40);
    r1c5View.setPreserveRatio(true);

    r1c5View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r1c5View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(1,5);}
    	else {x= new Point(1,5);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r2c1Pic = dot;
    ImageView r2c1View = new ImageView(r2c1Pic);
    r2c1View.setFitHeight(40);
    r2c1View.setFitWidth(40);
    r2c1View.setPreserveRatio(true);

    r2c1View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r2c1View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(2,1);}
    	else {x= new Point(2,1);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r2c2Pic = dot;
    ImageView r2c2View = new ImageView(r2c2Pic);
    r2c2View.setFitHeight(40);
    r2c2View.setFitWidth(40);
    r2c2View.setPreserveRatio(true);

    r2c2View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r2c2View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(2,2);}
    	else {x= new Point(2,2);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r2c3Pic = dot;
    ImageView r2c3View = new ImageView(r2c3Pic);
    r2c3View.setFitHeight(40);
    r2c3View.setFitWidth(40);
    r2c3View.setPreserveRatio(true);

    r2c3View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r2c3View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(2,3);}
    	else {x= new Point(2,3);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r2c4Pic = dot;
    ImageView r2c4View = new ImageView(r2c4Pic);
    r2c4View.setFitHeight(40);
    r2c4View.setFitWidth(40);
    r2c4View.setPreserveRatio(true);

    r2c4View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r2c4View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(2,4);}
    	else {x= new Point(2,4);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r2c5Pic = dot;
    ImageView r2c5View = new ImageView(r2c5Pic);
    r2c5View.setFitHeight(40);
    r2c5View.setFitWidth(40);
    r2c5View.setPreserveRatio(true);

    r2c5View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r2c5View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(2,5);}
    	else {x= new Point(2,5);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r3c1Pic = dot;
    ImageView r3c1View = new ImageView(r3c1Pic);
    r3c1View.setFitHeight(40);
    r3c1View.setFitWidth(40);
    r3c1View.setPreserveRatio(true);

    r3c1View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r3c1View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(3,1);}
    	else {x= new Point(3,1);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r3c2Pic = dot;
    ImageView r3c2View = new ImageView(r3c2Pic);
    r3c2View.setFitHeight(40);
    r3c2View.setFitWidth(40);
    r3c2View.setPreserveRatio(true);

    r3c2View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r3c2View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(3,2);}
    	else {x= new Point(3,2);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r3c3Pic = dot;
    ImageView r3c3View = new ImageView(r3c3Pic);
    r3c3View.setFitHeight(40);
    r3c3View.setFitWidth(40);
    r3c3View.setPreserveRatio(true);

    r3c3View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r3c3View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(3,3);}
    	else {x= new Point(3,3);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r3c4Pic = dot;
    ImageView r3c4View = new ImageView(r3c4Pic);
    r3c4View.setFitHeight(40);
    r3c4View.setFitWidth(40);
    r3c4View.setPreserveRatio(true);

    r3c4View.setOnMouseClicked( e-> {
    	if(turn==true) {
    	r3c4View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(3,4);}
    	else {x= new Point(3,4);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r3c5Pic = dot;
    ImageView r3c5View = new ImageView(r3c5Pic);
    r3c5View.setFitHeight(40);
    r3c5View.setFitWidth(40);
    r3c5View.setPreserveRatio(true);

    r3c5View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r3c5View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(3,5);}
    	else {x= new Point(3,5);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r4c1Pic = dot;
    ImageView r4c1View = new ImageView(r4c1Pic);
    r4c1View.setFitHeight(40);
    r4c1View.setFitWidth(40);
    r4c1View.setPreserveRatio(true);

    r4c1View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r4c1View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(4,1);}
    	else {x= new Point(4,1);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r4c2Pic = dot;
    ImageView r4c2View = new ImageView(r4c2Pic);
    r4c2View.setFitHeight(40);
    r4c2View.setFitWidth(40);
    r4c2View.setPreserveRatio(true);

    r4c2View.setOnMouseClicked( e-> {
    	if(turn==true) {
    	r4c2View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(4,2);}
    	else {x= new Point(4,2);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r4c3Pic = dot;
    ImageView r4c3View = new ImageView(r4c3Pic);
    r4c3View.setFitHeight(40);
    r4c3View.setFitWidth(40);
    r4c3View.setPreserveRatio(true);

    r4c3View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r4c3View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(4,3);}
    	else {x= new Point(4,3);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r4c4Pic = dot;
    ImageView r4c4View = new ImageView(r4c4Pic);
    r4c4View.setFitHeight(40);
    r4c4View.setFitWidth(40);
    r4c4View.setPreserveRatio(true);

    r4c4View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r4c4View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(4,4);}
    	else {x= new Point(4,4);
    		passL(new gline(a,x));//passline to server
    		}
    			}
    });

    Image r4c5Pic = dot;
    ImageView r4c5View = new ImageView(r4c5Pic);
    r4c5View.setFitHeight(40);
    r4c5View.setFitWidth(40);
    r4c5View.setPreserveRatio(true);

    r4c5View.setOnMouseClicked(e-> {
    	if(turn==true) {
    	r4c5View.setImage(new Image("blueDot.png"));
    	if(selected==false) {selected=true;
    	a = new Point(4,5);}
    	else {x= new Point(4,5);
    		passL(new gline(a,x));//passline to server
    		}}});
    

    HBox row1 = new HBox(0,r1c1View,l1,r1c2View,l2,r1c3View,l3,r1c4View);
    HBox row2 = new HBox(0, r2c1View,l4,r2c2View,l5,r2c3View,l6,r2c4View);
    HBox row3 = new HBox(0, r3c1View,l7,r3c2View,l8,r3c3View,l9,r3c4View);
    HBox row4 = new HBox(0, r4c1View,l10,r4c2View,l11,r4c3View,l12,r4c4View);

    HBox lineRow1 = new HBox(10,l13,l14,l15,l16);
    HBox lineRow2 = new HBox(10,l17,l18,l19,l20);
    HBox lineRow3 = new HBox(10,l21,l22,l23,l24);

    rt = new VBox(0, row1,lineRow1,row2,lineRow2,row3,lineRow3,row4,quit);
    rt.setSpacing(0);
    rt.setLayoutX(100);rt.setLayoutY(100);
    rt.setPrefSize(600, 600);	

	
	//CHANGE THIS COMPLETELY to make connect dots game only updates based on info from server -DWIJ 
public void game() {
	root.getChildren().clear();
	System.out.println("run");
	//code here	
	for(gline i:cbox) {
		setvTrue(i);
	}
	point = new Text("Your Points: "+pts);
	point.setX(450);point.setY(150);
	Text tmsg = new Text();
	tmsg.setText(s);
	tmsg.setX(300);tmsg.setY(80);
	root.getChildren().addAll(rt,point,tmsg);
	window.show();
}
 
/*
public void inithbt() {
	for(int i= 0;i<cclients.size();i++) {
		h[i] =new HBox();
		t[i]=new Text();
		
	}
}

//not using
public void Que() {
	root.getChildren().clear();
	System.out.println("c que");
	Text wait = new Text();
	Text pp = new Text();
	Button dicon = new Button();
	dicon.setText("Disconnect and Exit?");
	dicon.setLayoutX(880);dicon.setLayoutY(180);
	dicon.setOnAction(v->{
		try {
			socketClient.close();
			window.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
	pp.setText("You are Client "+pla);
	pp.setX(150);pp.setY(200);
	wait.setText("Clients Connected to the Server NOW!!  :");
	 h = new HBox[cclients.size()];
	t = new Text[cclients.size()];
	inithbt();
	for(int i3=0;(i3<cclients.size());++i3) {
		System.out.println(pla+" "+cclients.get(i3)+" "+i3);
		if(cclients.get(i3)!=pla) {
		t[i3].setText("Client: "+Integer.toString(cclients.get(i3)));
		h[i3].getChildren().addAll(t[i3]);h[i3].setSpacing(10);
		h[i3].setTranslateX(250);h[i3].setTranslateY(340+(i3*20));
		root.getChildren().add(h[i3]);}
	}
	Label ch = new Label("Challenge:");
	Button subchal = new Button();
	subchal.setText("Submit Challenge");
	TextField chall = new TextField ();
	chall.setText("");
	HBox hb1 = new HBox();
	hb1.getChildren().addAll(ch,chall,subchal);hb1.setSpacing(20);
	subchal.setOnAction(e->{
		challenge(Integer.valueOf(chall.getText()));
	});
	hb1.setLayoutY(180);hb1.setLayoutX(400);
	root.getChildren().addAll(wait,pp,hb1,dicon);
	wait.setX(200);wait.setY(320);
	
	//create new thread to wait for server msg
		winput wai2 = new winput();
			wai2.start();
	window.show();
}

public void challenge(int p) {
	out.println("challenge");
	out.println(p);
}

public void hQue() {
	root.getChildren().clear();
	Text wait = new Text();
	wait.setText("Waiting for other client to play his move........");
	root.getChildren().add(wait);
	wait.setX(200);wait.setY(300);
	System.out.println("wait que");
	//create a thread to wait for server to start the game (server will send win or lose)
			winput wai = new winput();
			wai.start();
	window.show();
		
}*/
	
public void clientConnect(int port,String addr) throws IOException{
		
		 socketClient= new Socket(addr,port);
	    System.out.println("Client: Connection Established");
	    in = new Scanner(socketClient.getInputStream());
		out = new PrintWriter(socketClient.getOutputStream(), true);
		winput cp = new winput();cp.run();
		//stgame();
}


//change
public void wlscreen(String msg1) {
	root.getChildren().clear();
	System.out.println("wlscreen");
	Text wl = new Text();wl.setX(200);wl.setY(250);
	if(msg1.equals("win")) {
		wl.setText("CONGRATULATIONS YOU WON THE GAME !!!!     OPPONENT PLAYED: "+opplay);}
	else if(msg1.equals("lose")) {
		wl.setText("SORRY YOU LOST THE GAME.......     OPPONENT PLAYED: "+opplay);}
	Button Exit = new Button();Button pa = new Button();
	Exit.setText("Exit");Exit.setLayoutX(200);Exit.setLayoutY(300);
	pa.setText("Play Again ?");pa.setLayoutX(350);pa.setLayoutY(300);
	
	Exit.setOnAction(e->{
		out.println("exit");
		Platform.exit();
	});
	pa.setOnAction(e1->{
		//Que();
	});
	root.getChildren().addAll(wl,Exit,pa);
	window.show();
}

public void play(String msg) {
	if(msg.equals("win")) {
		/*cpts++;
		if(cpts==3) {
			//win screen and buttons to exit or play another game!
			wlscreen("win");
		}
		else */
		wints="You WIN!!!!";wlscreen("win");}
	else if(msg.equals("lose")) {
		wints="You LOSE....";wlscreen("lose");
	}
	else if(msg.equals("tie")) {
		wints="Its a TIE....";wlscreen("tie");
	}
}


public class winput extends Thread{
	
	String msg=new String();
	public void run() {
		//while(socketClient.isConnected());
		msg=in.nextLine();
		System.out.println("msg "+msg);
		//start the game after server sends start
		if(msg.equals("start")) Platform.runLater(new Runnable() {public void run() {stgame();game();}});
		
		//get client connected to server data
		if(msg.equals("uare")) {
			pla = Integer.valueOf(in.nextLine());
			System.out.println(pla);
			//Platform.runLater(new Runnable() {public void run() {Que();}});
		}
		if(msg.equals("urturn")) {
			turn=true;
			Platform.runLater(new Runnable() {public void run() {game("Your Turn!!");}});
			//winput a = new winput();a.start();
		}
		if(msg.equals("clients")&&(game==false)) {
			int l= Integer.valueOf(in.nextLine());
			cclients.clear();
			System.out.println("z"+l);
			for(int i=0;i<l;i++) {
				String k = in.nextLine();
				System.out.println("in "+k);
				cclients.add(Integer.valueOf(k));}
			
			//Platform.runLater(new Runnable() {public void run() {Que();}});
		}
		
		//not using
		if(msg.equals("challenged")) {
			//function to change scene to que and wait despite update in client list!!!
			game=true;
			cpts=Integer.valueOf(in.nextLine());
			winput wst = new winput();wst.start();
			//Platform.runLater(new Runnable() {public void run() {game();}});
			}
		
		//call win or lose and continue the game
		if(msg.equals("win")||msg.equals("lose")||msg.equals("tie"))
		Platform.runLater(new Runnable() {public void run() {System.out.println("wuns");opplay=in.nextLine();play(msg);}});	
		if(msg.equals("lose game"))Platform.runLater(new Runnable() {public void run() {wlscreen("lose");}});
	}
}


public void move(String move) {out.println(move);}

public static void main(String[] args) {launch(args);}

}
