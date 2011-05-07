package net.sf.anathema.character.meritsflaws.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.quality.model.AbstractQualityModel;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.meritsflaws.model.perk.IPerk;
import net.sf.anathema.character.meritsflaws.model.perk.PerkCategory;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;
import net.sf.anathema.character.meritsflaws.presenter.IMeritsFlawsModel;

public class MeritsFlawsModel extends AbstractQualityModel<IPerk> implements IMeritsFlawsModel {

  private final List<IPerk> allPerks = new ArrayList<IPerk>();
  private PerkCategory filterCategory;
  private PerkType filterType;

  public MeritsFlawsModel(ICharacterModelContext context) {
    super(context);
    for (IPerk perk : MeritsFlawsProvider.getAllPerks(context.getBasicCharacterContext().getRuleSet().getEdition()))
    {
      if (perk.isLegalFor(context.getBasicCharacterContext().getCharacterType())) {
        allPerks.add(perk);
      }
    }
  }

  public IBasicCharacterData getBasicCharacterData() {
    return getContext().getBasicCharacterContext();
  }

  public IQualitySelection<IPerk>[] getSelectedMerits() {
    return getPerkSelections(PerkType.Merit);
  }

  public IQualitySelection<IPerk>[] getSelectedFlaws() {
    return getPerkSelections(PerkType.Flaw);
  }

  @SuppressWarnings("unchecked")
  private IQualitySelection<IPerk>[] getPerkSelections(PerkType type) {
    List<IQualitySelection<IPerk>> selection = new ArrayList<IQualitySelection<IPerk>>();
    for (IQualitySelection<IPerk> perkSelection : getSelectedQualities()) {
      if (perkSelection.getQuality().getType() == type) {
        selection.add(perkSelection);
      }
    }
    return selection.toArray(new IQualitySelection[selection.size()]);
  }

  public IPerk[] getAvailableQualities() {
    ArrayList<IPerk> availablePerks = new ArrayList<IPerk>();
    for (IPerk perk : allPerks) {
      if (filterType != null && perk.getType() != filterType) {
        continue;
      }
      if (filterCategory != null && perk.getCategory() != filterCategory) {
        continue;
      }
      availablePerks.add(perk);
    }
    return availablePerks.toArray(new IPerk[availablePerks.size()]);
  }

  public void addCharacterChangeListener(ICharacterChangeListener changeListener) {
    getContext().getCharacterListening().addChangeListener(changeListener);
  }

  public IPerk getPerk(String perkId, PerkType type, PerkCategory category) {
    for (IPerk perk : allPerks) {
      if (perk.getType() == type && perk.getCategory() == category && perk.getId().equals(perkId)) {
        return perk;
      }
    }
    throw new IllegalArgumentException("No perk found for id \"" //$NON-NLS-1$
        + perkId
        + "\", type \"" //$NON-NLS-1$
        + type
        + "\", category \"" //$NON-NLS-1$
        + category
        + "\"."); //$NON-NLS-1$
  }

  public void setCurrentFilter(PerkType type, PerkCategory category) {
    this.filterType = type;
    this.filterCategory = category;
  }
}