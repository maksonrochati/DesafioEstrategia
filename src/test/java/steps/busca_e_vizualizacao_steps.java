package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Web;

public class busca_e_vizualizacao_steps {

    private WebDriver navegador;
    public  Double valorParcela;
    public  Double valorDoCurso;

    // Cenario: Buscar curso por professor
    @Dado("que acesso a pagina da estratégia.")
    public void que_acesso_a_pagina_da_estratégia() {
        navegador = Web.createChrome();

        navegador.findElement(By.id("onesignal-slidedown-cancel-button")).click();
    }
    @Quando("utilizar a busca {string}")
    public void utilizar_a_busca(String valor) {
        navegador.findElement(By.linkText(valor)).click();
    }
    @Quando("acessar os cursos da professora {string}")
    public void acessar_os_cursos_da_professora(String professora) {

        JavascriptExecutor jse = (JavascriptExecutor)navegador;
        jse.executeScript("window.scrollBy(0,250)");

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.presenceOfElementLocated(By.linkText(professora)));

        navegador.findElement(By.linkText(professora)).click();
    }
    @Quando("escolha um dos cursos que estarão disponíveis")
    public void escolha_um_dos_cursos_que_estarão_disponíveis() {
      String  valor = navegador.findElement(By.xpath("//*[@id='b_cursos_porconcurso']/div[2]/div/div[2]/div[2]/section[1]/div")).getText();

      String valorParcelCurso = valor.substring(14,16);
      valorParcela = Double.parseDouble(valorParcelCurso);

      String valorCurso = valor.substring(24);
      valorCurso = valorCurso.replace(",", ".");
      valorDoCurso = Double.parseDouble(valorCurso);

        JavascriptExecutor jse = (JavascriptExecutor)navegador;
        jse.executeScript("window.scrollBy(0,250)");

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Assinatura Básica 1 Ano - Cartão até 12 x")));

      navegador.findElement(By.linkText("Assinatura Básica 1 Ano - Cartão até 12 x")).click();
    }
    @Entao("verifico se o valor do curso parcelado na listagem de cursos da professora bate com o valor da página de detalhes do curso.")
    public void verifico_se_o_valor_do_curso_parcelado_na_listagem_de_cursos_da_professora_bate_com_o_valor_da_página_de_detalhes_do_curso() {
        String valorCurso = navegador.findElement(By.xpath("//*[@id='b_curso']/div[1]/section/div[2]/div[1]/div[1]/div")).getText();

        valorCurso = valorCurso.substring(3);
        valorCurso = valorCurso.replace(".","");

        Double valorTotalCurso = valorDoCurso * valorParcela;

        String valorFormatado = String.format("%.2f", valorTotalCurso);
        String valorTotalCursoParcelado = valorFormatado;

        Assert.assertEquals(valorTotalCursoParcelado, valorCurso);

        navegador.close();
    }

    // Cenario: Buscar curso por materia

    @Dado("que acesso a pagina home da estratégia.")
    public void que_acesso_a_pagina_home_da_estratégia() {
        navegador = Web.createChrome();

        navegador.findElement(By.id("onesignal-slidedown-cancel-button")).click();
    }
    @Quando("utilizo a busca {string}")
    public void utilizo_a_busca(String valor) {
        navegador.findElement(By.linkText(valor)).click();
    }
    @Quando("buscar o curso da materia {string}")
    public void buscar_o_curso_da_materia(String materia) {
        JavascriptExecutor jse = (JavascriptExecutor)navegador;
        jse.executeScript("window.scrollBy(0,250)");

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.presenceOfElementLocated(By.linkText(materia)));

        navegador.findElement(By.linkText(materia)).click();
    }
    @Entao("seleciono o curso e verifico o valor do curso na listagem de cursos e igual ao valor da página de detalhes do curso.")
    public void seleciono_o_curso_e_verifico_o_valor_do_curso_na_listagem_de_cursos_e_igual_ao_valor_da_página_de_detalhes_do_curso() {
        String  valor = navegador.findElement(By.xpath("//*[@id='b_cursos_porconcurso']/div[2]/div/div[2]/div[2]/section[2]/div")).getText();
        String valorCursoPesquisa = valor.substring(3);

        JavascriptExecutor jse = (JavascriptExecutor)navegador;
        jse.executeScript("window.scrollBy(0,250)");

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Assinatura Básica 1 Ano - Boleto à vista com 10% de desconto")));

        navegador.findElement(By.linkText("Assinatura Básica 1 Ano - Boleto à vista com 10% de desconto")).click();

        String  valorCursoDet = navegador.findElement(By.xpath("//*[@id='b_curso']/div[1]/section/div[2]/div[1]/div/div")).getText();
        String valorCursoDetalhes = valorCursoDet.substring(3);

        Assert.assertEquals(valorCursoDetalhes, valorCursoPesquisa);

        navegador.close();
    }

    // Cenario: Cenario: Buscar por curso na opção de busca

    @Dado("que acesso o site da estratégia.")
    public void que_acesso_o_site_da_estratégia() {
        navegador = Web.createChrome();

        navegador.findElement(By.id("onesignal-slidedown-cancel-button")).click();
    }
    @Quando("utilizar a opção de busca por {string}")
    public void utilizar_a_opção_de_busca_por(String valor) {
        navegador.findElement(By.xpath("//input[@placeholder='Qual curso você está procurando? Ex.: Polícia Militar']")).sendKeys(valor);
        navegador.findElement(By.xpath("/html/body/header/div/div/form//button/span")).click();
    }
    @Quando("selecionar a opção {string}")
    public void selecionar_a_opção(String val) {
        JavascriptExecutor jse = (JavascriptExecutor)navegador;
        jse.executeScript("window.scrollBy(0,250)");

        navegador.findElement(By.xpath("//button[@data-type-button='"+val+"']")).click();

    }
    @Entao("seleciono um curso e verifico se o titulo do curso e igual ao titulo do curso na página de detalhes")
    public void seleciono_um_curso_e_verifico_se_o_titulo_do_curso_e_igual_ao_titulo_do_curso_na_página_de_detalhes() {

        WebElement curso = navegador.findElement(By.linkText("Português p/ Agência Nacional das Águas (ANA) - Pré-Edital"));
        String textoDoCurso = curso.getText();
        curso.click();

        String tituloDoCurso = navegador.findElement(By.className("cur-details-info-title")).getText();

        Assert.assertEquals(tituloDoCurso, textoDoCurso);

        navegador.close();
    }
}
