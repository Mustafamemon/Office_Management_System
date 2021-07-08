import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Employee extends Projects {
	private String username;
	private String password;
	private Scanner scan;
	private Scanner scanner;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void Display_Empployee(String Filename) throws FileNotFoundException {
		File file = new File("E:\\Java_Project\\Office_Management\\" + Filename);
		if (file.exists()) {
			scan = new Scanner(file);
			System.out.println("UserName     Password");
			System.out.println("----------------------------------");
			if (!scan.hasNextLine())
				System.out.println("No Data Found");
			while (scan.hasNextLine()) {
				System.out.printf("%s		%5s\n", scan.next(), scan.next());
			}
			scan.close();
		} else {
			System.out.println("No Data Found");
		}
	}

	public void Display_Empployee_detail(String name) throws FileNotFoundException {
		System.out.println("Employee Detail");
		File emp_detail = new File("E:\\Java_Project\\Office_Management\\" + name);
		scan = new Scanner(emp_detail);
		if (!scan.hasNextLine())
			System.out.println("No Data Found");
		while (scan.hasNextLine()) {
			System.out.println(scan.nextLine());
		}
		scan.close();
	}

	public void addEmployee(String Filename, String name, String pass) throws IOException {
		String newLine = System.getProperty("line.separator");

		while (true) {
			if (name.equals(" ")) {
				System.out.print("Enter Username  : ");
				scan = new Scanner(System.in);
				name = scan.next();
			}
			if (search(name, " ")) {
				System.out.println("Already Exist!");
				name = " ";
			} else {
				scan = new Scanner(System.in);
				FileWriter employee = new FileWriter("E:\\Java_Project\\Office_Management\\" + Filename, true);
				employee.write(newLine);
				employee.write(name);
				employee.write("	");
				if (pass.equals(" ")) {
					System.out.print("Enter Passowrd : ");
					scan = new Scanner(System.in);
					pass = scan.next();
					System.out.println("Sucessfully Added!");
				}
				employee.write(pass);

				employee.close();
				break;
			}
		}

	}

	public boolean search(String name, String pass) throws FileNotFoundException {
		File file = new File("E:\\Java_Project\\Office_Management\\Employee.txt");
		if (file.exists()) {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				if (scan.next().equals(name)) {
					if (pass.equals(" ")) {
						scan.close();
						return true;
					} else {
						if (scan.next().equals(pass)) {
							scan.close();
							return true;
						} else {
							scan.close();
							return false;
						}
					}

				}
				scan.next();
			}
			scan.close();
		}
		file = new File("E:\\Java_Project\\Office_Management\\Assign_Employee.txt");
		if (file.exists()) {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				if (scan.next().equals(name)) {
					if (pass.equals(" ")) {
						scan.close();
						return true;
					} else {
						if (scan.next().equals(pass)) {
							scan.close();
							return true;
						} else {
							scan.close();
							return false;
						}
					}
				}
				scan.next();
			}
			scan.close();
		}
		return false;
	}

	public String delEmployee(String name, int f) throws IOException {
		int flag = 0;
		String pass = null, user;
		String newLine = System.getProperty("line.separator");
		File file = new File("E:\\Java_Project\\Office_Management\\Employee.txt");
		if (file.exists()) {
			FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\Employeetemp.txt", true);
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				user = scan.next();
				if (user.equals(name)) {
					flag = 1;
					pass = scan.next();
					if (f == 1)
						System.out.println("Sucessfully Deleted!");
					else if (f != 5)
						System.out.println("Sucessfully Assigned!");
				} else {
					writer.write(newLine);
					writer.write(user + "	" + scan.next());
				}
			}
			writer.close();
			scan.close();
			file.delete();
			File tempfile = new File("E:\\Java_Project\\Office_Management\\Employeetemp.txt");
			tempfile.renameTo(file);
		}
		if (flag == 0) {
			file = new File("E:\\Java_Project\\Office_Management\\Assign_Employee.txt");
			if (file.exists()) {
				FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\Employeetemp.txt", true);
				scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					user = scanner.next();
					if (user.equals(name)) {
						String ans;
						if (f != 5) {
							System.out.print("The members has assigned with Project Do you want to delete ?(y/n) :");
							scan = new Scanner(System.in);
							ans = scan.next();
						} else
							ans = "y";
						if (ans.equals("y") || ans.equals("Y")) {
							String proj_name=return_project_name(user);
							freeProject(proj_name, user);
							last_employee(proj_name, user,0);
							File alert = new File("E:\\Java_Project\\Office_Management\\" + user + "Alert.txt");
							if (alert.exists() && f != 5)
								alert.delete();
							flag = 1;
							pass = scanner.next();
							if (f == 1)
								System.out.println("Sucessfully Deleted!");
							else if (f != 5)
								System.out.println("Sucessfully Assigned!");
						} else {
							flag = 1;
							writer.write(newLine);
							writer.write(user + "	" + scanner.next());
						}
					} else {
						writer.write(newLine);
						writer.write(user + "	" + scanner.next());
					}

				}
				writer.close();
				scanner.close();
				file.delete();
				File tempfile = new File("E:\\Java_Project\\Office_Management\\Employeetemp.txt");
				tempfile.renameTo(file);
			}
		}
		if (flag == 0 && f != 5)
			System.out.println("Data Not Found!");
		return pass;
	}

	public void alert_message(String Filename) throws FileNotFoundException {
		File file = new File("E:\\Java_Project\\Office_Management\\" + Filename);
		if (!file.exists())
			System.out.println("No Notification");
		else {
			scan = new Scanner(file);
			while (scan.hasNext()) {
				System.out.println(scan.nextLine());
			}
			scan.close();
			file.delete();
		}

	}

	public String return_project_name(String user) throws FileNotFoundException {
		File file = new File("E:\\Java_Project\\Office_Management\\Assign_Projects.txt");
		scan = new Scanner(file);
		String project_name = " ";
		while (scan.hasNext()) {
			project_name = scan.next();
			File file1 = new File("E:\\Java_Project\\Office_Management\\" + project_name + ".txt");
			Scanner scanner = new Scanner(file1);
			while (true) {
				String ans = scanner.nextLine();
				if (!ans.isEmpty()) {
					String arr[] = ans.split(" ");
					for (int i = 0; i < arr.length; i++) {
						if (arr[i].equals(user)) {
							scan.close();
							scanner.close();
							return project_name;
						}
					}
					break;

				}
			}
			scanner.close();

		}
		scan.close();
		return " ";

	}

	public void freeEmployee(String User) throws IOException {
		//System.out.println("User :" + User);
		String newLine = System.getProperty("line.separator");
		String arr[] = User.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].isEmpty()) {
				String pass = delEmployee(arr[i], 5);
				// System.out.println(arr[i] + " " + pass);
				addEmployee("Employee.txt", arr[i], pass);
				FileWriter Notification = new FileWriter("E:\\Java_Project\\Office_Management\\" + arr[i] + "Alert.txt",
						true);
				Notification.write(newLine + "Now you are free from Project ");
				Notification.close();
			}
		}

	}

	public void Project_finish(String user) throws IOException {
		String newLine = System.getProperty("line.separator");
		FileWriter Notification = new FileWriter("E:\\Java_Project\\Office_Management\\BossAlert.txt", true);
		String proj_name = return_project_name(user);
		Notification.write(newLine + user + " has done with " + proj_name);
		Notification.close();
		Notification = new FileWriter("E:\\Java_Project\\Office_Management\\Finish.txt", true);
		Notification.write(newLine + user + " " + proj_name);
		Notification.close();
	}
}
