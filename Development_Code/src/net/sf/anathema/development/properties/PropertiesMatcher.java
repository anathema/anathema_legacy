package net.sf.anathema.development.properties;

import net.sf.anathema.framework.presenter.action.SupportedLocale;
import net.sf.anathema.lib.io.PathUtils;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class PropertiesMatcher {

  public static void main(String[] args) throws IOException {
    SupportedLocale locale = (SupportedLocale) JOptionPane.showInputDialog(null, "Choose Locale:", "Locale",
            JOptionPane.QUESTION_MESSAGE, null, SupportedLocale.values(), null);
    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
      return;
    }
    Path superFolder = chooser.getSelectedFile().toPath();
    List<Path> defaultPropertiesFiles = new ArrayList<>();
    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(superFolder, new DirectoriesOnly())) {
      for (Path folder : directoryStream) {
        Path resourceFolder = folder.resolve("resources/language");
        if (Files.exists(resourceFolder)) {
          Collection<Path> propertyPaths = PathUtils.listAll(resourceFolder, "*.properties");
          for (Path properties : propertyPaths) {
            addDefaultPath(defaultPropertiesFiles, properties);
          }
        }
      }
    }
    for (Path propertiesFile : defaultPropertiesFiles) {
      PropertiesMatcher matcher = new PropertiesMatcher(propertiesFile, locale.getLocale());
      matcher.matchProperties();
    }
  }

  private static void addDefaultPath(List<Path> defaultPropertiesFiles, Path propertiesFile) {
    for (SupportedLocale supportedLocale : SupportedLocale.values()) {
      if (propertiesFile.getFileName().toString().contains("_" + supportedLocale.getLocale().getLanguage() + ".")) {
        return;
      }
    }
    defaultPropertiesFiles.add(propertiesFile);
  }

  private final Properties localeProperties = new Properties();
  private final Locale locale;

  private Path localePropertiesFile;
  private final BufferedReader defaultPropertiesReader;
  private static final String delimiter = "\\s*[=:]\\s*";
  private static final String whiteSpace = "\\s*";
  private final BufferedWriter localePropertiesWriter;

  public PropertiesMatcher(Path defaultPropertiesFile, Locale locale) throws IOException {
    Charset charset = Charset.forName("UTF-8");
    defaultPropertiesReader = Files.newBufferedReader(defaultPropertiesFile, charset);
    this.locale = locale;
    getLocaleProperties(defaultPropertiesFile);
    Path path = Paths.get("./language/");
    Files.createDirectory(path);
    localePropertiesWriter = Files.newBufferedWriter(path.resolve(localePropertiesFile), charset);
  }

  private void getLocaleProperties(Path defaultPropertiesFile) throws IOException {
    String defaultPath = defaultPropertiesFile.toString();
    String localizedPath = defaultPath.replace(".properties", "_" + locale.getLanguage() + ".properties");
    localePropertiesFile = Paths.get(localizedPath);
    if (Files.exists(localePropertiesFile)) {
      InputStream localeStream = Files.newInputStream(localePropertiesFile);
      localeProperties.load(localeStream);
      localeStream.close();
    }
  }

  private void matchProperties() throws IOException {
    String line = defaultPropertiesReader.readLine();
    while (line != null) {
      if (isComment(line) || isEmpty(line)) {
        writeString(line);
      } else {
        handleProperty(line);
      }
      line = defaultPropertiesReader.readLine();
    }
    defaultPropertiesReader.close();
    localePropertiesWriter.close();
  }

  private boolean isEmpty(String line) {
    return line.matches(whiteSpace);
  }

  private void handleProperty(String line) throws IOException {
    System.err.println(line);
    String[] keyValueArray = line.split(delimiter, 2);
    String key = keyValueArray[0].replaceAll(whiteSpace, "");
    String value = keyValueArray[1];
    String localizedValue = localeProperties.getProperty(key);
    if (localizedValue == null) {
      writeProperty(key, value, true);
    } else if (localizedValue.equals(value)) {
      writeProperty(key, value, true);
    } else {
      writeProperty(key, localizedValue, false);
    }
  }

  private boolean isComment(String line) {
    String string = line.replace(whiteSpace, "");
    return (string.indexOf("!") == 0) || (string.indexOf("#") == 0);
  }

  private void writeProperty(String key, String value, boolean isComment) throws IOException {
    String string = (isComment ? "##" : "") + key + "=" + value;
    writeString(string);
  }

  private void writeString(String string) throws IOException {
    localePropertiesWriter.write(string);
    localePropertiesWriter.newLine();
  }
}