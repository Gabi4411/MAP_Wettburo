package RepoLayerInterface;

import java.util.List;

/**
 * A generic repository interface for CRUD operations.
 * This interface provides methods for creating, retrieving, updating, and deleting objects.
 *
 * @param <T> the type of objects stored in the repository
 */

public interface repo<T> {

    /**
     * Creates a new entry in the repository.
     *
     * @param obj the object to be stored in the repository
     */
    void create(T obj);

    /**
     * Retrieves an object by its ID.
     *
     * @param id the ID of the object to retrieve
     * @return the object corresponding to the provided ID, or null if not found
     */
    T get(Integer id);

    /**
     * Updates an existing object in the repository.
     *
     * @param obj the object to be updated in the repository
     */
    void update(T obj);

    /**
     * Deletes an object from the repository based on its ID.
     *
     * @param id the ID of the object to delete
     */
    void delete(Integer id);

    /**
     * Finds and retrieves an object by its ID.
     *
     * @param id the ID of the object to find
     * @return the object corresponding to the provided ID, or null if not found
     */
    T find_by_ID(Integer id);

    /**
     * Retrieves all objects in the repository.
     *
     * @return a list of all stored objects
     */
    List<T> getAll();
}
