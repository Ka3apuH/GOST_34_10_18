package GOST_34_11_18;

import BigIntegerForGost.BigIntegerForGost;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static GOST_34_11_18.DATA_ofSubstitutions.*;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class GOST_34_11_18 {

    public static Byte[] HASH_512_GOST_34_11_18(@NotNull Byte[] input) {
        return HASH_GOST_34_11_18(input,512);
    }

    public static Byte[] HASH_256_GOST_34_11_18(@NotNull Byte[] input) {
        return HASH_GOST_34_11_18(input,256);
    }

     static Byte[] HASH_GOST_34_11_18(Byte[] message,int mod) {
        //1 этап
        //шаг 1.1
        Byte[] h = mod==512 ? param_initializationVector_512:param_initializationVector_256;
        //шаг 1.2
        Byte[] N = stream(new Byte[64]).map(s -> s = (byte) 0).toArray(Byte[]::new);
        //шаг 1.3
        Byte[] sigma = stream(new Byte[64]).map(s -> s = (byte) 0).toArray(Byte[]::new);

        //2этап
        Byte[] m = new Byte[64];

        while (message.length >= 64) {//шаг 2.1
            //шаг 2.2
            m= Arrays.copyOfRange(message,message.length-64, message.length);
            //шаг 2.3
            h = g_N(N, h, m);
            //шаг 2.4
            N = new BigIntegerForGost(N).add(new BigIntegerForGost("512")).
                    mod(BigIntegerForGost.TWO_POW_512).toByteArray(64);
            //шаг 2.5

            sigma = (new BigIntegerForGost(sigma)).add(new BigIntegerForGost(m)).mod(BigIntegerForGost.TWO_POW_512).toByteArray(64);
            //шаг 2.6
            message=Arrays.copyOfRange(message,0,message.length - 64);

            //шаг 2.7
        }

        //3 этап
        //шаг 3.1
        m= stream(m).map(s->s=(byte) 0).toArray(Byte[]::new);

        m[64 - 1 - message.length] = 0b0000_0001;
        System.arraycopy(message, 0, m, 64 - message.length, message.length);
        //шаг 3.2
        h = g_N(N, h, m);
        //шаг 3.3
        N = (new BigIntegerForGost(N)).add(new BigIntegerForGost(Integer.toString(message.length*8),10)).mod(BigIntegerForGost.TWO_POW_512).toByteArray(64);
        //шаг 3.4
        sigma = (new BigIntegerForGost(sigma)).add(new BigIntegerForGost(m)).mod(BigIntegerForGost.TWO_POW_512).toByteArray(64);
        //шаг 3.5
        h = g_N(stream(new Byte[64]).map(s -> s = (byte) 0).toArray(Byte[]::new), h, N);
        //шаг 3.6

        return stream(g_N(stream(new Byte[64]).map(s -> s = (byte) 0).toArray(Byte[]::new),h,sigma)).
                limit(mod/8).toArray(Byte[]::new);
        //шаг 3.7
    }

    static Byte[] S(Byte[] ch){
        return  stream(ch).parallel().map((s)->param_substitution_PI[Byte.toUnsignedInt(s)]).toArray(Byte[]::new);
    }


    static Byte[] L(Byte[] ch) {

        byte[] resultOf_L =new byte[64];

        for (int i = 0; i < 8; i++) {
            long iter_vecktor=0L;
            for (int j = 0; j <64; j++){
                iter_vecktor^=(((int)ch[i*8+ j/8])&(1<<(7- j%8)))==0?0:param_substitution_l[j];
            }
            System.arraycopy(ByteBuffer.allocate(8).putLong(iter_vecktor).array(),0, resultOf_L, i *8,8);
        }

        return ArrayUtils.toObject(resultOf_L);
    }

    static Byte[] P(Byte[] ch) {
        Byte[] ch2=new Byte[ch.length];
        for (int i = 0; i <ch.length; i++)
            ch2[i]=ch[param_substitution_Tau[i]];
        return ch2;
    }

    static Byte[] g_N(Byte[] N, Byte[] h, Byte[] m) {
         return xor(E((LPSX(h, N)), m),h,m);
    }

    static Byte[] LPSX(Byte[] k, Byte[] m) {
        return L(P(S(xor(k, m))));
    }

    static Byte[] xor(Byte[]... k) {
        BigIntegerForGost res= BigIntegerForGost.ZERO;
         for (Byte[] i: k) {
             res=res.xor(new BigIntegerForGost(i));
         }
         return (res).toByteArray(64);
    }

    static Byte[] E(Byte[] K, Byte[] m) {
        Byte[]m_res=copyOf(m,m.length);
        Byte[] iterKey =copyOf(K,K.length);

        for (BigIntegerForGost const_C : param_IterativeConstants_C) {
             m_res = LPSX(iterKey, m_res);
             iterKey = LPSX(iterKey, const_C.toByteArray(K.length));
         }
        return xor(m_res, iterKey);
    }

    //Значения параметров

    //Инициализационные векторы
     final static   Byte[] param_initializationVector_512= stream(new Byte[64]).map(s -> s = (byte) 0).toArray(Byte[]::new);

     final static   Byte[] param_initializationVector_256= stream(new Byte[64]).map(s -> s = (byte) 0b0000_0001).toArray(Byte[]::new);

}
