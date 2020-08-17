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
public abstract class AbstractItemPanel<T> extends JPanel {
	
	/*
	public AbstractItemPanel() {
		initComponents();
	}
	
	private void initComponents() {
		setBorder(new EmptyBorder(10, 10, 10, 10)); // padding. 안쪽 여백.
		setLayout(new GridLayout(0, 2, 20, 10));
		setPreferredSize(new Dimension(450, 600));
	}
	*/
	
	public abstract void setItem(T item);
	
	public abstract T getItem();

	// 추상클래스를 상속 받아도 private은 접근 불가능. default로 바꿔준다.
	abstract boolean isValidTf();

	boolean isTfEmpty() {
		for (Component c : getComponents()) {
			if( c instanceof JTextField) { 
				if(((JTextField) c).getText().isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	public void clearTf() {
		for (Component c : getComponents()) {
			if( c instanceof JTextField) { 
				((JTextField) c).setText("");
			}
		}
	}
	
	public abstract void setEditableTfNo(boolean isEditable);
	
	public void setTfEditable(boolean isEditable) {
		for (Component c : getComponents()) {
			if( c instanceof JTextField) { 
				((JTextField) c).setEditable(isEditable);
			}
		}
	}
	
} // end of AbstractItemPanel


