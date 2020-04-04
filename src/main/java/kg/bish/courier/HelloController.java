package kg.bish.courier;

import kg.bish.courier.models_to_db.AdminEntity;
import kg.bish.courier.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	@Autowired
	MyService myService;
	static{
		try {
			Class.forName ("org.postgresql.Driver");
			//Class.forName ("oracle.jdbc.driver.OracleDriver");
			//Class.forName ("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome() {
		return "hello";
	}


	@RequestMapping(value = "save",method = RequestMethod.GET)
	public String save() {
		AdminEntity adminEntity = new AdminEntity();
		myService.save(adminEntity);
		return "hello";
	}
}