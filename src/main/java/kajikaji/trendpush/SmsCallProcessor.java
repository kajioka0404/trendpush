package kajikaji.trendpush;
// Install the Java helper library from twilio.com/docs/java/install

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SmsCallProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Twilio.init(System.getenv("Twilio_SID"), System.getenv("Twilio_auth"));
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(System.getenv("Twilio_toNumber")),
                new com.twilio.type.PhoneNumber(System.getenv("Twilio_fromNumber")),
                exchange.getIn().getBody().toString())
            .create();

    }
}