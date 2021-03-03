package java_ethereum;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

public class ContractController {
	
	private Storage contract = null;
	private BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
	private BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
	private Web3Provider web3Provider;
	private static ContractController instance = null;
	
	private ContractController() {
		init();
	}
	
	private void init() {
		try {
			web3Provider = Web3Provider.getInstance();
			Credentials credentials = web3Provider.getCredentials();
			String contractAddress = deployContract(web3Provider.getWeb3j(), credentials);
			contract = loadContract(contractAddress, web3Provider.getWeb3j(), credentials);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ContractController getInstance() {
		if(instance == null) {
			instance = new ContractController();
		}
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	private String deployContract(Web3j web3j, Credentials credentials) throws Exception{
	    //return EthsyShipContract.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT).send().getContractAddress();
		return Storage.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT).send().getContractAddress();
	}
	
	@SuppressWarnings("deprecation")
	private Storage loadContract(String address, Web3j web3j, Credentials credentials){
	    return Storage.load(address, web3j, credentials, GAS_PRICE, GAS_LIMIT);
	}
	
	public void store(int num) throws Exception {
		contract.store(BigInteger.valueOf(num)).send();
	}
	
	public String retrieve() throws Exception {
		String result = contract.retrieve().send().toString();
		System.out.println("retrieve result: "+result);
		return result;
	}
	
	
}
