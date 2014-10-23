package com.epam.training.myapplication.processing;

import com.epam.training.myapplication.source.FileDataSource;
import com.epam.training.myapplication.source.HttpDataSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by NuclearOK on 23.10.2014.
 */
public class FileProcessor implements Processor<String, File> {
    @Override
    public String process(File file) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            StringBuilder stringbuilder = new StringBuilder("");
            byte[] buffer = new byte[1024];
            int n;
            while ((n = fis.read(buffer)) != -1)
            {
                stringbuilder.append(new String(buffer, 0, n));
            }
            return stringbuilder.toString();

        } finally {

            FileDataSource.close(fis);
        }
    }
}
