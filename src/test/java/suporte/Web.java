package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {

    private static WebDriver navegador;

    public static WebDriver createChrome() {

        // Variavel com o caminho do driver dentro do projeto.
        String urlDriverChrome = System.getProperty("user.dir") + "\\Driver\\chromedriver.exe";

        // Abre o navegador.
        System.setProperty("webdriver.chrome.driver", urlDriverChrome);

        navegador = new ChromeDriver();

        // Espera implicita
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximiza o navegador
        navegador.manage().window().maximize();

        // Acessa o site a ser testado
        navegador.get("https:www.estrategiaconcursos.com.br");

        return navegador;
    }
}
