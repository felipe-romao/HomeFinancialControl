package com.example.g508029.homefinancialcontrol.system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileSystem implements IFileSystem{
    @Override
    public OutputStream create(String path) throws FileNotFoundException {
        return new FileOutputStream(path);
    }
}
