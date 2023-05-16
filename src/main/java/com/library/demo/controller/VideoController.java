package com.library.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class VideoController {

    private static final String API_KEY = "apikey";
    private static final String VIDEO_ID = "R-bI0AhSyU0";

    @GetMapping("/video")
    public String showVideo(Model model) {
        try {
            String videoInfo = getVideoInfo(VIDEO_ID, API_KEY);
            String embedCode = getVideoEmbedCode(VIDEO_ID);
            model.addAttribute("videoInfo", videoInfo);
            model.addAttribute("embedCode", embedCode);
            return "secondArticle";
        } catch (IOException e) {
            // обработка ошибок
            return "firstArticle";
        }
    }

    public String getVideoInfo(String videoId, String apiKey) throws IOException {
        String url = "https://www.googleapis.com/youtube/v3/videos?id=" + videoId + "&key=" + apiKey + "&part=snippet,contentDetails,statistics,status";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }

    public String getVideoEmbedCode(String videoId) {
        return "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
    }

}
