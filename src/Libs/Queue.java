package Libs;

import java.util.Stack;

/**
 * Класс очередь должен позволять достовать первый элемент и вставлять в конец;
 *
 * Поддерживать команды редактирования очереди: втавка в i-ую позищию и удаление i-позиции,
 * Возможность менять очередность элементов
 *
 * Возможность получнения всех элементов очереди ввиде массива
 *
 * @param <T>
 */

// key - означает порядковый номер в очереди


public class Queue<T> {
    private Stack<T> load = new Stack<>();
    private Stack<T> unload = new Stack<>();

    public void push(T element){
        unloadToLoad();
        load.push(element);
        loadToUnload();
    }

    public T pop(){
        return unload.pop();
    }

    public void add(int key, T element){
        unload.add(key,element);
    }

    public boolean del(long key){
        if (key>unload.size())
            return false;
        unloadToLoad(key);
        unload.pop();
        unloadToLoad();
        loadToUnload();
        return true;
    }

    private void unloadToLoad() {
        unloadToLoad(unload.size());
    }

    private void unloadToLoad(long key){
        while (load.size()<key){
            load.push(unload.pop());
        }
    }

    private void loadToUnload(){
        loadToUnload(load.size());
    }

    private void loadToUnload(long key){
        while (unload.size()<key){
            unload.push(load.pop());
        }
    }

    public boolean swap(int key1,int key2){
        if (key2>unload.size()||key1>unload.size())
            return false;
        T element1 = unload.get(key1);
        T element2 = unload.get(key2);
        unload.set(key1,element2);
        unload.set(key2,element1);
        return true;
    }

    public long size(){
        return unload.size();
    }

    public T[] getQueue(){
        return (T[]) unload.toArray();
    }
}
