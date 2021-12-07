import java.util.Scanner;

public class FinalGradeCalculator {
	public static void main(String[] args) {
		// Initialize Scanner and Variables
		Scanner input = new Scanner(System.in);
		double weight = 0, possiblePoints = 0, earnedPoints;
		double grade = 0.0;
		boolean predict = true;
		
		// Prompt user for number of categories (example: Assignments, Homework, Exam 1, Exam 2)
		// & create an array of Categories
		System.out.print("Enter the number of categories: ");
		Category[] cat = new Category[input.nextInt()];
		
		// Run a for-loop and ask user to input values necessary for calculations
		for (int i = 0; i < cat.length; i++) {
			System.out.printf("%nEnter the weight of Category %d, in terms of %c of grade: ",
													 i + 1, '%');
			weight = input.nextDouble();
			
			System.out.printf("Enter the maximum amount of available points in Category %d: ", i + 1);
			possiblePoints = input.nextDouble();	
			
			System.out.printf("Enter the amount of points you've scored in Category %d: ", i + 1);
			earnedPoints = input.nextDouble();
			
			// Initialize a Category and retrieve the current final grade (so far)
			cat[i] = new Category(weight, possiblePoints, earnedPoints);
			grade += (cat[i].getGrade() * cat[i].getWeight()) * 100.0;
			
			System.out.println();
			System.out.print("Is the next (& last) category the final exam? (y/n): ");
			
			if (input.next().charAt(0) == 'y') {
				System.out.printf("%nEnter the weight of your final exam: ");
				weight = input.nextDouble() / 100.0;
				
				System.out.printf("%nEnter the maximum amount of available points: ");
				possiblePoints = input.nextDouble();	
				
				System.out.printf("%nHave you taken the final yet? (y/n): ");
				
				if (input.next().charAt(0) == 'y') {
					
					System.out.printf("%nEnter the amount of points you've scored: ");
					earnedPoints = input.nextDouble();
					
					// Initialize a Category and retrieve the current final grade (so far)
					cat[i] = new Category(weight, possiblePoints, earnedPoints);
					grade += cat[i].getGrade() / cat[i].getWeight();
					
					predict = false;
				}
				
				break;
			}
		}
	
		printReport(cat);
		System.out.println("Grade: " + grade);
		
		while (predict) {
			System.out.printf("%nWhat is your desired final grade?: ");
			double desired = input.nextDouble();
			
			double remainder = desired - grade;
			
			if (remainder > 0) {
				System.out.printf("You need %.2f points to reach your desired grade of %.1f.%nThis is equivilant to a score of %.2f%c.", 
						remainder, desired, remainder / weight, '%');
			} else {
				System.out.printf("CONGRATULATIONS!%nBased on your input, even if you got the "
						+ "lowest score imaginable, you've already earned enough points for your final desired grade of %.1f.", desired);
			}
			
			
			System.out.printf("%n%nWould you like to enter another desired grade? (y/n): ");
			if (input.next().charAt(0) == 'n') {
				predict = false;
			}
		}
		
		input.close();
		
	}
	public static void printReport(Category[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				System.out.printf("Category %d:\t%s%n", i + 1, array[i]);
			} else {
				break;
			}
		}
		System.out.printf("%n%n");
	}
}

class Category {
	private double weight;
	private double possiblePoints;
	private double earnedPoints;
	private double grade;
	
	public Category(double weight, double possiblePoints, double earnedPoints) {
		setWeight(weight);
		setPossiblePoints(possiblePoints);
		setEarnedPoints(earnedPoints);
		setGrade(getEarnedPoints() / getPossiblePoints());
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		if (weight < 100) {
			this.weight = weight / 100.0;
		}
	}
	public double getPossiblePoints() {
		return possiblePoints;
	}
	public void setPossiblePoints(double possiblePoints) {
		this.possiblePoints = possiblePoints;
	}
	public double getEarnedPoints() {
		return earnedPoints;
	}
	public void setEarnedPoints(double earnedPoints) {
		if (getPossiblePoints() >= earnedPoints) {
			this.earnedPoints = earnedPoints;
		}
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String toString() {
		return String.format("Weight:\t%.1f%c\tGrade:\t%.1f", getWeight() * 100.0, '%', getGrade() * 100.0);
	}
}