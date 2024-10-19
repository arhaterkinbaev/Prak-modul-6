package ex1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Logger {
    private static Logger instance;
    private static final Object lock = new Object();
    private LogLevel currentLogLevel;
    private String logFilePath;

    private Logger() {
        this.currentLogLevel = LogLevel.INFO; // Установим уровень логирования по умолчанию
        this.logFilePath = "logs.txt"; // Установим путь к лог-файлу по умолчанию
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void setLogLevel(LogLevel level) {
        this.currentLogLevel = level;
    }

    public void log(String message, LogLevel level) {
        if (level.ordinal() >= currentLogLevel.ordinal()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
                writer.write(level + ": " + message);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadConfiguration(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(filePath)));
            String logLevel = properties.getProperty("logLevel");
            String logFilePath = properties.getProperty("logFilePath");

            if (logLevel != null) {
                setLogLevel(LogLevel.valueOf(logLevel.toUpperCase()));
            }

            if (logFilePath != null) {
                this.logFilePath = logFilePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
