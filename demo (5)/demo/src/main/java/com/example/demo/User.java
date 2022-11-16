package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private List<Topic> topics = new ArrayList<>();

    public void addTop(Topic topic){
        this.topics.add(topic);
    }
    public void addTop(int i, Topic topic){
        this.topics.add(i, topic);
    }
    public void deleteTop(int i){
        this.topics.remove(i);
    }
}
