package C0914_IO_PJT;

public class GoodsInfo {
	String code; // 상품명
	int stocks; //상품수량
	
	//상품명과, 수량을 입력받는 생성자
	public GoodsInfo(String code, int stocks) {
		this.code = code;
		this.stocks = stocks;
	}
	
	//수량을 매개변수로 받아와서 해당 상품의 수량을 증가시키는 메소드
	public int store(int stocks) {
		this.stocks+=stocks;
		return this.stocks;
	}
	
	//수량을 매개변수로 받아와서 해당 상품의 수량을 감소시키는 메소드.
	//단, 수량은 0보다 작아질 수 없음! (조건문을 활용한다.)
	public int out(int stocks) {
		int newStocks = this.stocks-stocks;
		
		//조건문을 활용해서 기존 수량에서 감소시킬 수량의 값이 0보다 클 경우, 수량을 감소시킴
		//그렇지 않을 경우, 재고가 부족함을 출력하고 해당 작업을 수행하지 않음.
		if(newStocks>=0) {
			this.stocks=newStocks;
			return newStocks;
		}
		System.out.println("재고가 부족합니다.");
		return 0;
	}
	
}
