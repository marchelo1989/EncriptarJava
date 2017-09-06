/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encriptar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import Encriptar.MetodoBase64E;

/**
 *
 * @author march
 * Encriptar en MD5,SHA1,RSA,AES
 * Desencripta en RSA,AES
 */
public class Encriptar {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        // Encriptar en MD5
        MD5 md5 = new MD5();
        System.out.println("Encriptacion en MD5 de ABCabc123: '"+md5.encriptaEnMD5("ABCabc123")+"'");
        // Encriptar en SHA1
        SHA1 sha1 = new SHA1();
        System.out.println("Encriptacion en SHA1 de ABCabc123: '"+sha1.cript("ABCabc123")+"'");
        // Encriptar en MD5 y SHA
        System.out.println("Encriptacion en MD5 de ABCabc123: '"+MD5SHA1.md5("ABCabc123"));
        System.out.println("Encriptacion en SHA1 de ABCabc123: '"+MD5SHA1.sha1("ABCabc123"));
        //Encriptar y Desencripta BASE64
        MetodoBase64E mbe=new MetodoBase64E();
        System.out.println(mbe.cifrarBase64("ABCabc123"));
        System.out.println(mbe.descifrarBase64(mbe.cifrarBase64("ABCabc123")));
        // Encriptar en RSA
        if(args.length != 1) {
            System.out.println("Sintaxis: java RSA [tamaño de los primos]");
            System.out.println("por ejemplo: java RSA 512");
            args = new String[1];
            args[0]="1024";
        }
        int tamPrimo = Integer.parseInt(args[0]);
        RSA rsa = new RSA(tamPrimo);

        System.out.println("Tam Clave: ["+ tamPrimo + "]n");

        System.out.println("p: [" + rsa.damep().toString(16).toUpperCase() + "]");
        System.out.println("q: [" + rsa.dameq().toString(16).toUpperCase() + "]n");

        System.out.println("Clave publica (n,e)");
        System.out.println("n: [" + rsa.damen().toString(16).toUpperCase() + "]");
        System.out.println("e: [" + rsa.damee().toString(16).toUpperCase() + "]n");

        System.out.println("Clave publica (n,d)");
        System.out.println("n: [" + rsa.damen().toString(16).toUpperCase() + "]");
        System.out.println("d: [" + rsa.damed().toString(16).toUpperCase() + "]n");

        System.out.println("Texto a encriptar: ");
        String textoPlano = ( new BufferedReader(new
                        InputStreamReader(System.in))).readLine();
        
        BigInteger[] textoCifrado = rsa.encripta(textoPlano);
        
        System.out.println("Texto encriptado: [");
        for(int i=0; i<textoCifrado.length; i++) {
            System.out.print(textoCifrado[i].toString(16).toUpperCase());
            if(i != textoCifrado.length-1)
                System.out.println("");
        }
        System.out.println("]");
        
        String recuperarTextoPlano = rsa.desencripta(textoCifrado);
        System.out.println("Texto desencritado: ["+ recuperarTextoPlano +"]");
//        Para verlo en ventana
//        JFrame.setDefaultLookAndFeelDecorated(true);
//            ventanaRSA ventana = new ventanaRSA();
//            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//      Encriptar en AES
        // Generamos una clave de 128 bits adecuada para AES
      int bits = 128;
      String texto = "Este es el texto que queremos encriptar";
      AES aes = new AES();
      aes.calcular(texto, bits);
      
    
    }
}

