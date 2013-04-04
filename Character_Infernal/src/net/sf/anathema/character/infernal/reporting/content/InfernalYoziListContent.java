package net.sf.anathema.character.infernal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class InfernalYoziListContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public InfernalYoziListContent(IGenericCharacter character, Resources resources) {
    super(resources);
    this.character = character;
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printYozi = new ArrayList<>();
    for (ITraitType yozi : YoziType.values()) {
      IFavorableGenericTrait trait = character.getTraitCollection().getFavorableTrait(yozi);
      if (trait.isCasteOrFavored()) {
        printYozi.add(getResources().getString(trait.getType().getId()));
      }
    }
    return printYozi;
  }

  @Override
  public String getHeaderKey() {
    return "Infernal.Yozis";
  }
}