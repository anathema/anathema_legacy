package net.sf.anathema.framework.model;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.random.RandomUtilities;
import net.sf.anathema.lib.resources.ResourceFile;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public class WelcomeMessage {
  private final IMessaging messaging;
  private final ResourceLoader resourceLoader;

  public WelcomeMessage(IMessaging messaging, ResourceLoader resourceLoader) {
    this.messaging = messaging;
    this.resourceLoader = resourceLoader;
  }

  public void show() {
    List<String> wordsOfTheWise = loadWordsOfTheWise();
    String welcomeMessage = chooseMessage(wordsOfTheWise);
    messaging.addMessage(new BasicMessage(welcomeMessage, MessageType.INFORMATION));
  }

  private String chooseMessage(List<String> wordsOfTheWise) {
    if (wordsOfTheWise.isEmpty()) {
      return "Welcome to Anathema!";
    }
    return RandomUtilities.choose(wordsOfTheWise);
  }

  private List<String> loadWordsOfTheWise() {
    Set<ResourceFile> resourceFiles = resourceLoader.getResourcesMatching(".*\\.wotw");
    List<String> wordsOfTheWise = Lists.newArrayList();
    for (ResourceFile file : resourceFiles) {
      InputStream stream = null;
      try {
        stream = file.getURL().openStream();
        List<String> strings = IOUtils.readLines(stream);
        wordsOfTheWise.addAll(strings);
      } catch (IOException e) {
        IOUtils.closeQuietly(stream);
      }
    }
    return wordsOfTheWise;
  }
}