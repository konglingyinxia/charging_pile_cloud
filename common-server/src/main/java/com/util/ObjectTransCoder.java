package com.util;

import java.io.*;

/**
 * @author 张子艺
 * @packge com.util.cache
 * @data 2019-01-10 13:47
 * @project Currency
 */
public class ObjectTransCoder {
    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv=null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        } finally {
            try {
                if(os!=null)os.close();
                if(bos!=null)bos.close();
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return rv;
    }

    public static Object deserialize(byte[] in) {
        Object rv=null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if(in != null) {
                bis=new ByteArrayInputStream(in);
                is=new ObjectInputStream(bis);
                rv=is.readObject();
                is.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)is.close();
                if(bis!=null)bis.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return rv;
    }
}
