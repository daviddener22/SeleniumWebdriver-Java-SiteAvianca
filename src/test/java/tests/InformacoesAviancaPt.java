package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class InformacoesAviancaPt {

    private WebDriver navegador;

    @Before
    public void setUP(){
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\daviddener_silva\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();//Abrir o navegador maximizado
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//Aguardar 5 seg. para aplicação carregar os elementos para interagirmos com eles

        //Navegando para a página principal da Avianca para selecionar o idioma;
        navegador.get("https://www.avianca.com.br/");
    }

    @Test
    public void testVerificarIdiomaPt() {

        //Seleciona o idioma no RadioButton
        navegador.findElement(By.xpath("//label[@for='pt_BR']")).click();

        //Clica no botão Enviar para abrir a página com o idioma selecionado
        navegador.findElement(By.xpath("//*[text()='Enviar']")).click();

        //Identifica o texto no menu superior no idioma selecionado
        WebElement TextoMenuSuperior = navegador.findElement(By.linkText("Sua Viagem"));

        //Verifica se o linktext "Sua Viagem" está disponível, caso ainda não esteja, aguarda 10 seg. até ficar disponível para o click
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.elementToBeClickable(TextoMenuSuperior));

        //Atribuindo o valor de TextoMenuSuperior para textoMenu para ser validado
        String textoMenu = TextoMenuSuperior.getText();

        //Valida se o texto do textlink do menu superior é "Sua Viagem"
        assertEquals("Sua Viagem", textoMenu);
    }

    @After
    public void tearDown(){
        //Fechar o navegador
        navegador.quit();
    }
}
