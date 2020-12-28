import java.util.*;
//By Alena Midgen, no collaborators
class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		//since we want to maximize the sum of weights, the one with the higher weight should go first
		if(a1.weight>a2.weight) {
			return -1;
		} if(a1.weight<a2.weight) {
			return 1;
		}
		//if the weights are equal, choose the one which is due later to go first 
		if(a1.deadline>a2.deadline) {
			return -1;
		} if(a1.deadline<a2.deadline) {
			return 1;
		}
		
		//if they have the same deadline and weight, the order between them doesn't matter
		return 0;
	}
 
}
 
public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {									
		//TODO Implement this
		
		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		//making a deep copy of the assignment list, so that the algorithm can remove elements from it
		ArrayList<Assignment> a = new ArrayList<Assignment>();
 
		for(Assignment x:Assignments) {
			Assignment y = new Assignment(x.number, x.weight,x.deadline);
			a.add(y);
		}
		
		// If homeworkPlan[i] has a value -1, it indicates that the 
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];
		//iterating through all slots of homeworkPlan
		for (int i=0; i < homeworkPlan.length; ++i) {
			//if there are no assignments left to add, fill the homeworkPlan with -1s
			if(a.size()==0) {
				homeworkPlan[lastDeadline-i-1] = -1;
				continue;
			}
			//gets the next assignment
			Assignment next = a.get(0);
			//if it's due later than the last time slot, add it to the next availabe slot from the end of homeworkPlan
			if(next.deadline>=(lastDeadline-i)) {
				homeworkPlan[lastDeadline-i-1]=next.number;
				//remove the assignment that was added from the list of assignments
				a.remove(0);
			}
			//if it was due earlier than the time slot to add an assignment in
			else {
				//iterate through the rest of the assignments in the list
				for(int index = 1; index <= a.size(); index++) {
					//if the index is too big (has gone through all assignments), then set the value in the homework plan to -1, rest index and break
					if(index == a.size()) {
						homeworkPlan[lastDeadline-i-1] = -1;
						break;
					}
					next = a.get(index);
					//if the next assignments' deadline fits, put it in the homework plan, remove it, reset the index and break the while loop
					if(next.deadline >= (lastDeadline-i)) {
						homeworkPlan[lastDeadline-i-1] = next.number;
						a.remove(index);
						break;
					} 
				}	
			}
		}
		//after the loop, return homeworkPlan
		return homeworkPlan;
	}
}
