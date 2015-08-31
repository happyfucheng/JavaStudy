package study.java.jsrs.jsr303;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *  需要用到的包：
    hibernate-validator-4.2.0.Final.jar
    validation-api-1.0.0.GA.jar
    slf4j-api-1.6.1.jar
 * @author hadoop2
 *
 */
public class TestEntry {
	public static void main(String[] args) {
		Order order = new Order();
		order.setAddress(null);
		order.setCreateDate(null);
		order.setCustomer("fuc");
		order.setEmail("fc@asiainfo.com");
		order.setOrderId("12345");
		order.setStatus("createdde");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Order>> violations = validator.validate(order);
		if (violations.size() == 0) {
			System.out.println("验证成功，没有错误！");
		} else {
			StringBuffer buf = new StringBuffer();
			Locale locale = new Locale("en", "US"); 
			//Locale localse = new Locale("zh","CN");
			ResourceBundle bundle = ResourceBundle.getBundle("myres",locale);
			
			for (ConstraintViolation<Order> violation : violations) {
				String info = bundle.getString(violation.getMessage());
				//String info = violation.getPropertyPath().toString();
				buf.append(info + "\r\n");
			}
			System.out.println(buf.toString());

		}
	}
}
