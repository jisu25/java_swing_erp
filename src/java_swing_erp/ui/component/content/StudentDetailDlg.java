package java_swing_erp.ui.component.content;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java_swing_erp.dto.Student;

public class StudentDetailDlg extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private StudentPanel pStudent;
	private JButton okButton;

	public StudentDetailDlg() {
		initComponents();
	}
	
	public void setTfNotEditable() {
		pStudent.setTfEditable(false);

	}
	
	public void setStudent(Student student) {
		pStudent.setItem(student);
		
		JLabel lblTotal = new JLabel("총점");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		pStudent.add(lblTotal);
		
		JLabel lblTotalValue = new JLabel(String.valueOf(student.getTotal()));
		lblTotalValue.setHorizontalAlignment(SwingConstants.LEFT);
		pStudent.add(lblTotalValue);
		
		JLabel lblAvg = new JLabel("평균");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		pStudent.add(lblAvg);
		
		JLabel lblAvgValue = new JLabel(String.valueOf(student.getAverage()));
		lblAvgValue.setHorizontalAlignment(SwingConstants.LEFT);
		pStudent.add(lblAvgValue);
	}

	private void initComponents() {
		setTitle("학생 세부 정보");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "학생 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			pStudent = new StudentPanel();
			contentPanel.add(pStudent, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			actionPerformedOkButton(e);
		}
	}
	
	protected void actionPerformedOkButton(ActionEvent e) {
		dispose();
	}
}
