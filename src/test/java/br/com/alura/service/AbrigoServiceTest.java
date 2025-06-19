package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbrigoServiceTest {

    private ClientHttpConfiguration mockClientHttpConfiguration = mock(ClientHttpConfiguration.class);
    private HttpResponse<String> mockHttpResponse = mock(HttpResponse.class);

    private AbrigoService abrigoService = new AbrigoService(mockClientHttpConfiguration);
    private Abrigo abrigo = new Abrigo("Teste", "61981880392", "abrigo_alura@gmail.com");

    @Test
    @DisplayName("Deve verificar quando há abrigo(s)")
    public void listarAbrigosCadastradosCenario1() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectesIdENome = "0 - Teste";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        when(mockHttpResponse.body()).thenReturn("[{" + abrigo.toString() + "}]");
        when(mockClientHttpConfiguration.dispararRequisicaoGet(anyString())).thenReturn(mockHttpResponse);

        abrigoService.listarAbrigosCadastrados();

        String[] lines = byteArrayOutputStream.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[1];

        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectesIdENome, actualIdENome);
    }

    @Test
    @DisplayName("Deve verificar quando não há abrigo(s)")
    public  void listarAbrigosCadastradosCenario2() throws IOException, InterruptedException {
        String expected = "Não há abrigos cadastrados.";

        when(mockHttpResponse.body()).thenReturn("[]");
        when(mockClientHttpConfiguration.dispararRequisicaoGet(anyString())).thenReturn(mockHttpResponse);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        abrigoService.listarAbrigosCadastrados();

        String[] lines = byteArrayOutputStream.toString().split(System.lineSeparator());
        String actual = lines[0];

        Assertions.assertEquals(expected, actual);
    }
}
