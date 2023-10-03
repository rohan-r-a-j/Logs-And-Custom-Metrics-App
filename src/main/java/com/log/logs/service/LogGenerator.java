package com.log.logs.service;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LogGenerator{

	static String sessionPrefix = "web-";
	static String emailPrefix = "testEmail";
	static String emailSuffix = "@gmail.com";
	static String strSessionId = " Session Id ";
	static String strMethodName = "Method Name ";
	static String strSuccess = " Status=Success ";
	static String strError = " Status=Error ";
	
	private static final Logger logger = LogManager.getLogger(LogGenerator.class.getName());
	
	public static void main(String[] args) {
		String userAction = null;
		String iterationsStr = null;
		String product = null;
		int iterations = 1;
		String sessionId = null;

		if(args.length > 0) {
			userAction = args[0];
		}
		if(args.length > 1) {
			iterationsStr = args[1];
		}
		if(args.length > 2) {
			product = args[2];
		}

		System.out.println("User Action " + userAction);
		logger.info("User Action " + userAction);
//		logger.
		System.out.println("Iterations " + iterationsStr);
		logger.info("Iterations " + iterationsStr);
		System.out.println("Product " + product);
		logger.info("Product " + product);
		
		if(iterationsStr != null) {
			try{
				iterations = Integer.parseInt(iterationsStr);
			} catch (NumberFormatException nfe) {
				System.out.println("Method Name main() Please enter a numeric value for Iterations "+iterations);
				logger.error("Method Name main() Please enter a numeric value for Iterations "+iterations);
				return;
			}
		}

		if(userAction != null){
		    try{
			if(product == null){
				product = "";
			}
			for(int i=0; i<iterations; i++) {
				sessionId = sessionPrefix + System.currentTimeMillis();
				switch(userAction) {
					case "random":
						fnRandom(sessionId, product);
					break;
					case "randomError":
						fnRandomError(sessionId, product);
					break;
					default:
						fnSelectedAction(userAction, sessionId, product);
						break;
				}
			}
		    } catch (Exception exp) {
			System.out.println("Exception in Method Name main() " + exp);
			logger.error("Exception in Method Name main() " + exp);
		    }
		}
	}

	private static void fnSelectedAction(String userAction, String sessionId, String product){

		switch (userAction){
			case "register":
				fnRegister(sessionId);
			break;
			case "registerError":
				fnRegisterError(sessionId);
			break;
			case "login":
				fnLogin(sessionId);
			break;
			case "loginError":
				fnLoginError(sessionId);
			break;
			case "product":
				fnProduct(sessionId, product);
			break;
			case "productError":
				fnProductError(sessionId, product);
			break;
			case "addToCart":
				fnAddToCart(sessionId, product);
			break;
			case "addToCartError":
				fnAddToCartError(sessionId, product);
			break;
			case "shipping":
				fnShipping(sessionId, product);
			break;
			case "shippingError":
				fnShippingError(sessionId, product);
			break;
			case "payment":
				fnPayment(sessionId, product);
			break;
			case "paymentError":
				fnPaymentError(sessionId, product);
			break;
			case "transaction":
				fnTransaction(sessionId, product);
			break;
			case "transactionError":
				fnTransactionError(sessionId, product);
			break;
		}
	}

	private static String fnGetRandomEmail(int randomNumber) {
		return emailPrefix + randomNumber + emailSuffix;
	}

	private static int fnRandomNumber(int maxRandom){
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextInt(1, maxRandom);
	}

	private static void fnRegister(String sessionId) {
		String email = fnGetRandomEmail(fnRandomNumber(100));
		System.out.println(strMethodName + "fnRegister " + strSessionId + sessionId + strSuccess + " Registering user with email "+email);
		logger.info(strMethodName + "fnRegister " + strSessionId + sessionId + strSuccess + " Registering user with email "+email);
	}

	private static void fnRegisterError(String sessionId) {
		String email = fnGetRandomEmail(fnRandomNumber(100));
		System.out.println(strMethodName + "fnRegister " + strSessionId + sessionId + strError + " Error Registering user with email "+email);
		logger.info(strMethodName + "fnRegister " + strSessionId + sessionId + strError + " Error Registering user with email "+email);
	}

	private static String fnLogin(String sessionId) {
		String email = fnGetRandomEmail(fnRandomNumber(100));
		System.out.println(strMethodName + "fnLogin " + strSessionId + sessionId + strSuccess + " Logging in user with email "+email);
		logger.info(strMethodName + "fnLogin " + strSessionId + sessionId + strSuccess + " Logging in user with email "+email);
		return email;
	}

	private static void fnLoginError(String sessionId) {
		String email = fnGetRandomEmail(fnRandomNumber(100));
		System.out.println(strMethodName + "fnLogin " + strSessionId + sessionId + strError + " Error Logging in user with email "+email);
		logger.error(strMethodName + "fnLogin " + strSessionId + sessionId + strError + " Error Logging in user with email "+email);
	}

	private static void fnProduct(String sessionId, String product) {
		fnLogin(sessionId);
		System.out.println(strMethodName + "fnProduct " + strSessionId + sessionId + strSuccess + " Viewing product "+product);
		logger.info(strMethodName + "fnProduct " + strSessionId + sessionId + strSuccess + " Viewing product "+product);
	}

	private static void fnProductError(String sessionId, String product) {
		fnLogin(sessionId);
		System.out.println(strMethodName + "fnProduct " + strSessionId + sessionId + strError + " Error Viewing product "+product);
		logger.error(strMethodName + "fnProduct " + strSessionId + sessionId + strError + " Error Viewing product "+product);
	}

	private static void fnAddToCart(String sessionId, String product) {
		fnProduct(sessionId, product);
		System.out.println(strMethodName + "fnAddToCart " + strSessionId + sessionId + strSuccess + " Adding product to cart " + product);
		logger.info(strMethodName + "fnAddToCart " + strSessionId + sessionId + strSuccess + " Adding product to cart " + product);
	}

	private static void fnAddToCartError(String sessionId, String product) {
		fnProduct(sessionId, product);
		System.out.println(strMethodName + "fnAddToCart " + strSessionId + sessionId + strError + " Error Adding product to cart " + product);
		logger.error(strMethodName + "fnAddToCart " + strSessionId + sessionId + strError + " Error Adding product to cart " + product);
	}

	private static void fnShipping(String sessionId, String product) {
		fnAddToCart(sessionId, product);
		System.out.println(strMethodName + "fnShipping " + strSessionId + sessionId + strSuccess + " Shipping product " + product);
		logger.info(strMethodName + "fnShipping " + strSessionId + sessionId + strSuccess + " Shipping product " + product);
	}

	private static void fnShippingError(String sessionId, String product) {
		fnAddToCart(sessionId, product);
		System.out.println(strMethodName + "fnShipping " + strSessionId + sessionId + strError + " Error Shipping product " + product);
		logger.error(strMethodName + "fnShipping " + strSessionId + sessionId + strError + " Error Shipping product " + product);
	}


	private static void fnPayment(String sessionId, String product) {
		fnShipping(sessionId, product);
		System.out.println(strMethodName + "fnPayment " + strSessionId + sessionId + strSuccess + " Processing payment for product " + product);
		logger.info(strMethodName + "fnPayment " + strSessionId + sessionId + strSuccess + " Processing payment for product " + product);
	}

	private static void fnPaymentError(String sessionId, String product) {
		fnShipping(sessionId, product);
		System.out.println(strMethodName + "fnPayment " + strSessionId + sessionId + strError + " Error Processing payment for product " + product);
		logger.error(strMethodName + "fnPayment " + strSessionId + sessionId + strError + " Error Processing payment for product " + product);
	}

	private static void fnTransaction(String sessionId, String product) {
		fnPayment(sessionId, product);
		System.out.println(strMethodName + "fnTransaction " + strSessionId + sessionId + strSuccess + " Successfully completing transaction for product " + product);
		logger.info(strMethodName + "fnTransaction " + strSessionId + sessionId + strSuccess + " Successfully completing transaction for product " + product);
	}

	private static void fnTransactionError(String sessionId, String product) {
		fnPayment(sessionId, product);
		System.out.println(strMethodName + "fnTransaction " + strSessionId + sessionId + strError + " Error completing transaction for product " + product);
		logger.error(strMethodName + "fnTransaction " + strSessionId + sessionId + strError + " Error completing transaction for product " + product);
	}


	private static void fnRandom(String sessionId, String product) {
		int random = fnRandomNumber(8);
		System.out.println("Random number generated is " + random);
		logger.info("Random number generated is " + random);
		switch (random) {
			case 1:
				fnRegister(sessionId);
				break;
			case 2:
				fnLogin(sessionId);
				break;
			case 3:
				fnProduct(sessionId, product);
				break;
			case 4:
				fnAddToCart(sessionId, product);
				break;
			case 5:
				fnShipping(sessionId, product);
				break;
			case 6:
				fnPayment(sessionId, product);
				break;
			case 7:
				fnTransaction(sessionId, product);
				break;
		}
		
		System.out.println(strMethodName + "fnRandom " + strSessionId + sessionId + strSuccess + " Successfully Randomized for product " + product);
		logger.info(strMethodName + "fnRandom " + strSessionId + sessionId + strSuccess + " Successfully Randomized for product " + product);
	}

	private static void fnRandomError(String sessionId, String product) {
		int random = fnRandomNumber(8);
		System.out.println("Random error number generated is " + random);
		logger.info("Random error number generated is " + random);
		switch (random) {
			case 1:
				fnRegisterError(sessionId);
				break;
			case 2:
				fnLoginError(sessionId);
				break;
			case 3:
				fnProductError(sessionId, product);
				break;
			case 4:
				fnAddToCartError(sessionId, product);
				break;
			case 5:
				fnShippingError(sessionId, product);
				break;
			case 6:
				fnPaymentError(sessionId, product);
				break;
			case 7:
				fnTransactionError(sessionId, product);
				break;
		}
		
		System.out.println(strMethodName + "fnRandomError " + strSessionId + sessionId + strError + " Successfully Randomized Error for product " + product);
		logger.info(strMethodName + "fnRandomError " + strSessionId + sessionId + strError + " Successfully Randomized Error for product " + product);
	}

}