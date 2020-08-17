package java_swing_erp.ui.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java_swing_erp.dto.Student;
import java_swing_erp.ui.component.content.StudentDetailDlg;
import java_swing_erp.ui.component.content.StudentPanel;
import java_swing_erp.ui.component.table.StudentTable;

@SuppressWarnings("serial")
public class StudentManagement extends JFrame implements ActionListener {

	private JPanel contentPane;
	private StudentPanel pStudent;
	private JPanel pBtn;
	private JPanel pTable;
	private JButton btnAdd;
	private JButton btnCancle;
	private JScrollPane scrollPane;
	private StudentTable table;
	private ArrayList<Student> stdList = new ArrayList<Student>();

	public StudentManagement() {
		initComponents();

		Student std = new Student(1, "아이", 90, 80, 70);
		pStudent.setItem(std);

		// test용
		stdList.add(new Student(1, "이보리", 100, 90, 80));
		stdList.add(new Student(2, "권보리", 100, 80, 92));
		stdList.add(new Student(3, "김보리", 81, 100, 70));
		stdList.add(new Student(4, "박보리", 92, 83, 99));

		// 지금 콜럼이 안보이는데 콜럼이 보이려면 scrollPane안에다가 table을 넣어줘야한다.
		table.setItems(stdList);

	}

	private void initComponents() {
		setTitle("학생관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pStudent = new StudentPanel();
		contentPane.add(pStudent);

		pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnCancle = new JButton("취소");
		btnCancle.addActionListener(this);
		pBtn.add(btnCancle);

		pTable = new JPanel();
		contentPane.add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		pTable.add(scrollPane);

		table = new StudentTable();
		
		// popupmenu 장착
		customPopupMenu popMenu = new customPopupMenu(this);
		table.setComponentPopupMenu(popMenu);
		scrollPane.setViewportView(table);
	}

	public void actionPerformed(ActionEvent e) {
//		System.out.println(e); // 누른 button에 따른 이벤트 정보가 나옴
		if (e.getSource() instanceof JButton) {
			if (e.getSource() == btnCancle) {
				actionPerformedBtnCancle(e);
			}
			if (e.getSource() == btnAdd) {
				if(e.getActionCommand().equals("추가")) {
					actionPerformedBtnAdd(e);
				} else {
					actionPerformedBtnUpdate();
				}
			}
		}
		if (e.getSource() instanceof JMenuItem) {
			if(e.getActionCommand().equals("수정")) {
				actionPerformedMenuUpdate();
			}
			if(e.getActionCommand().equals("삭제")) {
				actionPerformedMenuDelete();
			}
			if(e.getActionCommand().equals("상세 정보")) {
				actionPerformedMenuDetail();
			}
		}
	}

	private void actionPerformedBtnUpdate() {
		// 1. StudentPanel에서 수정된 학생 정보를 가져옴
		// 2. stdList에 학생 정보 수정
		// 3. table에도 학생 정보 수정
		// 4. clearTf()
		// 5. setEditableTfNo(true)
		// 6. btnAdd의 text를 '추가'로 되돌리기
		// 7. message
		
		Student updatedStd = pStudent.getItem();
		int selIdx = stdList.indexOf(updatedStd);
		stdList.set(selIdx, updatedStd);
		table.updateRow(selIdx, updatedStd);
		pStudent.clearTf();
		pStudent.setTfEditable(true);
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, String.format("%s(%d) 수정 완료", updatedStd.getName(), updatedStd.getNo()));
		
	}

	private void actionPerformedMenuDetail() {
		System.out.println("상세정보");
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
			return;
		}
		Student detailStd = stdList.get(selIdx);
		System.out.println(detailStd);
		StudentDetailDlg dlg = new StudentDetailDlg();
        dlg.setStudent(detailStd);
        dlg.setTfNotEditable();
        dlg.setVisible(true);
	}

	private void actionPerformedMenuDelete() {
		System.out.println("삭제");
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
			return;
		}
		Student deletedStd = stdList.remove(selIdx); // remove의 return: 삭제한 요소의 정보
		table.removeRow(selIdx);
		JOptionPane.showMessageDialog(null, String.format("%s(%d) 삭제 완료", deletedStd.getName(), deletedStd.getNo()));
	}

	private void actionPerformedMenuUpdate() {
		System.out.println("수정");
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
			JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
			return;
		}
		Student selStd = stdList.get(selIdx);
		pStudent.setItem(selStd);
		// 1. btnAdd의 text를 '추가'에서 '수정'으로 변경
		// 2. tfNo는 수정불가
		btnAdd.setText("수정");
		pStudent.setEditableTfNo(false);
		
		// 수정 상태에서 취소버튼 누르면 clear해주면서 tf Editable 활성화 해주기
		// 수정 상태에서 btnAdd를 누르면 정보가 수정되도록 하기. 그리고 text를 '추가'로 다시 바꿔주기.
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		// 1. StudentPanel에서 getStudent()
		// 2. StudentTable에 addRow()
		// 3. stdList에도 add()
		// 4. pStudent clearTf()
		// 5. Message
		Student newStd = pStudent.getItem();
		table.addRow(newStd);
		stdList.add(newStd);
		pStudent.clearTf();
		JOptionPane.showMessageDialog(null, String.format("%s(%d) 추가완료", newStd.getName(), newStd.getNo()));
	}
	

	protected void actionPerformedBtnCancle(ActionEvent e) {
		pStudent.clearTf();
		if (btnAdd.getText().equals("수정")) {
			btnAdd.setText("추가");
			pStudent.setEditableTfNo(true);
			table.clearSelection(); // 선택 초기화
		}
	}
	
	private class customPopupMenu extends JPopupMenu {
		public customPopupMenu(ActionListener listener) {
			
			JMenuItem updateMenu = new JMenuItem("수정");
			updateMenu.addActionListener(listener);
			add(updateMenu);
			JMenuItem deleteMenu = new JMenuItem("삭제");
			deleteMenu.addActionListener(listener);
			add(deleteMenu);
			JMenuItem detailMenu = new JMenuItem("상세 정보");
			detailMenu.addActionListener(listener);
			add(detailMenu);
		}
		
	}
}
