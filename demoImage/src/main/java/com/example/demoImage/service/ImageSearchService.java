package com.example.demoImage.service;

import java.io.File;
import java.util.List;

/**
 * @author ravi.sidana
 */
public interface ImageSearchService {

  String subscriptionKey = "208dd5013492460096f4e1191358ffa3";
  String endpoint = "https://kiran.cognitiveservices.azure.com/";

  List<String> analyzeLocalImage(File file);
}
