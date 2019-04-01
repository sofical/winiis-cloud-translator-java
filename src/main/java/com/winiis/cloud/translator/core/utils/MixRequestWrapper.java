package com.winiis.cloud.translator.core.utils;

import org.apache.poi.util.IOUtils;
import org.springframework.mock.web.DelegatingServletInputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * MixRequestWrapper.
 *
 * @author zj.
 *         Created on 2018/8/29 0029.
 */
public class MixRequestWrapper extends HttpServletRequestWrapper {
    private byte[] inputStream;

    public MixRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.inputStream = IOUtils.toByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputStream);
        return new DelegatingServletInputStream(byteArrayInputStream);
    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public static String requestJsonBody(MixRequestWrapper mixRequestWrapper) {
        String result = "{}";
        try {
            BufferedReader br = mixRequestWrapper.getReader();
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
