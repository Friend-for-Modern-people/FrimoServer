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
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.ChattingService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Controller
@RequestMapping(path = "/app/chatting/")
public class ChattingController {

    // @Scheduled(cron="0 0 3 * * *")
    // @Scheduled(fixedDelay = 100000)
    @GetMapping(path = "/{day}")
    public ResponseEntity<String> getChat(@PathVariable(value = "day") String day) throws IOException {
        JSONParser parser = new JSONParser();

        // String command = "curl
        // https://frimo-93773-default-rtdb.firebaseio.com/namseunghyeon/chat.json?orderBy=\"message\"&equalTo=\"test\"&print=pretty";
        // // ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        // Process process = Runtime.getRuntime().exec(command);

        // InputStream result = process.getInputStream();
        // String text = new BufferedReader(new InputStreamReader(result,
        // StandardCharsets.UTF_8))
        // .lines()
        // .collect(Collectors.joining("\n"));

        // process.destroy();

        BufferedReader in = null;
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject jsonObject = null;

        try {
            URL obj = new URL(
                    "https://frimo-93773-default-rtdb.firebaseio.com/namseunghyeon/chat.json?orderBy=\"time/date\"&equalTo="
                            + day + "&print=pretty"); // 호출할 url
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