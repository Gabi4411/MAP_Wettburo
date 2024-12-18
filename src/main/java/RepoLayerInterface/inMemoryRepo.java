package RepoLayerInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A repository implementation that stores objects in memory using a HashMap.
 * This class provides CRUD operations for managing objects of type T.
 *
 * @param <T> the type of objects stored in the repository
 */
public class inMemoryRepo<T> implements repo<T> {
    private final Map<Integer, T> data = new HashMap<>();
    private int current_id=1;

    /**
     * Creates a new object in the repository with a unique ID.
     * If the object does not already exist, it is added to the repository.
     *
     * @param obj the object to be added to the repository
     */

    @Override
    public void create(T obj) {
        data.putIfAbsent(current_id, obj);
        current_id++;
    }


    /**
     * Retrieves an object from the repository by its ID.
     *
     * @param id the ID of the object to retrieve
     * @return the object associated with the given ID, or null if not found
     */
    @Override
    public T get(Integer id) {
        return data.get(id);
    }


    /**
     * Updates an existing object in the repository. The object is replaced
     * if the current ID exists in the repository.
     *
     * @param obj the object to be updated in the repository
     */
    @Override
    public void update(T obj) {
        if (data.containsKey(current_id)){
            data.replace(current_id, obj);
        }else{
            System.out.println(current_id+"does not exist");
        }
    }


    /**
     * Deletes an object from the repository based on its ID.
     *
     * @param id the ID of the object to delete
     */
    @Override
    public void delete(Integer id) {
        if(current_id==id){
            data.remove(current_id);
        }else{
            System.out.println(current_id+"cant be deleted");

        }

    }

//    /**
//     * Finds and retrieves an object by its ID.
//     *
//     * @param id the ID of the object to find
//     * @return the object associated with the given ID, or null if not found
//     */
//    public T find_by_ID(Integer id) {
//        if(data.containsKey(current_id)) {
//            return data.get(id);
//        }else{
//            System.out.println(current_id+"was not found");
//            return null;
//        }
//    }

    /**
     * Retrieves all objects stored in the repository.
     *
     * @return a list of all objects stored in the repository
     */
    @Override
    public List<T> getAll() {
        return data.values().stream().toList();
    }
}





