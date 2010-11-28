package net.sf.anathema.character.meritsflaws.presenter;

import net.sf.anathema.character.library.quality.presenter.IQualityVisitor;
import net.sf.anathema.character.meritsflaws.model.perk.MultiValuePerk;

public interface IPerkVisitor extends IQualityVisitor {

  public void visitMultiValuePerk(MultiValuePerk visitedPerk);
}