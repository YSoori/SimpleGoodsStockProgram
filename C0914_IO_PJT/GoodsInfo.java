package C0914_IO_PJT;

public class GoodsInfo {
	String code; // ��ǰ��
	int stocks; //��ǰ����
	
	//��ǰ���, ������ �Է¹޴� ������
	public GoodsInfo(String code, int stocks) {
		this.code = code;
		this.stocks = stocks;
	}
	
	//������ �Ű������� �޾ƿͼ� �ش� ��ǰ�� ������ ������Ű�� �޼ҵ�
	public int store(int stocks) {
		this.stocks+=stocks;
		return this.stocks;
	}
	
	//������ �Ű������� �޾ƿͼ� �ش� ��ǰ�� ������ ���ҽ�Ű�� �޼ҵ�.
	//��, ������ 0���� �۾��� �� ����! (���ǹ��� Ȱ���Ѵ�.)
	public int out(int stocks) {
		int newStocks = this.stocks-stocks;
		
		//���ǹ��� Ȱ���ؼ� ���� �������� ���ҽ�ų ������ ���� 0���� Ŭ ���, ������ ���ҽ�Ŵ
		//�׷��� ���� ���, ��� �������� ����ϰ� �ش� �۾��� �������� ����.
		if(newStocks>=0) {
			this.stocks=newStocks;
			return newStocks;
		}
		System.out.println("��� �����մϴ�.");
		return 0;
	}
	
}
