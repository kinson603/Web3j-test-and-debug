package java_ethereum;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;


public class Web3Provider {

	private Web3j web3j = null;
	private static Web3Provider provider = null;
	private Credentials credentials = null;
	
	private Web3Provider() {
		build();
	}
	
	public static Web3Provider getInstance() {
		if(provider == null){
			provider = new Web3Provider();
		}
		return provider;
	}
	
	private void build() {
		try {
			if(this.web3j == null)
				this.web3j = Web3j.build(new HttpService("http://localhost:8545"));
			
			System.out.println("Connected to Ethereum client version: " + this.web3j.web3ClientVersion().send().getWeb3ClientVersion());
			if(this.credentials == null) {
				//this.credentials = WalletUtils.loadCredentials("password", "keystorePath");
				//this.credentials = Credentials.create("privateKey");
				System.out.println("Credentials is loaded.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Web3j has initialized.");
	}
	
	public Web3j getWeb3j() {
		return this.web3j;
	}
	
	public Credentials getCredentials() {
		return this.credentials;
	}
	
	public List<String> getAccounts() throws IOException{
		EthAccounts ethAccounts = this.web3j.ethAccounts().send();
		return ethAccounts.getAccounts();
	}

}
