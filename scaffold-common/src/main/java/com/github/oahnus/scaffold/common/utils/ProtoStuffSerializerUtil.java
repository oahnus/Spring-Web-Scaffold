package com.github.oahnus.scaffold.common.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by oahnus on 2017/2/25.
 */
public class ProtoStuffSerializerUtil {
    public static <T> byte[] serialize(T obj) {
        if (obj == null) {
            throw new RuntimeException("Cannot Serialize Null Object");
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] bytes;
        try {
            bytes = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException("序列化(" + obj.getClass() + ")对象(" + obj + ")发生异常!", e);
        } finally {
            buffer.clear();
        }
        return bytes;
    }

    public static <T> T deserialize(byte[] bytes, Class<T> targetClass) {
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
        }
        T instance;
        try {
            instance = targetClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("反序列化过程中依据类型创建对象失败!", e);
        }
        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(bytes, instance, schema);
        return instance;
    }

    public static <T> byte[] serializeList(List<T> objList) {
        if (objList == null || objList.isEmpty()) {
            throw new RuntimeException("Cannot Serialize Empty List!");
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(objList.get(0).getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] bytes;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
            bytes = bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Occur An Error When Serialize (" + objList + ")!", e);
        } finally {
            buffer.clear();
            try {
                if(bos!=null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    public static <T> List<T> deserializeList(byte[] bytes, Class<T> targetClass) {
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("反序列化对象发生异常,byte序列为空!");
        }

        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        List<T> result;
        try {
            result = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(bytes), schema);
        } catch (IOException e) {
            throw new RuntimeException("反序列化对象列表发生异常!",e);
        }
        return result;
    }

}
