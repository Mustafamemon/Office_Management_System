import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Office_Management {

	private static Scanner scan;
	public static int select;

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void main(String[] args) throws IOException {
		do {
			System.out.println("1:Login As Head");
			System.out.println("2:Login As Employee");
			System.out.println("3:Exit");
			System.out.print("Waiting for user input... :");
			scan = new Scanner(System.in);
			select = scan.nextInt();
			while (select == 1) {
				System.out.print("Enter username :");
				String user = scan.next();
				System.out.print("Enter password :");
				String pass = scan.next();
				Head h = new Head(user, pass);
				if (h.getUsername().equals("Mustafa") && h.getPassword().equals("1234")) {
					do {
						System.out.println("----------------------------------");
						h.alert_message("BossAlert.txt");
						System.out.println("----------------------------------");
						System.out.println("1.Add Employee");
						System.out.println("2.Add Project");
						System.out.println("3.Del Employee");
						System.out.println("4.Del Project");
						System.out.println("5.Assigned Project to Employee");
						System.out.println("6.Check details who has finished project!");
						System.out.println("7.Display all Data");
						System.out.println("8.Logout");
						System.out.print("Waiting for user input... :");
						select = scan.nextInt();
						System.out.println("----------------------------------");
						if (select == 1) {
							h.addEmployee("Employee.txt", " ", " ");
						} else if (select == 2) {
							h.addProjects("Projects.txt", " ");
						} else if (select == 3) {
							System.out.print("Enter username :");
							user = scan.next();
							h.delEmployee(user, 1);
						} else if (select == 4) {
							System.out.print("Enter Project name :");
							user = scan.next();
							h.delProject(user, 1);
						} else if (select == 5) {
							h.Assigned();
						}else if (select == 6) {
							h.Check_finish();
						}else if (select == 7) {
							int s;
							do {
								System.out.println("----------------------------------");
								System.out.println("1.Employee data");
								System.out.println("2.Projects data");
								System.out.println("3.Free Employee data");
								System.out.println("4.Engaged Employee data");
								System.out.println("5.Assigned projects");
								System.out.println("6.Non-Assigned projects");
								System.out.println("7.Back");
								System.out.print("Waiting for user input... :");
								s = scan.nextInt();
								System.out.println("----------------------------------");
								if (s == 1) {
									System.out.println("----------------------------------");
									System.out.println("Free Employees");
									System.out.println("----------------------------------");
									h.Display_Empployee("Employee.txt");
									System.out.println("----------------------------------");
									System.out.println("Assign Employees");
									System.out.println("----------------------------------");
									h.Display_Empployee("Assign_Employee.txt");

								} else if (s == 2) {
									System.out.println("----------------------------------");
									System.out.println("Free Projects");
									System.out.println("----------------------------------");
									h.Display_Projects("Projects.txt");
									System.out.println("----------------------------------");
									System.out.println("Assign Projects");
									System.out.println("----------------------------------");
									h.Display_Projects("Assign_Projects.txt");

								} else if (s == 3) {
									h.Display_Empployee("Employee.txt");
								} else if (s == 4) {
									h.Display_Empployee("Assign_Employee.txt");
								} else if (s == 5) {
									h.Display_Projects("Assign_Projects.txt");
								} else if (s == 6) {
									h.Display_Projects("Projects.txt");
								}

							} while (s != 7);

						}
					} while (select != 8);
				} else {
					System.out.println("Wrong username or password!!");
				}
			}
			while (select == 2) {
				File file = new File("E:\\Java_Project\\Office_Management\\Employee.txt");
				if (file.exists()) {
					System.out.print("Enter username :");
					String user = scan.next();
					System.out.print("Enter password :");
					String pass = scan.next();
					System.out.println("----------------------------------");
					Employee E = new Employee();
					if (E.search(user, pass)) {
						select = 4;
						System.out.println("Sucessfully Login");
						System.out.println("----------------------------------");
						E.alert_message(user + "Alert.txt");
						int n;
						String project_name = E.return_project_name(user);
						// System.out.println(project_name);
						if (!project_name.equals(" ")) {
							do {
								System.out.println("----------------------------------");
								System.out.println("1.Add project details");
								System.out.println("2.Display project details");
								System.out.println("3.If you finised your work");
								System.out.println("4.Logout");
								System.out.print("Waiting for user input... :");
								n = scan.nextInt();
								System.out.println("----------------------------------");
								if (n == 1) {
									Head h1 = new Head();
									h1.add_details(user);
								} else if (n == 2) {

									E.Display_project_detail(project_name);
								} else if (n == 3) {
									System.out.println("Generating Email to boss...");
									E.Project_finish(user);
								}
							} while (n != 4);
						} else {
							System.out.println("You are not assigned with any projects.");
							System.out.println("Press Any Key to Logout...");
							scan.next();
						}

					} else {
						System.out.println("Failed to Login");
						System.out.println("----------------------------------");
						System.out.print("Re-");
					}
				} else {
					System.out.println("----------------------------------");
					System.out.println("No Employee Resgistered!");
					System.out.println("----------------------------------");
					select = 4;
				}
			}

		} while (select != 3);
	}
}
