package java_swing_erp.ui.component.table;

import java.util.ArrayList;

import java_swing_erp.dto.Department;

public class DepartmentTable extends AbstractItemTable<Department>{

	@Override
	String[] getColName() {
		return new String[] {"학과 번호", "학과명", "사무실 연락처"};
	}

	@Override
	Object[] toArray(Department item) {
		return new Object[] { item.getNo(), item.getName(), item.getTel()};
	}

	@Override
	void setWidthAndAlign() {
		
	}

}
