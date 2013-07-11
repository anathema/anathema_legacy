package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.combos.model.CombosModel;
import net.sf.anathema.hero.magic.display.AbstractMagicLearnProperties;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class CombinedComboViewAndMagicProperties extends AbstractMagicLearnProperties implements ComboViewProperties {
  private final CombosModel comboConfiguration;
  private final ICharmInfoStringBuilder charmInfoStringProvider;

  CombinedComboViewAndMagicProperties(Resources resources, CombosModel comboConfiguration, MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    this.charmInfoStringProvider = new CharmInfoStringBuilder(getResources(), magicDescriptionProvider);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  public RelativePath getFinalizeButtonIcon() {
    return new CharacterUI().getFinalizeIconPath();
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
  public boolean isMagicSelectionAvailable(List list) {
    return !list.isEmpty() && comboConfiguration.isComboLegal((Charm) list.get(0));
  }

  @Override
  public boolean isRemoveAllowed(List list) {
    return !list.isEmpty();
  }

  @Override
  public AgnosticUIConfiguration<Identifier> getLearnedMagicRenderer() {
    return new CharmUIConfiguration(getResources(), charmInfoStringProvider);
  }

  @Override
  public AgnosticUIConfiguration getAvailableMagicRenderer() {
    return new CharmUIConfiguration(getResources(), charmInfoStringProvider);
  }

  @Override
  public LegalityCheck getLegalityCheck() {
    return new ComboLegality(comboConfiguration);
  }

  @Override
  public RelativePath getClearButtonIcon() {
    return new BasicUi().getClearIconPath();
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
  public RelativePath getCancelEditButtonIcon() {
    return new CharacterUI().getCancelComboEditIconPath();
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