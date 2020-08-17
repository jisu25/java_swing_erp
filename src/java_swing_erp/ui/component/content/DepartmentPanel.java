package java_swing_erp.ui.component.content;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java_swing_erp.dto.Department;
import java_swing_erp.ui.exception.EmptyTfException;
import java_swing_erp.ui.exception.InvalidTfValue;

public class DepartmentPanel extends AbstractItemPanel<Department> {
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfTel;

	public DepartmentPanel() {

		initComponents();
	}
	
	private void initComponents() {
		setBorder(new EmptyBorder(10, 10, 10, 10)); // padding. 안쪽 여백.
		setLayout(new GridLayout(0, 2, 20, 10));
		setPreferredSize(new Dimension(450, 600));
		
		JLabel lblNo = new JLabel("학과 번호");
		add(lblNo);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("학과 번호");
		add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		add(tfName);
		
		JLabel lblTel = new JLabel("학과 번호");
		add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setColumns(10);
		add(tfTel);
	}

	@Override
	public void setItem(Department item) {
		tfNo.setText(String.valueOf(item.getNo()));
		tfName.setText(item.getName());
		tfTel.setText(item.getTel());
	}

	@Override
	public Department getItem() {
		if (isTfEmpty()) {
			throw new EmptyTfException("공란이 존재");
		}
		if (!isValidTf()) {
			throw new InvalidTfValue("유효하지 않습니다");
		}
		
		int no =Integer.parseInt(tfNo.getText().trim());
		String name = tfName.getText().trim();
		String tel = tfTel.getText().trim();
		
		return new Department(no, name, tel);
	}

	@Override
	boolean isValidTf() {
		// !!정규표현식!!
		// tfNo는 숫자만 가능
		// tfName은 한글만 가능
		// tfTel 3자리 숫자 - 3~4자리 숫자 - 3~4자리 숫자
		
		String no = tfNo.getText().trim();
		String name = tfName.getText().trim();
		String tel = tfTel.getText().trim();
		
		// 정규표현식 적용
		boolean noCheck = Pattern.matches("\\d{1,3}", no);
		boolean nameCheck = Pattern.matches("^[가-힣]+$", name);
		boolean telCheck = Pattern.matches("\\d{2,3}-\\d{3,4}-\\d{4}", tel);
//		System.out.println(no + name + tel);
//		System.out.println(noCheck + "" + nameCheck + "" + telCheck);
		return noCheck && nameCheck && telCheck;
	}

	@Override
	public void setEditableTfNo(boolean isEditable) {
		tfNo.setEditable(isEditable);
	}

//	@Override
//	public void setEditableTfNo(boolean isEditable) {
//		tfNo.setEditable(isEditable);
//	}

}
