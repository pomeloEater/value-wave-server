package kr.vng.valuewave.web;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/local")
public class LocalController {

    private static final Gson GSON = new Gson();

    @Value("${kakao.rest}")
    private String KAKAO_REST_API;

    private static final String API_SERVER_HOST = "https://dapi.kakao.com";
    private static final String ADDRESS_PATH = "/v2/local/search/address.json";

    @GetMapping("/get-address/{paramAddress}")
    public Object getAddress(@PathVariable(required = false) String paramAddress) {
        HttpsURLConnection con = null;
        BufferedReader br = null;
        String inputLine;
        StringBuffer response = new StringBuffer();
        Map resultMap = new HashMap();
        String urlStr = API_SERVER_HOST + ADDRESS_PATH;
        try {
            urlStr += "?query=" + URLEncoder.encode(paramAddress, "UTF-8");
            URL url = new URL(urlStr);
            con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "KakaoAK " + KAKAO_REST_API);
            int responseCode = con.getResponseCode();
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            if (responseCode == 200) {
                while((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
            } else {
                resultMap.put("result","failed");
                return resultMap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultMap.put("result","success");
            String responseStr = response.toString();
            resultMap.put("data", GSON.fromJson(responseStr, Map.class));
            return resultMap;
        }
    }

}
