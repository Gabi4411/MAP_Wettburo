package RepoLayerInterface;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class FileRepository<T extends Serializable> implements repo<T> {
    private final String filePath;

    public FileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void create(T obj) {
        doInFile(data -> {
            Integer id = generateId(data);
            data.put(id, obj);
        });
    }

    @Override
    public T get(Integer id) {
        return readDataFromFile().get(id);
    }

    @Override
    public void update(T obj) {
        doInFile(data -> {
            Integer id = findIdByObject(obj, data);
            if (id != null) {
                data.put(id, obj);
            } else {
                throw new NoSuchElementException("Object not found for update.");
            }
        });
    }

    @Override
    public void delete(Integer id) {
        doInFile(data -> {
            if (data.containsKey(id)) {
                data.remove(id);
            } else {
                throw new NoSuchElementException("Object not found for deletion.");
            }
        });
    }

    @Override
    public T find_by_ID(Integer id) {
        return get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(readDataFromFile().values());
    }

    // Helper Methods

    private synchronized void doInFile(Consumer<Map<Integer, T>> function) {
        Map<Integer, T> data = readDataFromFile(); // Load data from file
        function.accept(data);                    // Apply the operation
        writeDataToFile(data);                    // Save data back to file
    }

    private synchronized Map<Integer, T> readDataFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ConcurrentHashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//            return (Map<Integer, T>) ois.readObject();
            Map<Integer, T> data = (Map<Integer, T>) ois.readObject();
            data.forEach((id, obj) -> System.out.println("Loaded: " + obj)); // Debug log
            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ConcurrentHashMap<>();
        }
    }

    private synchronized void writeDataToFile(Map<Integer, T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving to file: " + e.getMessage());
        }
    }

//    private synchronized void writeDataToFile(Map<Integer, T> newData) {
//        Map<Integer, T> existingData = new HashMap<>();
//
//        // Read existing data from the file if it exists
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
//            existingData = (Map<Integer, T>) ois.readObject();
//        } catch (FileNotFoundException e) {
//            // File doesn't exist yet, so we can safely skip
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error reading from file: " + e.getMessage());
//        }
//
//        // Merge the new data into the existing data
//        existingData.putAll(newData);
//
//        // Now write the updated map (existing + new data) back to the file
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
//            oos.writeObject(existingData);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error saving to file: " + e.getMessage());
//        }
//    }


    private Integer generateId(Map<Integer, T> data) {
        return data.isEmpty() ? 1 : Collections.max(data.keySet()) + 1;
    }

    private Integer findIdByObject(T obj, Map<Integer, T> data) {
        for (Map.Entry<Integer, T> entry : data.entrySet()) {
            if (entry.getValue().equals(obj)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
