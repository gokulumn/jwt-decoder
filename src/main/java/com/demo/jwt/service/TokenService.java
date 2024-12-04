package com.demo.jwt.service;



import org.springframework.stereotype.Service;
import java.security.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@Service
public class TokenService {

    public List<String> getToken(String token) throws JSONException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchProviderException {

        //  JSONArray family = (JSONArray) jsonObject.get("family");
        ArrayList<String> tokenresponse = new ArrayList<String>();

        System.out.println(token.getBytes());

        JSONObject jObj = new JSONObject(String.valueOf(token));
      //  JSONObject jObj2 = new JSONObject(token.toString());
       Iterator<?> keys = jObj.keys();

        while( keys.hasNext() ) {
            String key = (String) keys.next();
            if (jObj.get(key) instanceof JSONObject) {
                System.out.println(jObj.getString(key));
            }
        }

//       byte[] decodedBytes = Base64.getDecoder().decode(token.toString());

//       String decodedString = new String(decodedBytes);

        System.out.println(token.getBytes());

        String[] parts = token.split("\\.");

        for (int i = 0; i < 2; i++) {
            var partbase64 = parts[i].replaceAll("\\-", "+").replaceAll("\\_", "/");

            byte[] decodedBytes = Base64.getDecoder().decode(partbase64.getBytes(StandardCharsets.UTF_8));

            //  String decodedString = new String(decodedBytes, "UTF-8");
        String decodedString = new String(decodedBytes);

            int spacesToIndentEachLevel = 2;
            new JSONObject(decodedString).toString(spacesToIndentEachLevel);
            tokenresponse.add(decodedString);

        }

        if (parts[2] != null) {


            var partbase64 = parts[2].replaceAll("\\-", "+").replaceAll("\\_", "/");
            byte[] decodedBytes = Base64.getDecoder().decode(partbase64.getBytes(StandardCharsets.UTF_8));
            String decodedString = new String(decodedBytes);

//            try {
//                // String token = "some-token";
//                String publicKey = "some-key";
//
//                //Convert public key string to RSAPublicKey
//                byte[] publicKeyByteArr = Base64.getDecoder().decode(publicKey);
//                //   X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(publicKey.getBytes());

            KeyFactory sr = KeyFactory.getInstance("DSA");

            // getting the algorithm of KeyFactory object

            tokenresponse.add(sr.getAlgorithm());


            // printing the status
     //       System.out.println("algorithm : " + str);
//            }
//
//            catch (NoSuchAlgorithmException e) {
//
//                System.out.println("Exception thrown : " + e);
//            }
//            catch (NullPointerException e) {
//
//                System.out.println("Exception thrown : " + e);
//            }


//        JSONObject header = new JSONObject(decode(parts[0]));
//        JSONObject payload = new JSONObject(decode(parts[1]));
//        String signature = decode(parts[2]);

            //  String[] parts = token.split("\\.", 0);

//        for (String part : parts) {
//            byte[] bytes = Base64.getUrlDecoder().decode(part);
//            String decodedString = new String(bytes, StandardCharsets.UTF_8);
//
//            System.out.println("Decoded: " + decodedString);

//            String header = parts[0];
//            String payload = parts[1];
//            String signature = parts[2];
//            tokenresponse = Arrays.asList(header, payload, signature);

//        }


            //}

        }
        return tokenresponse;

    }

}

