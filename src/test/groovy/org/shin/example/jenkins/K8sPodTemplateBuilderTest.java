package org.shin.example.jenkins;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class K8sPodTemplateBuilderTest {


    @Test
    public void testbuildPodTemplate() {
        K8sPodTemplateBuilder builder = new K8sPodTemplateBuilder();
        Map<String, String> map = new HashMap<>();
        map.put("kaniko", "yes");

        String s = builder.buildPodTemplate(map);
        System.out.println(s);
    }
}