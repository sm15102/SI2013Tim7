package etf.si.projekat.data_vision;

import javax.swing.*;

import java.awt.Choice;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.io.data.DataWriter;
import de.erichseifert.gral.io.data.DataWriterFactory;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
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
import ba.unsa.etf.si.beans.DeviceName;
import ba.unsa.etf.si.beans.EventLogs;
import ba.unsa.etf.si.hibernate_klase.HibernateDeviceName;
import ba.unsa.etf.si.hibernate_klase.HibernateEventLogs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.examples.ExamplePanel;
import de.erichseifert.gral.graphics.DrawingContext;
import de.erichseifert.gral.io.data.DataWriter;
import de.erichseifert.gral.io.data.DataWriterFactory;
import de.erichseifert.gral.io.plots.DrawableWriter;
import de.erichseifert.gral.io.plots.DrawableWriterFactory;
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
import ba.unsa.etf.si.beans.DeviceName;
import ba.unsa.etf.si.beans.EventLogs;
import ba.unsa.etf.si.hibernate_klase.HibernateDeviceName;
import ba.unsa.etf.si.hibernate_klase.HibernateEventLogs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ThreeGraphsViewPanel extends JPanel  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<DeviceName> list_device=new HibernateDeviceName().giveAllDeviceName();
	
	    
		final JTabbedPane tabbedPane;
		private int size;
	    private List<List<EventLogs>> list_logs;
		private List<List<Double>> list_values;
		private List<DataTable> datas;
		private List<DataSeries>series;
		final JSpinner spinner = new JSpinner();
		 DataTable data1;
		   
	     final static DataTable data=new DataTable(Long.class, Double.class);

		private static final Color COLOR1 = Color.RED;
		 XYPlot plot;
		 
		 
		 
		 // CHOICE
		 	final Choice choice= new Choice(); 
		 	final Choice choice_1 = new Choice();
		    final Choice choice_11= new Choice();

		    InteractivePanel interactivePanel ;
		 
		 
		    UtilDateModel model = new UtilDateModel();
	        JDatePanelImpl datePanel = new JDatePanelImpl(model);
	        final JDatePickerImpl datePicker = new JDatePickerImpl(datePanel); 
		 
		 
	        UtilDateModel model1 = new UtilDateModel();
	        JDatePanelImpl datePane1 = new JDatePanelImpl(model1);
	        final JDatePickerImpl datePicker1 = new JDatePickerImpl(datePane1);

			private int valueFirstGraph;

			private ArrayList<List<EventLogs>> list_logs1;

			private ArrayList<List<Double>> list_values1;

			private ArrayList<DataTable> datas1;

			private ArrayList<DataSeries> series1;
	        
	      

	/**
	 * Create the panel.
	 * @param tabbedPane_4 
	 */
	public ThreeGraphsViewPanel(JTabbedPane tabbedPane_4) {
		tabbedPane = tabbedPane_4;
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
		
		SensorChoosingPanel p = new SensorChoosingPanel();
		spinner.setEnabled(false);
		
	
		spinner.setModel(new SpinnerNumberModel(1, 1, 1, 1));
		spinner.setBounds(152, 137, 165, 18);
		
		add(spinner);
		
		/*UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);*/
        JLabel lblGraphType = new JLabel("Graph type");
        lblGraphType.setHorizontalAlignment(SwingConstants.RIGHT);
        lblGraphType.setBounds(55, 60, 68, 14);
        add(lblGraphType);
       // datePicker = new JDatePickerImpl(datePanel);
        SpringLayout springLayout_1 = (SpringLayout) datePicker.getLayout();
        springLayout_1.putConstraint(SpringLayout.SOUTH, datePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker);
        datePicker.setLocation(152, 75);
        datePicker.setSize(165, 29);
       add(datePicker);
        
        
       
        SpringLayout springLayout_2 = (SpringLayout) datePicker1.getLayout();
        springLayout_2.putConstraint(SpringLayout.SOUTH, datePicker1.getJFormattedTextField(), 0, SpringLayout.SOUTH, datePicker1);
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
      
      final JSeparator separator_1 = new JSeparator();
      separator_1.setBounds(222, 220, -220, -5);
      separator_1.setVisible(false);
      add(separator_1);
      
      
      final JSeparator separator_2 = new JSeparator();
      separator_2.setBackground(SystemColor.textHighlight);
      separator_2.setBounds(10, 233, 618, -5);
      add(separator_2);
      
      final  JLabel label_1 = new JLabel("Sensor type 1");
      label_1.setHorizontalAlignment(SwingConstants.RIGHT);
      label_1.setBounds(28, 220, 95, 18);
      label_1.setVisible(true);
      add(label_1);
      
      choice_1.setBounds(152, 220, 165, 20);
      choice_1.setVisible(true);
      add(choice_1);
      
		
		lblSensorType.setVisible(false);
		separator_2.setVisible(true);
	      
		
		valueFirstGraph = (Integer) spinner.getValue();
      
      
      final Choice choice_11 = new Choice();
     choice_11.setVisible(true);
      choice_11.setBounds(152, 247, 165, 20);
      add(choice_11);
      
      final Label label_13 = new Label("Sensor type 2");
      label_13.setFont(new Font("Dialog", Font.BOLD, 11));
      label_13.setAlignment(Label.RIGHT);
      label_13.setBounds(40, 253, 83, 14);
      add(label_13);
      
      JSeparator separator_4 = new JSeparator();
      //separator_4.setForeground(Color.GRAY);
      separator_4.setBounds(622, 30, -273, 15);
      add(separator_4);
      
      final  JSeparator separator_5 = new JSeparator();
      separator_5.setBackground(SystemColor.textHighlight);
      separator_5.setBounds(10, 487, 618, -10);
    
      add(separator_5);
      
      final  JSeparator separator_6 = new JSeparator();
      separator_6.setBounds(720, 220, -91, 22);
      add(separator_6);
     
      
      // GENERIRAJ AKCIJA
      		final JButton btnGenerateGraphs = new JButton("Generate graphs");
      		btnGenerateGraphs.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent arg0) {
      				
      				if(choice.getSelectedItem().toString() == "Line"){
      					Graf();
      					
      				}
      			}
      		});
      		btnGenerateGraphs.setBackground(SystemColor.control);
      		btnGenerateGraphs.setVisible(true);
      		btnGenerateGraphs.setBounds(183, 313, 134, 23);
      		add(btnGenerateGraphs);
	      
	      JSeparator separator_3 = new JSeparator();
	      separator_3.setBackground(SystemColor.textHighlight);
	      separator_3.setBounds(10, 199, 306, -13);
	      add(separator_3);
	      
	      JSeparator separator_7 = new JSeparator();
	      separator_7.setBackground(SystemColor.textHighlight);
	      separator_7.setBounds(10, 299, 307, 14);
	      add(separator_7);
	      
	      JSeparator separator_8 = new JSeparator();
	      separator_8.setBackground(SystemColor.textHighlight);
	      separator_8.setBounds(10, 186, 306, 15);
	      add(separator_8);
	      
	      
	      for (int i=0; i<list_device.size(); i++){
		    	 choice_1.add(list_device.get(i).getName() );
		    	 choice_11.add(list_device.get(i).getName() );
		    	
	      

	    }
	      
	      
	      
		  		
	}
	public void Graf(){
		
		List<DataTable> vrijednosti= new ArrayList<DataTable>();
		Integer value = (Integer) spinner.getValue();
		
		for(int k=0; k<value; k++)
		{
				//if(value==1){
				 data1 = new DataTable(Long.class, Double.class, String.class);	
				 
				//Podaci koji ce se prikazivati na grafu 
					Date dateString = (Date) datePicker.getModel().getValue();
			  		String date_from = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString);
			  		Date dateString1 = (Date) datePicker1.getModel().getValue();
			  		String date_to = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateString1);	
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date date_start;
					Date date_end;
					
					  try {
						date_start = sdf.parse(date_from);
						date_end = sdf.parse(date_to);
						 

						try {
						
							
							List<Choice> choices=new ArrayList<Choice>();
							choices.add(choice_1);
							choices.add(choice_11);
							
							
							list_logs=new ArrayList<List<EventLogs>>();
							list_logs1=new ArrayList<List<EventLogs>>();

							
						
							
								list_logs.add(new HibernateEventLogs().getdatesbetween(choices.get(0).getSelectedItem(),date_start,date_end));//.add( new HibernateEventLogs().getdatesbetween(choices.get(i).getSelectedItem(),date_start,date_end)); //lista eventlogova ciji su datumi između unesenih u datepickere i odgovara im odgovrajuci device name u suprotnom vraca null tako da bi i to trebalo ispitati.
								list_logs1.add(new HibernateEventLogs().getdatesbetween(choices.get(1).getSelectedItem(),date_start,date_end));//.add( new HibernateEventLogs().getdatesbetween(choices.get(i).getSelectedItem(),date_start,date_end)); //lista eventlogova ciji su datumi između unesenih u datepickere i odgovara im odgovrajuci device name u suprotnom vraca null tako da bi i to trebalo ispitati.

						
						size=list_logs.size();
						
						list_values=new ArrayList<List<Double>>();
						for(int i=0; i<list_logs.size();i++){
							
							List<Double>values=new ArrayList<Double>();
							for(int j=0;j<list_logs.get(i).size();j++){
								
							values.add(list_logs.get(i).get(j).getValue());//add(list_logs.get(i).get(j).getValue());           //Ovo čemo stavljati na graf valjda :D
							
							}
							list_values.add(values);
							
							 
						}
						
						
						list_values1=new ArrayList<List<Double>>();
						for(int i=0; i<list_logs1.size();i++){
							
							List<Double>values=new ArrayList<Double>();
							for(int j=0;j<list_logs1.get(i).size();j++){
								
							values.add(list_logs1.get(i).get(j).getValue());//add(list_logs.get(i).get(j).getValue());           //Ovo čemo stavljati na graf valjda :D
							
							}
							list_values1.add(values);
							
							 
						}
						
						}catch(Exception e){
							System.out.println("Ne poklapaju se vrijednosti");
						}
					} 
					  catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						 final JLabel lblExport= new JLabel("To export graph, make right click, and choose Export Image.");

						 
						 
					datas=new ArrayList<DataTable>();
					 series=new ArrayList<DataSeries>();
					  DataTable d=new DataTable(Long.class, Double.class, String.class);
					  
					  datas1=new ArrayList<DataTable>();
						 series1=new ArrayList<DataSeries>();
						  DataTable d1=new DataTable(Long.class, Double.class, String.class);
					 
						  
						  //Za prvo
					  for(int i=0;i<list_logs.size();i++)
					  {
						  
						  for(int j=0;j<list_logs.get(i).size();j++){
							
							d.add(list_logs.get(i).get(j).getTimestamp().getTime(), list_values.get(i).get(j), list_logs.get(i).get(j).getDevice_name());
			  }
						
						  datas.add(d);
						  series.add(new DataSeries(d));
						  }
			
					  
					  
					  // za drugo
					  for(int i=0;i<list_logs1.size();i++)
					  {
						  
						  for(int j=0;j<list_logs1.get(i).size();j++){
							
							d.add(list_logs1.get(i).get(j).getTimestamp().getTime(), list_values1.get(i).get(j), list_logs1.get(i).get(j).getDevice_name());
			  }
						
						  datas1.add(d);
						  series1.add(new DataSeries(d));
						  }
					  
					  
					  }
		 
	      switch(value)
	      {
	      case 1:
	      {
		XYPlot plot = new XYPlot(series.get(0));
		plot.setInsets(new Insets2D.Double(20.0, 40.0, 80.0, 40.0));
		 plot.getTitle().setText("Measured values");
			
         plot.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);
         
        AxisRenderer rendererX = plot.getAxisRenderer(XYPlot.AXIS_X);
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         rendererX.setTickLabelFormat(dateFormat);
         
		  LineRenderer lines = new DefaultLineRenderer2D();
	         plot.setLineRenderer(series.get(0), lines);
	         Color color = new Color(0.0f, 0.3f, 1.0f);
	        plot.getPointRenderer(series.get(0)).setColor(color);
	         plot.getLineRenderer(series.get(0)).setColor(color);
	         
	         plot.setLineRenderer(series1.get(0), lines);
	         Color color1 = new Color(0.1f, 0.3f, 1.0f);
	        plot.getPointRenderer(series1.get(0)).setColor(color);
	         plot.getLineRenderer(series1.get(0)).setColor(color);
	         
	         
	         
	         plot.getPlotArea().setBorderColor(new Color(0.0f, 0.3f, 1.0f));
	         
	         interactivePanel = new InteractivePanel(plot);
	         
	         interactivePanel.setLayout(null);
	         interactivePanel.setBounds(new Rectangle(0, 0, 0, 50));
	          plot.getTitle().setText("Bar plot");
	          interactivePanel.setVisible(true);
	          
	          tabbedPane.addTab("Line plot", interactivePanel);
	      		tabbedPane.setSelectedIndex(1);   
	      		
	      	//	plot.add(data);
	      		
	      		//drugi
	      		
	    			
	            
	             
	           
	    	        /* interactivePanel.setLayout(null);
	    	         interactivePanel.setBounds(new Rectangle(0, 0, 0, 50));
	    	          plot.getTitle().setText("Bar plot");
	    	          interactivePanel.setVisible(true);
	    	          
	    	          tabbedPane.addTab("Line plot", interactivePanel);
	    	      		tabbedPane.setSelectedIndex(1);   */
	    	         
	         
		break;
	      }
	      case 2:
	      {
	    	  XYPlot plot = new XYPlot(series.get(0), series.get(1));
	    	  plot.setInsets(new Insets2D.Double(20.0, 40.0, 80.0, 40.0));
	    	  plot.getTitle().setText("Measured values");
	    		
		         plot.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(1.0);
		         
		        AxisRenderer rendererX = plot.getAxisRenderer(XYPlot.AXIS_X);
		         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		         rendererX.setTickLabelFormat(dateFormat);
			  LineRenderer lines = new DefaultLineRenderer2D();
		         plot.setLineRenderer(series.get(0), lines);
		         Color color = new Color(0.0f, 0.3f, 1.0f);
		        plot.getPointRenderer(series.get(0)).setColor(color);
		        plot.getLineRenderer(series.get(0)).setColor(color);
		         
		         LineRenderer lines1 = new DefaultLineRenderer2D(); 
			     plot.setLineRenderer(series.get(1), lines1);
			     Color color1 = new Color(0.5f, 1.0f, 0.25f);
			     plot.getPointRenderer(series.get(1)).setColor(color1);
			     plot.getLineRenderer(series.get(1)).setColor(color1); 
			     
			     plot.getPlotArea().setBorderColor(new Color(0.0f, 0.3f, 1.0f));
			     
			    interactivePanel = new InteractivePanel(plot);
			     
			     interactivePanel.setLayout(null);
			     interactivePanel.setBounds(new Rectangle(0, 0, 0, 50));
			      plot.getTitle().setText("Bar plot");
			      interactivePanel.setVisible(true);
			      
			      tabbedPane.addTab("Line plot", interactivePanel);
			  		tabbedPane.setSelectedIndex(1);
			     
			     break;
	      }
	   
	      
	      }

		 final JButton btnChange = new JButton("Change data");
			
	       btnChange.addMouseListener(new MouseAdapter() {
	       	@Override
	       	public void mouseClicked(MouseEvent arg0) {
	       		tabbedPane.setSelectedIndex(0);
	       	}
	       });
	     btnChange.setBounds(690, 462, 137, 23);
		 interactivePanel.add(btnChange);
		 
	
		 final JLabel lblExport= new JLabel("To export graph, make right click, and choose Export Image.");
		 lblExport.setBounds(131, 462, 137, 23);
		 lblExport.setSize(400, 15);
		 
		 interactivePanel.add(lblExport);
		 final JButton btnExit = new JButton("Cancel");
		
	     btnExit.setBounds(831, 462, 137, 23);
		 interactivePanel.add(btnExit);
		 
		 
		
		 btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
			tabbedPane.remove(1);
			//tabbedPane.resetKeyboardActions();
			tabbedPane.remove(0);
			ThreeGraphsViewPanel three = new ThreeGraphsViewPanel(tabbedPane);
			tabbedPane.add("Basic data",three);
			three.setLayout(null);
		
			tabbedPane.setSelectedIndex(1);
			
				}
			});
		
		
	}

}

      