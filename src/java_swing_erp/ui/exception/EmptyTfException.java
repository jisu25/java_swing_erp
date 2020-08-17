package java_swing_erp.ui.exception;

// 런타임 예외
public class EmptyTfException extends RuntimeException {

	// alt + shift + m - super로부터 생성자 받기 - string만 매개변수인 거 고르기
	public EmptyTfException(String message) {
		super(message);
	}

}
