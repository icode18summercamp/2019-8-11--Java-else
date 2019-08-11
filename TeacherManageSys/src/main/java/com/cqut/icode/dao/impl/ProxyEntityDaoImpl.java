package com.cqut.icode.dao.impl;

import com.cqut.icode.dao.EntityDao;
import com.cqut.icode.entities.Page;
import com.cqut.icode.entities.base.BaseEntity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * @author 谭强
 * @date 2019/6/12
 */
public class ProxyEntityDaoImpl implements EntityDao {
    private static Socket socket;
    private static ObjectOutputStream outputStream = null;
    private static ObjectInputStream inputStream = null;

    static {
        try {
            socket = new Socket("localhost", 10010);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initStream() {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BaseEntity getEntity(BaseEntity entity) {
        try {
            initStream();
            outputStream.writeUTF("getEntity");
            outputStream.writeObject(entity);
            outputStream.flush();

            return (BaseEntity) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public BaseEntity getEntityById(Long id, Class entityClass) {
        try {
            initStream();
            outputStream.writeUTF("getEntityById");
            outputStream.writeLong(id);
            outputStream.writeObject(entityClass);
            outputStream.flush();

            return (BaseEntity) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List listEntitiesByEntity(BaseEntity entity) {
        try {
            initStream();
            outputStream.writeUTF("listEntitiesByEntity");
            outputStream.writeObject(entity);
            outputStream.flush();

            return (List) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List listEntitiesById(Long id, Page page, Class entityClass) {
        try {
            initStream();
            outputStream.writeUTF("listEntitiesById");
            outputStream.writeLong(id);
            outputStream.writeObject(page);
            outputStream.writeObject(entityClass);
            outputStream.flush();

            return (List) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List listEntities(Page page, Class entityClass) {
        try {
            initStream();
            outputStream.writeUTF("listEntities");
            outputStream.writeObject(page);
            outputStream.writeObject(entityClass);
            outputStream.flush();

            List result = (List) inputStream.readObject();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Long getTotalNumber(Class entityClass) {
        try {
            initStream();
            outputStream.writeUTF("getTotalNumber");
            outputStream.writeObject(entityClass);
            outputStream.flush();

            Long result = (Long) inputStream.readObject();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean saveEntity(BaseEntity entity) {
        try {
            initStream();
            outputStream.writeUTF("saveEntity");
            outputStream.writeObject(entity);
            outputStream.flush();

            System.out.println("ffffffffffff");
            Boolean result = (Boolean) inputStream.readObject();
            System.out.println("ffffffffffff");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateEntity(BaseEntity entity) {
        try {
            initStream();
            outputStream.writeUTF("updateEntity");
            outputStream.writeObject(entity);
            outputStream.flush();


            System.out.println("ffffffffffff");
            Boolean result = (Boolean) inputStream.readObject();
            System.out.println("ffffffffffff");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeEntities(List ids, Class aClass) {
        try {
            initStream();
            outputStream.writeUTF("removeEntities");
            outputStream.writeObject(ids);
            outputStream.writeObject(aClass);
            outputStream.flush();


            System.out.println("ffffffffffff");
            Boolean result = (Boolean) inputStream.readObject();
            System.out.println("ffffffffffff");
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
