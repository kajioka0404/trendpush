package kajikaji.trendpush;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsoupProcessor implements Processor {
	private static final Logger logger = LoggerFactory.getLogger(MySpringBootApplication.class);

    @Override
    public void process(Exchange exchange) throws Exception {

    	Document document = Jsoup.connect(exchange.getIn().getHeader("Siteurl").toString()).get();
//    	logger.info(document.toString());
    	Element element = document.getElementById("contentsBody");
//        logger.info(element.text());
        String str = element.text().toString();
        int start_c = str.indexOf("更新 1 ") + 3;
        int end_c = str.indexOf("人気ツイート");
        String hotwords = str.substring(start_c,end_c);
        logger.info(hotwords);

        // 検索対象文字列
        String key = ".*" + exchange.getIn().getHeader("Keyword") + ".*";

        // 文字列判定
        if(hotwords.matches(key)) {
        	exchange.getIn().setHeader("OutputFlg", "true");
        	exchange.getIn().setBody(hotwords);
        }

    }
}