package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    private ServiceLayer serviceLayer;
    private InvoiceRepository invoiceRepository;
    private TaxRepository taxRepository;
    private FeeRepository feeRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TShirtRepository tShirtRepository;


    @BeforeEach
    private void setUp(){

        serviceLayer = new ServiceLayer(invoiceRepository, taxRepository, feeRepository, consoleRepository, gameRepository, tShirtRepository);
    }

    private void setUpGameRepo(){
        gameRepository = mock(GameRepository.class);

        Game game = new Game();
        game.setGameId(1);
        game.setTitle("The Legend of Adventure");
        game.setEsrbRating("E10+");
        game.setDescription("Embark on an epic journey to save the mystical land from darkness.");
        game.setPrice(49.99);
        game.setStudio("QuestWorks Studios");
        game.setQuantity(100);

        List<Game> games = new ArrayList<>();
        games.add(game);

        doReturn(game).when(gameRepository).save(game);
        doReturn(Optional.of(game)).when(gameRepository).findById(1);
        doReturn(games).when(gameRepository).findAll();
    }

    public void setUpConsoleRepo(){
        consoleRepository = mock(ConsoleRepository.class);
        Console console = new Console();
        console.setConsoleId(1);
        console.setModel("PlayStation 5");
        console.setManufacturer("Sony");
        console.setMemoryAmount("825GB SSD");
        console.setProcessor("Custom AMD Zen 2");
        console.setPrice(499.99);
        console.setQuantity(100);


        List<Console> consoles = new ArrayList<>();
        consoles.add(console);

        doReturn(console).when(consoleRepository).save(console);
        doReturn(Optional.of(console)).when(gameRepository).findById(1);
        doReturn(consoles).when(consoleRepository).findAll();
    }
    public void setUpTShirtRepo(){
        tShirtRepository = mock(TShirtRepository.class);
        TShirt tShirt = new TShirt();
        tShirt.setTshirtId(1);
        tShirt.setSize("M");
        tShirt.setColor("Red");
        tShirt.setDescription("TShirt description");
        tShirt.setPrice(19.99);
        tShirt.setQuantity(20);


        List<TShirt> tShirts = new ArrayList<>();
        tShirts.add(tShirt);

        doReturn(tShirt).when(tShirtRepository).save(tShirt);
        doReturn(Optional.of(tShirts)).when(tShirtRepository).findById(1);
        doReturn(tShirts).when(tShirtRepository).findAll();
    }

    public void setUpFee(){
        feeRepository = mock(FeeRepository.class);

         Fee fee = new Fee();
         fee.setProductType("Console");
         fee.setFee(14.99);
         Fee fee1 = new Fee();
         fee1.setFee(1.98);
         fee1.setProductType("T-Shirt");
         Fee fee2 = new Fee();
         fee2.setProductType("Game");
         fee2.setFee(1.49);
         List<Fee> fees = new ArrayList<>();
         fees.add(fee);
         fees.add(fee1);
         fees.add(fee2);

        doReturn(fee).when(feeRepository).save(fee);
        doReturn(fee1).when(feeRepository).save(fee1);
        doReturn(fee2).when(feeRepository).save(fee2);
        doReturn(Optional.of(fee)).when(feeRepository).findByProductType("Console");
        doReturn(Optional.of(fee1)).when(feeRepository).findByProductType("T-Shirt");
        doReturn(Optional.of(fee2)).when(feeRepository).findByProductType("Game");
        doReturn(fees).when(feeRepository).findAll();
    }

    public void setUpTax(){
        taxRepository = mock(TaxRepository.class);
        Tax tax = new Tax();
        tax.setState("CA");
        tax.setRate(0.06);
        Tax tax1 = new Tax();
        tax1.setState("FL");
        tax1.setRate(0.06);
        Tax tax2 = new Tax();
        tax.setState("GA");
        tax.setRate(0.07);

        List<Tax> taxes = new ArrayList<>();
        taxes.add(tax);
        taxes.add(tax1);
        taxes.add(tax2);

        doReturn(tax).when(taxRepository).save(tax);
        doReturn(tax1).when(taxRepository).save(tax1);
        doReturn(tax2).when(taxRepository).save(tax2);
        doReturn(Optional.of(tax)).when(taxRepository).findByState("CA");
        doReturn(Optional.of(tax1)).when(taxRepository).findByState("FL");
        doReturn(Optional.of(tax2)).when(taxRepository).findByState("GA");
        doReturn(taxes).when(taxRepository).findAll();
    }


    @Test
    public void shouldAddNewInvoice(){

    }


    @Test
    public void shouldThrowInsufficientStockException(){

    }

    @Test
    public void shouldThrowUnknownStateCodeException(){

    }

    @Test
    public void shouldThrowInvalidQuantityException(){

    }

    @Test
    public void shouldThrowIfAttemptToUpdateNullObject(){

    }


    @Test
    public void shouldThrowIfAttemptToUpdateNotMatchingIDs(){

    }
}
