package com.azizi.opa.bundle.generator.controller;

import com.azizi.opa.bundle.generator.util.ArchiverUtils;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bundles")
@RequiredArgsConstructor
public class BundleController {

    private static final String ROOT_PATH = "/Users/azizisgandarzada/Projects/java-opa-example/opa-bundle-generator" +
            "/src/main/resources/";

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "bundle.tar.gz")
    public ResponseEntity<Resource> generate() throws IOException {
        Path data = Paths.get(ROOT_PATH + "data.json");
        Path policy = Paths.get(ROOT_PATH + "policy.rego");
        Path output = Paths.get(ROOT_PATH + "bundle.tar.gz");
        List<Path> files = Arrays.asList(data, policy);
        ArchiverUtils.createTarGzipFiles(files, output);
        Resource resource = resourceLoader.getResource("classpath:bundle.tar.gz");
        InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_ENCODING, "gzip, deflate, br")
                .contentType(MediaType.parseMediaType("application/gzip"))
                .contentLength(resource.getFile().length())
                .body(inputStreamResource);
    }

}
