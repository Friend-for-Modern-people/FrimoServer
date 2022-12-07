package com.gachon.frimo.web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@EnableScheduling
@Controller
@RequestMapping(path = "/app/chatting/")
public class ChattingController {

    @Scheduled(cron = "5 * * * * *") // 0 0 3 * * *
    @GetMapping(path = "")
    public ResponseEntity<String> getChat() throws IOException {
        BufferedReader in = null;
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        

        Date dDate = new Date();
        dDate = new Date(dDate.getTime() + (1000 * 60 * 60 * 24 * -1));
        SimpleDateFormat dSdf = new SimpleDateFormat("yyyy/MM/dd HH", Locale.KOREA);
        String yesterday = dSdf.format(dDate);

        try {
            URL obj = new URL(
                    "https://frimo-93773-default-rtdb.firebaseio.com/namseunghyeon/chat.json?orderBy=\"time/date\"&equalTo="
                            + 23 + "&print=pretty"); // 호출할 url
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            String line;

            while ((line = in.readLine()) != null) { // response를 차례대로 출력
                System.out.println(line);
                result = result.concat(line);
                stringBuilder.append(line).append('\n');
            }

            // jsonObject = (JSONObject) parser.parse(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return ResponseEntity.status(HttpStatus.OK).body(stringBuilder.toString());

    }

}