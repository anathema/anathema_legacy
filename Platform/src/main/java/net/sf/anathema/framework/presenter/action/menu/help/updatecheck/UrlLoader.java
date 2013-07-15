package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlLoader {

  private final String urlString;

  public UrlLoader(String url) {
    this.urlString = url;
  }

  public String readAll() throws IOException {
    URL url = new URL(urlString);
    InputStream input = url.openStream();
    String response = IOUtils.toString(input);
    input.close();
    return response;
  }
}