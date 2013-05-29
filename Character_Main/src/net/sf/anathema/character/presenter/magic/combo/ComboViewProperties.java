package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.view.magic.IComboViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public final class ComboViewProperties extends AbstractMagicLearnProperties implements IComboViewProperties {
  private final IComboConfiguration comboConfiguration;
  private final ICharmInfoStringBuilder charmInfoStringProvider;

  ComboViewProperties(Resources resources, IComboConfiguration comboConfiguration, MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    this.charmInfoStringProvider = new CharmInfoStringBuilder(getResources(), magicDescriptionProvider);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  public Icon getFinalizeButtonIcon() {
    return new CharacterUI().getFinalizeIcon();
  }

  @Override
  public String getAvailableComboCharmsLabel() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.AvailableLabel");
  }

  @Override
  public String getComboedCharmsLabel() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.SelectedLabel");
  }

  @Override
  public boolean isMagicSelectionAvailable(Object object) {
    return object != null && comboConfiguration.isComboLegal((ICharm) object);
  }

  @Override
  public boolean isRemoveButtonEnabled(ICharm charm) {
    return charm != null;
  }

  @Override
  public ListCellRenderer getLearnedMagicRenderer() {
    return new AlwaysLegalCharmRenderer(getResources(), charmInfoStringProvider);
  }

  @Override
  public ListCellRenderer getAvailableMagicRenderer() {
    return new ComboLegalityCharmRenderer(getResources(), charmInfoStringProvider, comboConfiguration);
  }

  @Override
  public Icon getClearButtonIcon() {
    return new BasicUi().getClearIcon();
  }

  @Override
  public String getFinalizeButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.FinalizeToolTip");
  }

  @Override
  public String getClearButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.ClearToolTip");
  }

  @Override
  public String getAddButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.AddToolTip");
  }

  @Override
  public String getRemoveButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.RemoveToolTip");
  }

  @Override
  public Icon getCancelEditButtonIcon() {
    return new CharacterUI().getCancelComboEditIcon();
  }

  @Override
  public String getFinalizeButtonEditToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.FinalizeEditToolTip");
  }

  @Override
  public String getCancelButtonEditToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.ClearEditToolTip");
  }
}