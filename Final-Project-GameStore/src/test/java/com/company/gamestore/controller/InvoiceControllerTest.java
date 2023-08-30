package com.company.gamestore.controller;


import com.company.gamestore.model.Console;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.*;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
@AutoConfigureMockMvc(addFilters = false)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private TaxRepository taxRepository;

    @MockBean
    private FeeRepository feeRepository;

    @MockBean
    private GameRepository gameRepository;
    @MockBean
    private TShirtRepository tShirtRepository;

    @MockBean
    private ConsoleRepository consoleRepository;

    @MockBean
    private ServiceLayer serviceLayer;

    private InvoiceViewModel model;

    private Invoice invoice;




    @BeforeEach
    public void setUp() {


        model = new InvoiceViewModel();
        model.setInvoiceId(1);
        model.setName("Customer name");
        model.setStreet("1111 Customer street");
        model.setCity("Redwood");
        model.setState("California");
        model.setZipcode("94065");
        model.setItemType("Console");
        model.setItemId(1);
        model.setQuantity(1);

        serviceLayer = new ServiceLayer(invoiceRepository, taxRepository, feeRepository, consoleRepository, gameRepository, tShirtRepository);
        invoice = serviceLayer.saveInvoice(model);

    }

    @Test
    public void shouldCreateNewInvoice() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/invoices")
                                .content(mapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isCreated())
                .andReturn();
//                .andExpect(jsonPath("$.name").value("Customer name"))
//                .andExpect(jsonPath("$.street").value("1111 Customer street"))
//                .andExpect(jsonPath("$.city").value("Redwood"))
//                .andExpect(jsonPath("$.state").value("California"))
//                .andExpect(jsonPath("$.zipcode").value("94065"))
//                .andExpect(jsonPath("$.item_type").value("Console"))
//                .andExpect(jsonPath("$.item_id").value(1))
//                .andExpect(jsonPath("$.quantity").value(1));
    }

    @Test
    public void shouldGetAllInvoices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Test
    public void shouldGetInvoiceById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/invoices/{id}", invoice.getInvoiceId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldGetInvoiceByCustomerName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/invoices/customers/{name}", invoice.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void shouldUpdateInvoice() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/invoices/{id}", invoice.getInvoiceId())
                        .content(mapper.writeValueAsString(invoice))
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteInvoice() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/invoices/{id}", invoice.getInvoiceId())

        ).andExpect(status().isNoContent());
    }


}
