package java_swing_erp.ui.component.content;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java_swing_erp.dto.Student;
import java_swing_erp.ui.exception.EmptyTfException;
import java_swing_erp.ui.exception.InvalidTfValue;

@SuppressWarnings("serial")
public class StudentPanel extends AbstractItemPanel<Student> {
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMath;

	public StudentPanel() {
		initComponents();
	}
	
	private void initComponents() {
		setBorder(new EmptyBorder(10, 10, 10, 10)); // padding. 안쪽 여백.
		setLayout(new GridLayout(0, 2, 20, 10));
		setPreferredSize(new Dimension(450, 600));
		
		JLabel lblNo = new JLabel("학생 번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNo);
		
		tfNo = new JTextField();
		add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("학생 성명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		add(tfName);
		
		JLabel lblKor = new JLabel("국어");
		lblKor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblKor);
		
		tfKor = new JTextField();
		tfKor.setColumns(10);
		add(tfKor);
		
		JLabel lblEng = new JLabel("영어");
		lblEng.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEng);
		
		tfEng = new JTextField();
		tfEng.setColumns(10);
		add(tfEng);
		
		JLabel lblMath = new JLabel("수학");
		lblMath.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMath);
		
		tfMath = new JTextField();
		tfMath.setColumns(10);
		add(tfMath);
	}

	@Override
	public void setItem(Student item) {
		tfNo.setText(String.valueOf(item.getNo())); // setText안에 문자열이 와야함.
		tfName.setText(item.getName()); // name은 스트링이니까 호호
		tfKor.setText(String.valueOf(item.getKor()));
		tfEng.setText(String.valueOf(item.getEng()));
		tfMath.setText(String.valueOf(item.getMath()));
	}
	
	// set을 Test하기 전에 get을 먼저 Test해야하겠죵.
	// 하나라도 비어 있으면 안되겠죵?
	@Override
	public Student getItem() {
		if (isTfEmpty()) {
			// 하나라도 비어있으면 안 된다!! 요 조건에 걸려야 한다.
			throw new EmptyTfException("공란이 존재");
		}
		// isValid가 이미 있는 함수여서 이름 바꿈
		if (isValidTf()) {
			// 국어, 영어, 수학이 0 미만이거나 100을 초과하면
			throw new InvalidTfValue("0~100 사이만 입력 가능");
		}
		int no = Integer.parseInt(tfNo.getText().trim());
		String name = tfName.getText();
		int kor = Integer.parseInt(tfKor.getText().trim());
		int eng = Integer.parseInt(tfEng.getText().trim());
		int math  = Integer.parseInt(tfMath.getText().trim());
		return new Student(no, name, kor, eng, math); // 아니네 변수가 없다.
	}

	@Override
	boolean isValidTf() {
		int kor = Integer.parseInt(tfKor.getText().trim());
		int eng = Integer.parseInt(tfEng.getText().trim());
		int math = Integer.parseInt(tfMath.getText().trim());
		if ( kor < 0 || kor > 100) { return true; }
		if ( eng < 0 || eng > 100) { return true; }
		if ( math < 0 || math > 100) { return true; }
		return false;
	}

	// isEmptyTf는 부모가 갖고 있다. 지우기~

	public void clearTf() {
		for (Component c : getComponents()) {
			if( c instanceof JTextField) { 
				((JTextField) c).setText("");
			}
		}
//		tfNo.setText("");
//		tfName.setText("");
//		tfKor.setText("");
//		tfEng.setText("");
//		tfMath.setText("");
	}
	
	// 왜 메서드로 만들어주냐? tf가 StudentPanel 안에 캡슐화 돼있기 때문에 외부에서 접근할 수 없다.
	// setter처럼 만들어준 것. adapt method
	@Override
	public void setEditableTfNo(boolean isEditable) {
		tfNo.setEditable(isEditable);
	}

	
	
} // end of StudentPanel


