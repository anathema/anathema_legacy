package net.sf.anathema.lib.control;

import java.util.EventListener;

public interface IChangeListener extends EventListener {

  void changeOccurred();
}