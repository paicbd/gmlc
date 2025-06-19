package org.mobicents.gmlc.slee.utils;

public class TripleObjects<X, Y, Z> {
    public final X firstObject;
    public final Y secondObject;
    public final Z thirdObject;

    public TripleObjects(X firstObj, Y secondObj, Z thirdObj) {
        this.firstObject = firstObj;
        this.secondObject = secondObj;
        this.thirdObject = thirdObj;
    }
}
