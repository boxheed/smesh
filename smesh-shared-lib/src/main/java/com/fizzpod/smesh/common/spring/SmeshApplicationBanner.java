package com.fizzpod.smesh.common.spring;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Formatter;
import java.util.Properties;

import javax.crypto.Cipher;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import com.github.dtmo.jfiglet.FigFontResources;
import com.github.dtmo.jfiglet.FigletRenderer;


public class SmeshApplicationBanner implements Banner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmeshApplicationBanner.class);

    @Override
    public void printBanner(final Environment environment, final Class<?> sourceClass, final PrintStream out) {
        String banner = getBanner();
        out.println(banner);

    }

    /**
     * Collect environment info with details on the java and os deployment versions.
     *
     * @return environment info
     */
    private static String getBanner() {
        try (Formatter formatter = new Formatter()) {
            formatApplicationName(formatter);
            formatServletServerVersion(formatter);

            final Properties properties = System.getProperties();
            // formatter.format("Build Date/Time: %s%n", getBuildTime());
            formatter.format("System Temp Directory: %s%n", FileUtils.getTempDirectoryPath());
            formatter.format("Java Home: %s%n", properties.get("java.home"));
            formatter.format("Java Vendor: %s%n", properties.get("java.vendor"));
            formatter.format("Java Version: %s%n", properties.get("java.version"));
            formatter.format("JCE Installed: %s%n", BooleanUtils.toStringYesNo(isJceInstalled()));
            formatter.format("OS Architecture: %s%n", properties.get("os.arch"));
            formatter.format("OS Name: %s%n", properties.get("os.name"));
            formatter.format("OS Version: %s%n", properties.get("os.version"));
            return formatter.toString();
        }
    }

    private static void formatApplicationName(Formatter formatter) {

        FigletRenderer figletRenderer;
        String name = "SMESH";
        try {
            figletRenderer = new FigletRenderer(FigFontResources.loadFigFontResource(FigFontResources.BIG_FLF));
            name = figletRenderer.renderText(name);
        } catch (IOException e) {
            LOGGER.error("Could not render name into Ascii Art", e);
        }

        formatter.format("%S%n", name);

    }

    private static void formatServletServerVersion(final Formatter formatter) {
        try {
            final Class clz = Class.forName("io.undertow.Version");
            final Method method = clz.getMethod("getFullVersionString");
            final Object version = method.invoke(null);
            formatter.format("Undertow Version: %s%n", version);
        } catch (final Exception e) {
            LOGGER.trace(e.getMessage(), e);
        }
    }
    
    private static boolean isJceInstalled() {
        try {
            final int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
            return maxKeyLen == Integer.MAX_VALUE;
        } catch (final Exception e) {
            return false;
        }
    }

}
