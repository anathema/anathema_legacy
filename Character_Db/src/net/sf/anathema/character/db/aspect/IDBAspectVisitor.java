package net.sf.anathema.character.db.aspect;

import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;

public interface IDBAspectVisitor extends ICasteTypeVisitor {

  public void visitAir(DBAspect aspect);

  public void visisEarth(DBAspect aspect);

  public void visitFire(DBAspect aspect);

  public void visitWater(DBAspect aspect);

  public void visitWood(DBAspect aspect);
}