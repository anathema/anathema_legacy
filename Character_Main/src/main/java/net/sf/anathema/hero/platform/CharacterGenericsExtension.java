package net.sf.anathema.hero.platform;

import net.sf.anathema.character.framework.CharacterTemplateInitializer;
import net.sf.anathema.character.framework.data.IExtensibleDataSetProvider;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.extension.AnathemaExtension;
import net.sf.anathema.hero.framework.DataSetInitializer;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtension;
import net.sf.anathema.hero.framework.HeroEnvironmentImpl;
import net.sf.anathema.initialization.repository.DataFileProvider;

public class CharacterGenericsExtension implements HeroEnvironmentExtension, AnathemaExtension {

  private HeroEnvironment environment;

  @Override
  public HeroEnvironment getEnvironment() {
    return environment;
  }

  public void initialize(DataFileProvider dataFileProvider, ObjectFactory objectFactory, ResourceLoader loader) {
    DataSetInitializer dataSetInitializer = new DataSetInitializer(loader, objectFactory);
    IExtensibleDataSetProvider dataSetProvider = dataSetInitializer.initializeExtensibleResources();
    this.environment = new HeroEnvironmentImpl(dataFileProvider, objectFactory, dataSetProvider);
    new CharacterTemplateInitializer(environment).addCharacterTemplates();
  }
}