package net.sf.anathema.character.generic.template.points;

import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IFavorableTraitCreationPoints extends ICloneable<IFavorableTraitCreationPoints> {

  public int getFavoredDotCount();

  public int getFavorableTraitCount();

  public int getDefaultDotCount();
}