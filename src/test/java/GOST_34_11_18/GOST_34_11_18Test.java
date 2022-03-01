package GOST_34_11_18;

import BigIntegerForGost.BigIntegerForGost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public final class GOST_34_11_18Test{

    @ParameterizedTest(name = "[HASH_512_GOST_34_11_18(mes)]")
    @CsvFileSource(resources = "test_GOST_34_11_18_512_Res.csv",numLinesToSkip = 1)
    void testGOST_34_11_18_512(String mes, String HASH_res) {

        Assertions.assertArrayEquals(GOST_34_11_18.HASH_512_GOST_34_11_18(new BigIntegerForGost(mes,16).toByteArray(mes.length()/2)),
                new BigIntegerForGost(HASH_res,16).toByteArray(64));
    }
    @ParameterizedTest(name = "[HASH_256_GOST_34_11_18(mes)]")
    @CsvFileSource(resources = "test_GOST_34_11_18_256_Res.csv",numLinesToSkip = 1)
    void testGOST_34_11_18_256(String mes, String HASH_res) {
        Assertions.assertArrayEquals(GOST_34_11_18.HASH_256_GOST_34_11_18(new BigIntegerForGost(mes,16).toByteArray(mes.length()/2)),
                new BigIntegerForGost(HASH_res,16).toByteArray(32));
    }

    @ParameterizedTest(name = "[S(a)]")
    @CsvFileSource(resources = "test_S_Res.csv",numLinesToSkip = 1)
    void S(String a, String res) {
        Assertions.assertArrayEquals(GOST_34_11_18.S(new BigIntegerForGost(a,16).toByteArray(64)),
                new BigIntegerForGost(res,16).toByteArray(64));
    }

    @ParameterizedTest(name = "[L(a)]")
    @CsvFileSource(resources = "test_L_Res.csv",numLinesToSkip = 1)
    void L(String a, String res) {
        Assertions.assertArrayEquals(GOST_34_11_18.L(new BigIntegerForGost(a,16).toByteArray(64)),
                new BigIntegerForGost(res,16).toByteArray(64));
    }

    @ParameterizedTest(name = "[LPSX[k](a)]")
    @CsvFileSource(resources = "test_LPSX_Res.csv",numLinesToSkip = 1)
    void LPSX(String k,String m, String res) {
        Assertions.assertArrayEquals(GOST_34_11_18.LPSX(new BigIntegerForGost(k,16).toByteArray(64),new BigIntegerForGost(m,16).toByteArray(64)),
                new BigIntegerForGost(res,16).toByteArray(64));
    }

    @ParameterizedTest(name = "[P(a)]")
    @CsvFileSource(resources = "test_P_Res.csv",numLinesToSkip = 1)
    void P(String a, String res) {
        Assertions.assertArrayEquals(GOST_34_11_18.P(new BigIntegerForGost(a,16).toByteArray(64)),
                new BigIntegerForGost(res,16).toByteArray(64));
    }

    @ParameterizedTest(name = "[g_N(h,m)]")
    @CsvFileSource(resources = "test_g_N_Res.csv",numLinesToSkip = 1)
    void g_N(String N, String h,String m, String res){
        Assertions.assertArrayEquals(GOST_34_11_18.g_N(
                new BigIntegerForGost(N,16).toByteArray(64),
                        new BigIntegerForGost(h,16).toByteArray(64),
                        new BigIntegerForGost(m,16).toByteArray(64)),
                new BigIntegerForGost(res,16).toByteArray(64));
    }

    @ParameterizedTest(name = "[X[K](m)]")
    @CsvFileSource(resources = "test_X_Res.csv",numLinesToSkip = 1)
    void X_xor_(String K, String m, String res) {
        Assertions.assertArrayEquals(GOST_34_11_18.xor(new BigIntegerForGost(K,16).toByteArray(64),new BigIntegerForGost(m,16).toByteArray(64)),
                new BigIntegerForGost(res,16).toByteArray(64));
    }

    @ParameterizedTest(name = "[E(K,m)]")
    @CsvFileSource(resources = "test_E_Res.csv",numLinesToSkip = 1)
    void E(String K, String m, String res) {
        Assertions.assertArrayEquals(GOST_34_11_18.E(new BigIntegerForGost(K,16).toByteArray(64),new BigIntegerForGost(m,16).toByteArray(64)),
                new BigIntegerForGost(res,16).toByteArray(64));

    }

}