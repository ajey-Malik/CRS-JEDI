package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.business.ProfessorOperations;

import java.util.List;
import java.util.Scanner;

public class CRSProfessorMenu {
	private ProfessorOperations professorOps;

	public CRSProfessorMenu() {
		professorOps = new ProfessorOperations();
	}
	public void CreateProfessorMenu(Integer profId) {

		System.out.println("in professor menu!");
		Scanner sc=new Scanner(System.in);

		int input = 0;
		while(true)
		{

			System.out.println("\n************* Welcome Professor *************\n");
			System.out.println("\nChoose an option from the menu: ");
			System.out.println("---------------------------------------");
			System.out.println("Press 1: Course Selection");
			System.out.println("Press 2: Add Grade");
			System.out.println("Press 3: View Enrolled Students");
			System.out.println("Press 4: Logout ");
			System.out.println("*********************************************************");
			input =sc.nextInt();
			switch(input)
			{
				case 1:
					//view all the courses taught by the professor
					courseSelection(profId);
					break;
				case 2:
					//view all the enrolled students for the course
					addGrade(profId);
					break;

				case 3:
					//add grade for a student
					viewEnrolledStudents(profId);
					break;
				case 4:
					//logout from the system
					return;
//					System.exit(0);
				default:
					System.out.println("***** Wrong Choice *****");
			}
		}

	}
	public void viewEnrolledStudents(Integer profId)
	{
		professorOps.viewEnrolledStudents(profId);
	}

	public void addGrade(int professorId) {
		// Fetch courses taught by the professor
		List<Course> courses = professorOps.getCoursesTaughtByProfessor(professorId);

		// Check if the professor is teaching any courses
		if (courses.isEmpty()) {
			System.out.println("No courses found for Professor ID: " + professorId);
			return;
		}

		// Display courses to the professor
		System.out.println("Courses taught by Professor ID " + professorId + ":");
		for (int i = 0; i < courses.size(); i++) {
			System.out.println((i + 1) + ". " + courses.get(i).getCourseID() + " " +courses.get(i).getCoursename()); // Display course ID and name
		}

		// Prompt professor to select a course ID
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Course ID to add grades: ");
		String courseId = sc.nextLine();

		// Validate course ID
		boolean validCourse = courses.stream()
				.anyMatch(course -> course.getCourseID().equals(courseId));

		if (!validCourse) {
			System.out.println("Invalid Course ID. Please enter a valid Course ID from the list.");
			return;
		}

		// Add grades for the selected course
		boolean success = professorOps.addGradesForCourse(professorId, courseId);

		if (success) {
			System.out.println("Grades successfully added for all students in course " + courseId);
		} else {
			System.out.println("Failed to add grades. Please check the course ID and try again.");
		}
	}

	public void courseSelection(Integer profId) {
		professorOps.courseSelection(profId);
	}

}

