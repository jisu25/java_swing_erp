package java_swing_erp.dto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java_swing_erp.ui.component.StudentManagement;
import java_swing_erp.ui.component.content.DepartmentPanel;
import java_swing_erp.ui.component.content.StudentPanel;
import java_swing_erp.ui.component.table.DepartmentTable;
import java_swing_erp.ui.component.table.StudentTable;

public class TestMain {
	public static void main(String[] args) {
//		testStudent();
//		testDepartment();
		
//		regExample();
		
		
		// StudentManagement Test
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagement frame = new StudentManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}


	private static void regExample() {
		String no = "12a";
		boolean isValidNum = Pattern.matches("\\d{1,3}", no); 
		System.out.println(no + ": " + isValidNum);
		
		String name = "이핳";
		boolean isValidHangle = Pattern.matches("^[가-힣]+$", name);
		System.out.println(name + ": " + isValidHangle);
		
		String tel = "02-012-3456";
		boolean isValidTel = Pattern.matches("\\d{2,3}-\\d{3,4}-\\d{4}", tel);
		System.out.println(tel + ": " + isValidTel);
	}
		

	private static void testDepartment() {
		JFrame frame = new JFrame(); // ㅎㅎ;;;
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DepartmentPanel dp = new DepartmentPanel();
		frame.add(dp, BorderLayout.NORTH);
		frame.setVisible(true);
		
		// isTfEmpty 테스트를 위해 이름을 비움
		Department dept = new Department(1, "경영학과", "053-1234-5678");
		dp.setItem(dept); // set돼죵??
		
		JButton btn = new JButton("확인"); // borderlayout south에 추가할 것.
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(dp.getItem());
			}
		});
		frame.add(btn, BorderLayout.EAST); // btn을 frame의 BorderLayout South에 추가해주세용
		System.out.println(dp.getItem()); // 굿굿
		
		
		ArrayList<Department> deptList = new ArrayList<>();
		
		// test용
		deptList.add(new Department(1, "경영학과", "053-111-1111"));
		deptList.add(new Department(2, "무역학과", "053-222-2222"));
		
		// 지금 콜럼이 안보이는데 콜럼이 보이려면 scrollPane안에다가 table을 넣어줘야한다.
		DepartmentTable table = new DepartmentTable();
		
		JScrollPane jp = new JScrollPane();
		jp.setViewportView(table);
		
		table.setItems(deptList); // stdList내용을 table에 넣어주는 함수를 만들어 보자.
		frame.add(jp, BorderLayout.CENTER);
		
		Department tdept = new Department(3, "경제학과", "053-333-3333");
		
//		stdList.add(tdept);
//		table.addRow(tdept);
		
//		table.removeRow(1); // 테이블에서 삭제하고 나면 어레이리스트에서도 삭제해줘야한다
//		stdList.remove(1);

		table.updateRow(1, tdept);
		deptList.set(1, tdept);
		
		frame.setVisible(true);
	}
	
	private static void testStudent() {
		JFrame frame = new JFrame(); // ㅎㅎ;;;
		frame.setSize(400, 600);
		StudentPanel sp = new StudentPanel();
		frame.add(sp, BorderLayout.NORTH);
		frame.setVisible(true);
		
		// isTfEmpty 테스트를 위해 이름을 비움
		Student std = new Student(1, "아이", 90, 80, 70);
		sp.setItem(std); // set돼죵??
		
		JButton btn = new JButton("확인"); // borderlayout south에 추가할 것.
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(sp.getItem());
			}
		});
		frame.add(btn, BorderLayout.EAST); // btn을 frame의 BorderLayout South에 추가해주세용
		System.out.println(sp.getItem()); // 굿굿
		// 전에는 SM 만들어서 테스트 했는데 지금은 패널 먼저 만들어서 테스트 합니당~
		// 이거의 문제점은 가져올 때 입력하면 요게(TextField의 값) 100보다 작아야 돼죠? 100보다 작게 조건 걸자
		// 글고 비어있는 곳도 없어야 한다.
		
		
		ArrayList<Student> stdList = new ArrayList<>();
		
		// test용
		stdList.add(new Student(1, "이보리", 100, 90, 80));
		stdList.add(new Student(2, "권보리", 100, 80, 92));
		stdList.add(new Student(3, "김보리", 81, 100, 70));
		stdList.add(new Student(4, "박보리", 92, 83, 99));
		
		// 지금 콜럼이 안보이는데 콜럼이 보이려면 scrollPane안에다가 table을 넣어줘야한다.
		StudentTable table = new StudentTable();
		
		JScrollPane jp = new JScrollPane();
		jp.setViewportView(table);
		
		table.setItems(stdList); // stdList내용을 table에 넣어주는 함수를 만들어 보자.
		frame.add(jp, BorderLayout.CENTER);
				
		Student tstd = new Student(5, "이지수", 80, 70, 60);

		stdList.add(tstd);
		table.addRow(tstd);
		
		table.removeRow(1); // 테이블에서 삭제하고 나면 어레이리스트에서도 삭제해줘야한다
		stdList.remove(1);
		
		tstd.setName("백령");
		tstd.setKor(100);
		tstd.setEng(0);
		tstd.setMath(100);
		
		int searchIdx = stdList.indexOf(tstd);
		
		table.updateRow(searchIdx, tstd);
		
		frame.setVisible(true);
	}

}
