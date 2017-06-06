package net.warvale.core.utils.files;

import net.warvale.core.utils.ftp.AbstractFileConnection;
import net.warvale.core.utils.ftp.FTPFileConnection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class DownloadUtil {
    public static void download(File to, String address, CopyOption option) throws IOException {
        Files.copy(download(address), to.toPath(), option);
    }

    public static void download(File to, String address) throws IOException {
        download(to, address, StandardCopyOption.REPLACE_EXISTING);
    }

    public static InputStream download(String address) throws IOException {
        return new URL(address).openStream();
    }

    public static void download(File to, String address, AbstractFileConnection connection, CopyOption option) throws Exception{
        Files.copy(connection.get(address), to.toPath(), option);
    }

    public static void download(File to, String address, AbstractFileConnection connection) throws Exception{
        download(to, address, connection, StandardCopyOption.REPLACE_EXISTING);
    }
}
