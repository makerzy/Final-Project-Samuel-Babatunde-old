package com.company.gamestore.controller;


import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
@AutoConfigureMockMvc(addFilters = false)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private InvoiceRepository invoiceRepository;

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
        invoice = serviceLayer.saveInvoice(model);

    }

    @Test
    public void shouldCreateNewInvoice() throws Exception {
        when(serviceLayer.saveInvoice(any(InvoiceViewModel.class))).thenReturn(invoice);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/invoices")
                                .content(mapper.writeValueAsString(model))
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Customer name"))
                .andExpect(jsonPath("$.street").value("1111 Customer street"))
                .andExpect(jsonPath("$.city").value("Redwood"))
                .andExpect(jsonPath("$.state").value("California"))
                .andExpect(jsonPath("$.zipcode").value("94065"))
                .andExpect(jsonPath("$.item_type").value("Console"))
                .andExpect(jsonPath("$.item_id").value(1))
                .andExpect(jsonPath("$.quantity").value(1));
    }

    @Test
    public void shouldGetAllInvoices() throws Exception {

    }

    @Test
    public void shouldGetInvoiceById() throws Exception {

    }

    @Test
    public void shouldGetInvoiceByCustomerName() throws Exception {

    }


    @Test
    public void shouldUpdateInvoice() throws Exception {

    }

    @Test
    public void shouldDeleteInvoice() throws Exception {

    }


}
