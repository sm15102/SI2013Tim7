package etf.si.projekat.data_vision;

import javax.swing.*;
import java.awt.Choice;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.Label;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.BarPlot.BarRenderer;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.GraphicsUtils;
import de.erichseifert.gral.util.Insets2D;
import de.erichseifert.gral.util.Location;
import ba.unsa.etf.si.beans.EventLogs;
import ba.unsa.etf.si.hibernate_klase.HibernateDeviceName;
import ba.unsa.etf.si.hibernate_klase.HibernateEventLogs;

import ba.unsa.etf.si.beans.DeviceName;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.SystemColor;


public class ThreeGraphsViewPanel extends ExamplePanel  {

	private static final long serialVersionUID = 1L;


private static final Logger LOGGER = Logger.getLogger(ThreeGraphsViewPanel.class.getName());
	List<DeviceName> listDevice=new HibernateDeviceName().giveAllDeviceName();



		InteractivePanel interactivePanel1;
		InteractivePanel interactivePanel2;
		final JTabbedPane tabbedPane;
		final JSpinner spinner = new JSpinner();
		 DataTable data1;
		
			XYPlot plot1;
			  private JPanel contentPane;
			  boolean paneliBar;
			  boolean paneliLine;
			  List<EventLogs> logs;
			  List<EventLogs> logs1;
			  List<Double>values;
			  List<Double>values1;



		private static final Color COLOR1 = Color.RED;

		 
	
		List<Double>values2;



		 // CHOICE
		 	final Choice choice= new Choice(); 
		 	final Choice choice1 = new Choice();
		    final Choice choice11= new Choice();


		    InteractivePanel interactivePanel ;


		    UtilDateModel model = new UtilDateModel();
	        JDatePanelImpl datePanel = new JDatePanelImpl(model);
	        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel); 


	        UtilDateModel model1 = new UtilDateModel();
	        JDatePanelImpl datePane1 = new JDatePanelImpl(model1);
	        final JDatePickerImpl datePicker1 = new JDatePickerImpl(datePane1);


			private int valueFirstGraph;
			private final Choice choice12 = new Choice();


	

	
	public ThreeGraphsViewPanel(JTabbedPane tabbedPane4) {
		tabbedPane = tabbedPane4;
		setBackground(SystemColor.menu);


		setLayout(null);






		// Svi elemeti za dodavanje prvog grafa
		JLabel lblTimeIntervalFrom = new JLabel("Time interval from");
		lblTimeIntervalFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeIntervalFrom.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTimeIntervalFrom.setBounds(10, 85, 113, 14);
		add(lblTimeIntervalFrom);


		JLabel lblTimeIntervalTo = new JLabel("Time interval to");
		lblTimeIntervalTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeIntervalTo.setBounds(30, 114, 93, 14);
		add(lblTimeIntervalTo);


		JLabel lblDataNumber = new JLabel("Data number");
		lblDataNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataNumber.setBounds(40, 139, 83, 14);
		add(lblDataNumber);


	// SPINNER-NOT ENABLED


		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(152, 137, 165, 18);


		add(spinner);
		spinner.setValue((Integer)2);
		spinner.setEnabled(false);

        JLabel lblGraphType = new JLabel("Graph type");
        lblGraphType.setHorizontalAlignment(SwingConstants.RIGHT);
        lblGraphType.setBounds(55, 60, 68, 14);
        add(lblGraphType);
       
        SpringLayout springLayout1 = (SpringLayout) datePicker.getLayout();
        springLayout1.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
        datePicker.setLocation(152, 75);
        datePicker.setSize(165, 29);
       add(datePicker);
        
        
       
        SpringLayout springLayout2 = (SpringLayout) datePicker1.getLayout();
        springLayout2.putConstraint(SpringLayout.SOUTH, datePicker1.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker1);
        datePicker1.setLocation(152, 104);
        datePicker1.setSize(165, 29);
      add(datePicker1);
      
      
      choice.setBounds(152, 51, 165, 23);
      choice.add("Line");
      choice.add("Bar");
      choice.setVisible(true);
      add(choice);
      
      Label label = new Label("Basic data");
      label.setBounds(10, 10, 65, 18);
      add(label);
      
      JSeparator separator = new JSeparator();
      separator.setBackground(SystemColor.textHighlight);
      separator.setBounds(10, 34, 306, 15);
      add(separator);
      
      final JLabel lblSensorType = new JLabel("Sensor type");
      lblSensorType.setBounds(10, 172, 77, 14);
      add(lblSensorType);
      
      final JSeparator separator1 = new JSeparator();
      separator1.setBounds(222, 220, -220, -5);
      separator1.setVisible(false);
      add(separator1);
      
      
      final JSeparator separator2 = new JSeparator();
      separator2.setBackground(SystemColor.textHighlight);
      separator2.setBounds(10, 233, 618, -5);
      add(separator2);
      
      final  JLabel label1 = new JLabel("Sensor type 1");
      label1.setHorizontalAlignment(SwingConstants.RIGHT);
      label1.setBounds(28, 220, 95, 18);
      label1.setVisible(true);
      add(label1);
      
      choice1.setBounds(152, 220, 165, 20);
      choice1.setVisible(true);
      add(choice1);
      


		lblSensorType.setVisible(false);
		separator2.setVisible(true);




		setValueFirstGraph((Integer) spinner.getValue());
      
      final Label label13 = new Label("Sensor type 2");
      label13.setFont(new Font("Dialog", Font.BOLD, 11));
      label13.setAlignment(Label.RIGHT);
      label13.setBounds(40, 253, 83, 14);
      add(label13);
      
      JSeparator separator4 = new JSeparator();
      separator4.setBounds(622, 30, -273, 15);
      add(separator4);
      
      final  JSeparator separator5 = new JSeparator();
      separator5.setBackground(SystemColor.textHighlight);
      separator5.setBounds(10, 487, 618, -10);
    
      add(separator5);
      
      final  JSeparator separator6 = new JSeparator();
      separator6.setBounds(720, 220, -91, 22);
      add(separator6);
     
      
      // GENERIRAJ AKCIJA
      		final JButton btnGenerateGraphs = new JButton("Generate graphs");
      		btnGenerateGraphs.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent arg0) {
      			}
      		});
      		btnGenerateGraphs.addMouseListener(new MouseAdapter() {
      			@Override
      			public void mouseClicked(MouseEvent arg0) {
      				choice1.enable();
      				choice12.enable();
      				
      				boolean islect=false;
      	      		boolean date1Beforedate2=false;
      	      		boolean inFuture1=false;
      	      		boolean inFuture2=false;

      	      		if(datePicker.getModel().isSelected() && datePicker1.getModel().isSelected()){
      					islect=true;
      				}else{
      	      			JOptionPane.showMessageDialog(null, "Time is not selected");
      	      		}
      	      		if(islect){
      	      		Date dateString = (Date) datePicker.getModel().getValue();
      		  		String dateFrom = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString);
      		  		Date dateString1 = (Date) datePicker1.getModel().getValue();
      		  		String dateTo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString1);	
      				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      				Date dateStart;
      				Date dateEnd;
      				Date dateNow;
      		        String now=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
      		        
      					try {
      						dateStart = sdf.parse(dateFrom);
      						dateEnd = sdf.parse(dateTo);
      					    dateNow = sdf.parse(now);
      					    

      	      	    if( dateStart.compareTo(dateEnd) < 0) {
      	      	    	date1Beforedate2=true;
      	      	    }else{
      	      	    	JOptionPane.showMessageDialog(null, "'Time interval from' is before 'Time interval to'");
      	      	    	}
      	      	    if(dateStart.compareTo(dateNow) < 0){
      	    	    	inFuture1=true;
      	    	    }else{
      	    	    	JOptionPane.showMessageDialog(null, "'Time interval from' is in the future");
      	    	    }
      	      	    if(dateEnd.compareTo(dateNow) < 0){
      	      	    	inFuture2=true;
      	      	    }else{
      	      	    	JOptionPane.showMessageDialog(null, "'Time interval to' is  in the future");

      	      	    }
      				} catch (ParseException e) {
      					
      					LOGGER.log( Level.SEVERE, "context", e );
      					}
      				
      	      		}
      	      		
      	      		if(date1Beforedate2 && inFuture1 && inFuture2){
      				
      				
      				
      				if("Line".equals(choice.getSelectedItem().toString())){
      					graf();
      					
      				}else{
      					grafBar();
      				}
      			}
      			}
      		});
      	
      		
      		
      		btnGenerateGraphs.setBackground(SystemColor.control);
      		btnGenerateGraphs.setVisible(true);
      		btnGenerateGraphs.setBounds(183, 313, 134, 23);
      		add(btnGenerateGraphs);


	      JSeparator separator3 = new JSeparator();
	      separator3.setBackground(SystemColor.textHighlight);
	      separator3.setBounds(10, 199, 306, -13);
	      add(separator3);


	      JSeparator separator7 = new JSeparator();
	      separator7.setBackground(SystemColor.textHighlight);
	      separator7.setBounds(10, 299, 307, 14);
	      add(separator7);
	      choice12.setBounds(152, 247, 165, 20);
	      add(choice12);


	      JSeparator separator8 = new JSeparator();
	      separator8.setBackground(SystemColor.textHighlight);
	      separator8.setBounds(10, 186, 306, 15);
	      add(separator8);




	      for (int i=0; i<listDevice.size(); i++){
		    	 choice1.add(listDevice.get(i).getName() );
		    	

	    }
	      choice1.addMouseListener(new MouseAdapter() {
	        	@Override
	          	public void mouseClicked(MouseEvent arg0) {
	        		 for (int i=0; i<listDevice.size(); i++){
	        			 if(choice1.getSelectedItem()==listDevice.get(i).getName()){
	        				 continue;
	        			 }
	        			 choice12.add(listDevice.get(i).getName() );
	                      choice1.disable();
	        		 }
		
				}
			});
	      choice12.addMouseListener(new MouseAdapter() {
	        	@Override
	          	public void mouseClicked(MouseEvent arg0) {
	        		
	                      choice12.disable();
	        		 
		
				}
			});

	}
	public void graf(){
				boolean have1=true;
				boolean have2=true;

							 
				//Podaci koji ce se prikazivati na grafu 
					Date dateString = (Date) datePicker.getModel().getValue();
			  		String dateFrom = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString);
			  		Date dateString1 = (Date) datePicker1.getModel().getValue();
			  		String dateTo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString1);	
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date dateStart;
					Date dateEnd;
					
					  try {
						dateStart = sdf.parse(dateFrom);
						dateEnd = sdf.parse(dateTo);
						 

						try {
							logs=new ArrayList<EventLogs>();
							
							logs=new HibernateEventLogs().getdatesbetween(choice1.getSelectedItem(),dateStart,dateEnd);
							}catch(Exception e){
								LOGGER.log( Level.SEVERE, "Ne poklapaju se vrijednosti", e );
							}
						logs.size();
						if(logs.isEmpty()){
							have1=false;
							JOptionPane.showMessageDialog(null, "Graph one is empty");
							}
						values=new ArrayList<Double>();
						for(int i=0; i<logs.size();i++){
							values.add(logs.get(i).getValue());
						}
					  }catch(ParseException e1){
						  LOGGER.log( Level.SEVERE, "context", e1 );
						  
						  }
					  
					  DataTable d=new DataTable(Long.class, Double.class, String.class);
					  					  
					  for(int i=0;i<logs.size();i++){
						  d.add(logs.get(i).getTimestamp().getTime(), values.get(i),logs.get(i).getDevice_name());
					  }
					  
					  
					  //two graph
					  Date dateString2 = (Date) datePicker.getModel().getValue();
					  String dateFrom1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString);
					  Date dateString3 = (Date) datePicker1.getModel().getValue();
					  String dateTo1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString1);	
					  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					  Date dateStart1;
					  Date dateEnd1;
					  
					  try{
						  dateStart1 = sdf1.parse(dateFrom1);
						  dateEnd1 = sdf1.parse(dateTo1);
						  try{
							  dateStart = sdf.parse(dateFrom);
							  dateEnd = sdf.parse(dateTo);
							  logs1=new ArrayList<EventLogs>();
							  logs1=new HibernateEventLogs().getdatesbetween(choice12.getSelectedItem(),dateStart1,dateEnd1);
						  }catch(ParseException e){
							  LOGGER.log(Level.SEVERE,"context",e);
						  }
						  
						  logs1.size();
						  if(logs1.isEmpty()){
							  have1=false;
							  JOptionPane.showMessageDialog(null, "Graph two is empty");
						  }
						  values1=new ArrayList<Double>();
						  for(int i=0; i<logs1.size();i++){
							  values1.add(logs1.get(i).getValue());
						  }
					  }catch(ParseException e1){
						  LOGGER.log(Level.SEVERE,"Ne poklapaju se vrijednosti", e1);
					  }
					  
					  
					  //------------------------------
					  
					  if(have1 && have2){
						  XYPlot plot = new XYPlot(d);
						  plot.setInsets(new Insets2D.Double(30.0, 20.0, 40.0, 0));
						  plot.getTitle().setText("Line plot");
						  plot.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);
						  
						  AxisRenderer rendererX = plot.getAxisRenderer(XYPlot.AXIS_X);
						  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						  rendererX.setTickLabelFormat(dateFormat);
		  
						  LineRenderer lines = new DefaultLineRenderer2D();
						  plot.setLineRenderer(d, lines);
						  Color color = new Color(0.0f, 0.3f, 1.0f);
						  plot.getPointRenderer(d).setColor(color);
						  plot.getLineRenderer(d).setColor(color);
						  
						  plot.getPlotArea().setBorderColor(new Color(0.0f, 0.3f, 1.0f));
	         
						  DataTable d1=new DataTable(Long.class, Double.class, String.class);
				 				  
						  for(int j=0;j<logs1.size();j++){
							  d1.add(logs1.get(j).getTimestamp().getTime(), values1.get(j),logs1.get(j).getDevice_name());
						  }
						  					  
						  XYPlot plot1 = new XYPlot(d1);
						  plot1.setInsets(new Insets2D.Double(30.0, 510.0, 40.0, 0));
	
						  plot1.getTitle().setText("Line plot");
		
						  plot1.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);
   
						  AxisRenderer rendererX1 = plot1.getAxisRenderer(XYPlot.AXIS_X);
						  DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						  rendererX1.setTickLabelFormat(dateFormat1);
	  
						  LineRenderer lines1 = new DefaultLineRenderer2D();
						  plot1.setLineRenderer(d1, lines1);
						  Color color1 = new Color(0.0f, 0.3f, 1.0f);
						  plot1.getPointRenderer(d1).setColor(color1);
						  plot1.getLineRenderer(d1).setColor(color1);
       
						  plot1.getPlotArea().setBorderColor(new Color(0.0f, 0.3f, 1.0f));
       
       
						  values2=new ArrayList<Double>();
						  double vel;
						  int k;
						  if(logs1.get(logs1.size()-1).getTimestamp().getDate()<logs.get(logs.size()-1).getTimestamp().getDate()){
							  k=logs1.size();
						  }else{
							  k=logs.size();  
						  }
						  DataTable dt=new DataTable(Double.class, Double.class);
				
						  for(int i=0;i<k;i++){
							  for(int j=i;j<k;j++){
								  if(logs1.get(i).getTimestamp().getDate()==logs.get(j).getTimestamp().getDate()){
									  dt.add(logs1.get(i).getValue(),logs.get(j).getValue());
									  break;
								  }
							}
						  }
						  
						  XYPlot plot3 = new XYPlot(dt);
						  plot3.setVisible(dt, true);
						  plot3.setInsets(new Insets2D.Double(260.0, 200.0, 40,0));
			              plot3.getTitle().setText("Combined graphs");
		         
			              LineRenderer lines3 = new DefaultLineRenderer2D();
			              plot3.setLineRenderer(dt, lines3);
			              Color color3 = new Color(0.0f, 0.3f, 1.0f);
			              plot3.getPointRenderer(dt).setColor(color3);
			              plot3.getLineRenderer(dt).setColor(color3);
		 	              plot3.getAxisRenderer(XYPlot.AXIS_X).setTickSpacing(1.0);
		 	              plot3.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);
		         
		 	              interactivePanel = new InteractivePanel(plot);
		 	              interactivePanel.setBounds(new Rectangle(0, 0, 440, 230));
		 	              interactivePanel2 = new InteractivePanel(plot3);
		 	              interactivePanel2.setBounds(new Rectangle(0,0, 540,500));
				          interactivePanel1 = new InteractivePanel(plot1);
				          interactivePanel1.setBounds(new Rectangle(0, 0, 950, 230));
				     
				          contentPane = new JPanel();
				          contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
			 			
				          tabbedPane.addTab("Three graphs", contentPane);
				          contentPane.setVisible(true);
				          contentPane.add(interactivePanel);
			 			
				          contentPane.add(interactivePanel1);
			 			  contentPane.add(interactivePanel2);
			 			     	
			 			  interactivePanel.setLayout(null);	
			 			  interactivePanel1.setLayout(null);
			 			  interactivePanel2.setLayout(null);
			 		
			 			  contentPane.setLayout(null);
			 			  tabbedPane.setSelectedIndex(1);
			  	 				
			 			  final JButton btnChange = new JButton("Change data");
			 			  btnChange.addMouseListener(new MouseAdapter() {
			 			       	@Override
			 			       	public void mouseClicked(MouseEvent arg0) {
			 			       		tabbedPane.setSelectedIndex(0);
			 			       	}
			 			       });
			 			  btnChange.setBounds(650, 492, 137, 23);
			 			  contentPane.add(btnChange);
			 				
			 			  final JLabel lblExport= new JLabel("To export graph, make right click, and choose Export Image.");
			 			  lblExport.setBounds(40, 499, 137, 23);
			 			  lblExport.setSize(400, 15);
			 			  lblExport.setForeground(Color.red);
			 			  contentPane.add(lblExport);
			 			  final JButton btnExit = new JButton("Cancel");
			 				
			 			  btnExit.setBounds(800, 492, 137, 23);
			 			  contentPane.add(btnExit);
			 			
			 			  btnExit.addActionListener(new ActionListener() {
			 				  public void actionPerformed(ActionEvent e) {
			 					tabbedPane.remove(1);
			 								 							
			 				    tabbedPane.remove(0);
			 				    ThreeGraphsViewPanel threeGraphs= new ThreeGraphsViewPanel(tabbedPane);
			 					tabbedPane.add("Three graphs",threeGraphs);
			 					threeGraphs.setLayout(null);
			 				    
			 					tabbedPane.setSelectedIndex(0);
			 				  }
			 			  });
			 			  
					  }
	}
	
	void grafBar(){
			
			boolean have1=true;
			boolean have2=true;

		      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			//Podaci koji ce se prikazivati na grafu 
			Date dateString = (Date) datePicker.getModel().getValue();
	  		String dateFrom = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString);
	  		Date dateString1 = (Date) datePicker1.getModel().getValue();
	  		String dateTo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString1);	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date dateStart;
			Date dateEnd;
			
			  try {
				  dateStart = sdf.parse(dateFrom);
				  dateEnd = sdf.parse(dateTo);
				  
				  try{
					  logs=new HibernateEventLogs().getdatesbetween(choice1.getSelectedItem(),dateStart,dateEnd);
				  }catch(Exception e){
					  LOGGER.log(Level.SEVERE,"context",e);
				  }
				  
				  logs.size();
				  if(logs.isEmpty()){
					  have1=false;
					  JOptionPane.showMessageDialog(null, "Graph one is empty");
				  }
				  values=new ArrayList<Double>();
				  for(int i=0; i<logs.size();i++){
					  values.add(logs.get(i).getValue());           
				  }
				  			
				}catch(Exception e1){
					LOGGER.log(Level.SEVERE,"Ne poklapaju se vrijednosti",e1);
				}
			  
			  
			  //two graph
			  
			  Date dateString2 = (Date) datePicker.getModel().getValue();
		  		String dateFrom1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString);
		  		Date dateString3 = (Date) datePicker1.getModel().getValue();
		  		String dateTo1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString1);	
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dateStart1;
				Date dateEnd1;
				
				  try {
					  dateStart1 = sdf1.parse(dateFrom1);
					  dateEnd1 = sdf1.parse(dateTo1);
					  try{
						  dateStart = sdf.parse(dateFrom);
						  dateEnd = sdf.parse(dateTo);
						  logs1=new HibernateEventLogs().getdatesbetween(choice12.getSelectedItem(),dateStart1,dateEnd1);
					  }catch(ParseException e){
						  LOGGER.log(Level.SEVERE,"context",e);
					  }
					  
					logs1.size();
					if(logs1.isEmpty()){
						have2=false;
						JOptionPane.showMessageDialog(null, "Graph two is empty");
					
					}
					values1=new ArrayList<Double>();
					for(int i=0; i<logs1.size();i++){
						values1.add(logs1.get(i).getValue());						 
					}
				  }catch(ParseException e1){
					  LOGGER.log(Level.SEVERE,"Ne poklapaju se vrijednosti",e1);
				  }
				  
				  if(have1 && have2){
					  DataTable d=new DataTable(Long.class, Double.class, String.class);
					  for(int i=0;i<logs.size();i++){
						  d.add(logs.get(i).getTimestamp().getTime(), values.get(i),logs.get(i).getDevice_name());
					  }
					  
					  final BarPlot plot= new BarPlot(d);
					  plot.setInsets(new Insets2D.Double(30.0, 20.0, 40.0, 0.0));
					  plot.getTitle().setText("Bar plot");
					
					  plot.setBarWidth(0.075);
			         // Format bars
					  BarRenderer pointRenderer = (BarRenderer) plot.getPointRenderer(d);
					  pointRenderer.setColor(
			           new LinearGradientPaint(0f,0f, 0f,1f,
			           new float[] { 0.0f, 1.0f },
			           new Color[] { COLOR1, GraphicsUtils.deriveBrighter(COLOR1) }
			                 )
			         );
			       
			    pointRenderer.setBorderStroke(new BasicStroke(3f));
		      pointRenderer.setBorderColor(
		              new LinearGradientPaint(0f,0f, 0f,1f,
		                              new float[] { 0.0f, 1.0f },
		                              new Color[] { GraphicsUtils.deriveBrighter(COLOR1), COLOR1 }
		              )
		      );
		      
		     		 
		      plot.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);

		      AxisRenderer rendererX = plot.getAxisRenderer(XYPlot.AXIS_X);
		      rendererX.setTickLabelFormat(dateFormat);
		      
		      
		      pointRenderer.setValueVisible(true);
		      pointRenderer.setValueColumn(1);
		      pointRenderer.setValueLocation(Location.CENTER);
		      pointRenderer.setValueColor(GraphicsUtils.deriveDarker(COLOR1));
		      pointRenderer.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
			         
			         interactivePanel = new InteractivePanel(plot);
			         
			      
			         interactivePanel.setBounds(new Rectangle(0, 0, 440, 230));
			
			        
						 
							  
						  DataTable d1=new DataTable(Long.class, Double.class, String.class);
						 
						  
						  for(int j=0;j<logs1.size();j++){
							 
								
								d1.add(logs1.get(j).getTimestamp().getTime(), values1.get(j),logs1.get(j).getDevice_name());
						  }
							
						
						  
						  
						  final BarPlot plot1= new BarPlot(d1);
							
							plot1.setInsets(new Insets2D.Double(30.0, 510.0, 40.0, 0));
							 plot1.getTitle().setText("Bar plot");
								
							  plot1.setBarWidth(0.075);
						        
						    BarRenderer pointRenderer1 = (BarRenderer) plot1.getPointRenderer(d1);
						    pointRenderer1.setColor(
						           new LinearGradientPaint(0f,0f, 0f,1f,
						           new float[] { 0.0f, 1.0f },
						           new Color[] { COLOR1, GraphicsUtils.deriveBrighter(COLOR1) }
						                 )
						         );
						       
						    pointRenderer1.setBorderStroke(new BasicStroke(3f));
					      pointRenderer1.setBorderColor(
					              new LinearGradientPaint(0f,0f, 0f,1f,
					                              new float[] { 0.0f, 1.0f },
					                              new Color[] { GraphicsUtils.deriveBrighter(COLOR1), COLOR1 }
					              )
					      );
					      
					     		 
					      plot1.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);

					      AxisRenderer rendererX1 = plot1.getAxisRenderer(XYPlot.AXIS_X);
					      DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					      rendererX1.setTickLabelFormat(dateFormat1);
					      
					      
					      pointRenderer1.setValueVisible(true);
					      pointRenderer1.setValueColumn(1);
					      pointRenderer1.setValueLocation(Location.CENTER);
					      pointRenderer1.setValueColor(GraphicsUtils.deriveDarker(COLOR1));
					      pointRenderer1.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
						         
						   
					      values2=new ArrayList<Double>();
							int k;
							if(logs1.get(logs1.size()-1).getTimestamp().getDate()<logs.get(logs.size()-1).getTimestamp().getDate()){
							 k=logs1.size();
							}else{
								k=logs.size();
							}
							DataTable dt=new DataTable(Double.class, Double.class);
							
							for(int i=0;i<k;i++){
								for(int j=i;j<k;j++){
									if(logs1.get(i).getTimestamp().getDate()==logs.get(j).getTimestamp().getDate()){
										dt.add(logs1.get(i).getValue(),logs.get(j).getValue());
										
										break;
										}
									
									}
								}
								
						
							final BarPlot plot3= new BarPlot(dt);
							plot3.setInsets(new Insets2D.Double(260.0, 200.0, 40,0));
					
						         // Format bars
						    BarRenderer pointRenderer3 = (BarRenderer) plot3.getPointRenderer(dt);
						    pointRenderer3.setColor(
						           new LinearGradientPaint(0f,0f, 0f,1f,
						           new float[] { 0.0f, 1.0f },
						           new Color[] { COLOR1, GraphicsUtils.deriveBrighter(COLOR1) }
						                 )
						         );
						       
						    pointRenderer3.setBorderStroke(new BasicStroke(3f));
					         pointRenderer3.setBorderColor(
					                 new LinearGradientPaint(0f,0f, 0f,1f,
					                                 new float[] { 0.0f, 1.0f },
					                                 new Color[] { GraphicsUtils.deriveBrighter(COLOR1), COLOR1 }
					                 )
					         );
					         
					         
					        		 
					         plot3.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);

					         AxisRenderer rendererX3 = plot3.getAxisRenderer(XYPlot.AXIS_X);
					         DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					         rendererX3.setTickLabelFormat(dateFormat3);
					         
					         
					         pointRenderer3.setValueVisible(true);
					         pointRenderer3.setValueColumn(1);
					         pointRenderer3.setValueLocation(Location.CENTER);
					         pointRenderer3.setValueColor(GraphicsUtils.deriveDarker(COLOR1));
					         pointRenderer3.setValueFont(Font.decode(null).deriveFont(Font.BOLD));	
						

					         interactivePanel2 = new InteractivePanel(plot3);
					         
					         interactivePanel2.setLayout(null);
					         interactivePanel2.setBounds(new Rectangle(0, 0, 540, 500));
					        

					       
					        
		
							          
					      interactivePanel1 = new InteractivePanel(plot1);
						         
						         interactivePanel1.setLayout(null);
						         interactivePanel1.setBounds(new Rectangle(0, 0, 950, 230));
						         

						       
						       
			
						          contentPane = new JPanel();
							       
						          contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
						 			
						 			
						 		        
						 				 tabbedPane.addTab("Three graphs", contentPane);
						 				 contentPane.setVisible(true);
						 				contentPane.add(interactivePanel);
						 				
						 			         
						 			     contentPane.add(interactivePanel1);
						 			    contentPane.add(interactivePanel2);
						 			     	
						 			     	 interactivePanel.setLayout(null);	
						 				     interactivePanel1.setLayout(null);
						 				    interactivePanel2.setLayout(null);
						 			     
						 				contentPane.setLayout(null);
						 				tabbedPane.setSelectedIndex(1);		          
						        
						 				
						 				
						 				
						 				 final JButton btnChange = new JButton("Change data");
						 				
						 			       btnChange.addMouseListener(new MouseAdapter() {
						 			       	@Override
						 			       	public void mouseClicked(MouseEvent arg0) {
						 			       		tabbedPane.setSelectedIndex(0);
						 			       	}
						 			       });
						 			     btnChange.setBounds(650, 492, 137, 23);
						 				 contentPane.add(btnChange);
						 				 
						 			
						 				 final JLabel lblExport= new JLabel("To export graph, make right click, and choose Export Image.");
						 				 lblExport.setBounds(40, 499, 137, 23);
						 				 lblExport.setSize(400, 15);
						 				 lblExport.setForeground(Color.red);
						 				 contentPane.add(lblExport);
						 				 final JButton btnExit = new JButton("Cancel");
						 				
						 			     btnExit.setBounds(800, 492, 137, 23);
						 				contentPane.add(btnExit);
						 				 
						 				 
						 				
						 				 btnExit.addActionListener(new ActionListener() {
						 						public void actionPerformed(ActionEvent e) {
						 							
						 							tabbedPane.remove(1);
						 							
								 				    tabbedPane.remove(0);
								 				   ThreeGraphsViewPanel threeGraphs= new ThreeGraphsViewPanel(tabbedPane);
								 					tabbedPane.add("Three graphs",threeGraphs);
								 					threeGraphs.setLayout(null);
								 				    
								 					tabbedPane.setSelectedIndex(0);
						 						}
						 					});		
					
			 }
		
		}	
		
			public int getValueFirstGraph() {
				return valueFirstGraph;
			}
			public void setValueFirstGraph(int valueFirstGraph) {
				this.valueFirstGraph = valueFirstGraph;
			}
			@Override
			public String getDescription() {
				
				return null;
			}
			@Override
			public String getTitle() {
				
				return null;
			}
}

