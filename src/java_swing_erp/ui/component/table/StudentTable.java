package java_swing_erp.ui.component.table;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java_swing_erp.dto.Student;

@SuppressWarnings("serial")
public class StudentTable extends AbstractItemTable<Student>{

		@Override
		String[] getColName() {
			return new String[] { "번호", "성명", "국어", "영어", "수학", "총점", "평균" };
		}

		
		@Override
		Object[] toArray(Student std) {
			return new Object[] {
					std.getNo(),
					std.getName(),
					std.getKor(),
					std.getEng(),
					std.getMath(),
					std.getTotal(),
					std.getAverage()
			};
		}

		/*
		public void setItems(ArrayList<Student> stdList) {
			loadData(stdList);
			
			// column width
			tableSetwidth(80, 120, 80, 80, 80, 100, 100);
			// column alignment
			tableCellAlign(SwingConstants.CENTER, 0, 1);
			tableCellAlign(SwingConstants.RIGHT, 2, 3, 4, 5, 6);
		}
		*/

		@Override
		public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			Component c = super.prepareRenderer(renderer, row, column);
			double value = (double) getValueAt(row, 6); // 평균점수
			if (value >= 90) { // 평균이 90점 이상이면
				c.setBackground(new Color(255, 0, 0, 127));
			} else {
				c.setBackground(Color.white);
			}
			super.prepareRenderer(renderer, row, column); // 다시 호출해줘야, 선택한 행 색이 바뀜
			return c;
		}

		@Override
		void setWidthAndAlign() {
			// column width
			tableSetwidth(80, 120, 80, 80, 80, 100, 100);
			// column alignment
			tableCellAlign(SwingConstants.CENTER, 0, 1);
			tableCellAlign(SwingConstants.RIGHT, 2, 3, 4, 5, 6);
		}

}
