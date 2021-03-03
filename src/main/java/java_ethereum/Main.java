package java_ethereum;


public class Main {
	public static void main(String[] args) {
		ContractController controller = ContractController.getInstance();
		try {
			controller.store(123);
			System.out.println(controller.retrieve());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
