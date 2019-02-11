package com.example.g508029.homefinancialcontrol.system;

import java.io.OutputStream;

public interface IFileSystem {
    OutputStream create(String path) throws Exception;
}
