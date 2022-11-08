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

package com.bernardomg.association.config;

import java.nio.charset.Charset;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.bernardomg.security.data.persistence.repository.PrivilegeRepository;
import com.bernardomg.security.data.persistence.repository.UserRepository;
import com.bernardomg.security.jwt.entrypoint.ErrorResponseAuthenticationEntryPoint;
import com.bernardomg.security.jwt.property.JwtProperties;
import com.bernardomg.security.jwt.token.JwtTokenProvider;
import com.bernardomg.security.jwt.token.JwtTokenValidator;
import com.bernardomg.security.login.service.LoginService;
import com.bernardomg.security.login.service.TokenLoginService;
import com.bernardomg.security.springframework.userdetails.PersistentUserDetailsService;
import com.bernardomg.security.token.TokenProvider;

import io.jsonwebtoken.security.Keys;

/**
 * Security configuration.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    public SecurityConfig() {
        super();
    }

    @Bean("authenticationEntryPoint")
    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new ErrorResponseAuthenticationEntryPoint();
    }

    @Bean("jwtSecretKey")
    public SecretKey getJwtSecretKey(final JwtProperties properties) {
        return Keys.hmacShaKeyFor(properties.getSecret()
            .getBytes(Charset.forName("UTF-8")));
    }

    @Bean("loginService")
    public LoginService getLoginService(final UserDetailsService userDetailsService,
            final PasswordEncoder passwordEncoder, final TokenProvider tokenProv) {
        return new TokenLoginService(userDetailsService, passwordEncoder, tokenProv);
    }

    @Bean("passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        // TODO: Shouldn't be using a seed?
        return new BCryptPasswordEncoder();
    }

    @Bean("tokenProcessor")
    public JwtTokenValidator getTokenProcessor(final SecretKey key) {
        return new JwtTokenValidator(key);
    }

    @Bean("tokenProvider")
    public TokenProvider getTokenProvider(final SecretKey key, final JwtProperties properties) {
        return new JwtTokenProvider(key, properties.getValidity());
    }

    @Bean("userDetailsService")
    public UserDetailsService getUserDetailsService(final UserRepository userRepository,
            final PrivilegeRepository privilegeRepository) {
        return new PersistentUserDetailsService(userRepository, privilegeRepository);
    }

}
