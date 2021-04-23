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
        if (r != null) {
            storage[counter] = r;
            counter++;
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
