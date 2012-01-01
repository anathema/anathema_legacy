package net.sf.anathema.character.lunar.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.model.FirstEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.model.gift.IGift;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiftContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public GiftContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Lunar.Gifts"; //$NON-NLS-1$
  }

  @Override
  public List<String> getPrintEntries() {
    if (getModel() instanceof FirstEditionBeastformModel) {
      return getGitsToPrint();
    }
    return getMutationsToPrint();
  }

  private List<String> getGitsToPrint() {
    List<String> printGifts = new ArrayList<String>();
    for (IQualitySelection<IGift> gift : getModel().getGiftModel().getSelectedQualities()) {
      String text = getGiftText(gift.getQuality());
      printGifts.add(text);
    }
    return printGifts;
  }

  private List<String> getMutationsToPrint() {
    List<String> printGifts = new ArrayList<String>();
    for (IQualitySelection<IMutation> mutation : getModel().getMutationModel().getSelectedQualities()) {
      String text = getMutationText(mutation.getQuality());
      printGifts.add(text);
    }
    return printGifts;
  }

  private String getMutationText(IIdentificate mutation) {
    return getString("Mutations.Mutation." + mutation.getId());
  }

  private String getGiftText(IIdentificate gift) {
    String text = getString("DeadlyBeastmanTransformation.Gift." + gift.getId()); //$NON-NLS-1$
    text = text.replaceFirst("\\(\\p{Alnum}+\\) ", "");
    return text;
  }

  private List<IQualitySelection<IGift>> getGifts() {
    return Arrays.asList(getModel().getGiftModel().getSelectedQualities());
  }

  private IBeastformModel getModel() {
    return (IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
  }
}
