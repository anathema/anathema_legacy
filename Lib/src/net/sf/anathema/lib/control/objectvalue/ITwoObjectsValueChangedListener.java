package net.sf.anathema.lib.control.objectvalue;

public interface ITwoObjectsValueChangedListener<K,V> {

  public void valueChanged(K oldValue1, V oldValue2, K newValue1, V newValue2);
}