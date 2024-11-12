package RepoLayerInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class inMemoryRepo<T> implements repo<T> {
    private final Map<Integer, T> data = new HashMap<>();
    private int current_id=1;


    @Override
    public void create(T obj) {
        data.putIfAbsent(current_id, obj);
        current_id++;
    }


    @Override
    public T get(Integer id) {
        return data.get(id);
    }


    @Override
    public void update(T obj) {
        if (data.containsKey(current_id)){
            data.replace(current_id, obj);
        }else{
            System.out.println(current_id+"does not exist");
        }
    }


    @Override
    public void delete(Integer id) {
        if(current_id==id){
            data.remove(current_id);
        }else{
            System.out.println(current_id+"cant be deleted");

        }

    }

    public T find_by_ID(Integer id) {
        if(data.containsKey(current_id)) {
            return data.get(id);
        }else{
            System.out.println(current_id+"was not found");
            return null;
        }
    }

    @Override
    public List<T> getAll() {
        return data.values().stream().toList();
    }
}





