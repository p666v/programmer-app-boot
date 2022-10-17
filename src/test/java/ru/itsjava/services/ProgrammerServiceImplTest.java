package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Класс ProgrammerService")
public class ProgrammerServiceImplTest {
    @Autowired
    private ProgrammerService programmerService;

    @DisplayName(" корректный метод Привет-программист")
    @Test
    public void shouldHaveCorrectSayHiToNewProgrammer() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        programmerService.hiToNewProgrammer();

        assertEquals("Enter your name: \r\n" +
                "Hello vitalii\r\n" +
                "Your computer: Notebook{Asus 115AF 2018}\r\n", out.toString());


    }

    @Configuration
    static class MyConfiguration {
        @Bean
        public ProgrammerService programmerService(NotebookService notebookService, IOService ioService) {
            return new ProgrammerServiceImpl(notebookService, ioService);
        }

        @Bean
        public IOService ioService() {
            IOServiceImpl mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn("vitalii");
            return mockIOService;
        }

        @Bean
        public NotebookService notebookService() {
            NotebookService mockNotebookService = Mockito.mock(NotebookServiceImpl.class);
            when(mockNotebookService.getNotebook()).thenReturn(new Notebook("Asus", "115AF", 2018));
            return mockNotebookService;
        }


    }
}
