package enrico;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartUp {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUp window = new StartUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartUp() throws IOException {
		initialize();

	}

	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(0, 0, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton botao;
		switch (System.getProperty("os.name").split(" ")[0]){
			case "Windows":
				if(new File(System.getProperty("user.home")+"\\Start Menu\\Programs\\Startup\\enrico.bat").isFile())
					botao = new JButton("Cancel");
				else
					botao = new JButton("OK");
				break;
			case "Linux":
				if(new File("/etc/init.d/enrico.sh").isFile())
					botao = new JButton("Cancel");
				else
					botao = new JButton("OK");
				break;
			default:
				botao = null;
		}
		botao.setBounds(100, 100, 100, 100);
		botao.setVisible(true);
		botao.addActionListener(new Enable());
		frame.add(botao);

	}
	
	static class Enable implements ActionListener {        
		@Override  
		public void actionPerformed (ActionEvent e) {
			Object o = e.getSource();
			if (o instanceof JButton){
				JButton b = (JButton) o;
				if (e.getActionCommand().equals("OK")){
					okAction(b);
				}else if (e.getActionCommand().equals("Cancel")){
					cancelAction(b);
				}
			}
		 }

		private void cancelAction(JButton b) {
			File file = null;
			switch (System.getProperty("os.name").split(" ")[0]){
				case "Windows":
					file = new File(System.getProperty("user.home")+"\\Start Menu\\Programs\\Startup\\enrico.bat");
					break;
				case "Linux":
					file = new File("/etc/init.d/enrico.sh");
					break;
			}
			if (file.isFile())
				file.delete();
			b.setText("OK");
		}

		private void okAction(JButton b) {
			PrintWriter writer = null;
			switch (System.getProperty("os.name").split(" ")[0]){
				case "Windows":
					try {
						writer = new PrintWriter(System.getProperty("user.home")+"\\Start Menu\\Programs\\Startup\\enrico.bat","UTF-8");
						//writer.println("@javaw -jar C:\\Users\\renan_000\\Desktop\\Nuts\\test.jar");
						writer.println("start "+System.getProperty("user.dir")+"\\test.jar");
						writer.close();
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "Linux":
					try {
						writer = new PrintWriter("enrico.sh","UTF-8");
						writer.println("#!/bin/bash");
						writer.println(new File(".").getAbsolutePath()+"./test.jar");
						writer.close();
					} catch (FileNotFoundException | UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String password = null;
					try {
						password = LinuxCommand.getPasswdForRoot();
						LinuxCommand.runFromRoot("mv enrico.sh /etc/init.d/", password);
						LinuxCommand.runFromRoot("chmod 777 /etc/init.d/enrico.sh", password);
						LinuxCommand.runFromRoot("update-rc.d enrico.sh defaults", password);						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;				
			}			
			b.setText("Cancel");
		}
	}  
}
