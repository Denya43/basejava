package com.unise.webapp.storage;

import com.unise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size >= 10_000) {
            System.out.println("База резюме переполнения");
        } else if (findResumeIndex(resume.getUuid()) != -1) {
            System.out.println("Резюме с uuid " + resume.getUuid() + " уже содержится в базе");
        }
        else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = findResumeIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Ошибка обновления! Не найдено резюме с uuid = " + resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Не найдено резюме с uuid " + uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
        } else {
            System.out.println("Не найдено резюме с uuid " + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
