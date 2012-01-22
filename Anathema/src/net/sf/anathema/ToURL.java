package net.sf.anathema;

import com.google.common.base.Function;

import javax.annotation.Nullable;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ToURL implements Function<File, URL> {

  public URL apply(@Nullable File input) {
    try {
      return input.toURI().toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException("Could not load all files.", e);
    } catch (NullPointerException e) {
      throw new RuntimeException("Could not load all files.", e);
    }
  }
}