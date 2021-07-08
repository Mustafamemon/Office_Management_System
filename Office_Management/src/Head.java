import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Head extends Employee {
	private String username1;
	private String password1;
	private Scanner scan;

	public String getUsername() {
		return username1;
	}

	public void setUsername(String username) {
		this.username1 = username;
	}

	public String getPassword() {
		return password1;
	}

	public void setPassword(String password) {
		this.password1 = password;
	}

	public Head() {

	}

	public Head(String username, String password) {
		this.username1 = username;
		this.password1 = password;
	}

	public void Assigned() throws IOException {
		File file = new File("E:\\Java_Project\\Office_Management\\Projects.txt");
		if (file.exists()) {
			if (file.length() > 0) {
				String user = " ", proj = " ";
				System.out.println("Free Project");
				Display_Projects("Projects.txt");
				while (true) {
					System.out.println("Enter Project name to Assign");
					scan = new Scanner(System.in);
					proj = scan.next();
					if (search1(proj))
						break;
					else {
						System.out.println("Project name not found!");
						System.out.print("Re-");

					}
				}
				// proj += ".txt";
				file = new File("E:\\Java_Project\\Office_Management\\Employee.txt");
				int cnt = 0,n;
				if (file.exists()) {
					if (file.length() > 0) {
						scan = new Scanner(file);
						while (scan.hasNextLine()) {
							scan.nextLine();
							cnt++;
						}
						cnt--;
						scan.close();
						FileWriter Assign = new FileWriter("E:\\Java_Project\\Office_Management\\" + proj + ".txt",
								true);
						System.out.println("Free Employees");
						Display_Empployee("Employee.txt");
						while (true) {
							System.out.println("Enter the number of employee : ");
							scan = new Scanner(System.in);
							 n = scan.nextInt();
							if (n > cnt) {
								System.out.println("Number of employee not Available!");
								System.out.print("Re-");

							} else
								break;
						}
						for (int i = 0; i < n; i++) {
							while (true) {
								System.out.printf("Enter %d Employee UserName : ", i + 1);
								scan = new Scanner(System.in);
								user = scan.next();
								if (search(user, " "))
									break;
								else {
									System.out.println("Username not found!");
									System.out.print("Re-");
								}
							}
							String newLine = System.getProperty("line.separator");
							String pass = delEmployee(user, 0);
							addEmployee("Assign_Employee.txt", user, pass);
							// Display_Empployee_detail(user);
							FileWriter Notification = new FileWriter(
									"E:\\Java_Project\\Office_Management\\" + user + "Alert.txt", true);
							Notification.write(newLine + "You are Asssigned with Project  : " + proj);
							Notification.close();

							System.out.println();

							Assign.write(" " + user);
						}
						delProject(proj, 0);
						addProjects("Assign_Projects.txt", proj);
						Assign.close();
					}
					else {
						System.out.println("All Employees are engaged!");
					}
				}
				else {
					System.out.println("All Employees are engaged!");
				}

			} else {
				System.out.println("No Free Project!");
			}
		} else {
			System.out.println("No Free Project!");
		}

	}
}
