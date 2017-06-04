import java.util.Scanner;

public class Lecture extends Event {
	// Default lecture object
	public Lecture(){
	}
	
	//Filled lecture object
	public Lecture (int startTime, int endTime, int duration, String name, String title){
		super(startTime, endTime, duration, name, title);
	}
	// add event method
	public static Event addEvent(){
		Scanner in = new Scanner (System.in);
		String title, name;
		String startTime;
		
		Event lecture = new Lecture();
		
		System.out.print("What would you like to call this lecture?");
		title = in.nextLine();
		
		System.out.print("Which person is this lecture with?");
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
		
		
		lecture.setDuration(60);
		lecture.setEndTime(Scheduler.convertTimeToInt(startTime) + lecture.getDuration());
		lecture.setTitle(title);
		lecture.setName(name);
		lecture.setStartTime(Scheduler.convertTimeToInt(startTime));
	
		in.close();
		return (Event)lecture;
	}
	public static Event setEvent(String title, String name, int startTime, int endTime){
			
			Event event = new Lecture();
			event.setTitle(title);
			event.setName(name);
			event.setStartTime(startTime);
			event.setEndTime(endTime);
			event.setDuration(endTime - startTime);
			
			return event;
		}
}
