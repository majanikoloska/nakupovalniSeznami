package api.v1.viri;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        title = "REST Service",
        version = "v1.0.0"),
        servers = @Server(url = "http://localhost:8080/v1"))

@ApplicationPath("v1")
public class NakupovalniSeznamApp extends Application {
}
