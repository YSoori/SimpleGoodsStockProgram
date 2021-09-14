package C0914_IO_PJT;

import java.util.*;
import java.io.*;

public class GoodsTest {
	
	//ȭ���� ������ �޼ҵ�
	static void line() {
		System.out.println("\n=========================================\n");
	}
	
	//�ش� ��� �̹� �����ϴ��� Ȯ���ϴ� �޼ҵ�
	static GoodsInfo searchGoods(GoodsInfo[] g, String code) {
		for(int i=0;i<cnt;i++) {
			if(g[i].code.equals(code)) {
				return g[i];
			}
		}
		return null;
	}
	
	//��� ���� ������ Ȯ���ϴ� �޼ҵ�
	static void showAll(GoodsInfo[] g) {
		if(cnt==0) {
			System.out.println("��ϵ� ��� �����ϴ�.");
		}else {
			for(int i=0;i<cnt;i++) {
				System.out.println(g[i].code +" : "+g[i].stocks);
			}
		}
	}
	
	//������ ����� �ε��� ��ȣ�� ã�� �޼ҵ�
	static int findDel(GoodsInfo[] g, String code) {
		for(int i=0;i<cnt;i++) {
			if(g[i].code.equals(code)) {
				return i;
			}
		}
		return -1;
	}
	
	//��� �����ϴ� �޼ҵ�
	static void delGoods(GoodsInfo[] g, int delCnt) {
		for(int i=delCnt;i<cnt;i++) {
			g[i]=g[i+1];
		}
		cnt--;
	}
	
	//��� �迭�� �ε����� ��Ÿ���� ��������
	public static int cnt = 0;
	
	// �켱 ��� �� �迭����
	static GoodsInfo[] g = new GoodsInfo[100]; 
	
	static boolean readFile(String path) {
		LineNumberReader reader = null;
		try {
			reader = new LineNumberReader(new FileReader(path));
			while(true) {
				String str = reader.readLine();
				if(str==null) {
					break;
				}
				String[] result = str.split(" ");
				g[cnt++] = new GoodsInfo(result[0], Integer.parseInt(result[1]));
			}
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} catch(IOException ioe){
			System.out.println("������ ���� �� �����ϴ�.");
		}finally {
		
			try {
				reader.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	static boolean writeFile(String path) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path);
			for(int i=0;i<cnt;i++) {
				writer.write(g[i].code +" "+g[i].stocks+"\n");
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("������ ����� �� �����ϴ�.");
		} finally {
			try {
				writer.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String code; // ��ǰ��
		int stocks, select=0, delCnt; //����, ���α׷� ���� ��ư, ������ ����� �ε���
		GoodsInfo g2 = null; //�԰� �� �ߺ��� �ִ��� Ȯ���� ����
		
		System.out.println("��� ���� ���α׷��� �����մϴ�.");
		readFile("C0914_IO_PJT/stocks.txt");
		line();
		
		//�ݺ��� ����Ͽ� �ݺ����� ����� ������ ��� ����.
		while(select!=5) {
			
			//���ۿ� ���� ��ȣ���� ���� ���
			System.out.print("1.�԰� 2.��� 3.���� 4.��Ȳ 5.���� : ");
			
			//������ ��ȣ�� ��ĳ�ʸ� ���� �Է¹޴´�.
			select = sc.nextInt();
			
			line();
			
			//�Է¹��� ��ȣ�� ����ġ���� ����ؼ� �����Ѵ�.
			switch (select) {
			//1�� �Է½� �԰����
			case 1:
				System.out.print("��ǰ�� : "); code = sc.next(); // ��ǰ���� ���� �Է¹���.
				System.out.print("���� : "); stocks = sc.nextInt(); // �ش� ��ǰ�� ������ �Է¹���.
				g2 = searchGoods(g, code); //�ش� ��ǰ�� �����ϴ��� Ȯ���ϴ� �޼ҵ带 Ȱ���ؼ� �̹� ����� �Ǿ��ִ� ��ǰ���� ���� ����� ��ǰ���� Ȯ��.
				//�̹� ��ϵ� ��ǰ�� ���ٸ� ��� �迭�� ��ǰ������ ����ϰ�, ���������� �ε����� 1�� ������Ŵ.
				//�̹� ��ϵ� ��ǰ�� �����ϸ� �ش� ��ǰ�� ������ �Է¹��� ������ŭ ������Ų��.
				if(g2==null) {
					g[cnt++] = new GoodsInfo(code, stocks);
					
				}else {
					g2.store(stocks);
					
				}
				writeFile("C0914_IO_PJT/stocks.txt");
				
				line();
				break;
				
			//2�� �Է½� ��� ����	
			case 2:
				
				//�������� cnt�� 0�̸� ��ϵ� ��� �ϳ��� �����Ƿ� ��ϵ� ��ǰ�� ������ ���, 0�� �ƴϸ� ��ϵ� ��� �����Ƿ� ����� ��ǰ���� �Է¹���.
				if(cnt==0) {
					System.out.println("��ϵ� ����� �����ϴ�.");
				}else {
					
					//����� ��ǰ���� �Է¹޾��� �� �ش� ��ǰ���� ���迭�� �����ϴ��� Ȯ�� �� ������ �ش� ��ǰ�� ������ ���
					System.out.print("��ǰ�� : "); code = sc.next();
					g2 = searchGoods(g, code);
					
					if(g2 == null) {
						System.out.println("�ش��ϴ� ��� �����ϴ�.");
					}else {
						System.out.print("���� : "); stocks = sc.nextInt();
						g2.out(stocks);
					}
					//�ش��ϴ� ��� �����ϸ� g2.out(stocks)�޼ҵ带 ����. 
				}
				writeFile("C0914_IO_PJT/stocks.txt");
				line();
				break;
				
			//3�� �Է½� ���� ����	
			case 3:
				
				//2���� ���� �������� cnt�� 0�̸� ��ϵ� ��� �ϳ��� �����Ƿ� ��ϵ� ��ǰ�� ������ ���, 0�� �ƴϸ� ��ϵ� ��� �����Ƿ� ����� ��ǰ���� �Է¹���.
				if(cnt==0) {
					System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
				}else {
					
					//����� ��ǰ���� �Է¹޾��� �� �ش� ��ǰ���� ���迭�� �����ϴ��� Ȯ�� �� ������ �ش� ��ǰ�� ������ ���
					System.out.print("��ǰ�� : "); code = sc.next();
					delCnt = findDel(g, code);
					if(delCnt==-1) {
						System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
					}else {
						delGoods(g, delCnt);
					}
					//�ش��ϴ� ��� �����ϸ� delGoods(g, delCnt)�޼ҵ带 ����. 
				}
				writeFile("C0914_IO_PJT/stocks.txt");
				line();
				break;
				
			//4�� �Է½� ��ϵ� ����� �̸��� ������ ��� ������.	
			case 4:
				System.out.println("��� ��Ȳ�� ����մϴ�.");
				
				showAll(g);
				line();
				break;
				
			//5�� �Է½� ���α׷��� �����ϰ� �ݺ����� ��������.	
			case 5:
				System.out.println("���α׷��� �����մϴ�.");
				break;

			default:
				System.out.println("�ùٸ� ��ȣ�� �Է����ּ���.");
				line();
				break;
			}
			
		}
		
	}
}
