package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/busca_e_visualizacao.feature",
        glue = "steps",
        tags = "",
        plugin = "pretty"
)
public class Busca_e_Visualizacao_Runner {

}
