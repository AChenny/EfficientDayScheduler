import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class Event{
	private int startTime;
	private int endTime;
	private int duration;
	private String name;
	private String title;
	
	//Default event object
	public Event (){
		this.startTime = 0;
		this.endTime = 0;
		this.duration = 0;
		this.name= "";
		this.title = "";
	}
	
	//Event object for lecture
	public Event (int startTime, int endTime, String name, String title){
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = 60;
		this.name = name;
		this.title = title;
	}
	
	//Event object for talk
	public Event (int startTime, int endTime, int duration, String name, String title){
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.name = name;
		this.title = title;
	}
	
	// Accessor methods 
	public int getStartTime(){
		return startTime;
	}
	public int getEndTime(){
		return endTime;
	}
	public int getDuration(){
		return duration;
	}
	public String getName(){
		return name;
	}
	public String getTitle(){
		return title;
	}
	
	// Mutator methods
	public void setStartTime(int startTime){
		this.startTime = startTime;
	}
	public void setEndTime(int endTime){
		this.endTime = endTime;
	}
	public void setDuration(int duration){
		this.duration = duration;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setTitle(String title){
		this.title = title;
	}

//	 user input method for the user to enter a valid time for the event
	public static String timeInput(){
		Scanner in = new Scanner(System.in);
		String time = "";
		boolean success = false; 
		
		System.out.print("Please enter a time in format (hh:mm a): ");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");
		
		//  do while loop to catch an invalid format. If it is then it will catch it and then retry.  	
		do	{ 
			   try {
	            time = in.nextLine();
	            
	            LocalTime lt = LocalTime.parse(time, timeFormat);
	            time = lt.format(timeFormat);
	            System.out.println(time);
	            
	            success = true;
			   } 
			   
			   catch (DateTimeParseException e) {
	           System.out.println("Not a valid time. Please use this format (HH:MM AM/PM)");
	            
			   }
		 }
		 while (success != true); 
		 	
		 return time;
	}
	
}
