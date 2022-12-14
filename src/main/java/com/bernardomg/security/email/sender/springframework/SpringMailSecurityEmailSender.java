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

package com.bernardomg.security.email.sender.springframework;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.bernardomg.security.email.sender.SecurityMessageSender;

import lombok.NonNull;

/**
 * Email sender for security operations which integrates with Spring Mail.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public final class SpringMailSecurityEmailSender implements SecurityMessageSender {

    private final String         fromEmail;

    private final JavaMailSender mailSender;

    private final String         passwordRecoverySubject = "";

    private final String         passwordRecoveryText    = "";

    private final String         signUpSubject           = "";

    private final String         signUpText              = "";

    public SpringMailSecurityEmailSender(@NonNull final String from, @NonNull final JavaMailSender mSender) {
        super();

        fromEmail = from;
        mailSender = mSender;
    }

    @Override
    public final void sendPasswordRecoveryEmail(final String email, final String token) {
        final SimpleMailMessage message;

        // TODO: Use token

        message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject(passwordRecoverySubject);
        message.setText(passwordRecoveryText);
        mailSender.send(message);
    }

    @Override
    public final void sendSignUpEmail(final String username, final String email) {
        final SimpleMailMessage message;

        message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(email);
        message.setSubject(signUpSubject);
        message.setText(signUpText);
        mailSender.send(message);
    }

}
