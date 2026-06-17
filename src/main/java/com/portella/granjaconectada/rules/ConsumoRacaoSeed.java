package com.portella.granjaconectada.rules;

import com.portella.granjaconectada.Model.ConsumoModel;
import com.portella.granjaconectada.Repository.ConsumoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsumoRacaoSeed implements CommandLineRunner {
    private final ConsumoRepository consumoRepository;


    @Override
    public void run(String... args) throws Exception {

        if (consumoRepository.count() > 0){
            return;
        }

        List<ConsumoModel> dados = List.of(


                new ConsumoModel(null, 1, 13.0, 40.0,true, null),
                new ConsumoModel(null, 2, 18.0, 50.0,true, null),
                new ConsumoModel(null, 3, 25.0, 65.0,true, null),
                new ConsumoModel(null, 4, 32.0, 85.0,true, null),
                new ConsumoModel(null, 5, 40.0, 110.0,true, null),
                new ConsumoModel(null, 6, 50.0, 140.0,true, null),
                new ConsumoModel(null, 7, 60.0, 180.0,true, null),

                new ConsumoModel(null, 8, 75.0, 220.0,true, null),
                new ConsumoModel(null, 9, 90.0, 270.0,true, null),
                new ConsumoModel(null, 10, 110.0, 320.0,true, null),
                new ConsumoModel(null, 11, 130.0, 380.0,true, null),
                new ConsumoModel(null, 12, 150.0, 440.0,true, null),
                new ConsumoModel(null, 13, 170.0, 500.0,true, null),

                new ConsumoModel(null, 14, 190.0, 560.0,true, null),
                new ConsumoModel(null, 15, 210.0, 630.0,true, null),
                new ConsumoModel(null, 16, 230.0, 700.0,true, null),
                new ConsumoModel(null, 17, 250.0, 780.0,true, null),
                new ConsumoModel(null, 18, 270.0, 860.0,true, null),
                new ConsumoModel(null, 19, 290.0, 950.0,true, null),
                new ConsumoModel(null, 20, 310.0, 1040.0,true, null),
                new ConsumoModel(null, 21, 330.0, 1130.0,true, null),

                new ConsumoModel(null, 22, 350.0, 1230.0,true, null),
                new ConsumoModel(null, 23, 370.0, 1330.0,true, null),
                new ConsumoModel(null, 24, 390.0, 1440.0,true, null),
                new ConsumoModel(null, 25, 410.0, 1550.0,true, null),
                new ConsumoModel(null, 26, 430.0, 1660.0,true, null),
                new ConsumoModel(null, 27, 450.0, 1780.0,true, null),
                new ConsumoModel(null, 28, 470.0, 1900.0,true, null),

                new ConsumoModel(null, 29, 490.0, 2020.0,true, null),
                new ConsumoModel(null, 30, 510.0, 2150.0,true, null),
                new ConsumoModel(null, 31, 530.0, 2280.0,true, null),
                new ConsumoModel(null, 32, 550.0, 2410.0,true, null),
                new ConsumoModel(null, 33, 570.0, 2550.0,true, null),
                new ConsumoModel(null, 34, 590.0, 2690.0,true, null),
                new ConsumoModel(null, 35, 610.0, 2830.0,true, null),

                new ConsumoModel(null, 36, 630.0, 2970.0,true, null),
                new ConsumoModel(null, 37, 650.0, 3110.0,true, null),
                new ConsumoModel(null, 38, 670.0, 3260.0,true, null),
                new ConsumoModel(null, 39, 690.0, 3410.0,true, null),
                new ConsumoModel(null, 40, 710.0, 3560.0,true, null),
                new ConsumoModel(null, 41, 730.0, 3710.0,true, null),
                new ConsumoModel(null, 42, 750.0, 3860.0,true, null)
        );
        consumoRepository.saveAll(dados);
    }
}
