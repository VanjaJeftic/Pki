package com.bsep.keystores;

import java.security.*;
import java.security.cert.Certificate;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import org.springframework.stereotype.Component;

@Component
public class KeyStoreWriter {


    //KeyStore je Java klasa za citanje specijalizovanih datoteka koje se koriste za cuvanje kljuceva
    //Tri tipa entiteta koji se obicno nalaze u ovakvim datotekama su:
    // - Sertifikati koji ukljucuju javni kljuc
    // - Privatni kljucevi
    // - Tajni kljucevi, koji se koriste u simetricnima siframa
    private KeyStore keyStore;

    public KeyStoreWriter(KeyStore keyStore) {
        super();
        this.keyStore = keyStore;
    }

    public KeyStoreWriter() {
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public void loadKeyStore(String fileName, char[] password) {
        try {

            if (fileName != null) {
                System.out.println("File name "+fileName + " password " + password);
                keyStore.load(new FileInputStream(Paths.get(ResourceUtils.getFile("classpath:") + "\\..\\..\\src\\main\\resources").toRealPath().toString() + "\\" + fileName), password);
            } else {
                keyStore.load(null, password);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveKeyStore(String fileName, char[] password) {
        try {
            keyStore.store(new FileOutputStream(Paths.get(ResourceUtils.getFile("classpath:")+"\\..\\..\\src\\main\\resources").toRealPath().toString()+"\\"+fileName), password);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String alias, PrivateKey privateKey, char[] password, Certificate[] certificate) {
        try {
            keyStore.setKeyEntry(alias, privateKey, password,  certificate);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

}
