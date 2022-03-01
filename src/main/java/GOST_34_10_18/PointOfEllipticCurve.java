package GOST_34_10_18;

import BigIntegerForGost.BigIntegerForGost;
import Exceptions.SettingElepticCurveParamException;

import java.util.Stack;

public class PointOfEllipticCurve implements Cloneable  {

    BigIntegerForGost x;
    BigIntegerForGost y;
    ElepticCurve E;

    /**
     * конструкторы для точек определенной элептической кривой
     */

    public PointOfEllipticCurve(BigIntegerForGost x, BigIntegerForGost y,ElepticCurve E) {
        this.x = x;
        this.y = y;
        this.E=E;
    }

    public PointOfEllipticCurve(String x, String y,int radix,ElepticCurve E) {
        this.x = new BigIntegerForGost(x,radix);
        this.y = new BigIntegerForGost(y,radix);
        this.E=E;
    }

    /**
     * нахождение точки кратности k
     */
    public PointOfEllipticCurve multiple(BigIntegerForGost k) throws SettingElepticCurveParamException{

        PointOfEllipticCurve result=this.clone();

        Stack<PointOfEllipticCurve> stackOfPoints=new Stack<>();
        stackOfPoints.push(result);

        BigIntegerForGost i=BigIntegerForGost.TWO.clone();

        for (; k.subtract(i).compareTo(BigIntegerForGost.ZERO)>0 ;i= i.add(i)) {
            result=result.addPoint(result);
            stackOfPoints.push(result);
        }

        BigIntegerForGost n=i.divide(BigIntegerForGost.TWO);
        i=i.divide(BigIntegerForGost.TWO);

        while (k.subtract(i).compareTo(BigIntegerForGost.ZERO)!=0){
            if(k.subtract(i.add(n)).compareTo(BigIntegerForGost.ZERO)>=0){
                i=i.add(n);
                n=n.divide(BigIntegerForGost.TWO);
                result=result.addPoint(stackOfPoints.pop());
                continue;
            }
            n=n.divide(BigIntegerForGost.TWO);
            stackOfPoints.pop();
        }
        return result;
    }

    /**
     * суума точек элептической кривой
     */
    public PointOfEllipticCurve addPoint(PointOfEllipticCurve point) throws SettingElepticCurveParamException {

        BigIntegerForGost lambda;

        PointOfEllipticCurve result=new PointOfEllipticCurve(new BigIntegerForGost("0"),
                new BigIntegerForGost("0"),this.E);

        if(this.x.equals(point.x)&&this.y.equals(point.y)) {

            lambda = (
                    (BigIntegerForGost.THREE.multiply(point.x.pow(2)).add(this.E.a_Coefficient))
                            .multiply(BigIntegerForGost.TWO.multiply(this.y).modInverse(this.E.p_EllipticCurveModule)))
                    .mod(this.E.p_EllipticCurveModule);

            result.x=(lambda.pow(2).subtract(BigIntegerForGost.TWO.multiply(this.x)))
                    .mod(this.E.p_EllipticCurveModule);

            result.y=(lambda.multiply(this.x.subtract(result.x)).subtract(this.y)).mod(this.E.p_EllipticCurveModule);
        }

        if(!this.x.equals(point.x)) {

            lambda = (
                    (point.y.subtract(this.y))
                           .multiply((point.x.subtract(this.x)).modInverse(this.E.p_EllipticCurveModule)))
                    .mod(this.E.p_EllipticCurveModule);

            result.x=(lambda.pow(2).subtract(this.x.add(point.x)))
                    .mod(this.E.p_EllipticCurveModule);

            result.y=(lambda.multiply(this.x.subtract(result.x)).subtract(this.y))
                    .mod(this.E.p_EllipticCurveModule);

        }
        if(point.x.equals(this.x) &&
                this.y.equals(point.y.negate().mod(this.E.p_EllipticCurveModule)))
            throw new SettingElepticCurveParamException("summIsNull");

        return result;
    }

    /**
     * возращает <em>true</em> если точка равна (0,0)
     */

    boolean IsNull(){
        return this.x.equals(BigIntegerForGost.ZERO) &&
                this.y.equals(BigIntegerForGost.ZERO);

    }

    /**
     * возращает <em>true</em> если точка кратности k равна O
     */
    boolean Is_pointMultiplicity_O(BigIntegerForGost k) throws SettingElepticCurveParamException {
        PointOfEllipticCurve e2 = this.multiple(k.subtract(BigIntegerForGost.ONE));
        return e2.x.equals(this.x) &&
                this.y.equals(
                        e2.y.negate().mod(this.E.p_EllipticCurveModule));
    }


    /**
     * возращает <em>true</em> если точка равна переданному параметру
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfEllipticCurve that = (PointOfEllipticCurve) o;
        return x.equals(that.x) && y.equals(that.y) && E.equals(that.E);
    }

    /**
     * возращает созданный элемент равный this
     */
    @Override
    protected PointOfEllipticCurve clone(){
        return new PointOfEllipticCurve(this.x.clone(),this.y.clone(),this.E.clone());
    }

}

