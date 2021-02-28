package org.example.service.impl;

import org.example.model.Doggo;
import org.example.service.DoggoService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.max;

@Component
public class DoggoServiceImpl implements DoggoService {

    private Map<Integer, Doggo> doggos;

    public DoggoServiceImpl() {
        doggos = new HashMap<>();
    }

    @Override
    public Doggo getDoggo(int id) {
        if (doggos.containsKey(id)) {
            return doggos.get(id);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateDoggo(int id, Doggo doggo) {
        if (doggos.containsKey(id)) {
            Doggo doggo1 = new Doggo(
                    doggo.getName(),
                    doggo.getBreed(),
                    doggo.getAge(),
                    doggo.getId());
            doggos.put(id, doggo1);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public int createDoggo(Doggo doggo) {
        int id = doggo.getId();
        if (id == -1) {
            id = nextId();
            doggo.setId(id);
        }

        if (doggos.containsKey(id)) {
            throw new RuntimeException();
        }

        Doggo doggo1 = new Doggo(
                doggo.getName(),
                doggo.getBreed(),
                doggo.getAge(),
                doggo.getId());

        doggos.put(id, doggo1);
        return id;
    }

    @Override
    public void deleteDoggo(int id) {
        if (doggos.containsKey(id)) {
            doggos.remove(id);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Doggo> getAllDoggos() {
        return new ArrayList(doggos.values());
    }

    // possible overflow, but for a small example OK
    private int nextId() {
        if (doggos.isEmpty()) {
            return 0;
        } else {
            return 1 + max(doggos.keySet());
        }
    }

}
