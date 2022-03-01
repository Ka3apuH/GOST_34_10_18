package GOST_34_10_18;

import BigIntegerForGost.BigIntegerForGost;
import Exceptions.SettingElepticCurveParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class GOST_34_10_18Test{


    @ParameterizedTest(name = "[generation_SIGN_Test]")
    @CsvFileSource(resources = "test_GOST_34_10_18_Res.csv",numLinesToSkip = 1)
    void generation_SIGN_Test(String p,String a,String b,String m,
                              String q,String x_P,String y_P,
                              String x_Q,String y_Q, String e,
                              String d,String k,String res) throws SettingElepticCurveParamException {

        //Arrays.stream(new BigIntegerForGost("111", 16).concatenation(new BigIntegerForGost("fff", 16)).toByteArray()).forEach(s->System.out.printf("%02x",s));

        ElepticCurve elepticCurve=new ElepticCurve(
                new BigIntegerForGost(a,16),
                new BigIntegerForGost(b,16),
                new BigIntegerForGost(p,16));

        PointOfEllipticCurve point_P=new PointOfEllipticCurve(
                new BigIntegerForGost(x_P,16),
                new BigIntegerForGost(y_P,16),
                elepticCurve);


        GOST_34_10_18 gost = new GOST_34_10_18(
                elepticCurve,
                new BigIntegerForGost(m,16),
                new BigIntegerForGost(q,16),
                point_P);


        Assertions.assertArrayEquals(
                gost.Generation_SIGN_for_TEST(new BigIntegerForGost(e,16),
                        new BigIntegerForGost(d,16),new BigIntegerForGost(k,16)),

                new BigIntegerForGost(res,16).toByteArray());

    }

    @ParameterizedTest(name = "[verification_SIGN_Test]")
    @CsvFileSource(resources = "test_GOST_34_10_18_Res.csv",numLinesToSkip = 1)
    void verification_SIGN_Test(String p,String a,String b,String m,
                                String q,String x_P,String y_P,
                                String x_Q,String y_Q, String e,
                                String d,String k,String SIGN) throws SettingElepticCurveParamException {

        //Arrays.stream(new BigIntegerForGost("111", 16).concatenation(new BigIntegerForGost("fff", 16)).toByteArray()).forEach(s->System.out.printf("%02x",s));

        ElepticCurve elepticCurve=new ElepticCurve(
                new BigIntegerForGost(a,16),
                new BigIntegerForGost(b,16),
                new BigIntegerForGost(p,16));

        PointOfEllipticCurve point_P=new PointOfEllipticCurve(
                new BigIntegerForGost(x_P,16),
                new BigIntegerForGost(y_P,16),
                elepticCurve);

        PointOfEllipticCurve point_Q=new PointOfEllipticCurve(
                new BigIntegerForGost(x_Q,16),
                new BigIntegerForGost(y_Q,16),
                elepticCurve);

        GOST_34_10_18 gost = new GOST_34_10_18(
                elepticCurve,
                new BigIntegerForGost(m,16),
                new BigIntegerForGost(q,16),
                point_P);

        Assertions.assertTrue(gost.Verification_SIGN_for_TEST(new BigIntegerForGost(e,16),
                        point_Q,new BigIntegerForGost(SIGN,16).toByteArray()));

    }

    @ParameterizedTest(name = "[multip_points(k)]")
    @CsvFileSource(resources = "test_multip_points_Res.csv",numLinesToSkip = 1)
    void multip_points_Test(String p,String a,String b,
                            String k,String x_P,String y_P,String x_res,
                            String y_res) throws SettingElepticCurveParamException {

        //System.out.println(p);
        //Arrays.stream(new BigIntegerForGost(p, 16).toByteArray()).forEach(s->System.out.printf("%02X",s));


        PointOfEllipticCurve point_P=new PointOfEllipticCurve(
                new BigIntegerForGost(x_P,16),
                new BigIntegerForGost(y_P,16),
                new ElepticCurve(
                        new BigIntegerForGost(a,16),
                        new BigIntegerForGost(b,16),
                        new BigIntegerForGost(p,16))
        );

        PointOfEllipticCurve point_res=new PointOfEllipticCurve(
                new BigIntegerForGost(x_res,16),
                new BigIntegerForGost(y_res,16),
                new ElepticCurve(
                        new BigIntegerForGost(a,16),
                        new BigIntegerForGost(b,16),
                        new BigIntegerForGost(p,16))
        );

        point_P=point_P.multiple(new BigIntegerForGost(k,16));

        Assertions.assertArrayEquals(point_P.x.toByteArray(),point_res.x.toByteArray());
        Assertions.assertArrayEquals(point_P.y.toByteArray(),point_res.y.toByteArray());
        //модуль элептической кривой,коэффиценты элептической кривой a,b,k,x_P,y_P,x_res,y_res

    }

    @ParameterizedTest(name = "[summOfPoints(p_2)]")
    @CsvFileSource(resources = "test_sum_points_Res.csv",numLinesToSkip = 1)
    void sum_points_Test(String p,String a,String b,String x_P_1,String y_P_1,
                         String x_P_2,String y_P_2,String x_res,
                            String y_res) throws SettingElepticCurveParamException {

        //System.out.println(p);
        //Arrays.stream(new BigIntegerForGost(p, 16).toByteArray()).forEach(s->System.out.printf("%02X",s));


        PointOfEllipticCurve point_P_1=new PointOfEllipticCurve(
                new BigIntegerForGost(x_P_1,10),
                new BigIntegerForGost(y_P_1,10),
                new ElepticCurve(
                        new BigIntegerForGost(a,10),
                        new BigIntegerForGost(b,10),
                        new BigIntegerForGost(p,10))
        );

        PointOfEllipticCurve point_P_2=new PointOfEllipticCurve(
                new BigIntegerForGost(x_P_2,10),
                new BigIntegerForGost(y_P_2,10),
                new ElepticCurve(
                        new BigIntegerForGost(a,10),
                        new BigIntegerForGost(b,10),
                        new BigIntegerForGost(p,10))
        );

        PointOfEllipticCurve point_res=new PointOfEllipticCurve(
                new BigIntegerForGost(x_res,10),
                new BigIntegerForGost(y_res,10),
                new ElepticCurve(
                        new BigIntegerForGost(a,10),
                        new BigIntegerForGost(b,10),
                        new BigIntegerForGost(p,10))
        );

        point_P_1= point_P_1.addPoint(point_P_2);

        Assertions.assertArrayEquals(point_P_1.x.toByteArray(),point_res.x.toByteArray());
        Assertions.assertArrayEquals(point_P_1.y.toByteArray(),point_res.y.toByteArray());

    }

}