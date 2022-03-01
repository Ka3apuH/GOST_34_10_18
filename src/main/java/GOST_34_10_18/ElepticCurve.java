package GOST_34_10_18;

import BigIntegerForGost.BigIntegerForGost;

public class ElepticCurve implements Cloneable{

    public BigIntegerForGost a_Coefficient;
    public BigIntegerForGost b_Coefficient;
    public BigIntegerForGost p_EllipticCurveModule;
    public BigIntegerForGost J_Invariant_E;

    /**
     * конструктор элептической кривой(в него передаем параметры элептической кривой(a,b) и ее модуль)
     */
    public ElepticCurve(BigIntegerForGost a, BigIntegerForGost b,BigIntegerForGost p) {
        this.a_Coefficient = a;
        this.b_Coefficient = b;
        this.p_EllipticCurveModule = p;

        this.J_Invariant_E=(BigIntegerForGost.valueOf(1728*4).multiply(this.a_Coefficient.pow(3)).
                multiply(BigIntegerForGost.valueOf(4).multiply(a).
                        pow(3).add(BigIntegerForGost.valueOf(27).
                                multiply(a).pow(2)).modInverse(p_EllipticCurveModule))).mod(p_EllipticCurveModule);
    }

    /**
     * конструктор элептической кривой(в него передаем параметр элептической кривой(j(E)) и ее модуль)
     */

    public ElepticCurve(BigIntegerForGost j,BigIntegerForGost p) {
        J_Invariant_E = j;
        this.p_EllipticCurveModule = p;

        BigIntegerForGost k=(this.J_Invariant_E.multiply
                (BigIntegerForGost.valueOf(1728).subtract(this.J_Invariant_E).modInverse(this.p_EllipticCurveModule))
        ).mod(this.p_EllipticCurveModule);

        this.a_Coefficient =BigIntegerForGost.THREE.multiply(k).mod(this.p_EllipticCurveModule);

        this.b_Coefficient =BigIntegerForGost.TWO.multiply(k).mod(this.p_EllipticCurveModule);
    }

    /**
     * создание нового элемента равного this
     */

    @Override
    public ElepticCurve clone() {
        return new ElepticCurve(
                this.a_Coefficient.clone(),
                this.b_Coefficient.clone(),
                this.p_EllipticCurveModule.clone());
    }
}
