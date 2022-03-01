import BigIntegerForGost.BigIntegerForGost;
import Exceptions.SettingElepticCurveParamException;
import GOST_34_10_18.ElepticCurve;
import GOST_34_10_18.GOST_34_10_18;
import GOST_34_10_18.PointOfEllipticCurve;

import static org.apache.commons.lang3.ArrayUtils.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

public class Main {

    public static void help(){
        System.out.println("первым выводится файл с параметрами подписи(файл csv,c параметрами во второй строчке) ");
        System.out.println("\tвторым вводится флаг подписи [-s] или проверки подписи [-v]");
        System.out.println("\t\t затем имя файла если установлен флаг подписи (Например param.csv -s text.txt) ");
        System.out.println("\t\t либо 2 имени файла сам файл и его подпись если установлен флаг проверки подписи (Например param.csv -v text.txt SIGN.bin)");
    }


    public static void main(String[] args) throws SettingElepticCurveParamException, IOException {

        //String[] args ={"GOST_34_10_18_Res.csv","-v","t.txt","SIGN.bin"};

        if (args.length == 1 && args[0].equals("help") || args.length == 0) {
            help();
            return;
        }

        if (!((args.length==3&& args[1].equals("-s"))||
                (args.length==4&& args[1].equals("-v")))) {
            help();
            return;
        }

        if(args.length==3&&!(new File(args[2]).exists())) {
            System.out.println("файл с сообщением не сущустует");
            return;
        }

        if(args.length==4&&!(new File(args[2]).exists()&&new File(args[3]).exists())) {
            System.out.println("файл с сообщением или с SIGN не сущустует");
            return;
        }

        String[] atr_SIGN = Files.lines(Paths.get(new File(args[0]).getAbsolutePath())).skip(1).flatMap(s->Stream.of(s.split(","))).toArray(String[]::new);

        if(atr_SIGN.length<10) {
            System.out.println("параметры подписи не все...");
            return;
        }

//String p,String a,String b,String m,String q,String x_P,String y_P,String x_Q,String y_Q,String d
        ElepticCurve elepticCurve=new ElepticCurve(
                new BigIntegerForGost(atr_SIGN[1],16),
                new BigIntegerForGost(atr_SIGN[2],16),
                new BigIntegerForGost(atr_SIGN[0],16));

        PointOfEllipticCurve point_P=new PointOfEllipticCurve(
                new BigIntegerForGost(atr_SIGN[5],16),
                new BigIntegerForGost(atr_SIGN[6],16),
                elepticCurve);

        PointOfEllipticCurve point_Q=new PointOfEllipticCurve(
                new BigIntegerForGost(atr_SIGN[7],16),
                new BigIntegerForGost(atr_SIGN[8],16),
                elepticCurve);

        GOST_34_10_18 gost = new GOST_34_10_18(
                elepticCurve,
                new BigIntegerForGost(atr_SIGN[3],16),
                new BigIntegerForGost(atr_SIGN[4],16),
                point_P);

        if(args.length==3) {

            Date date1=new Date();

            Byte[] SIGN = gost.Generation_SIGN(
                    Files.lines(Paths.get(args[2]), StandardCharsets.ISO_8859_1).parallel().flatMap(s->Stream.of(toObject(s.getBytes()))),
                    new BigIntegerForGost(atr_SIGN[9], 16));

            Date date2=new Date();
            System.out.println("время подписания документа: "+(date2.getTime()-date1.getTime()));

            StringBuilder name = new StringBuilder();
            String[] mass_of_dir= args[2].split("/");
            for (int i = 0; i < mass_of_dir.length-1; i++) {
                name.append(mass_of_dir[i]);
                name.append("/");
            }

            String nameOfFile=name.append("SIGN.bin").toString();
            Files.createFile(Paths.get(nameOfFile));
            Files.write(Paths.get(nameOfFile),toPrimitive(SIGN));
            System.out.println("файл успешно подписан:"+nameOfFile);
        }

        if(args.length==4) {
            boolean ver_SIGN = gost.Verification_SIGN(
                    Arrays.stream(toObject(Files.readAllBytes(Paths.get(args[2])))),point_Q,
                    toObject(Files.readAllBytes(Paths.get(args[3]))));

            System.out.println("результат проверки подписи: "+ver_SIGN);
        }
    }


}
