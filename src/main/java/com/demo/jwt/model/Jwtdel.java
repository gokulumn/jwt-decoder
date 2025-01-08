package com.demo.jwt.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder(builderMethodName = "builder")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class Jwtdel {

    // Getters and setters

    @Setter
    @Getter
    @JsonProperty ("token")
    private String token;
    @JsonProperty ("jwkurl")
    private String jwkurl;
    @JsonProperty ("kid")
    private String kid;

    public String getUrl() {
            return jwkurl;
        }

    public void setUrl(String jwkurl) {
            this.jwkurl = jwkurl;
        }

        public String getKid() {
            return kid;
        }


}
