package com.demo.jwt.service;

import com.demo.jwt.model.Jwtdel;
import com.demo.jwt.model.Respjwt;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;
import static com.demo.jwt.service.JsonParser.readAll;

@JsonIgnoreProperties(ignoreUnknown = true)
@Service
public class TokenService {


    public BigInteger n_mod = null;
    public BigInteger e_expo = null;
    boolean sig_verify = false;
    public Respjwt getToken(Jwtdel jwtdel, Respjwt respjwt) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException, SignatureException, JSONException {


        String[] parts = jwtdel.getToken().split("\\.");

       byte[] signaturetoken = Base64.getUrlDecoder().decode(parts[2]);

        String decodedString = "";
            for (int i = 0; i < 2; i++) {
                var partbase64 = parts[i].replaceAll("\\-", "+").replaceAll("\\_", "/");

                byte[] decodedBytes = Base64.getDecoder().decode(partbase64.getBytes(StandardCharsets.UTF_8));

                decodedString = new String(decodedBytes);
                if (i == 0) {
                    respjwt.setHeader(decodedString);
                } else {
                    respjwt.setPayload(decodedString);
                }
            }


        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = new URL(jwtdel.getJwkurl()).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        JSONObject json = new JSONObject(jsonText);
        objectMapper = new ObjectMapper();
        objectMapper.enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature());
        JsonNode rootNode = objectMapper.readTree(String.valueOf(json));
        JsonNode KeysNode = rootNode.get("keys");

        if (KeysNode.isArray()) {
            for (JsonNode node : KeysNode) {
                    if (node.get("kid").asText().equals(jwtdel.getKid())) {
                        n_mod = new BigInteger(1, Base64.getUrlDecoder().decode(node.get("n").asText()));
                        e_expo = new BigInteger(1, Base64.getUrlDecoder().decode(node.get("e").asText()));
                    break;
                }  else {
                          n_mod = null;
                          e_expo = null;
                    }
            }
        }


        try {
            if (e_expo != null || n_mod != null) {
                PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(n_mod, e_expo));
                Signature sig = Signature.getInstance("SHA256withRSA");
                sig.initVerify(publicKey);
                byte[] signingInfo = String.join(".", parts[0], parts[1]).getBytes(StandardCharsets.UTF_8);
                sig.update(signingInfo);
                sig_verify = sig.verify(signaturetoken);
                respjwt.setSignature(sig_verify);
            } else {
                respjwt.setSignature(false);
            }
        }
            catch(NullPointerException e) {
                System.out.println("Not Match for Kid in Jwki URI : " + jwtdel.getUrl());

            }
        return respjwt;
    }
}

