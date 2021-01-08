package io.syndesis.extension.convert.body;

import io.syndesis.extension.api.Step;
import io.syndesis.extension.api.annotations.Action;
import org.apache.camel.CamelContext;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

//@Action(id = "ConvertBody", name = "Convert Body", description = "Convert Body to a plain String", tags = {"body", "extension"})
public class ConvertBodyAction implements Step {
    private static Logger logger = LoggerFactory.getLogger(ConvertBodyAction.class);

    @Override
    public Optional<ProcessorDefinition<?>> configure(CamelContext context, ProcessorDefinition<?> route, Map<String, Object> parameters) {
        ObjectHelper.notNull(route, "route");

//        ProcessorDefinition<?> processor = route.process(exchange -> {
//            String body = exchange.getIn().getBody(String.class);
//            route.setBody(() -> "Additional text" + body);
//        });
//        return Optional.of(processor);

        route.setBody(exchange -> {
            logger.info("Setting body inside lambda");
            return "Additional text " + exchange.getIn().getBody(String.class);
        });

        route.convertBodyTo(String.class);

//        route.setBody(() -> "Addition text");
        System.out.println("Keys in parameter map :" + parameters.keySet());
        logger.info("Keys in parameter map :" + parameters.keySet());
        System.out.println("Parameter map: " + parameters);
        logger.info("Parameter map: " + parameters);
        return Optional.empty();
    }
}
