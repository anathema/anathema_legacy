package net.sf.anathema.character.linguistics.model;

import java.util.Arrays;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class LinguisticsModel extends AbstractRemovableEntryModel<IIdentificate> implements ILinguisticsModel {

  private final IIdentificate[] languages = new IIdentificate[] { new Identificate("HighRealm"), //$NON-NLS-1$
      new Identificate("LowRealm") }; //$NON-NLS-1$

  private IIdentificate selection;

  private final ICharacterModelContext context;

  public LinguisticsModel(ICharacterModelContext context) {
    this.context = context;
  }

  public IIdentificate[] getPredefinedLanguages() {
    return languages;
  }

  @Override
  protected IIdentificate createEntry() {
    IIdentificate selectionCopy = selection;
    this.selection = null;
    fireEntryChanged();
    return selectionCopy;
  }

  @Override
  public boolean isEntryAllowed() {
    return selection != null && !getEntries().contains(selection);
  }

  public boolean isPredefinedLanguage(Object object) {
    return ArrayUtilities.contains(languages, object);
  }

  public void selectBarbarianLanguage(String customName) {
    Ensure.ensureTrue("Name must not be null or empty", !StringUtilities.isNullOrTrimEmpty(customName)); //$NON-NLS-1$
    selectLanguage(new Identificate(customName));
  }

  public void selectLanguage(final IIdentificate language) {
    Ensure.ensureNotNull(language);
    IIdentificate foundLanguage = new Predicate<IIdentificate>() {
      public boolean evaluate(IIdentificate selectedLanguage) {
        return ObjectUtilities.equals(language, selectedLanguage);
      }
    }.find(getEntries());
    if (foundLanguage != null) {
      return;
    }
    this.selection = language;
    fireEntryChanged();
  }

  public IIdentificate getPredefinedLanguageById(final String id) {
    return new Predicate<IIdentificate>() {
      public boolean evaluate(IIdentificate definedLanuage) {
        return ObjectUtilities.equals(id, definedLanuage.getId());
      }
    }.find(Arrays.asList(languages));
  }
}