import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public class FileReaderMain {

    public static void main(String[] args) throws Exception {
        IOCopier2.joinFiles(new File("src/SampleChunkOP.txt"), new File[] {
                new File("src/Sample1.txt"), new File("src/Sample2.txt") });


        //File listFiles = new File("/Users/sujaynag/Downloads/FileChunking/src/SampleChunk_split");
        //IOCopier2.joinFiles(new File("src/SampleChunkOPs.txt"), directoryPath.listFiles());

    }
}

class IOCopier2 {
    public static void joinFiles(File destination, File[] sources)
            throws IOException {
        OutputStream output = null;
        try {
            output = createAppendableStream(destination);
            for (File source : sources) {
                System.out.println("Size of "+source.getName()+" - "+source.getTotalSpace());
                appendFile(output, source);
            }
        } finally {
            IOUtils.closeQuietly(output);
        }
    }

    private static BufferedOutputStream createAppendableStream(File destination)
            throws FileNotFoundException {
        return new BufferedOutputStream(new FileOutputStream(destination, true));
    }

    private static void appendFile(OutputStream output, File source)
            throws IOException {
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(source));
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
}