import java.util.Scanner;

public class Talk extends Event {
	public Talk(){}
	
	//Filled lecture object
	public Talk (int startTime, int endTime, int duration, String name, String title){
		super(startTime, endTime, duration, name, title);
	}
	
	// add event method
	public static Event addEvent(){
		Scanner in = new Scanner (System.in);
		String title, name;
		String startTime, endTime;
		
		Event talk = new Talk();
		
		System.out.print("What would you like to call this talk?");
		title = in.nextLine();
		
		System.out.print("Which person is this talk with?");
		name = in.nextLine();
		
		System.out.print("What time will this start? (HH:MM AM/PM): ");
		startTime = in.nextLine();
		while (!Scheduler.checkTimeFormat(startTime)){
			System.out.println("Sorry, please enter the start time with format HH:MM AM/PM");
			startTime = in.nextLine();
			if (Scheduler.checkTimeFormat(startTime)){
				break;
			}
		}
		
		System.out.print("What time will this end? (HH:MM AM/PM): ");
		endTime = in.nextLine();
		
		boolean format = false;
		boolean endTimeCheck = false;
		while (format == false || endTimeCheck == false){
			
			if (Scheduler.checkTimeFormat(endTime)){
				
				format = true;
				
				if (Scheduler.convertTimeToInt(startTime) > Scheduler.convertTimeToInt(endTime)){
					System.out.println("Your end time is before your start time. Please enter a valid end time.");
					
					endTime = in.nextLine();
					endTimeCheck = false;
				}
				else {
					endTimeCheck = true;
				}
			}
			
			else {
				System.out.println("Sorry, please enter the end time with format HH:MM AM/PM");
				format = false;
				endTime = in.nextLine();
			}
		}
	
		talk.setDuration(60);
		talk.setEndTime(Scheduler.convertTimeToInt(endTime));
		
		talk.setTitle(title);
		talk.setName(name);
		talk.setStartTime(Scheduler.convertTimeToInt(startTime));
		
		return (Event)talk;
		
	}
}
