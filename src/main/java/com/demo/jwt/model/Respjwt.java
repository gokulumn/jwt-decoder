package com.demo.jwt.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder(builderMethodName = "builder")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        creatorVisibility = JsonAutoDetect.Visibility.ANY
)
public class Respjwt {


    @Setter
    @Getter
     @JsonProperty ("header")
    private String header;
    @JsonProperty ("payload")
    private String payload;
    @JsonProperty ("Signature")
    private boolean signature;


    public String getHeader() {
        return header;
    }
    public void setHeader(String header) {
        this.header = header;
    }


    public String getPayload() {
        return payload;
    }


    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setSignature(boolean signature) {
        this.signature = signature;
    }


}
