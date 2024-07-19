package iuh.fit.salesappbackend.oauth2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class SocialAccount {
    protected String accountId;
    protected String email;
    protected String name;

    public SocialAccount(String accountId, String email, String name) {
        this.accountId = accountId;
        this.email = email;
        this.name = name;
    }
}
