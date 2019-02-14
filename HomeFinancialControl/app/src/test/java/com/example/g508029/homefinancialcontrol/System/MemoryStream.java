package com.example.g508029.homefinancialcontrol.System;

import com.example.g508029.homefinancialcontrol.system.IFileSystem;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class MemoryStream implements IFileSystem {
    private ByteArrayOutputStream stream;

    @Override
    public OutputStream create(String path) throws Exception {
        this.stream = new ByteArrayOutputStream();
        return this.stream;
    }

    public byte[] getByteArray(){
        return this.stream.toByteArray();
    }
}
