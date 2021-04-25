import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int counter = 0;

    void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    void save(Resume r) {
        if (counter < storage.length && r != null) {
            storage[counter] = r;
            counter++;
        } else if (r != null) {
            System.out.println("Сохраняемое резюме не заполнено");
        } else {
            System.out.println("Хранилище резюме переполнено");
        }
    }

    void update(Resume resume) {
        int tempCounter = 0;
        while (tempCounter < counter) {
            if (storage[tempCounter].uuid.equals(resume.uuid)) {
                storage[tempCounter] = resume;
                break;
            }
            tempCounter++;
        }
        if (tempCounter >= counter) {
            System.out.println("Ошибка обновления! Не найдено резюме с uuid = " + resume.uuid);
        }
    }

    Resume get(String uuid) {
        int tempCounter = 0;
        while (tempCounter < counter) {
            if (storage[tempCounter].uuid.equals(uuid)) {
                return storage[tempCounter];
            }
            tempCounter++;
        }
        System.out.println("Не найдено резюме с uuid = " + uuid);
        return null;
    }

    void delete(String uuid) {
        int tempCounter = 0;
        while (tempCounter < counter) {
            if (storage[tempCounter].uuid.equals(uuid)) {
                while (tempCounter < counter) {
                    storage[tempCounter] = storage[tempCounter + 1];
                    tempCounter++;
                }
                counter--;
                break;
            }
        }
        if (tempCounter >= counter) {
            System.out.println("Не найдено резюме с uuid " + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, counter);
    }

    int size() {
        return counter;
    }
}
