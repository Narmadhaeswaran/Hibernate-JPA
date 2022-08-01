package com.mayuratech.bank;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Scanner;

public class Admin {
	public void userDetails() {
		
		Scanner scan=new Scanner(System.in);
		
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		int val=1;
		while(val==1) {
		System.out.println("Choose the Option:\n1.See All User Details\n2.Add New User\n3.Delete User\n4.Get Customer Based on Account Number\n0.LogOut");
		int adminOption=scan.nextInt();
		
		switch(adminOption) {
		case 1:
			//System.out.println("po");
			Query query = session.createQuery("from User");
			
			java.util.List<User> list = query.list();
			System.out.println("\n--------------------------------------------------------------------------------");
			System.out.format("|%-5s|%-20s|%-30s|%-20s|", "ID","Name","Account Number","Balance");
			System.out.println("\n--------------------------------------------------------------------------------");
			
			for(int i=0;i<list.size();i++) {
				System.out.format("|%-5d|%-20s|%-30s|%-20f|",list.get(i).getId(),list.get(i).getName(),list.get(i).getAccountNumber()
						, list.get(i).getBalance());
				System.out.println();
				//System.out.println(list.get(i).getAccountNumber());
			}
			System.out.println("--------------------------------------------------------------------------------");
			break;
		case 2:
			System.out.println("Enter the New User Name:");
			String name=scan.next();
			System.out.println("Enter the User Account Number:");
			String accountNumber=scan.next();
			if(accountNumber.length()==10) {
				
			
//			System.out.println(accountNumber.length());
			System.out.println("Enter the User Password:");
			String password=scan.next();
			System.out.println("Enter the Current initial Balance:");
			
			
			float balance=scan.nextFloat();
			
			
			User user=new User(name,accountNumber,password,balance);
			session.save(user);
			}else {
				System.out.println("Account number should be 10 digit");
				
			}
			break;
			
		case 3:
			System.out.println("Enter the Account Number to delete:");
			accountNumber=scan.next();
			if(accountNumber==accountNumber) {
			System.out.println("Are you sure to delete"+"Account number is="+accountNumber+". Choose 'Ok' to continue else Cancel"
											+ " the Transaction.\n1.Ok\n2.Cancel");
			int confirmOption=scan.nextInt();
			if(confirmOption==1) {
			Query query1 = session.createQuery("delete from User where accountNumber='"+accountNumber+"'");
			query1.executeUpdate();
			}
			}else {
				Query query1 = session.createQuery("Can't delete the Account Number"+accountNumber+"'");
				query1.executeUpdate();
//			System.out.println("Can't delete the Account Number");
			}
			break;
		
		case 4:
			System.out.println("Enter the Account Number to Search:");
			accountNumber=scan.next();
			query = session.createQuery("from User where accountNumber='"+accountNumber+"'");
			list = query.list();
			System.out.println("\n--------------------------------------------------------------------------------");
			System.out.format("|%-5s|%-20s|%-30s|%-20s|", "ID","Name","Account Number","Balance");
			System.out.println("\n--------------------------------------------------------------------------------");
			
			for(int i=0;i<list.size();i++) {
				System.out.format("|%-5d|%-20s|%-30s|%-20f|",list.get(i).getId(),list.get(i).getName(),list.get(i).getAccountNumber()
						, list.get(i).getBalance());
				System.out.println();
				//System.out.println(list.get(i).getAccountNumber());
			}
			System.out.println("--------------------------------------------------------------------------------");
			break;
			
		case 0:
			val=0;
			break;
		}
		
	
		
		
		}
		session.getTransaction().commit();
		/*
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		
		User u=new User("name1","name1","name1",20.0f);
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		*/
	}
}
