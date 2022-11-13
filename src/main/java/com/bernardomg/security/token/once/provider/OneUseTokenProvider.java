
package com.bernardomg.security.token.once.provider;

import java.util.Calendar;
import java.util.UUID;

import com.bernardomg.security.token.once.persistence.model.PersistentToken;
import com.bernardomg.security.token.once.persistence.repository.TokenRepository;
import com.bernardomg.security.token.provider.TokenProvider;

import lombok.NonNull;

public final class OneUseTokenProvider implements TokenProvider {

    private final TokenRepository tokenRepository;

    public OneUseTokenProvider(@NonNull final TokenRepository tRepository) {
        super();

        tokenRepository = tRepository;
    }

    @Override
    public final String generateToken(final String subject) {
        final PersistentToken token;
        final Calendar        expiration;
        final String          uniqueID;

        expiration = Calendar.getInstance();
        expiration.add(Calendar.DATE, 1);

        uniqueID = UUID.randomUUID()
            .toString();

        token = new PersistentToken();
        token.setToken(uniqueID);
        token.setExpired(false);
        token.setExpirationDate(expiration);

        tokenRepository.save(token);

        return uniqueID;
    }

}
