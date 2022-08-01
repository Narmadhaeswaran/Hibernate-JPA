package com.mayuratech.bank;

import java.util.Scanner;

public class Main {
	public static void main(String []args) {
		Scanner scan=new Scanner(System.in);
		while(true) {
			System.out.println("Welcome\n1.Admin\n2.User\nChoose your Login Type");
			int loginType=scan.nextInt();
			if(loginType==0) {
				break;
			}else if(loginType==1) {
				System.out.println("Enter UserName:");
				String adminName=scan.next();
				System.out.println("Enter Password");
				String adminPassword=scan.next();
				if(adminName.equals("Admin") && adminPassword.equals("@dm!n@123")) {
					Admin ad=new Admin();
					ad.userDetails();
				}else {
					System.out.println("Invalid UserName or Password...!");
				}
			}else if(loginType==2) {
				UserDetails ud=new UserDetails();
				ud.userFunction();
			}else {
				System.out.println("Choose Valid Login Type...!");
			}
		}
		scan.close();
	}
}
