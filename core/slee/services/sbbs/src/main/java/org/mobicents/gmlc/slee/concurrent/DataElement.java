package org.mobicents.gmlc.slee.concurrent;

import java.util.HashMap;

/**
 * @author <a href="mailto:aferreiraguido@gmail.com"> Alejandro Ferreira Guido </a>
 */
public class DataElement {

  HashMap<String, Object> dataElements;

  public Object get(String key) {
    return dataElements.get(key);
  }

  public DataElement() {
    dataElements = new HashMap<>();
  }

  public void set(String key, Object value) {
    if (dataElements.containsKey(key))
      dataElements.replace(key, value);
    else
      dataElements.put(key, value);
  }
}
