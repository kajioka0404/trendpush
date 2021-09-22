package kajikaji.trendpush;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.support.DefaultExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@ImportResource("classpath:beans.xml")
@Configuration
@PropertySource(value = "classpath:/app.properties", encoding = "UTF-8")
public class MySpringBootApplication {

	@Autowired
	private CamelContext camelContext;

	@RequestMapping("/camelhttp")
	public String camelhello() {
		// camelで流通するデータの作成
		Exchange exchange = new DefaultExchange(camelContext);

		// routeにデータを送信
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.send("seda://CamelTrendSearch", exchange);

		// camelのrouteが例外で終了したかどうかの確認
		if (exchange.getException() != null) {
			return "NG";
		}

		return exchange.getIn().getBody(String.class);
	}

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}
