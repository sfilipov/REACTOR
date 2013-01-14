package simulator; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;


public class TextUI extends JFrame implements KeyListener 
{
	private final static String START_TEXT = 
			"To start a name game type\t\t: newgame\n"
            +"To load a saved game type\t\t: loadgame\n"
            +"To view highscore type\t\t\t: highscores\n"
            +"To view credits type\t\t\t: credits\n";
	// This looks weird because the backslashes need escaping!
	private final static String REACTOR_ASCII =  
    " _______  _______  _______  _______ _________ _______  _______\n"
    +"(  ____ )(  ____ \\(  ___  )(  ____ \\\\__   __/(  ___  )(  ____ )\n"
    +"| (    )|| (    \\/| (   ) || (    \\/   ) (   | (   ) || (    )|\n"
    +"| (____)|| (__    | (___) || |         | |   | |   | || (____)|\n"
    +"|     __)|  __)   |  ___  || |         | |   | |   | ||     __)\n"
    +"| (\\ (   | (      | (   ) || |         | |   | |   | || (\\ (\n" 
    +"| ) \\ \\__| (____/\\| )   ( || (____/\\   | |   | (___) || ) \\ \\__\n"
    +"|/   \\__/(_______/|/     \\|(_______/   )_(   (_______)|/   \\__/\n"
    +"==============================================================\n";

	private PlantPresenter presenter;
	
	// UI variables
	private JTextArea systemText = new JTextArea(10,20);
    private JTextArea outputText = new JTextArea(10,20);
    private JTextArea inputText = new JTextArea(10,20);
    private JTextField inputBox = new JTextField(30);
    private final static Font default_font = new Font("Monospaced",Font.PLAIN, 12);
    private final static String prompt = "> ";
    
    
    public TextUI(PlantPresenter presenter)
    {
    	super("REACTOR");
    	this.presenter = presenter;
        initWindow();
        startUp();     
    }

    private void initWindow() {
    	setSize(900,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel leftPanel = new JPanel();           
        GridBagLayout leftPanelGridBagLayout = new GridBagLayout();
        leftPanel.setLayout(leftPanelGridBagLayout);
        initInputArea(leftPanel);
        initOutputArea(leftPanel);
        
        JPanel rightPanel = new JPanel();
        initSystemArea(rightPanel);
        
        GridLayout rightPanelGridLayout = new GridLayout(1,1);
        rightPanel.setLayout(rightPanelGridLayout);
        
        GridLayout grid = new GridLayout(1,2, 0, 0);
        setLayout(grid);
        add(leftPanel);
        add(rightPanel);
        setVisible(true);
    }
    
    private void initInputArea(JPanel parentPanel) {
    	GridBagLayout layout = (GridBagLayout) parentPanel.getLayout(); 
  
    	inputText.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.GRAY));
        inputText.setBackground(Color.BLACK);
        inputText.setForeground(Color.WHITE);
        inputText.setLineWrap(true);  
        inputText.setWrapStyleWord(true);
        inputText.setFont(default_font);  
        inputText.setEditable(false);
        
        JScrollPane Scrolled = new JScrollPane(inputText);
        Scrolled.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        GridBagConstraints inputTextConstraints = new GridBagConstraints();
        inputTextConstraints.gridx = 0;
        inputTextConstraints.gridy = 1;
        inputTextConstraints.gridwidth = 1;
        inputTextConstraints.gridheight = 1;
        inputTextConstraints.weightx = 10;
        inputTextConstraints.weighty = 5;
        inputTextConstraints.fill = GridBagConstraints.BOTH;
        inputTextConstraints.anchor = GridBagConstraints.SOUTH;
        layout.setConstraints(Scrolled, inputTextConstraints);        
        parentPanel.add(Scrolled); 
                
        
        inputBox.setBackground(Color.BLACK);
        inputBox.setForeground(Color.WHITE);
        inputBox.setFont(default_font);
        inputBox.setCaretColor(inputBox.getForeground());
        
        GridBagConstraints inputBoxConstraints = new GridBagConstraints();
        inputBoxConstraints.gridx = 0;
        inputBoxConstraints.gridy = 2;
        inputBoxConstraints.gridwidth = 1;
        inputBoxConstraints.gridheight = 1;
        inputBoxConstraints.weightx = 10;
        inputBoxConstraints.weighty = 1;
        inputBoxConstraints.fill = GridBagConstraints.BOTH;
        inputBoxConstraints.anchor = GridBagConstraints.SOUTH;
        layout.setConstraints(inputBox, inputBoxConstraints);
        inputBox.addKeyListener(this);
        parentPanel.add(inputBox);
    }
    
    private void initOutputArea(JPanel parentPanel) {
    	GridBagLayout layout = (GridBagLayout) parentPanel.getLayout();
    	
    	outputText.setBorder(BorderFactory.createMatteBorder(0,0,2,1,Color.GRAY));
        outputText.setBackground(Color.BLACK);
        outputText.setForeground(Color.WHITE); 
        outputText.setFont(default_font);
        outputText.setLineWrap(true);  
        outputText.setWrapStyleWord(true);
        outputText.setEditable(false);
        
        JScrollPane Scroll = new JScrollPane(outputText);
        Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         
        GridBagConstraints OutputTextConstraints = new GridBagConstraints();
        OutputTextConstraints.gridx = 0;
        OutputTextConstraints.gridy = 0;
        OutputTextConstraints.gridwidth = 1;
        OutputTextConstraints.gridheight = 1;
        OutputTextConstraints.weightx = 10;
        OutputTextConstraints.weighty = 100;
        OutputTextConstraints.fill = GridBagConstraints.BOTH;
        OutputTextConstraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(Scroll, OutputTextConstraints);
        parentPanel.add(Scroll);
    }
    
    private void initSystemArea(JPanel parentPanel) {

        systemText.setBackground(Color.BLACK);
        systemText.setForeground(Color.WHITE);
        systemText.setFont(default_font);
        systemText.setEditable(false);
        parentPanel.add(systemText);
    }
    
    private void startUp()
    {
        outputText.setText(START_TEXT + REACTOR_ASCII);
        inputText.setText(prompt);
        inputBox.setText(prompt);
    }
    
    public void keyReleased(KeyEvent k)
    {
    	
    }
    
    public void keyTyped(KeyEvent k)
    { 
    }
    
    public void keyPressed(KeyEvent k) 
    {    	
        int keyCode = k.getKeyCode();
        switch (keyCode) {
			case KeyEvent.VK_ENTER:
				actUponInput();
				break;	
        }
    }
    
    private void actUponInput() {
    	String command = inputBox.getText().toLowerCase().substring(prompt.length());
		// parse(command);
		inputText.setText(inputText.getText() + command + "\n" + prompt); 
		inputBox.setText(prompt);
    }
 
    
}
