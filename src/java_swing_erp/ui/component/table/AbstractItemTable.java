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
public abstract class AbstractItemTable<T> extends JTable{

		private CustomModel model;
		private ArrayList<T> list;

		public AbstractItemTable() {
			initComponents();
		}
		
		void initComponents() {
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		void loadData(ArrayList<T> itemList) {
			model = new CustomModel(getRows(itemList), getColName());
			setModel(model);
		}

		abstract String[] getColName();

		private Object[][] getRows(ArrayList<T> itemList) {
			Object[][] rows = new Object[itemList.size()][];
			for(int i = 0; i < rows.length; i++) {
				rows[i] = toArray(itemList.get(i));
			}
			return rows;
		}
		
		abstract Object[] toArray(T item);
		
		public void setItems(ArrayList<T> itemList) {
			loadData(itemList);
			
			// 위에 loadData는 살리고 싶지만
			// 밑에 너비 지정 메소드들은 abstract 해야함. 그래서 메소드로 따로 빼줌
			setWidthAndAlign();
		}

		/**
		 *  // column width
		 *  private void setWidthAndAlign() {
				tableSetwidth(80, 120, 80, 80, 80, 100, 100);
				// column alignment
				tableCellAlign(SwingConstants.CENTER, 0, 1); <br>
				tableCellAlign(SwingConstants.RIGHT, 2, 3, 4, 5, 6); <br>
			}
		 */
		
		abstract void setWidthAndAlign();
		
		protected void tableCellAlign(int align, int...idx) {
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(align);
			
			TableColumnModel tcm = getColumnModel();
			for(int i = 0; i < idx.length; i++) {
				tcm.getColumn(idx[i]).setCellRenderer(dtcr);
			}
		}
	
		protected void tableSetwidth(int...width) {
			TableColumnModel tcm = getColumnModel();
			for(int i = 0; i < width.length; i++) {
				tcm.getColumn(i).setPreferredWidth(width[i]);
			}
		}
		

		// 이 클래스는 맨 뒤에 놓을 거임
		private class CustomModel extends DefaultTableModel {

			public CustomModel(Object[][] data, Object[] columnNames) {
				super(data, columnNames);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		}
		
		public void addRow(T item) {
			model.addRow(toArray(item));
		}
		
		public void removeRow(int idx) {
			model.removeRow(idx);
		}

		public void updateRow(int idx, T item) {
			model.removeRow(idx);
			model.insertRow(idx, toArray(item));
		}
}
