import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		ArrayList<Event> list = new ArrayList<Event>();
		
		int response = 0;
		while (response != 4){
			while (true){
			// Main Menu 
			System.out.println(">>Main Menu<<\n1.Add Event\n2.Display Events\n3.Scheduler\n4.Exit");
			System.out.print("What would you like to do?\n");
			response = 0;
				
				try{
					response = in.nextInt();
					if (response <= 4 && response > 0){
					break;
					}
					else {
						System.out.println("Please enter a number between 1-4");
					}
				}
				catch (Exception e){
					System.out.println("I'm sorry, please enter a number between 1-4\n");
					response = in.nextInt();
				}
			}
			
			switch (response){
			//1. Will do the add event method.
			case 1: 
				String response2;
				System.out.println("Would you like to add a lecture or a talk?");
				response2 = in.next();
				if (response2.equalsIgnoreCase("Lecture")){
					list.add(Lecture.addEvent());
				}
				
				else if (response2.equalsIgnoreCase("Talk")){
					list.add(Talk.addEvent());
				}
				else {
					System.out.println("Not a valid response.");
				}
				
				break;
			//2. Will do the display events method
				case 2 : 
					System.out.println("<Events list>");
					for (int i = 0; i < list.size(); i++){
						System.out.printf("[%s] with %s | %s to %s |\n", list.get(i).getTitle(), list.get(i).getName(),
								Scheduler.convertIntToTime(list.get(i).getStartTime()),
								Scheduler.convertIntToTime(list.get(i).getEndTime()));
					}
					break;
				
			//3. Will do the scheduler method
				case 3 : 
					Scheduler.scheduler(list);
					break;
			//4. will exit
				case 4 : 
					in.close();
					break;
			}
		}	
	}
}
