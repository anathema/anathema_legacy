package net.sf.anathema.character.lunar.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;

import java.util.ArrayList;
import java.util.List;

public class GiftContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public GiftContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Lunar.Gifts";
  }

  @Override
  public List<String> getPrintEntries() {
    return getMutationsToPrint();
  }

  private List<String> getMutationsToPrint() {
    List<String> printGifts = new ArrayList<>();
    for (IQualitySelection<IMutation> mutation : getModel().getMutationModel().getSelectedQualities()) {
      String text = getMutationText(mutation.getQuality());
      printGifts.add(text);
    }
    return printGifts;
  }

  private String getMutationText(Identified mutation) {
    return getString("Mutations.Mutation." + mutation.getId());
  }

  private IBeastformModel getModel() {
    return (IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
  }
}
