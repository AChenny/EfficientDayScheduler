import java.util.ArrayList;

/*
 * This scheduler works by taking the old list and organizing it from earliest to latest
 * Then taking that and adding them into an another list until there is an overlap
 * If there is an overlap, then the application will make another branch list one with each event that overlapped
 * Then, at the end we will have several list of schedules where we will take the schedule with the longest duration in an event
 * Thus, the longest duration will be the most efficient schedule and we will print that out
 */

public class Scheduler {
	public static final int hours = 60;
	
	public static void scheduler(ArrayList<Event>oldList){
		
		ArrayList<Event> list = new ArrayList<Event>();
		list = organizeEventList(oldList);
		Event [][] newList = new Event [list.size()][list.size()];
		
		newList [0][0] = list.get(0);
		int [] colCounter = new int [10];
		int [] keyCounter = new int [10];
		keyCounter[0] = 1;
		int rowCounter=0;
		
		for (int row = rowCounter; row <= rowCounter; row++){
			int col = colCounter[row];// should grab the event from list1
			int key = keyCounter[row]; // holds the column for lister array
			
			for (int x = key; x < list.size(); x++){
				
				boolean overlap = checkForOverlap(newList[row][col].getStartTime(), newList[row][col].getEndTime(), list.get(key).getStartTime(), list.get(key).getEndTime());
				
				if (overlap == true){
					rowCounter++;
					for (int y = 0; y < col; y++){
						newList[rowCounter][y] = newList[row][y];
					}
					newList[rowCounter][col] = list.get(key);
					colCounter[rowCounter] = col;
					key++;
					keyCounter[rowCounter] = key;
					
				}
				
				else {
					newList[row][col+1] = list.get(key);
					key++;
					col++;
				}
				
			}
			
		}
		int [] durations = new int[rowCounter];
		
		for (int row = 0; row < rowCounter; row++){
			for (int column = 0; column < newList[row].length; column++){
				if (newList[row][column]!= null){
					durations[row] += newList[row][column].getDuration();
				}
			}
		}
		
		int totalDuration = 0;
		int row =0;
		
		for (int x = 0; x < rowCounter; x++){
			if (durations[x] > totalDuration){
				totalDuration = durations[x];
				row = x;
			}
		}
		
		for (int i = 0; i < newList[row].length; i++ ){
			System.out.printf("[%s] %s - %s \n", newList[row][i].getTitle(), convertIntToTime(newList[row][i].getStartTime()), convertIntToTime(newList[row][i].getEndTime()));
		}
			
	}
	
	public static ArrayList<Event> organizeEventList(ArrayList<Event>list){
		for (int y = list.size(); y > 0; y--){ 
			int key = 1;
			
			for (int x = 0; key < list.size(); x++){
				Event temp;
				
				if (list.get(key).getStartTime() < list.get(x).getStartTime()){ // push early items to the front
					temp = list.get(key);
					list.remove(key);
					list.add(x, temp);
				}
				
				else if (list.get(key).getStartTime() == list.get(x).getStartTime()){ // if the start times are equal, we will compare for the longer duration
					
					if (list.get(key).getDuration() > list.get(x).getDuration()){
						temp = list.get(key);
						list.remove(key);
						list.add(x, temp);
					}
					
					else if (list.get(key).getDuration() == list.get(x).getDuration()){
						System.out.println("A duplicate event was detected. Removed " + list.get(key).getTitle());
						list.remove(x);
					}
				}
				
				key ++;
			}
			
		}
		
		return list;
		
	}
	
	public static boolean checkForOverlap (int startTime1, int endTime1, int startTime2, int endTime2){
		if (startTime1 < startTime2){
			if (endTime1 < startTime2){
				return false;
			}
			
			else if (startTime2 == endTime1){
				return false;
			}
			
			else {
				return true;
			}
		}
		
		else if (startTime1 > startTime2){
			if (endTime2 > startTime1){
				return false;
			}
			
			else if (startTime1 == endTime2){
				return false;
			}
			
			else {
				return true;
			}
		}
		
		else {
			return true;
		}
	}
	
	public static int calculateTimeBetween(String key, String x, String startTime){
		int endTimeKey = convertTimeToInt(key);
		int endTimeX = convertTimeToInt(x);
		int start = convertTimeToInt(startTime);
		int result;
		
		int first = start - endTimeKey;
		int second = start - endTimeX;
		
		/* Return -1 if key is more efficient or x endTime was after startTime -  IE remove x
		 * Return 1 if x is more efficient or key endTime was after startTime - IE remove key
		 * Return 0 if key and x event times are the exact same
		 * Return 2 if both endtimes are after startTime - where then it would just remove the startTime event and take the later endTime and remove the other
		 */
		
		if (first < 0 || second < 0){
			if (first < 0 && second < 0){
				return 2;
			}
			
			else if (first < 0){
				return 1;
			}
			
			else{
				return -1;
			}
		}
		
		else {
			
			if (first > second){
			result = -1;
			}
			
			else if (first < second){
				result = 1;
			}
			
			else{
				result = 0;
			}
			
		}
		
		return result;
	}
	
	public static int convertTimeToInt(String event){
		int h, m;
		String day;
		int time;
		
		event = event.replaceAll("\\s+", "");
		String[] splitTime = event.split(":|AM|PM");
		
		h = Integer.parseInt(splitTime[0]); 
		m = Integer.parseInt(splitTime[1]);
		
		day = (event.contains("AM") ? "AM" : "PM");
		
		time = (h*hours) + m;
		if (day.equalsIgnoreCase("PM")){
			time += (hours * 12);
		}
		
		return time;
	}
	
	public static String convertIntToTime(int intTime){
		String time;
		int h = 0, m = 0;
		String day;
		
		if (intTime == 1440){
			h = 12;
			m = 0;
			day = "AM";
		}
		
		else if (intTime < 60){
			h = 12;
			m = intTime;
			day = "AM";
		}
		
		else {
				if (intTime >= (13 * hours)){
				day = "PM";
				intTime -= (12 * hours);
			}
				
				else if (intTime < (13 * hours) && intTime >= (12*hours)){
					h = 12;
					intTime -= (12 * hours);
					day = "PM";
				}
			
			else {
				day = "AM";
			}
			
			h += intTime/hours;
			
			m += intTime%hours;
		
		}
		
		
		time = String.format("%02d:%02d %s", h, m, day);
		
		return time;
	}
	
	public static String calculateEndTime(String startTime, int duration){

		String endTime;
		String h, m;
		String timeOfDay;
		int hour, minute;
		
		String[] splitTime = startTime.split(":|\\ ");
		
		h= splitTime[0];
		m = splitTime[1];
		timeOfDay = splitTime[2];
		
		hour = Integer.parseInt(h); 
		minute = Integer.parseInt(m);
		
		/*nested loops
		 * if minute + duration > 60; then add +1 to hour and minus 60 from minute
		 * if hour is greater than or equal to 12; then switch (timeOfDay) case "AM" = timeOfDay = PM. case "PM" = "AM"
		 */
		minute = minute + duration;
		String newTimeOfDay = null;
		
		while (minute >= 60){
			hour++;
			if (hour >= 12){
				switch (timeOfDay){
				case "AM": newTimeOfDay = "PM";
				break;
				case "PM": newTimeOfDay = "AM";
				break;
				}	
				timeOfDay = newTimeOfDay;
				hour = hour - 12;
			}
			minute -= 60;
		}
		
		if (hour == 0){
			hour =+ 12;
		}
		
		h = ( hour < 10 ? "0" : "") + hour;
		m = ( minute < 10 ? "0" : "") + minute;
		
		endTime = "" + h + ":" + m + " " + timeOfDay;
		
		return endTime;
	}
	
	public static boolean checkTimeFormat(String time){

		try {
			time.toUpperCase();
				
			time = time.replaceAll("\\s+", "");
			time = time.toUpperCase();
			
			if(time.matches("([0][1-9]|[1][0-2]):[0-5][0-9](AM|PM)")){
				return true;
			}
			
			else {
				return false;
			}
		}
		
		catch (Exception NumberFormatException){
			return false;
		}
	}
}
