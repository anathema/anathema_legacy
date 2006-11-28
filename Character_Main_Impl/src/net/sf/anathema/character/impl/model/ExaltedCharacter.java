package net.sf.anathema.character.impl.model;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class ExaltedCharacter implements ICharacter {

  private CharacterStatistics statistics;
  private final ICharacterDescription description = new CharacterDescription();
  private final CharacterChangeManagement management = new CharacterChangeManagement();

  public ExaltedCharacter() {
    for (ITextualDescription currentDescription : description.getAllDescriptions()) {
      currentDescription.addTextChangedListener(management.getDescriptionChangeListener());
    }
  }

  public ICharacterStatistics getStatistics() {
    return statistics;
  }

  public ICharacterDescription getDescription() {
    return description;
  }

  public ICharacterStatistics createCharacterStatistics(
      ICharacterTemplate template,
      ICharacterGenerics generics,
      IExaltedRuleSet rules) throws SpellException {
    Ensure.ensureNull("Character Statistics can only be created once per character.", statistics); //$NON-NLS-1$
    if (template instanceof IUnsupportedTemplate) {
      throw new IllegalArgumentException("The template " + template + " is not yet supported."); //$NON-NLS-1$//$NON-NLS-2$
    }
    Ensure.ensureArgumentNotNull("Template must not be null.", template); //$NON-NLS-1$
    Ensure.ensureArgumentNotNull("Generics must not be null.", generics); //$NON-NLS-1$
    Ensure.ensureArgumentNotNull("RuleSet must not be null.", rules); //$NON-NLS-1$
    this.statistics = new CharacterStatistics(template, generics, rules);
    for (IGlobalAdditionalTemplate globalTemplate : generics.getGlobalAdditionalTemplateRegistry().getAll()) {
      if (globalTemplate.supportsEdition(rules.getEdition())) {
        addAdditionalModels(generics, new IAdditionalTemplate[] { globalTemplate });
      }
    }
    addAdditionalModels(generics, template.getAdditionalTemplates());
    addCompulsiveCharms(template);
    statistics.getCharacterContext()
        .getCharacterListening()
        .addChangeListener(management.getStatisticsChangeListener());
    return statistics;
  }

  private void addCompulsiveCharms(ICharacterTemplate template) {
    String[] compulsiveCharms = template.getAdditionalRules().getCompulsiveCharmIDs();
    for (String charmId : compulsiveCharms) {
      ICharmConfiguration charmConfiguration = statistics.getCharms();
      ICharm charm = charmConfiguration.getCharmById(charmId);
      charmConfiguration.getGroup(charm).learnCharm(charm, false);
    }
  }

  private void addAdditionalModels(ICharacterGenerics generics, IAdditionalTemplate... additionalTemplates) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = generics.getAdditionalModelFactoryRegistry();
    for (IAdditionalTemplate additionalTemplate : additionalTemplates) {
      IAdditionalModelFactory factory = additionalModelFactoryRegistry.get(additionalTemplate.getId());
      statistics.getExtendedConfiguration().addAdditionalModel(factory, additionalTemplate);
    }
  }

  public boolean hasStatistics() {
    return statistics != null;
  }

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    description.getName().addTextChangedListener(adjuster);
  }

  public void addDirtyListener(IChangeListener changeListener) {
    management.addChangeListener(changeListener);
  }

  public boolean isDirty() {
    return management.isDirty();
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    management.removeChangeListener(changeListener);
  }

  public void setClean() {
    management.setClean();
  }
}