package br.com.ms_produtos.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"br.com.ms_produtos"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-html-report.html", "json:target/cucumber-reports/cucumber-json-report.json"},
        tags = "@Produtos",
        monochrome = true
)
public class ProdutoRunnerTest {
}
