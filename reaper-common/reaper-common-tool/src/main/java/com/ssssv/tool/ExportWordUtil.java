package com.ssssv.tool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

public class ExportWordUtil {

    private static Configuration configuration;
    private static final String SUFFIX = ".doc";

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(ExportWordUtil.class, "/template/word");
    }

    private static File createDocFile(Map dataMap, Template template) throws IOException, TemplateException {
        File file = new File("init.doc");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8);
        template.process(dataMap, outputStreamWriter);
        outputStreamWriter.close();
        return file;
    }

    public static void exportWord(Map map, String title, String ftlName) throws IOException, TemplateException {
        Template template = configuration.getTemplate(ftlName);
        File file = null;
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        file = createDocFile(map, template);
        inputStream = Files.newInputStream(file.toPath());
        String fileName = title + SUFFIX;
        HttpServletResponse response = SpringContextUtil2.getHttpServletResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/word");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        servletOutputStream = response.getOutputStream();
        byte[] buffer = new byte[512];
        int byteToRead = -1;
        while ((byteToRead = inputStream.read(buffer)) != -1) {
            servletOutputStream.write(buffer, 0, byteToRead);
        }
        inputStream.close();
        if (servletOutputStream != null) {
            servletOutputStream.close();
        }
        boolean delete = file.delete();
    }
}
