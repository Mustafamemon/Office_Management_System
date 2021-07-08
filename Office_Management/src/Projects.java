import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Projects {
	private Scanner scan;
	private Scanner scanner;
	private Employee employee_detail;

	public void Display_Projects(String Filename) throws FileNotFoundException {
		File file = new File("E:\\Java_Project\\Office_Management\\" + Filename);
		if (file.exists()) {
			scan = new Scanner(file);
			if (!scan.hasNextLine())
				System.out.println("No Data Found");
			while (scan.hasNextLine()) {
				System.out.println(scan.next());
			}
			scan.close();
		} else
			System.out.println("No Data Found");
	}

	public boolean search1(String name) throws FileNotFoundException {
		File file = new File("E:\\Java_Project\\Office_Management\\Projects.txt");
		if (file.exists()) {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				if (scan.next().equals(name)) {
					scan.close();
					return true;
				}
			}
			scan.close();
		}
		file = new File("E:\\Java_Project\\Office_Management\\Assign_Projects.txt");
		if (file.exists()) {
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				if (scan.next().equals(name)) {
					scan.close();
					return true;
				}
			}
			scan.close();
		}
		return false;
	}

	public void addProjects(String Filename, String name) throws IOException {
		String newLine = System.getProperty("line.separator");
		int f = 0;
		while (true) {
			if (name.equals(" ")) {
				f = 1;
				System.out.print("Enter project name  : ");
				scan = new Scanner(System.in);
				name = scan.next();
			}
			if (search1(name)) {
				System.out.println("Already Exist!");
				name = " ";
			} else {

				FileWriter project = new FileWriter("E:\\Java_Project\\Office_Management\\" + Filename, true);
				project.write(newLine);
				project.write(name);
				if (f == 1)
					System.out.println("Sucessfully Added!");
				project.close();
				break;
			}
		}

	}

	public void delProject(String name, int f) throws IOException {
		// System.out.println(f);
		String newLine = System.getProperty("line.separator");
		String user;
		int flag = 0;
		File file = new File("E:\\Java_Project\\Office_Management\\Projects.txt");
		if (file.exists()) {
			FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\Projectstemp.txt", true);
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				user = scan.next();
				if (user.equals(name)) {
					flag = 1;
					if (f == 1)
						System.out.println("Sucessfully Deleted!");
				} else {
					writer.write(newLine + user);
				}
			}
			writer.close();
			scan.close();
			file.delete();
			File tempfile = new File("E:\\Java_Project\\Office_Management\\Projectstemp.txt");
			tempfile.renameTo(file);
		}
		// System.out.println(flag);
		if (flag == 0) {
			file = new File("E:\\Java_Project\\Office_Management\\Assign_Projects.txt");
			if (file.exists()) {
				scan = new Scanner(file);
				FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\Projectstemp.txt", true);
				while (scan.hasNextLine()) {
					user = scan.next();
					if (user.equals(name)) {
						flag = 3;
						String ans;
						if (f != 5) {
							System.out.println("Do you want delete Assign Projects ?(y/n)");
							scanner = new Scanner(System.in);
							ans = scanner.next();
						} else
							ans = "y";
						if (ans.equals("y") || ans.equals("Y")) {
							if (f == 1)
								System.out.println("Sucessfully Deleted!");

							File file1 = new File("E:\\Java_Project\\Office_Management\\" + name + ".txt");
							if (f != 5) {
								scanner = new Scanner(file1);
								employee_detail = new Employee();
								String n = scanner.nextLine();
								if (n.isEmpty())
									n = scanner.nextLine();
								scanner.close();

								employee_detail.freeEmployee(n);
							}
							file1.delete();

						} else {
							writer.write(newLine + user);
						}

					} else {
						writer.write(newLine + user);
					}
				}
				scan.close();
				writer.close();
				file.delete();
				File tempfile = new File("E:\\Java_Project\\Office_Management\\Projectstemp.txt");
				tempfile.renameTo(file);
			}
		}
		if (flag == 0)
			System.out.println("Data not found!");

	}

	public void Display_project_detail(String Filename) throws FileNotFoundException {
		File file = new File("E:\\Java_Project\\Office_Management\\" + Filename + ".txt");
		if (file.exists()) {
			scan = new Scanner(file);
			scan.nextLine();
			if (!scan.hasNextLine())
				System.out.println("No Data Found");
			while (scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
			scan.close();
		} else
			System.out.println("File Not Found");
	}

	public void add_details(String User) throws FileNotFoundException, IOException {
		String newLine = System.getProperty("line.separator");
		employee_detail = new Employee();
		String proj_name = employee_detail.return_project_name(User);
		FileWriter file = new FileWriter("E:\\Java_Project\\Office_Management\\" + proj_name + ".txt", true);
		file.write(newLine + "----------------------------------------------------" + newLine);
		System.out.print("How many time do you spend on project ? ");
		scan = new Scanner(System.in);
		String n = scan.next();
		file.write(newLine + User + " spend " + n + " on project! " + newLine);
		file.write("Detail : ");
		System.out.print("Enter detail of your work : ");
		String data = scan.next();
		do {
			file.write(data);
			data = scan.nextLine();
		} while (data.length() > 0);
		file.write('.');
		file.close();
		File file_read = new File("E:\\Java_Project\\Office_Management\\" + proj_name + ".txt");
		scanner = new Scanner(file_read);
		n = scanner.nextLine();
		if (n.isEmpty())
			n = scanner.nextLine();
		scanner.close();
		String arr[] = n.split(" ");
		int flag = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].isEmpty() && !arr[i].equals(User)) {
				file_read = new File("E:\\Java_Project\\Office_Management\\" + arr[i] + "Alert.txt");

				if (file_read.exists()) {
					scanner = new Scanner(file_read);
					while (scanner.hasNextLine()) {
						if (scanner.nextLine().equals("Press 2 to check wht your teammate add details in project!")) {
							flag = 1;
							break;
						}
					}
					scanner.close();
				}
				if (flag == 0) {
					file = new FileWriter("E:\\Java_Project\\Office_Management\\" + arr[i] + "Alert.txt", true);
					file.write(newLine + "Press 2 to check wht your teammate add details in project!");
					file.close();
				}
			}
		}

	}

	public void freeProject(String Filename, String user) throws IOException {
		String newLine = System.getProperty("line.separator");
		File file = new File("E:\\Java_Project\\Office_Management\\" + Filename + ".txt");
		if (file.exists()) {
			FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\" + Filename + "temp.txt");
			scan = new Scanner(file);
			String n = scan.nextLine();
			String arr[] = n.split(" ");
			for (int i = 0; i < arr.length; i++) {
				if (!(arr[i].equals(user)))
					writer.write(" " + arr[i]);
			}
			while (scan.hasNextLine()) {
				writer.write(newLine + scan.nextLine());
			}
			writer.close();
			scan.close();
			file.delete();
			File tempfile = new File("E:\\Java_Project\\Office_Management\\" + Filename + "temp.txt");
			tempfile.renameTo(file);
		} else
			System.out.println("File not found");
	}

	public void Check_finish() throws IOException {
		String newLine = System.getProperty("line.separator");
		File file = new File("E:\\Java_Project\\Office_Management\\Finish.txt");
		if (file.exists()) {
			if (file.length() > 0) {
				scanner = new Scanner(file);
				System.out.println("Username   Projectname");
				while (scanner.hasNextLine()) {
					System.out.printf("%s %10s\n", scanner.next(), scanner.next());
				}
				scanner.close();
				System.out.println();
				System.out.println("----------------------------------");
				while (true) {
					int flag = 0;

					System.out.print("Enter Username to check project detail! : ");
					scan = new Scanner(System.in);
					String user = scan.next();
					scanner = new Scanner(file);
					while (scanner.hasNextLine()) {
						if (scanner.next().equals(user)) {
							String proj_name = scanner.next();
							Display_project_detail(proj_name);
							System.out.println();
							System.out.println("----------------------------------");
							System.out.println("Do you want to release that user from project(y/n)");
							scan = new Scanner(System.in);
							String ans = scan.next();
							if (ans.equals("y") || ans.equals("Y")) {
								System.out.println("Sucessfully removed!!");
								employee_detail = new Employee();
								employee_detail.freeEmployee(user);
								scanner.close();
								last_employee(proj_name, user,5);
								del_finish(user);
							} else if (ans.equals("N") || ans.equals("n")) {
								FileWriter Notification = new FileWriter(
										"E:\\Java_Project\\Office_Management\\" + user + "Alert.txt", true);
								Notification.write(newLine + "Boss did not release you from project!");
								Notification.close();
								scanner.close();
								del_finish(user);
							}
							flag = 1;
							break;

						} else
							scanner.next();
					}
					scanner.close();
					if (flag == 0) {
						System.out.println("Wrong username!");
						System.out.print("Re-");
					} else {
						break;
					}
				}

			} else {
				System.out.println("No data found!");
			}
		} else {
			System.out.println("No data found!");
		}

	}

	public void del_finish(String user) throws IOException {
		String newLine = System.getProperty("line.separator");
		File file = new File("E:\\Java_Project\\Office_Management\\Finish.txt");
		if (file.exists()) {
			FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\Finishtemp.txt", true);
			Scanner scan1 = new Scanner(file);
			while (scan1.hasNextLine()) {
				String ans = scan1.next();
				if (ans.equals(user)) {
					scan1.next();
				} else {
					writer.write(newLine);
					writer.write(ans + "	" + scan1.next());
				}
			}
			writer.close();
			scan1.close();
			file.delete();
			File tempfile = new File("E:\\Java_Project\\Office_Management\\Finishtemp.txt");
			tempfile.renameTo(file);
		}
	}

	public void last_employee(String name, String user, int f) throws IOException {
		// System.out.println(name);
		String newLine = System.getProperty("line.separator");
		int flag = 0;
		String n = "";
		File file = new File("E:\\Java_Project\\Office_Management\\" + name + ".txt");
		if (file.exists()) {
			scanner = new Scanner(file);
			n = scanner.nextLine();
			if (n.isEmpty())
				n = scanner.nextLine();
			scanner.close();
			String arr[] = n.split(" ");
			// System.out.println(arr.length);
			for (int i = 0; i < arr.length; i++) {
				if (!arr[i].isEmpty() && !arr[i].equals(user)) {

					flag = 1;
				}
			}

		}
		if (flag == 0) {
			delProject(name, 5);
			if (f != 5) {
				FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\Projects.txt", true);
				writer.write(newLine + name);
				writer.close();
			}

		}
		if (flag == 1) {
			String arr[] = n.split(" ");
			for (int i = 0; i < arr.length; i++) {
				if (!arr[i].isEmpty()) {
					FileWriter writer = new FileWriter("E:\\Java_Project\\Office_Management\\" + arr[i] + "Alert.txt",
							true);
					writer.write(newLine + "You teammate(" + user + ") has left the Project");
					writer.close();
				}
			}
		}

	}
}
