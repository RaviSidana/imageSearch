package com.example.demoImage.service.impl;

import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageAnalysis;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageCaption;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageTag;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.VisualFeatureTypes;
import org.springframework.stereotype.Service;
import com.example.demoImage.service.ImageSearchService;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageSearch implements ImageSearchService {

    public static ComputerVisionClient Authenticate(String subscriptionKey, String endpoint) {
        return ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint);
    }

    @Override
    public List<String> analyzeLocalImage(File rawImage) {
        System.out.println("\nAzure Cognitive Services Computer Vision - Java Quickstart Sample");
        ComputerVisionClient compVisClient = Authenticate(subscriptionKey, endpoint);
//        String pathToLocalImage = "/Users/ishas/Downloads/demoImage/src/test/java/img_1.png";
        List<VisualFeatureTypes> featuresToExtractFromLocalImage = new ArrayList<>();
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.DESCRIPTION);
        featuresToExtractFromLocalImage.add(VisualFeatureTypes.TAGS);
        List<String> tags = new ArrayList<>();

        System.out.println("\nAnalyzing local image ...");
        try {
            byte[] imageByteArray = Files.readAllBytes(rawImage.toPath());
            ImageAnalysis analysis = compVisClient.computerVision().analyzeImageInStream().withImage(imageByteArray)
                    .withVisualFeatures(featuresToExtractFromLocalImage).execute();
            System.out.println("\nCaptions: ");
            for (ImageCaption caption : analysis.description().captions()) {
                System.out.printf("\'%s\' with confidence %f\n", caption.text(), caption.confidence());
            }

            System.out.println("\nTags: ");
            for (ImageTag tag : analysis.tags()) {
                tags.add(tag.name());
            }
            System.out.println(tags);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return tags;
    }
}

