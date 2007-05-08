package net.sf.anathema.development.properties;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import net.sf.anathema.framework.presenter.action.SupportedLocale;

import com.l2fprod.common.swing.JDirectoryChooser;

public class PropertiesMatcher {

  public static void main(String[] args) throws IOException {
    SupportedLocale locale = (SupportedLocale) JOptionPane.showInputDialog(
        null,
        "Choose Locale:", "Locale", JOptionPane.QUESTION_MESSAGE, null, SupportedLocale.values(), null); //$NON-NLS-1$ //$NON-NLS-2$
    JDirectoryChooser chooser = new JDirectoryChooser();
    int choice = chooser.showOpenDialog(null);
    if (choice != JFileChooser.APPROVE_OPTION) {
      return;
    }
    File superFolder = chooser.getSelectedFile();
    List<File> defaultPropertiesFiles = new ArrayList<File>();
    File[] projectFolders = superFolder.listFiles();
    for (File file : projectFolders) {
      if (file.isDirectory()) {
        File resourceFolder = new File(file, "/resources/language");
        if (resourceFolder.exists()) {
          File[] files = resourceFolder.listFiles(new PropertiesFilter());
          for (File propertiesFile : files) {
            if (propertiesFile.isDirectory()) {
              continue;
            }
            addDefaultFile(defaultPropertiesFiles, propertiesFile);
          }
        }
      }
    }

    for (File propertiesFile : defaultPropertiesFiles) {
      PropertiesMatcher matcher = new PropertiesMatcher(propertiesFile, locale.getLocale());
      matcher.matchProperties();
    }
  }

  private static void addDefaultFile(List<File> defaultPropertiesFiles, File propertiesFile) {
    for (SupportedLocale supportedLocale : SupportedLocale.values()) {
      if (propertiesFile.getName().contains("_" + supportedLocale.getLocale().getLanguage() + ".")) {
        return;
      }
    }
    defaultPropertiesFiles.add(propertiesFile);
  }

  private final Properties localeProperties = new Properties();
  private final Locale locale;

  private File localePropertiesFile;
  private final BufferedReader defaultPropertiesReader;
  private final String delimiter = "\\s*[=:]\\s*";
  private final String whiteSpace = "\\s*";
  private final BufferedWriter localePropertiesWriter;

  public PropertiesMatcher(File defaultPropertiesFile, Locale locale) throws IOException {
    defaultPropertiesReader = new BufferedReader(new FileReader(defaultPropertiesFile));
    this.locale = locale;
    getLocaleProperties(defaultPropertiesFile);
    new File("./language/").mkdir();
    localePropertiesWriter = new BufferedWriter(
        new FileWriter(new File("./language/" + localePropertiesFile.getName())));
  }

  private void getLocaleProperties(File defaultPropertiesFile) throws IOException {
    String defaultPath = defaultPropertiesFile.getCanonicalPath();
    String localizedPath = defaultPath.replace(".properties", "_" + locale.getLanguage() + ".properties");
    localePropertiesFile = new File(localizedPath);
    if (localePropertiesFile.exists()) {
      InputStream localeStream = new FileInputStream(localePropertiesFile);
      localeProperties.load(localeStream);
      localeStream.close();
    }
  }

  private void matchProperties() throws IOException {
    String line = defaultPropertiesReader.readLine();
    while (line != null) {
      if (isComment(line) || isEmpty(line)) {
        writeString(line);
      }
      else {
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
    }
    else if (localizedValue.equals(value)) {
      writeProperty(key, value, true);
    }
    else {
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