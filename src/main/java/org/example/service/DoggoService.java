package org.example.service;

import org.example.model.Doggo;

import java.util.List;

public interface DoggoService {

    public Doggo getDoggo(int id);
    public List<Doggo> getAllDoggos();
    public void updateDoggo(int id, Doggo doggo);
    public int createDoggo(Doggo doggo);
    public void deleteDoggo(int id);
}
