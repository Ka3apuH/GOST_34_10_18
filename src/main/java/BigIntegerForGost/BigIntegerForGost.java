package BigIntegerForGost;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * класс оболочка для BigInteger, для дополнения его нужными для реализации
 * гостов методами
 */

public class BigIntegerForGost implements Cloneable, Comparable<BigIntegerForGost> {

    private final BigInteger ch;
    
    public static final BigIntegerForGost ZERO =valueOf(0);
    public static final BigIntegerForGost ONE =valueOf(1);
    public static final BigIntegerForGost TWO =valueOf(2);
    public static final BigIntegerForGost THREE =valueOf(3);
    public static final BigIntegerForGost TWO_POW_512=BigIntegerForGost.TWO.pow(512);

    /**
     * конструкторы класса(они в основнов такие же как и в классле BigIteger
     */

    public BigIntegerForGost(@NotNull byte[] val, int off, int len) {
        ch=new BigInteger(val, off, len);
    }

    public BigIntegerForGost(@NotNull Byte[] val, int off, int len) {
        ch=new BigInteger(ArrayUtils.toPrimitive(val), off, len);
    }

    public BigIntegerForGost(byte[] val) {
        ch=new BigInteger(val);
    }

    public BigIntegerForGost(Byte[] val) {
        ch=new BigInteger(ArrayUtils.toPrimitive(val));
    }

    public BigIntegerForGost(int signum, @NotNull byte[] magnitude, int off, int len) {
        ch=new BigInteger(signum, magnitude, off, len);
    }

    public BigIntegerForGost(int signum, byte[] magnitude) {
        ch=new BigInteger(signum, magnitude);
    }

    public BigIntegerForGost(String val, int radix) {
        ch=new BigInteger(val, radix);
    }

    private BigIntegerForGost(BigInteger chq) {
        ch=chq;
    }

    public BigIntegerForGost(@NotNull String val) {
        ch=new BigInteger(val);
    }

    public BigIntegerForGost(int numBits, Random rnd) {
        ch=new BigInteger(numBits, rnd);
    }

    public BigIntegerForGost(int bitLength, int certainty, Random rnd) {
        ch=new BigInteger(bitLength, certainty, rnd);
    }

    /**
     * методы класса(они в основнов такие же как и в классе BigIteger(некоторые из них немного переделанны
     */

    public boolean isProbablePrime(int certainty) {
        return !ch.isProbablePrime(certainty);
    }

    public BigIntegerForGost subtract(BigIntegerForGost radix) {

        return new BigIntegerForGost(ch.subtract(radix.ch));
    }

    public BigIntegerForGost negate(){
        return new BigIntegerForGost(ch.negate());
    }

public static BigIntegerForGost generateRundomNumber(BigIntegerForGost low_,BigIntegerForGost top_){

        BigIntegerForGost rund_num;

        do {
            rund_num= new BigIntegerForGost
                    (SecureRandom.getSeed(top_.getBytesLen()));
        }while (rund_num.compareTo(top_)>0 || rund_num.compareTo(low_)<0);

        return rund_num;
}

    public BigIntegerForGost multiply(BigIntegerForGost radix) {

        return new BigIntegerForGost(ch.multiply(radix.ch));
    }

    public BigIntegerForGost divide(BigIntegerForGost val) {
        return new BigIntegerForGost(ch.divide(val.ch));
    }


    public int compareTo(BigIntegerForGost radix) {
        return ch.compareTo(radix.ch);
    }

    public BigIntegerForGost clone(){
        return new BigIntegerForGost(this.ch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BigIntegerForGost that = (BigIntegerForGost) o;
        return Objects.equals(ch, that.ch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ch);
    }

    @NotNull
    public BigIntegerForGost xor(BigIntegerForGost val) {
        return  new BigIntegerForGost(this.ch.xor(val.ch));
    }

    @NotNull
    public static  BigIntegerForGost valueOf(long i){
        return new BigIntegerForGost(Long.toString(i));
    }

    @NotNull
    public BigIntegerForGost add(BigIntegerForGost val) {
        return  new BigIntegerForGost(this.ch.add(val.ch));
    }

    @NotNull
    public BigIntegerForGost pow(int exponent) {
        return  new BigIntegerForGost(this.ch.pow(exponent));
    }

    @NotNull
    public BigIntegerForGost mod(BigIntegerForGost m) {
        return  new BigIntegerForGost(this.ch.mod(m.ch));
    }

    @NotNull
    public Byte[] toByteArray(int size) {

        Byte[] ch = ArrayUtils.toObject(this.ch.toByteArray());
        Byte[] ch2= Arrays.stream(new Byte[size]).map(s->s=(byte) 0).toArray(Byte[]::new);

        if(Math.ceil((double)(this.ch.bitLength()+1)/8.0)==Math.ceil((double)(this.ch.bitLength())/8.0)) {

            System.arraycopy(ch, 0, ch2, Math.max((size - ch.length), 0), Math.min(ch.length, size));
        }
        else
            System.arraycopy(ch,1,ch2, Math.max((size- ch.length+1), 0),Math.min(ch.length-1,size));
        return ch2;
    }

    public Byte[] concatenation(BigIntegerForGost k){
        int max_el=Math.max(this.getBytesLen(),k.getBytesLen());

        Byte[] res =new Byte[2*max_el];

        System.arraycopy(this.toByteArray(max_el),0,res,0,max_el);
        System.arraycopy(k.toByteArray(max_el),0,res,max_el,max_el);

        return res;
    }

    public int getBytesLen(){
        return (int) Math.ceil((double) (this.ch.bitLength())/8.);
    }

    public BigIntegerForGost modInverse(BigIntegerForGost m)
    {
        return new BigIntegerForGost(this.ch.modInverse(m.ch));
    }

    public Byte[] toByteArray() {
        return this.toByteArray(this.getBytesLen());
    }

    public long longValue() {
        return this.ch.longValue();
    }

    public String toString(int radix) {
        return ch.toString(radix);
    }

    @Override
    public String toString() {
        return ch.toString();
    }
}
