package GOST_34_11_18;

import BigIntegerForGost.BigIntegerForGost;

public final class DATA_ofSubstitutions {

    //Нелинейное биективное преобразование множества двоичных векторов
    final static Byte[] param_substitution_PI=new Byte[]{
            (byte)0xFC, (byte)0xEE, (byte)0xDD, (byte)0x11, (byte)0xCF, (byte)0x6E, (byte)0x31, (byte)0x16, (byte)0xFB,
            (byte)0xC4, (byte)0xFA, (byte)0xDA, (byte)0x23, (byte)0xC5, (byte)0x04, (byte)0x4D,
            (byte)0xE9, (byte)0x77, (byte)0xF0, (byte)0xDB, (byte)0x93, (byte)0x2E, (byte)0x99, (byte)0xBA, (byte)0x17,
            (byte)0x36, (byte)0xF1, (byte)0xBB, (byte)0x14, (byte)0xCD, (byte)0x5F, (byte)0xC1,
            (byte)0xF9, (byte)0x18, (byte)0x65, (byte)0x5A, (byte)0xE2, (byte)0x5C, (byte)0xEF, (byte)0x21, (byte)0x81,
            (byte)0x1C, (byte)0x3C, (byte)0x42, (byte)0x8B, (byte)0x01, (byte)0x8E, (byte)0x4F,
            (byte)0x05, (byte)0x84, (byte)0x02, (byte)0xAE, (byte)0xE3, (byte)0x6A, (byte)0x8F, (byte)0xA0, (byte)0x06,
            (byte)0x0B, (byte)0xED, (byte)0x98, (byte)0x7F, (byte)0xD4, (byte)0xD3, (byte)0x1F,
            (byte)0xEB, (byte)0x34, (byte)0x2C, (byte)0x51, (byte)0xEA, (byte)0xC8, (byte)0x48, (byte)0xAB, (byte)0xF2,
            (byte)0x2A, (byte)0x68, (byte)0xA2, (byte)0xFD, (byte)0x3A, (byte)0xCE, (byte)0xCC,
            (byte)0xB5, (byte)0x70, (byte)0x0E, (byte)0x56, (byte)0x08, (byte)0x0C, (byte)0x76, (byte)0x12, (byte)0xBF,
            (byte)0x72, (byte)0x13, (byte)0x47, (byte)0x9C, (byte)0xB7, (byte)0x5D, (byte)0x87,
            (byte)0x15, (byte)0xA1, (byte)0x96, (byte)0x29, (byte)0x10, (byte)0x7B, (byte)0x9A, (byte)0xC7, (byte)0xF3,
            (byte)0x91, (byte)0x78, (byte)0x6F, (byte)0x9D, (byte)0x9E, (byte)0xB2, (byte)0xB1,
            (byte)0x32, (byte)0x75, (byte)0x19, (byte)0x3D, (byte)0xFF, (byte)0x35, (byte)0x8A, (byte)0x7E, (byte)0x6D,
            (byte)0x54, (byte)0xC6, (byte)0x80, (byte)0xC3, (byte)0xBD, (byte)0x0D, (byte)0x57,
            (byte)0xDF, (byte)0xF5, (byte)0x24, (byte)0xA9, (byte)0x3E, (byte)0xA8, (byte)0x43, (byte)0xC9, (byte)0xD7,
            (byte)0x79, (byte)0xD6, (byte)0xF6, (byte)0x7C, (byte)0x22, (byte)0xB9, (byte)0x03,
            (byte)0xE0, (byte)0x0F, (byte)0xEC, (byte)0xDE, (byte)0x7A, (byte)0x94, (byte)0xB0, (byte)0xBC, (byte)0xDC,
            (byte)0xE8, (byte)0x28, (byte)0x50, (byte)0x4E, (byte)0x33, (byte)0x0A, (byte)0x4A,
            (byte)0xA7, (byte)0x97, (byte)0x60, (byte)0x73, (byte)0x1E, (byte)0x00, (byte)0x62, (byte)0x44, (byte)0x1A,
            (byte)0xB8, (byte)0x38, (byte)0x82, (byte)0x64, (byte)0x9F, (byte)0x26, (byte)0x41,
            (byte)0xAD, (byte)0x45, (byte)0x46, (byte)0x92, (byte)0x27, (byte)0x5E, (byte)0x55, (byte)0x2F, (byte)0x8C,
            (byte)0xA3, (byte)0xA5, (byte)0x7D, (byte)0x69, (byte)0xD5, (byte)0x95, (byte)0x3B,
            (byte)0x07, (byte)0x58, (byte)0xB3, (byte)0x40, (byte)0x86, (byte)0xAC, (byte)0x1D, (byte)0xF7, (byte)0x30,
            (byte)0x37, (byte)0x6B, (byte)0xE4, (byte)0x88, (byte)0xD9, (byte)0xE7, (byte)0x89,
            (byte)0xE1, (byte)0x1B, (byte)0x83, (byte)0x49, (byte)0x4C, (byte)0x3F, (byte)0xF8, (byte)0xFE, (byte)0x8D,
            (byte)0x53, (byte)0xAA, (byte)0x90, (byte)0xCA, (byte)0xD8, (byte)0x85, (byte)0x61,
            (byte)0x20, (byte)0x71, (byte)0x67, (byte)0xA4, (byte)0x2D, (byte)0x2B, (byte)0x09, (byte)0x5B, (byte)0xCB,
            (byte)0x9B, (byte)0x25, (byte)0xD0, (byte)0xBE, (byte)0xE5, (byte)0x6C, (byte)0x52,
            (byte)0x59, (byte)0xA6, (byte)0x74, (byte)0xD2, (byte)0xE6, (byte)0xF4, (byte)0xB4, (byte)0xC0, (byte)0xD1,
            (byte)0x66, (byte)0xAF, (byte)0xC2, (byte)0x39, (byte)0x4B, (byte)0x63, (byte)0xB6
    };
    // Перестановка байт
     final static int[] param_substitution_Tau=new int[]{
            0,  8, 16, 24, 32, 40, 48, 56,
            1,  9, 17, 25, 33, 41, 49, 57,
            2, 10, 18, 26, 34, 42, 50, 58,
            3, 11, 19, 27, 35, 43, 51, 59,
            4, 12, 20, 28, 36, 44, 52, 60,
            5, 13, 21, 29, 37, 45, 53, 61,
            6, 14, 22, 30, 38, 46, 54, 62,
            7, 15, 23, 31, 39, 47, 55, 63
    };

    //Линейное преобразование множества двоичных векторов
     final static Long[] param_substitution_l=new Long[]{
            0x8e20faa72ba0b470L, 0x47107ddd9b505a38L, 0xad08b0e0c3282d1cL, 0xd8045870ef14980eL,
            0x6c022c38f90a4c07L, 0x3601161cf205268dL, 0x1b8e0b0e798c13c8L, 0x83478b07b2468764L,
            0xa011d380818e8f40L, 0x5086e740ce47c920L, 0x2843fd2067adea10L, 0x14aff010bdd87508L,
            0x0ad97808d06cb404L, 0x05e23c0468365a02L, 0x8c711e02341b2d01L, 0x46b60f011a83988eL,
            0x90dab52a387ae76fL, 0x486dd4151c3dfdb9L, 0x24b86a840e90f0d2L, 0x125c354207487869L,
            0x092e94218d243cbaL, 0x8a174a9ec8121e5dL, 0x4585254f64090fa0L, 0xaccc9ca9328a8950L,
            0x9d4df05d5f661451L, 0xc0a878a0a1330aa6L, 0x60543c50de970553L, 0x302a1e286fc58ca7L,
            0x18150f14b9ec46ddL, 0x0c84890ad27623e0L, 0x0642ca05693b9f70L, 0x0321658cba93c138L,
            0x86275df09ce8aaa8L, 0x439da0784e745554L, 0xafc0503c273aa42aL, 0xd960281e9d1d5215L,
            0xe230140fc0802984L, 0x71180a8960409a42L, 0xb60c05ca30204d21L, 0x5b068c651810a89eL,
            0x456c34887a3805b9L, 0xac361a443d1c8cd2L, 0x561b0d22900e4669L, 0x2b838811480723baL,
            0x9bcf4486248d9f5dL, 0xc3e9224312c8c1a0L, 0xeffa11af0964ee50L, 0xf97d86d98a327728L,
            0xe4fa2054a80b329cL, 0x727d102a548b194eL, 0x39b008152acb8227L, 0x9258048415eb419dL,
            0x492c024284fbaec0L, 0xaa16012142f35760L, 0x550b8e9e21f7a530L, 0xa48b474f9ef5dc18L,
            0x70a6a56e2440598eL, 0x3853dc371220a247L, 0x1ca76e95091051adL, 0x0edd37c48a08a6d8L,
            0x07e095624504536cL, 0x8d70c431ac02a736L, 0xc83862965601dd1bL, 0x641c314b2b8ee083L
    };
    // Итерационные константы
     final static BigIntegerForGost[] param_IterativeConstants_C=new BigIntegerForGost[]{
            new BigIntegerForGost("b1085bda1ecadae9ebcb2f81c0657c1f2f6a76432e45d016714e" +
                    "b88d7585c4fc4b7ce09192676901a2422a08a460d315057" +
                    "67436cc744d23dd806559f2a64507",16),
            new BigIntegerForGost("6fa3b58aa99d2f1a4fe39d460f70b5d7f3feea720a232b9861d5" +
                    "5e0f16b501319ab5176b12d699585cb561c2db0aa7ca55dd" +
                    "a21bd7cbcd56e679047021b19bb7",16),
            new BigIntegerForGost("f574dcac2bce2fc70a39fc286a3d843506f15e5f529c1f8bf2ea" +
                    "7514b1297b7bd3e20fe490359eb1c1c93a376062db09c2b6" +
                    "f443867adb31991e96f50aba0ab2",16),
            new BigIntegerForGost("ef1fdfb3e81566d2f948e1a05d71e4dd488e857e335c3c7d9d72" +
                    "1cad685e353fa9d72c82ed03d675d8b71333935203be345" +
                    "3eaa193e837f1220cbebc84e3d12e",16),
            new BigIntegerForGost("4bea6bacad4747999a3f410c6ca923637f151c1f1686104a359e" +
                    "35d7800fffbdbfcd1747253af5a3dfff00b723271a167a56" +
                    "a27ea9ea63f5601758fd7c6cfe57",16),
            new BigIntegerForGost("ae4faeae1d3ad3d96fa4c33b7a3039c02d66c4f95142a46c187f" +
                    "9ab49af08ec6cffaa6b71c9ab7b40af21f66c2bec6b6bf71c" +
                    "57236904f35fa68407a46647d6e",16),
            new BigIntegerForGost("f4c70e16eeaac5ec51ac86febf240954399ec6c7e6bf87c9d347" +
                    "3e33197a93c90992abc52d822c3706476983284a050435174" +
                    "54ca23c4af38886564d3a14d493",16),
            new BigIntegerForGost("9b1f5b424d93c9a703e7aa020c6e41414eb7f8719c36de1e89b4" +
                    "443b4ddbc49af4892bcb929b069069d18d2bd1a5c42f36acc" +
                    "2355951a8d9a47f0dd4bf02e71e",16),
            new BigIntegerForGost("378f5a541631229b944c9ad8ec165fde3a7d3a1b258942243cd95" +
                    "5b7e00d0984800a440bdbb2ceb17b2b8a9aa6079c540e38d" +
                    "c92cb1f2a607261445183235adb",16),
            new BigIntegerForGost("abbedea680056f52382ae548b2e4f3f38941e71cff8a78db1fffe" +
                    "18a1b3361039fe76702af69334b7a1e6c303b7652f43698" +
                    "fad1153bb6c374b4c7fb98459ced",16),
            new BigIntegerForGost("7bcd9ed0efc889fb3002c6cd635afe94d8fa6bbbebab076120018" +
                    "021148466798a1d71efea48b9caefbacd1d7d476e98dea259" +
                    "4ac06fd85d6bcaa4cd81f32d1b",16),
            new BigIntegerForGost("378ee767f11631bad21380b00449b17acda43c32bcdf1d77f8201" +
                    "2d430219f9b5d80ef9d1891cc86e71da4aa88e12852faf417" +
                    "d5d9b21b9948bc924af11bd720",16)
    };
}
