package com.example.demoImage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demoImage.service.ImageSearchService;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ravi.sidana
 */
@RestController
@CrossOrigin
@RequestMapping("/backend/image")
@Slf4j
public class ImageSearchController {

  @Autowired
  private ImageSearchService imageSearchService;


  @RequestMapping(method = RequestMethod.POST, value = "/search")
  public List<String> imageSearch(@RequestParam(value = "image") MultipartFile file)
      throws IOException {
    File image = new File(System.getProperty("java.io.tmpdir")+"/"+"image");
    file.transferTo(image);
    return imageSearchService.analyzeLocalImage(image);
  }





}
