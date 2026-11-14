package ma.enset.ebankaiagent.rest;

import ma.enset.ebankaiagent.agents.EbankAiagent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;


@RestController
public class EbankAgentRestController {
    private EbankAiagent ebankAiagent;
    public EbankAgentRestController(EbankAiagent eBankAgent) {
        this.ebankAiagent = eBankAgent;
    }
    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String query) {
        return ebankAiagent.chat(query);
    }
}
