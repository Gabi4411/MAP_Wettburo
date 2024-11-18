package RepoLayerInterface;

import  java.io.*;
import  java.util.*;
import java.util.concurrent.ConcurrentHashMap;



public class FileRepository  <T extends  Serializable> implements repo<T> {

    private final String filePath;
    private final Map<Integer, T> storage;

    public FileRepository(String filePath) {
        this.filePath = filePath;
        this.storage = new ConcurrentHashMap<>();
    }


    @Override
    public void create(T obj) {
        Integer id = generateId();
        storage.put(id, obj);
        saveToFile();
    }

    @Override
    public T get(Integer id) {
        return storage.get(id);
    }

    @Override
    public void update(T obj) {
        Integer id = findIdByObject(obj);
        if (id != null) {
            storage.put(id, obj);
            saveToFile();
        } else {
            throw new NoSuchElementException("Object not found for update.");
        }
    }

    @Override
    public void delete(Integer id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            saveToFile();
        } else {
            throw new NoSuchElementException("Object not found for deletion.");
        }
    }

    @Override
    public T find_by_ID(Integer id) {
        return storage.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }

    private Integer generateId() {
        return storage.isEmpty() ? 1 : Collections.max(storage.keySet()) + 1;
    }

    private Integer findIdByObject(T obj) {
        for (Map.Entry<Integer, T> entry : storage.entrySet()) {
            if (entry.getValue().equals(obj)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private synchronized void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(storage);
        } catch (IOException e) {
            throw new RuntimeException("Error saving to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private synchronized Map<Integer, T> loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ConcurrentHashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<Integer, T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error loading from file: " + e.getMessage());
        }
    }


}
