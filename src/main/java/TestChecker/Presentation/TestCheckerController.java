package TestChecker.Presentation;

import TestChecker.BusinessLogic.Test;
import TestChecker.BusinessLogic.TestCheckerLogic;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.UUID;


@RestController
public class TestCheckerController {

    TestCheckerLogic testChecker;

    // create 3 clients to connect to
    WebClient clientAssessmentJournal;
    WebClient clientClassroomLogin;
    WebClient clientGateway;

//    WebClient message_web_client = WebClient.create("http://localhost:8081/");
//    WebClient logging_web_client = WebClient.create("http://localhost:8082/");

    // POST requests
    @PostMapping("/addTest")
    public Mono<String> addTest(@RequestBody UUID user_id,
                                String testName,
                                Test testBody) {

        String if_success = testChecker.addTest(user_id,
                testName,
                testBody);
        return Mono.just(if_success);
    }

    @PostMapping("/checkTest")
    public Mono<String> checkTest(@RequestBody UUID user_id,
                                  String testName,
                                  Test testBody) {
        String if_success = testChecker.checkTest(user_id,
                testName,
                testBody);
        return Mono.just(if_success);
    }


    //GET requests
    @GetMapping("/getTest")
    public Mono<Test> getTest(@RequestBody UUID user_id,
                              String testName) {
        Test result = testChecker.getTestNoAnswers(user_id,
                testName);
        return Mono.just(result);
    }

    @GetMapping("/getAllTestsNames")
    public Mono<ArrayList<String>> getAllTestsNames(@RequestBody Message text) {
        return null;
    }

    @GetMapping("/getAllTests")
    public Mono<ArrayList<Test>> getAllTests(@RequestBody Message text) {
        return null;
    }

//    @GetMapping("/testCheckerService")
//    public Mono<String> client_web_client() {
//
//        Mono<String> message_mono = message_web_client.get()
//                .uri("/message_service")
//                .retrieve()
//                .bodyToMono(String.class);
//
//        Mono<String> cached_values = logging_web_client.get()
//                .uri("/log_service")
//                .retrieve()
//                .bodyToMono(String.class);
//
//
//        Mono<String> result = cached_values.zipWith(message_mono, (cached, message) -> STR."\{cached}: \{message}")
//                .onErrorReturn("Error: in @GetMapping(\"/facade_service\") in cached_values.zipWith()");
//
////        System.out.println("Get request: message_mono = " + message_mono.toString());
////        System.out.println("Get request: cached_values = " + cached_values.toString());
////        System.out.println("Get request " + result.toString() + "\n");
//        System.out.println("Get request: facade_service");
//        return result;
//
//    }


//    @PostMapping("/facade_service")
//    public Mono<Void> facade_web_client(@RequestBody String text) {
//
//        var message = new Message(UUID.randomUUID(), text);
//
//        Mono<String> message_mono = message_web_client.post()
//                .uri("/message_service")
//                .retrieve()
//                .bodyToMono(String.class);
//
//        Mono<Void> logging_mono = logging_web_client.post()
//                .uri("/log_service")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(message), Message.class)
//                .retrieve()
//                .bodyToMono(Void.class);
//
//        System.out.println(STR."Post request: \{message}");
//
//        return message_mono.then(logging_mono);
//    }


}
