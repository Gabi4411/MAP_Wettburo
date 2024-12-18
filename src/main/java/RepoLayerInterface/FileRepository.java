package RepoLayerInterface;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * File-based repository implementation for managing objects of type T.
 *
 * @param <T> The type of objects stored in the repository.
 */
public class FileRepository<T> implements repo<T> {
    private final String filePath;
    private final Function<String, T> fromCSV; // Function to deserialize objects from CSV.
    private final Function<T, String> toCSV;  // Function to serialize objects to CSV.
    private final Function<T, Integer> getId; // Function to extract the ID from an object.

    /**
     * Constructs a FileRepository with the given parameters.
     *
     * @param filePath The path to the file where data is stored.
     * @param fromCSV  A function to convert a CSV line into an object of type T.
     * @param toCSV    A function to convert an object of type T to a CSV string.
     * @param getId    A function to extract the ID from an object of type T.
     */
    public FileRepository(String filePath, Function<String, T> fromCSV, Function<T, String> toCSV, Function<T, Integer> getId) {
        this.filePath = filePath;
        this.fromCSV = fromCSV;
        this.toCSV = toCSV;
        this.getId = getId;
    }

    @Override
    public void create(T obj) {
        doInFile(data -> data.putIfAbsent(getId.apply(obj), obj));
    }

    @Override
    public T get(Integer id) {
        return readDataFromFile().get(id);
    }

    @Override
    public void update(T obj) {
        doInFile(data -> data.replace(getId.apply(obj), obj));
    }

    @Override
    public void delete(Integer id) {
        doInFile(data -> data.remove(id));
    }

//    @Override
//    public T find_by_ID(Integer id) {
//        // Delegate to the `get` method
//        return get(id);
//    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(readDataFromFile().values());
    }

    /**
     * Performs an operation on the data stored in the file.
     *
     * @param function The function to apply to the data.
     */
    private void doInFile(Consumer<Map<Integer, T>> function) {
        Map<Integer, T> data = readDataFromFile();
        function.accept(data);
        writeDataToFile(data);
    }

    /**
     * Reads the data from the file.
     *
     * @return A map of object IDs to objects.
     */
    private Map<Integer, T> readDataFromFile() {
        Map<Integer, T> objects = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return objects; // Return an empty map if the file doesn't exist.
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine(); // Skip or process the header line.
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    T obj = fromCSV.apply(line);
                    objects.put(getId.apply(obj), obj);
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return objects;
    }

    /**
     * Writes the data to the file.
     *
     * @param data A map of object IDs to objects.
     */
    private void writeDataToFile(Map<Integer, T> data) {
        if (data.isEmpty()) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header, if applicable.
            Optional<T> firstEntry = data.values().stream().findFirst();
            if (firstEntry.isPresent()) {
                writer.write("ID," + toCSV.apply(firstEntry.get()).split(",", 2)[1]);
                writer.newLine();
            }

            // Write the data.
            for (T obj : data.values()) {
                writer.write(getId.apply(obj) + "," + toCSV.apply(obj));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
