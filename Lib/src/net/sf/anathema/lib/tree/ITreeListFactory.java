package net.sf.anathema.lib.tree;

import java.util.List;

import net.sf.anathema.lib.util.IIdentificate;

public interface ITreeListFactory<I extends IIdentificate> {

  public I[] concertToArray(List<I> list);

  public IdentificateNode<I> createNode(I content);
}