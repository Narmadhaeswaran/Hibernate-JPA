package com.mayuratech.bank;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class UserDetails {
	public void userFunction() {
		int process=1;
		String accountNumber="";
		String password="";
		int check=0;
		
		Scanner scan=new Scanner(System.in);
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
		while(process==1) {
		
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from User");
		java.util.List<User> list = query.list();
		if(check!=1) {
		System.out.println("Enter the Account Number:");
		accountNumber=scan.next();
		System.out.println("Enter the Password:");
		password=scan.next();
		}
		
		
		
		for(int i=0;i<list.size();i++) {
			if((list.get(i).getAccountNumber()).equals(accountNumber) && (list.get(i).getPassword()).equals(password)) {
				check=1;
				
					
					System.out.println("Welcome\nChoose the Option:\n1.Check Balance\n2.Deposit\n3.Withdraw\n0.LogOut");
					int option=scan.nextInt();
					switch(option) {
					case 1:
						System.out.println("Current Balance :   "+ list.get(i).getBalance());
						break;
						
					case 2:
						System.out.println("Enter Amount to deposit :");
						float deposit=scan.nextFloat();
						float balance=list.get(i).getBalance() + deposit;
						Query query1 = session.createQuery("update User set balance="+balance+" where accountNumber='"+accountNumber+"'");
						query1.executeUpdate();
						break;
						
					case 3:
						System.out.println("Enter Amount to withdraw :");
						float withdraw=scan.nextFloat();
						balance=list.get(i).getBalance() - withdraw;
						if(balance<100.00) {
							System.out.println("If Current Balance is less then 100 means your account will Block."
									+ " Your Current Balance is "+balance+". Choose 'Ok' to continue else Cancel"
											+ " the Transaction.\n1.Ok\n2.Cancel");
							int confirmOption=scan.nextInt();
							if(confirmOption==1) {
								Query accountDelete = session.createQuery("delete from User where accountNumber='"+accountNumber+"'");
								accountDelete.executeUpdate();
								process=0;
							}
							
						}else {
							Query queryWithDraw = session.createQuery("update User set balance="+balance+" where accountNumber='"+accountNumber+"'");
							queryWithDraw.executeUpdate();
						}
						break;
						
					case 0:
						process=0;
						break;
					}
					//session.getTransaction().commit();
					//session.beginTransaction();
				
				
			}
		}
		session.getTransaction().commit();
		
	}
	}
}