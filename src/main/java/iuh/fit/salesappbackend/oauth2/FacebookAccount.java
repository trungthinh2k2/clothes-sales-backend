package iuh.fit.salesappbackend.oauth2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class FacebookAccount extends SocialAccount{
    public FacebookAccount(String accountId, String email, String name) {
        super(accountId, email, name);
    }
}
