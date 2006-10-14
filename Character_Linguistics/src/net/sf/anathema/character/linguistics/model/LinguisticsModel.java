package net.sf.anathema.character.linguistics.model;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class LinguisticsModel extends AbstractRemovableEntryModel<IIdentificate> implements ILinguisticsModel {

  private final IIdentificate[] languages = new IIdentificate[] {
      new Identificate("HighRealm"),
      new Identificate("LowRealm") };

  private IIdentificate selection;

  public LinguisticsModel(ICharacterModelContext context) {

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
  protected boolean isEntryAllowed() {
    return selection != null && !getEntries().contains(selection);
  }

  public void barbarianLanguageSelected(String string) {
    this.selection = new Identificate(string);
    fireEntryChanged();
  }

  public boolean isPredefinedLanguage(Object object) {
    return ArrayUtilities.contains(languages, object);
  }

  public void languageSelected(IIdentificate identificate) {
    this.selection = identificate;
    fireEntryChanged();
  }
}