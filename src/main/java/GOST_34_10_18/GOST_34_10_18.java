package GOST_34_10_18;

import BigIntegerForGost.BigIntegerForGost;
import Exceptions.SettingElepticCurveParamException;
import GOST_34_11_18.GOST_34_11_18;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

public class GOST_34_10_18 {
    private final ElepticCurve E_curve;

    private final PointOfEllipticCurve parap_point_P;

    private final BigIntegerForGost param_OrdOfCyclicalGroup_m;
    private final BigIntegerForGost param_ordOfCyclicSubgroup_q;

    private  HASH HASH_Stribog;

public GOST_34_10_18(
        ElepticCurve eleptic_curve,
        BigIntegerForGost param_OrdOfCyclicalGroup,
        BigIntegerForGost param_ordOfCyclicSubgroup,
        PointOfEllipticCurve parap_point_P) throws SettingElepticCurveParamException
{
    this.param_OrdOfCyclicalGroup_m = param_OrdOfCyclicalGroup;
    this.param_ordOfCyclicSubgroup_q = param_ordOfCyclicSubgroup;
    this.parap_point_P = parap_point_P;
    this.E_curve=eleptic_curve;
    this.GOST_34_10_18_init();

}

    private void GOST_34_10_18_init() throws SettingElepticCurveParamException{
    // установка вытекающих параметров (HASH  функции и инварианта)
        //проверка инварианта соотвествие алгебре элептических кривых

        if((this.E_curve.J_Invariant_E.compareTo(BigIntegerForGost.ZERO)&
                this.E_curve.J_Invariant_E.compareTo(BigIntegerForGost.valueOf(1728)))==0)
            throw new SettingElepticCurveParamException("J(E)==0||J(E)==1728");
        //проверка — порядока группы  и порядока циклической подгруппы группы точек эллиптической кривой
        if(param_ordOfCyclicSubgroup_q.isProbablePrime(0x7fffffff))
            throw new SettingElepticCurveParamException("ordOfCyclicSubgroup-not prime number ");

        if(param_OrdOfCyclicalGroup_m.mod(param_ordOfCyclicSubgroup_q).compareTo(BigIntegerForGost.ZERO)!=0)
            throw new SettingElepticCurveParamException("Not exist n: m=nq");

        //проверка точки P эллиптической кривой Е
        if(parap_point_P.IsNull())
            throw new SettingElepticCurveParamException("P=(0,0) ");

        if(!parap_point_P.Is_pointMultiplicity_O(this.param_ordOfCyclicSubgroup_q))
            throw new SettingElepticCurveParamException("point_P-not correctly selected ");

        //установка (HASH  функции)
        if(this.param_ordOfCyclicSubgroup_q.compareTo(BigIntegerForGost.TWO.pow(254))>0&&
                this.param_ordOfCyclicSubgroup_q.compareTo(BigIntegerForGost.TWO.pow(256))<0) {
            this.HASH_Stribog = GOST_34_11_18::HASH_256_GOST_34_11_18;

            int B=31;
            for (int i = 1; i <B ; i++) {
                if(this.E_curve.p_EllipticCurveModule.pow(i).
                        mod(param_ordOfCyclicSubgroup_q).
                        equals(BigIntegerForGost.ONE))
                    throw new SettingElepticCurveParamException("EllipticCurveModule and ordOfCyclicSubgroup-not correctly selected ");
            }
        }
        else if(this.param_ordOfCyclicSubgroup_q.compareTo(BigIntegerForGost.TWO.clone().pow(508))>0&&
                this.param_ordOfCyclicSubgroup_q.compareTo(BigIntegerForGost.TWO.clone().pow(512))<0) {
            this.HASH_Stribog = GOST_34_11_18::HASH_512_GOST_34_11_18;
            int B=131;
            for (int i = 1; i <B ; i++) {
                if(this.E_curve.p_EllipticCurveModule.pow(i).
                        mod(param_ordOfCyclicSubgroup_q).
                        equals(BigIntegerForGost.ONE))
                    throw new SettingElepticCurveParamException("EllipticCurveModule and ordOfCyclicSubgroup-not correctly selected ");
            }
        }
        else throw new SettingElepticCurveParamException("ordOfCyclicSubgroup-not correctly selected ");

        if(param_OrdOfCyclicalGroup_m.equals(this.E_curve.p_EllipticCurveModule))
            throw new SettingElepticCurveParamException("OrdOfCyclicalGroup_m-not correctly selected ");
    }


    public Byte[] Generation_SIGN(Stream<Byte> massage_M, BigIntegerForGost param_SignKey_d) throws SettingElepticCurveParamException {
        //шаг 1
        Date date1=new Date();
        Byte[] hash_mass=this.HASH_Stribog.HASH_Func(massage_M.toArray(Byte[]::new));
        Date date2=new Date();
        System.out.println("время hash документа: "+(date2.getTime()-date1.getTime()));
        //шаг 2

        BigIntegerForGost e=new BigIntegerForGost(hash_mass).mod(this.param_ordOfCyclicSubgroup_q);
        if(e.compareTo(BigIntegerForGost.ZERO)==0)e=BigIntegerForGost.ZERO.clone();

        do {
        //шаг 3
            BigIntegerForGost k=BigIntegerForGost.generateRundomNumber(
                BigIntegerForGost.ZERO,this.param_ordOfCyclicSubgroup_q);

        //шаг 4
            BigIntegerForGost r = this.parap_point_P.multiple(k).x.mod(this.param_ordOfCyclicSubgroup_q);

            if (r.compareTo(BigIntegerForGost.ZERO)==0)continue;

            BigIntegerForGost s=(r.multiply(param_SignKey_d).add(k.multiply(e))).mod(param_ordOfCyclicSubgroup_q);

            if (s.compareTo(BigIntegerForGost.ZERO)==0)continue;

            return r.concatenation(s);
        }while (true);
    }

     Byte[] Generation_SIGN_for_TEST( BigIntegerForGost e,BigIntegerForGost param_SignKey_d,BigIntegerForGost k) throws SettingElepticCurveParamException {

            BigIntegerForGost r = this.parap_point_P.multiple(k).x.mod(this.param_ordOfCyclicSubgroup_q);

            BigIntegerForGost s=(r.multiply(param_SignKey_d).add(k.multiply(e))).mod(param_ordOfCyclicSubgroup_q);

            return r.concatenation(s);

    }

    public boolean Verification_SIGN(Stream<Byte> massage_M,PointOfEllipticCurve param_SignKey_Q,Byte[] SIGN) throws SettingElepticCurveParamException {

        //шаг 1
        BigIntegerForGost r=new BigIntegerForGost(Arrays.stream(SIGN).limit(SIGN.length/2).toArray(Byte[]::new));
        BigIntegerForGost s=new BigIntegerForGost(Arrays.stream(SIGN).skip(SIGN.length/2).toArray(Byte[]::new));

        if(r.compareTo(param_ordOfCyclicSubgroup_q)>=0||s.compareTo(param_ordOfCyclicSubgroup_q)>=0)
            return false;
        //шаг 2
        Byte[] hash_mass=this.HASH_Stribog.HASH_Func(massage_M.toArray(Byte[]::new));
        //шаг 3
        BigIntegerForGost e=new BigIntegerForGost(hash_mass).mod(this.param_ordOfCyclicSubgroup_q);
        if(e.compareTo(BigIntegerForGost.ZERO)==0)e=BigIntegerForGost.ZERO.clone();
        // шаг 4

        BigIntegerForGost v=e.modInverse(param_ordOfCyclicSubgroup_q);

        //шаг 5

        BigIntegerForGost z_1=s.multiply(v).mod(param_ordOfCyclicSubgroup_q);
        BigIntegerForGost z_2=r.negate().multiply(v).mod(param_ordOfCyclicSubgroup_q);

        //шаг 6

        PointOfEllipticCurve C_point= parap_point_P.multiple(z_1).addPoint(param_SignKey_Q.multiple(z_2));

        return C_point.x.mod(param_ordOfCyclicSubgroup_q).compareTo(r) == 0;
    }

    boolean Verification_SIGN_for_TEST(BigIntegerForGost e,PointOfEllipticCurve param_SignKey_Q,Byte[] SIGN) throws SettingElepticCurveParamException {

        //шаг 1
        BigIntegerForGost r=new BigIntegerForGost(Arrays.stream(SIGN).limit(SIGN.length/2).toArray(Byte[]::new));
        BigIntegerForGost s=new BigIntegerForGost(Arrays.stream(SIGN).skip(SIGN.length/2).toArray(Byte[]::new));

        if(r.compareTo(param_ordOfCyclicSubgroup_q)>=0||s.compareTo(param_ordOfCyclicSubgroup_q)>=0)
            return false;
        //шаг 2

        BigIntegerForGost v=e.modInverse(param_ordOfCyclicSubgroup_q);

        //шаг 5

        BigIntegerForGost z_1=s.multiply(v).mod(param_ordOfCyclicSubgroup_q);
        BigIntegerForGost z_2=r.negate().multiply(v).mod(param_ordOfCyclicSubgroup_q);

        //шаг 6

        PointOfEllipticCurve C_point= parap_point_P.multiple(z_1).addPoint(param_SignKey_Q.multiple(z_2));

        return C_point.x.mod(param_ordOfCyclicSubgroup_q).compareTo(r) == 0;
    }
}
