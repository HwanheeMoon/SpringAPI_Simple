package com.test.APIServer;



import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.Reader;


@Controller
@RequiredArgsConstructor
@RestController
public class UserController {
    @RequiredArgsConstructor
    @Getter
    @Setter
    public class checkStatus {
        String message;
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    public class countSum {
        long sum;
    }

    public static long sum() throws Exception{
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("user.json");
        JSONArray array = (JSONArray) parser.parse(reader);
        long sum = 0;
        for(int i = 0; i < array.size(); i ++) {
            JSONObject element = (JSONObject) array.get(i);
            long id =(long) element.get("user_id");
            String username = (String) element.get("username");
            long post_count = (long) element.get("post_count");
            sum += post_count;
        }
        return sum;
    }

    @GetMapping("/")
    public checkStatus check() {
        checkStatus checkStatus = new checkStatus();
        checkStatus.setMessage("server check");

        return checkStatus;
    }

    @GetMapping("/sum")
    public countSum returnSum() {
        countSum countSum = new countSum();
        long n;
        try {
            n = sum();
            countSum.setSum(n);
        } catch (Exception e) {

        }
        return countSum;
    }

}

