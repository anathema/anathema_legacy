package net.sf.anathema.initialization.reflections;

import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import static net.sf.anathema.initialization.reflections.EncodingClasspathHelper.*;

public class IdeCompatibleConfiguration extends ConfigurationBuilder {

  private final String[] prefixes;
  private final ClassLoader[] classLoaders;

  public IdeCompatibleConfiguration(String[] prefixes, ClassLoader[] classLoaders) {
    this.prefixes = prefixes;
    this.classLoaders = classLoaders;
    addUrls();
    addFilters();
    addScanners();
  }

  private void addUrls() {
    for (String prefix : prefixes) {
      addUrls(forPackage(prefix, classLoaders));
    }
  }

  private void addFilters() {
    FilterBuilder prefixFilter = new FilterBuilder();
    for (String prefix : prefixes) {
      prefixFilter.include(FilterBuilder.prefix(prefix));
    }
    filterInputsBy(prefixFilter);
  }

  private void addScanners() {
    setScanners(new TypeAnnotationsScanner(), new ResourcesScanner());
  }
}