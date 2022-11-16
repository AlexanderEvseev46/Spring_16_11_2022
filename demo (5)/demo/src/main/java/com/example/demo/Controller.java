package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final List<User> users= new ArrayList<>();
    //curl -X POST http://localhost:8080/addUser -d "{\"name\" : \"Ivan\", \"topics\" : }" -H "Content-Type: application/json"
    @PostMapping("addUser")
    public ResponseEntity<Void> addUser(@RequestBody User user){
        users.add(user);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("addTopic/{index}")
    public ResponseEntity<Void> addTopic(@RequestBody Topic topic,
                                         @PathVariable ("index") Integer index){
        users.get(index).addTop(topic);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping("deleteTopic/{indexUser}/{indexTopic}")
    public ResponseEntity<Void> addTopic(@PathVariable ("indexTopic") Integer indexTopic,
                                         @PathVariable ("indexUser") Integer indexUser){
        users.get(indexUser).deleteTop(indexTopic);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("getAllTopics/{index}")
    public ResponseEntity<List<Topic>> getAllTopics(@PathVariable ("index") Integer index){
        return ResponseEntity.ok(users.get(index).getTopics());
    }
    @PutMapping("updateTopic/{indexUser}/{indexTopic}")
    public ResponseEntity<Void> updateTopic(@PathVariable ("indexTopic") Integer indexTopic,
                                         @PathVariable ("indexUser") Integer indexUser,
                                            @RequestBody Topic topic){
        users.get(indexUser).deleteTop(indexTopic);
        users.get(indexUser).addTop(indexTopic, topic);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("getCountTopic/{indexUser}")
    public ResponseEntity<Integer> getCountTopic(@PathVariable ("indexUser") Integer index){
        return ResponseEntity.ok(users.get(index).getTopics().size());
    }
    @DeleteMapping("deleteAllTopics/{indexUser}")
    public ResponseEntity<Void> deleteAllTopics(@PathVariable ("indexUser") Integer index){
        for (int i = 0; i < users.get(index).getTopics().size(); i ++){
            users.get(i).getTopics().remove(i);
        }
        return ResponseEntity.accepted().build();
    }
    @GetMapping("getTopic/{indexUser}/{indexTopic}")
    public ResponseEntity<String> getTopic(@PathVariable ("indexUser") Integer indexUser,
                                            @PathVariable ("indexTopic") Integer indexTopic){
        return ResponseEntity.ok(users.get(indexUser).getTopics().get(indexTopic).getName());
    }
    @PostMapping("addComment/{indexUser}/{indexTopic}")
    public ResponseEntity<Void> addTopic(@RequestBody String comment,
                                         @PathVariable ("indexTopic") Integer indexTopic,
                                         @PathVariable ("indexUser") Integer indexUser){
        users.get(indexUser).getTopics().get(indexTopic).getComments().add(comment);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping("deleteTopic/{indexUser}/{indexTopic}/{indexComment}")
    public ResponseEntity<Void> addTopic(@PathVariable ("indexTopic") Integer indexTopic,
                                         @PathVariable ("indexUser") Integer indexUser,
                                         @PathVariable ("indexComment") Integer indexComment){
        users.get(indexUser).getTopics().get(indexTopic).getComments().remove(indexComment);
        return ResponseEntity.accepted().build();
    }
    @PutMapping("updateComment/{indexUser}/{indexTopic}/{indexComment}")
    public ResponseEntity<Void> updateTopic(@PathVariable ("indexTopic") Integer indexTopic,
                                            @PathVariable ("indexUser") Integer indexUser,
                                            @PathVariable ("indexComment") Integer indexComment,
                                            @RequestBody String comment){
        users.get(indexUser).getTopics().get(indexTopic).getComments().remove(indexComment);
        users.get(indexUser).getTopics().get(indexTopic).getComments().add(indexComment, comment);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("getComments/{indexUser}/{indexTopic}")
    public ResponseEntity<List<String>> getAllComments(@PathVariable ("indexTopic") Integer indexTopic,
                                                       @PathVariable ("indexUser") Integer indexUser){
        return ResponseEntity.ok(users.get(indexUser).getTopics().get(indexTopic).getComments());
    }
    @GetMapping("getComments/{indexUser}")
    public ResponseEntity<List<String>> getComments(@PathVariable ("indexUser") Integer indexUser){
        List<String> filtredComments = new ArrayList<>();
        for(int i = 0; i < users.get(indexUser).getTopics().size(); i ++){
            filtredComments.addAll(users.get(indexUser).getTopics().get(i).getComments());
        }
        return ResponseEntity.ok(filtredComments);
    }
    @DeleteMapping("deleteComments/{indexUser}")
    public ResponseEntity<Void> deleteComments(@PathVariable ("indexUser") Integer indexUser){
        for (int i = 0; i < users.get(indexUser).getTopics().size(); i ++){
            users.get(indexUser).getTopics().get(i).getComments().clear();
        }
        return ResponseEntity.accepted().build();
    }

}
