package jmigration.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplitReader {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Lambda<String, Void> mapper;
    private final String encoding;

    public SplitReader(Lambda<String, Void> mapper) {
        this(mapper, "UTF-8");
    }

    public SplitReader(Lambda<String, Void> mapper, String encoding) {
        this.mapper = mapper;
        this.encoding = encoding;
    }

    public void doRead(String path) {
        BufferedReader br = null;

        try {

            String line;

            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));

            while ((line = br.readLine()) != null) {

                if (mapper != null) {
                    mapper.execute(line);
                }
            }

        } catch (IOException e) {
            logger.error("fail read ", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                logger.error("fail close ", ex);
            }
        }

    }


}
