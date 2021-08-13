package com.azizi.opa.bundle.generator.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArchiverUtils {

    public static void createTarGzipFiles(List<Path> files, Path output) throws IOException {
        try (OutputStream fOut = Files.newOutputStream(output);
             BufferedOutputStream buffOut = new BufferedOutputStream(fOut);
             GzipCompressorOutputStream gzOut = new GzipCompressorOutputStream(buffOut);
             TarArchiveOutputStream tOut = new TarArchiveOutputStream(gzOut)) {
            for (Path path : files) {
                if (!Files.isRegularFile(path)) {
                    throw new IOException("Support only file!");
                }
                TarArchiveEntry tarEntry = new TarArchiveEntry(path.toFile(), path.getFileName().toString());
                tOut.putArchiveEntry(tarEntry);
                // copy file to TarArchiveOutputStream
                Files.copy(path, tOut);

                tOut.closeArchiveEntry();
            }
            tOut.finish();
        }
    }

}
