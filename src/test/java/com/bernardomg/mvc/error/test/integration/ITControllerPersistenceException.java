/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2022 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.mvc.error.test.integration;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bernardomg.association.test.config.annotation.MvcIntegrationTest;
import com.bernardomg.mvc.error.test.util.controller.PersistenceExceptionTestController;

@MvcIntegrationTest
@DisplayName("Controller error handling - persistence exceptions")
public final class ITControllerPersistenceException {

    @Autowired
    private MockMvc mockMvc;

    public ITControllerPersistenceException() {
        super();
    }

    @Test
    @DisplayName("Returns the response structure for a data integrity exception")
    public final void testErrorHandling_DataIntegrity_Response() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getDataIntegrityExceptionRequest());

        // The operation was rejected
        result.andExpect(MockMvcResultMatchers.status()
            .isInternalServerError());

        // The response contains the expected attributes
        result.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.equalTo("Invalid query")));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.equalTo("Invalid query")));

        // The response contains no content field
        result.andExpect(MockMvcResultMatchers.jsonPath("$.content")
            .doesNotExist());

        // The response contains no errors attribute
        result.andExpect(MockMvcResultMatchers.jsonPath("$.errors")
            .doesNotExist());
    }

    @Test
    @DisplayName("Returns the response structure for a JDBC grammar exception")
    public final void testErrorHandling_JdbcGrammar_Response() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getJdbcGrammarExceptionRequest());

        // The operation was rejected
        result.andExpect(MockMvcResultMatchers.status()
            .isInternalServerError());

        // The response contains the expected attributes
        result.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.equalTo("Invalid query")));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.equalTo("Invalid query")));

        // The response contains no content field
        result.andExpect(MockMvcResultMatchers.jsonPath("$.content")
            .doesNotExist());

        // The response contains no errors attribute
        result.andExpect(MockMvcResultMatchers.jsonPath("$.errors")
            .doesNotExist());
    }

    @Test
    @DisplayName("Returns the response structure for a property reference exception")
    public final void testErrorHandling_PropertyReference_Response() throws Exception {
        final ResultActions result;

        result = mockMvc.perform(getPropertyReferenceExceptionRequest());

        // The operation was rejected
        result.andExpect(MockMvcResultMatchers.status()
            .isInternalServerError());

        // The response contains the expected attributes
        result.andExpect(MockMvcResultMatchers.jsonPath("$.errors", Matchers.hasSize(1)));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", Matchers.equalTo("Invalid query")));

        // The response contains no content field
        result.andExpect(MockMvcResultMatchers.jsonPath("$.content")
            .doesNotExist());

        // The response contains no field error attribute
        result.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].field")
            .doesNotExist());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].value")
            .doesNotExist());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].object")
            .doesNotExist());
    }

    private final RequestBuilder getDataIntegrityExceptionRequest() {
        return MockMvcRequestBuilders.get(PersistenceExceptionTestController.PATH_DATA_INTEGRITY)
            .contentType(MediaType.APPLICATION_JSON);
    }

    private final RequestBuilder getJdbcGrammarExceptionRequest() {
        return MockMvcRequestBuilders.get(PersistenceExceptionTestController.PATH_JDBC_GRAMMAR)
            .contentType(MediaType.APPLICATION_JSON);
    }

    private final RequestBuilder getPropertyReferenceExceptionRequest() {
        return MockMvcRequestBuilders.get(PersistenceExceptionTestController.PATH_PROPERTY_REFERENCE)
            .contentType(MediaType.APPLICATION_JSON);
    }

}
