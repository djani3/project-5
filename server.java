import java.io.*;	
import java.net.*;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.javafx.font.t2k.T2KFactory;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.stage.*;

public class server extends Application{

	ToggleButton onoff;
	Text t1 = new Text();
	Group root = new Group();//use this in  game to update values 
	Stage window = new Stage();
	ServerSocket mysocket;
	int connectedp;Text t2 = new Text();Group g2 = new Group();
	String p1="";String p2="";
	int c1p=0,c2p=0;
	String c1h="Ntg yet",c2h="Ntg Yet";
	Text c1 = new Text();Text c2 = new Text();
	Text c1pn=new Text();Text c2pn =new Text();
	ArrayList<Integer> clientsc = new ArrayList<Integer>();
	int challenged;int challenger;
	
	ArrayList<ThreadServer> clients = new ArrayList<ThreadServer>();//4 clients
	ArrayList<Line> cbox = new ArrayList<Line>();//Array of Lines
	
	
	public void start(Stage primaryStage)throws IOException {
		//all initializations
		window = primaryStage;
		primaryStage.setTitle("Connect The Dots Game!! (Server)");
		Group root = new Group();
		Scene scene = new Scene(root,500,500);
		primaryStage.setScene(scene);
		StackPane ss1= new StackPane();
		Scene ss = new Scene(ss1,500,500);
		
		onoff=new ToggleButton();
		onoff.setText("On / Off");
		onoff.setLayoutX(220);
		onoff.setLayoutY(220);
		root.getChildren().add(onoff);//on/off button
		
		Label label1 = new Label("Port Number:");
		Button s = new Button();
		TextField textField = new TextField ();
		textField.setText("5555");
		HBox hb = new HBox();
		
		t1.setText("Server is : ON");
		t1.setX(215);
		t1.setY(270);
		root.getChildren().add(t1);//Server oN/off text
		hb.getChildren().addAll(label1, textField,s);
		root.getChildren().add(hb);
		
		//Visibiliy states
		t1.setVisible(false);
		label1.setVisible(false);
		s.setVisible(false);
		textField.setVisible(false);
		hb.setVisible(false);
		
		//Server On/Off Action event
		onoff.setOnAction(e->{
			if(onoff.isSelected()) {
			primaryStage.setScene(scene);
			s.setText("Submit");
			hb.setSpacing(10);
			hb.setLayoutX(100);
			hb.setLayoutY(300);
			
			s.setOnAction(ev->{
				if(s.getText()!=null&&!s.getText().isEmpty()) {
					try {
						//THREADED SERVER ACCESS COde!
						hb.setVisible(false);t1.setVisible(false);s.setVisible(false);textField.setVisible(false);
						mysocket = new ServerSocket(Integer.valueOf(textField.getText()));
						waitc now = new waitc(mysocket);
						now.start();
						//waiting for clients and game starts in game()
						connect();
				
						if(!onoff.isSelected()) {
							mysocket.close();
							Text t3 = new Text();
							t3.setText("Server is turned OFF!");
							t3.setX(210);t3.setY(260);
							StackPane g3 = new StackPane();
							g3.getChildren().add(t3);
							g3.getChildren().add(onoff);
							Scene sc = new Scene(g3,500,500);
							primaryStage.setScene(sc);
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();}
					}});
			
			t1.setVisible(true);
			label1.setVisible(true);
			s.setVisible(true);
			textField.setVisible(true);
			hb.setVisible(true);}
			else {hb.setVisible(false);t1.setVisible(false);s.setVisible(false);}
			});
		primaryStage.show();
	}
	
	/*not usin
	public void stgame(){
		
		t2.setText("Clients Connected Game  Has Started!!");

		c1.setText("Client 1 played: "+c1h);c2.setText("Client 2 played: "+c2h);
		c1pn.setText("Client 1 : "+c1p);c2pn.setText("Client 2 points: "+c2p);
		
		c1.setX(70);c1.setY(280);c2.setX(300);c2.setY(280);
		c1pn.setX(70);c1pn.setY(350);c2pn.setX(300);c2pn.setY(350);
		
		g2.getChildren().add(c1);g2.getChildren().add(c2);
		g2.getChildren().add(c1pn);g2.getChildren().add(c2pn);
		
		window.show();
		
		new wcin(0).start();new wcin(1).start();
	}
	
	//not usin
	public void game(int pl) {
		if(pl==1||pl==2)t2.setText("Client "+pl + " Won the Hand! Next hand is Being played right now!!");
		else if(pl==3)t2.setText("Client 1 has WON THE GAME ");
		else if(pl==4)t2.setText("Client 2 has WON THE GAME ");
		else if(pl==0)t2.setText("It was a TIE !!! next hand is being played right now!!");
		c1.setText("Client 1 played: "+c1h);c2.setText("Client 2 played: "+c2h);
		c1pn.setText("Client 1 points: "+c1p);c2pn.setText("Client 2 points: "+c2p);
	}*/
	
	
	public void connect() {
		t2.setText("Waiting for Clients.....Connected: "+connectedp);
		t2.setX(50);t2.setY(150);
		g2.getChildren().add(t2);
		g2.getChildren().add(onoff);
		Scene s1 = new Scene(g2,500,500);
		window.setScene(s1);
	}
	
	//change
	public void updatec() {
		Platform.runLater(new Runnable() {public void run() {
			t2.setText("Clients Connected: "+connectedp);window.show();
			if(connectedp==4) {sendStart();}
		}});
	}
	

	//sending start to all 4 clients -VIKRAM
	public void sendStart() {
		//send start to both clients
		System.out.println("sent starts");
		for(int i =0;i<clients.size();i++) {
		clients.get(i).out.println("start");}
	}
	
	public void sendclients() {
		for(int i=0;i<clients.size();i++) {
			clients.get(i).out.println("clients");
			clients.get(i).out.println(clientsc.size());
			for(int j=0;j<clientsc.size();j++) {
				clients.get(i).out.println(clientsc.get(j));
				}
		}
	}
	
	
	/*public void wl(int p,String m) {
		if(p==challenged)p1=m;
		else p2=m;
		System.out.println("calling decide"+p);
		callhand();
	}
	

	//no need
public void p1win(String p1a,String p2a) {
	c1p++;
	if(c1p==3) {
		//c1 wins
		Platform.runLater(new Runnable() {
			public void run() {
		game(3);}});
		clients.get(challenger).out.println("lose game");
		clients.get(challenged).out.println("win");
		clients.get(challenged).out.println(p2a);
	}
	else {
		clients.get(challenged).out.println("win");
		clients.get(challenged).out.println(p2a);//send oppsite side moves same in below function
		clients.get(challenger).out.println("lose");
		clients.get(challenger).out.println(p1a);
		c1h=p1a;c2h=p2a;
		p1="";p2="";
	Platform.runLater(new Runnable() {
		public void run() {
			game(1);
		}
	});}
	new wcin(0).start();new wcin(1).start();
	
}
//no need
public void p2win(String p1b,String p2b) {
	c2p++;
	if(c2p==3) {
		//c2 wins
		Platform.runLater(new Runnable() {
			public void run() {
		game(4);}});
		
		clients.get(challenged).out.println("lose game");
		clients.get(challenger).out.println("win");
		clients.get(challenger).out.println(p1b);
	}
	else {
		clients.get(challenger).out.println("win");
		clients.get(challenger).out.println(p1b);
		clients.get(challenged).out.println("lose");
		clients.get(challenged).out.println(p2b);
		c1h=p1b;c2h=p2b;
		p1="";p2="";
	Platform.runLater(new Runnable() {public void run() {game(2);}});
	new wcin(0).start();new wcin(1).start();}
}
//no need
public void tie(String p1c,String p2c){
	clients.get(challenged).out.println("tie");
	clients.get(challenged).out.println(p1c);
	clients.get(challenger).out.println("tie");
	clients.get(challenger).out.println(p1c);
	c1h=p1c;c2h=p2c;
	p1="";p2="";
	Platform.runLater(new Runnable() {public void run() {game(0);}});
	new wcin(0).start();new wcin(1).start();
}*/


void checkclients() {
	//use .isConnected!
	clientsc.clear();
	for(int i=0;i<clients.size();i++) {
		if(!clients.get(i).Connection.isClosed()) {
			clientsc.add(clients.get(i).p);
		}
		else clients.remove(i);
	}
}

//wait for multiple clients
public class waitc extends Thread{
	
	ServerSocket wait;
	int p=-1;
	waitc(ServerSocket i){this.wait=i;}
	public void run() {
		while(true) {
			try {
				Socket so=wait.accept();
				ThreadServer n = new ThreadServer(so,++p);System.out.println(p);clients.add(n);n.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}


	//THREADED SERVER
	public class ThreadServer extends Thread{
		Scanner in;PrintWriter out;ObjectInputStream in1;
		Socket Connection;
		int p;
		ThreadServer(Socket s,int i){this.Connection =s;this.p=i;}
		String cmsg;
		
		public void run() {
			try {
			 in = new Scanner(Connection.getInputStream());
			 in1 = new ObjectInputStream(Connection.getInputStream());
			 out = new PrintWriter(Connection.getOutputStream(), true);
			connectedp++;
			clientsc.add(p);
			/*not using
			out.println("uare");
			out.println(p);
			sendclients();*/
			checkclients();
			updatec();
			 wcin a = new wcin(p);a.start();
			}catch(IOException e){System.out.println("lel this Thread Server bombed");}
		}
	}
	
	public class wcin extends Thread {
		int p ;String cmsg="1";
		wcin(int c){
		p=c;}
			 public void run() {
				 //while(!clients.get(p).in.hasNextLine()&&clients.get(p).Connection.isConnected());
				 while(true) {
						try{String line  = clients.get(p).in.nextLine();cmsg=line;}catch(Exception e) {
							if(e instanceof NoSuchElementException){
								clientdiscon(p);
							}
						}
						System.out.println("Server Thread "+p+ " received: "+ cmsg);
						if(cmsg.equals("continue")||cmsg.equals("exit")) {
							//play again if both clients are playing else close
						}
						else if(cmsg.equals("iwin")) {
							cwon(p);
							break;
						}
						//----------Receive Line info and Call Function
						else if(cmsg.equals("nline")){
						//take input from client with 4 lines after the msg x y point 1 then  x y point 2 to enter into a Line and Now check validity.
						//enter line into arrayList and send the updates data to all clients
						gline fc = new gline(new Point(Integer.parseInt(clients.get(p).in.nextLine()),Integer.parseInt(clients.get(p).in.nextLine())),
								new Point(Integer.parseInt(clients.get(p).in.nextLine()),Integer.parseInt(clients.get(p).in.nextLine())));
						//add line fc to ArrayList.and send this new Line to all other clients to update their 
						cbox.add(fc);
						System.out.println("got line");
						sendLinec(fc,p);
						startTurn();
						//wcin pa = new wcin(p);pa.start();
						}
						else if(cmsg.equals("taken")) {
							int s = Integer.parseInt(clients.get(p).in.nextLine());
							sendtal(s,p);
						}
						//else
						//wl(p,cmsg);
						}}}
	
	
	
	public static void main(String[] args)throws IOException{
		launch(args);
	}

}


	   
	
