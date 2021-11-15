package newnotepad;
//------------------------------------------------------------------------------
import javax.swing.*;  
import java.awt.event.*;  
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
//------------------------------------------------------------------------------
public class Notepad {
    
	JFrame frame;
	JMenuBar menuBar;
	JMenu file,edit,fonnt;
	JMenuItem open,ccut, save, exit,select,post,copy,delete,neww,nnew,font,color,small,larg,medium;
	JFileChooser fileChooser;
	JTextArea textArea;
        JColorChooser ccc;
//------------------------------------------------------------------------------
	Notepad() {
		frame = new JFrame("Notepad Application");
		file = new JMenu("File");
                edit =new JMenu("Edit");
		open = new JMenuItem("Open");
                fonnt=new JMenu("Font");
                larg=new JMenuItem("larg");
                small=new JMenuItem("small");
                copy = new JMenuItem("Copy");
                ccut=new JMenuItem("cut");
                post = new JMenuItem("Post");
		save = new JMenuItem("Save");
                select=new JMenuItem("Select All");
		exit = new JMenuItem("Exit");
                delete=new JMenuItem("Delete");
                nnew = new JMenuItem("New");
                neww=new JMenuItem("New Window");
                medium=new JMenuItem("Medium");
            //    color = new JMenu("Color");
		textArea = new JTextArea();
		fileChooser = new JFileChooser();
		menuBar = new JMenuBar();
		
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(textArea);
                file.add(nnew);
                file.add(neww);
		file.add(open);
		file.add(save);
		file.add(exit);
                edit.add(ccut);
                edit.add(copy);
                edit.add(post);
                edit.add(select);
                edit.add(delete);
                edit.add(fonnt);
                fonnt.add(small);
                fonnt.add(medium);
                fonnt.add(larg);
                
		menuBar.add(file);
                menuBar.add(edit);
             //   menuBar.add(color);
		frame.setJMenuBar(menuBar);
		
                WindowListener newwL=new WindowListener();
                Deletelistener deleteL=new Deletelistener();
                SelectListener selectL=new SelectListener();
                FontListener zoomL=new FontListener();
              //  ColorListener colorL = new ColorListener();
                PostListener postL = new PostListener();
                CopyListener copyL = new CopyListener();
		OpenListener openL = new OpenListener();
		SaveListener saveL = new SaveListener();
		ExitListener exitL = new ExitListener();
                CutListener cutL=new CutListener();
		open.addActionListener(openL);
		save.addActionListener(saveL);
		exit.addActionListener(exitL);
                ccut.addActionListener(cutL);
                copy.addActionListener(copyL);
                post.addActionListener(postL);
               // color.addActionListener(colorL);
                fonnt.addActionListener(zoomL);
                select.addActionListener(selectL);
                delete.addActionListener(deleteL);
                neww.addActionListener(newwL);
                        
                medium.addActionListener(zoomL);
                small.addActionListener(zoomL);
                larg.addActionListener(zoomL);
                
		
		frame.setSize(800, 600);
		frame.setVisible(true);
	}  

   
//------------------------------------------------------------------------------
        //FONT SIZE
        class FontListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==small){
                    Font f= new Font(" ",Font.ITALIC,18);
                    textArea.setFont(f);
                }
                else if (e.getSource()==larg){
                     Font f= new Font(" ",Font.BOLD,60);
                    textArea.setFont(f);
                }
                else if(e.getSource()==medium)
                {
                    Font f=new Font(" ",Font.BOLD,30);
                    textArea.setFont(f);
                }
        } 
     }
//------------------------------------------------------------------------------        
    
	class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(frame)) {
				File file = fileChooser.getSelectedFile();
				textArea.setText("");
				Scanner in = null;
				try {
					in = new Scanner(file);
					while(in.hasNext()) {
						String line = in.nextLine();
						textArea.append(line+"\n");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					in.close();
				}
			}
		}
	}
        /*
        
        
        */
//------------------------------------------------------------------------------	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame)) {
				File file = fileChooser.getSelectedFile();
				PrintWriter out = null;
				try {
					out = new PrintWriter(file);
					String output = textArea.getText();
					System.out.println(output);
					out.println(output);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {out.flush();} catch(Exception ex1) {}
					try {out.close();} catch(Exception ex1) {}
				}
			}
		}
	}

        class WindowListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==neww){
                    Notepad n=new Notepad();
                }
            }
        }
	
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	/*
        
        */
        class Deletelistener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                textArea.setText(" ");
            }
        }
        class CopyListener implements ActionListener{
                public void actionPerformed(ActionEvent ae) {
                        textArea.copy();
                }
        }
        class CutListener implements ActionListener {
                public void actionPerformed(ActionEvent ae) {
                        textArea.cut();
                }    
        }
        class PostListener implements ActionListener{
            public void actionPerformed(ActionEvent ea){
                textArea.paste();
            }
        }
        class SelectListener implements ActionListener{
            public void actionPerformed(ActionEvent ae) {
                textArea.selectAll();
            }
        }
//------------------------------------------------------------------------------
        //Main
//------------------------------------------------------------------------------
	public static void main(String args[]) {
            JFrame.setDefaultLookAndFeelDecorated(true);
		Notepad n = new Notepad();
	}
}