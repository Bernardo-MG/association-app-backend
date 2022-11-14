
package com.bernardomg.security.token.persistence.provider;

import java.util.Calendar;

import org.springframework.security.core.token.TokenService;

import com.bernardomg.security.token.persistence.model.PersistentToken;
import com.bernardomg.security.token.persistence.repository.TokenRepository;
import com.bernardomg.security.token.provider.TokenProvider;

import lombok.NonNull;

public final class PersistentTokenProvider implements TokenProvider {

    private final TokenRepository tokenRepository;

    private final TokenService    tokenService;

    public PersistentTokenProvider(@NonNull final TokenRepository tRepository, @NonNull final TokenService tService) {
        super();

        tokenRepository = tRepository;
        tokenService = tService;
    }

    @Override
    public final String generateToken(final String subject) {
        final PersistentToken token;
        final Calendar        expiration;
        final String          uniqueID;

        expiration = Calendar.getInstance();
        expiration.add(Calendar.DATE, 1);

        uniqueID = tokenService.allocateToken(subject)
            .getKey();

        token = new PersistentToken();
        token.setToken(uniqueID);
        token.setExpired(false);
        token.setExpirationDate(expiration);

        tokenRepository.save(token);

        return uniqueID;
    }

}
