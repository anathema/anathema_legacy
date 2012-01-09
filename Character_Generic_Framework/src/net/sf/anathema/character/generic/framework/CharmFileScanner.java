package net.sf.anathema.character.generic.framework;

import com.google.common.base.Predicate;
import com.google.common.collect.Multimap;
import net.sf.anathema.lib.logging.Logger;
import org.reflections.Configuration;
import org.reflections.ReflectionsException;
import org.reflections.scanners.Scanner;
import org.reflections.vfs.Vfs;

public class CharmFileScanner implements Scanner {

  public static final String EXTENSION = ".acd";
  private Logger logger = Logger.getLogger(CharmFileScanner.class);

  private Configuration configuration;
  private Multimap<String, String> store;
  private Predicate<String> resultFilter;


  public boolean acceptsInput(String file) {
    return file.endsWith(EXTENSION); //is a class file
  }

  public void scan(Vfs.File file) {
    try {
      if (file.getName().endsWith(EXTENSION)) {
        logger.info("Charm file found at " + file.getFullPath());
        store.put(EXTENSION, file.getName());
      }
    } catch (Exception e) {
      throw new ReflectionsException("could not create class file from " + file.getName(), e);
    }
  }

  public void setConfiguration(final Configuration configuration) {
    this.configuration = configuration;
  }

  public Multimap<String, String> getStore() {
    return store;
  }

  public void setStore(final Multimap<String, String> store) {
    this.store = store;
  }

  public Scanner filterResultsBy(Predicate<String> filter) {
    this.resultFilter = filter;
    return this;
  }

  public boolean acceptResult(final String fqn) {
    return fqn != null && resultFilter.apply(fqn);
  }

  @Override
  public boolean equals(Object o) {
    return this == o || o != null && getClass() == o.getClass();
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
