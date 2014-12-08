package ass3;

import java.net.URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.justinsb.etcd.EtcdClient;
import com.justinsb.etcd.EtcdResult;

/**
 * @author User Controller class
 */
@RestController
public class UserController extends WebMvcConfigurerAdapter {

	EtcdClient client = new EtcdClient(URI.create("http://54.67.78.211:4001/"));

	@RequestMapping(value = "/api/v1/counter", method = RequestMethod.GET)
	public EtcdResult setAndGet() throws Exception {

		EtcdResult result;

		String key = "/009997416/counter";
		result = this.client.get(key);
		System.out.println("Before : "+result);
		String str = "";
		try {
			str = Integer.toString(Integer.parseInt(result.node.value) + 1);
		} catch (Exception exp) {
			str = "1";
		} finally {
				EtcdResult result1 = this.client.set(key, str);
				result = this.client.get(key);
				System.out.println("After : "+result);
		}
		return result;
	}
}
