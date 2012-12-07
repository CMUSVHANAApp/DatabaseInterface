package com.databaseinterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class DatainterfaceClass {

	private File FileDatabase ;
	private  Scanner ScanDatabase;
	private FileWriter WriteDatabase;
	
	public DatainterfaceClass() 
	{
		FileDatabase = new File("Flight_data.csv");		
	}
	
	public boolean ConfigureDatabase()
	{		
		try
	    {
	    	ScanDatabase = new Scanner(FileDatabase);	    	
	    } 
	    catch (FileNotFoundException ex) 
	    {
	    	JOptionPane.showMessageDialog(null,ex);
	    	return false;
	    }
		try 
		{
			WriteDatabase = new FileWriter(FileDatabase, true);
		} 
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null,e);
			return false;		    
		}		
		return true;
	}
	
	public boolean InputDataToDatabase(String[] Values) 
	{
		for (int i = 0; i< Values.length ; i++)
		{
			try
			{
				WriteDatabase.append("\t");
				WriteDatabase.append(Values[i]);				
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(null,e);
				return false;
			}			
		}
		try {
			WriteDatabase.flush();
			WriteDatabase.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e);
			return false;
		}		
		return true;
	}

	public String[] GetDataFromDatabase(int month, int Airline_ID, int FL_NUM)
	{			
		ArrayList<String> ArrayListVal = new ArrayList<String>();
		ScanDatabase.nextLine();
	    while (ScanDatabase.hasNext()) 
	    {
	        String line = ScanDatabase.nextLine();
	        if (line.charAt(0) == '#') 
	        {
	            continue;
	        }
	        String[] split = line.split("\t");
	        if(month == Integer.parseInt(split[0]) &&
	        		Airline_ID == Integer.parseInt(split[1]) &&
	        		FL_NUM == Integer.parseInt(split[2]))
	        {
	        	ArrayListVal.add(line);	        	
	        }	        
	    }
	    String[] StringArray = new String[ArrayListVal.size()];
        Object ArrReturnval[] = ArrayListVal.toArray();
        for(int i = 0; i < ArrayListVal.size();i++)
        {
        	StringArray[i] = ArrReturnval[i].toString();
        }
        return StringArray;
	}
}
