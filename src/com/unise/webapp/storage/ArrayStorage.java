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
        if (findResumePositionByUuid(resume.uuid) == null) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Резюме с uuid " + resume.uuid + " уже содержится в базе");
        }
    }

    public void update(Resume resume) {
        Integer tempCounter = findResumePositionByUuid(resume.uuid);
        if (tempCounter != null) {
            storage[tempCounter] = resume;
        }
        else {
            System.out.println("Ошибка обновления! Не найдено резюме с uuid = " + resume.uuid);
        }
    }

    public Resume get(String uuid) {
        Integer tempCounter = findResumePositionByUuid(uuid);
        if (tempCounter != null) {
            return storage[tempCounter];
        } else {
            System.out.println("Не найдено резюме с uuid " + uuid);
        }
        return null;
    }

    public void delete(String uuid) {
        Integer tempCounter = findResumePositionByUuid(uuid);
        if (tempCounter != null) {
            storage[tempCounter] = storage[size - 1];
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

    private Integer findResumePositionByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
