package RepoLayerInterface;

import ModelLayer.HasId;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * File-based repository implementation for managing objects of type T.
 *
 * @param <T> the type of objects stored in the repository, extending HasId
 */
public class FileRepository<T extends HasId> implements repo<T> {
    private final String filePath;

    /**
     * Constructs a FileRepository with the specified file path.
     *
     * @param filePath the path to the file where data is stored
     */
    public FileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void create(T obj) {
        doInFile(data -> data.putIfAbsent(obj.getId(), obj));
    }

    @Override
    public T get(Integer id) {
        return readDataFromFile().get(id);
    }

    @Override
    public void update(T obj) {
        doInFile(data -> data.replace(obj.getId(), obj));
    }

    @Override
    public void delete(Integer id) {
        doInFile(data -> data.remove(id));
    }

    @Override
    public List<T> getAll() {
        return readDataFromFile().values().stream().collect(Collectors.toList());
    }

    /**
     * Performs an operation on the data stored in the file.
     *
     * @param function the function to apply to the data
     */
    private void doInFile(Consumer<Map<Integer, T>> function) {
        Map<Integer, T> data = readDataFromFile();
        function.accept(data);
        writeDataToFile(data);
    }

    /**
     * Reads the data from the file.
     *
     * @return a map of object IDs to objects
     */
    private Map<Integer, T> readDataFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<Integer, T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    /**
     * Writes the data to the file.
     *
     * @param data a map of object IDs to objects
     */
    private void writeDataToFile(Map<Integer, T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
