package RepoLayerInterface;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class FileRepository<T> implements repo<T> {
    private final String filePath;
    private final Class<T> clazz;

    public FileRepository(String filePath, Class<T> clazz) {
        this.filePath = filePath;
        this.clazz = clazz;
    }

    @Override
    public void create(T obj) {
        doInFile(data -> {
            Integer id = generateId(data);
            invokeSetId(obj, id); // Set the ID in the object.
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
        Map<Integer, T> data = readDataFromFile();
        function.accept(data);
        writeDataToFile(data);
    }

    private synchronized Map<Integer, T> readDataFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ConcurrentHashMap<>();
        }

        Map<Integer, T> data = new ConcurrentHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T obj = fromCSV(line);
                Integer id = invokeGetId(obj);
                data.put(id, obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private synchronized void writeDataToFile(Map<Integer, T> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T obj : data.values()) {
                writer.write(toCSV(obj));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving to file: " + e.getMessage());
        }
    }

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

    private T fromCSV(String csvLine) {
        try {
            return (T) clazz.getMethod("fromCSV", String.class).invoke(null, csvLine);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing from CSV: " + e.getMessage(), e);
        }
    }

    private String toCSV(T obj) {
        try {
            return (String) obj.getClass().getMethod("toCSV").invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing to CSV: " + e.getMessage(), e);
        }
    }

    private Integer invokeGetId(T obj) {
        try {
            return (Integer) obj.getClass().getMethod("getId").invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException("Error getting ID: " + e.getMessage(), e);
        }
    }

    private void invokeSetId(T obj, Integer id) {
        try {
            obj.getClass().getMethod("setId", Integer.class).invoke(obj, id);
        } catch (Exception e) {
            throw new RuntimeException("Error setting ID: " + e.getMessage(), e);
        }
    }
}
